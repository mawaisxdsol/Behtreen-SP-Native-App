package com.behtreen.spapp.view.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.controller.utilities.URLList;
import com.behtreen.spapp.model.IsSpStatusOnlineModel;
import com.behtreen.spapp.model.SpStatusModel;
import com.behtreen.spapp.model.UserInfoModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDashboardFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,CompoundButton.OnCheckedChangeListener {

    /* Root view of Fragment*/
    View root_view;

    /* Elements */
    private BitmapDescriptor icon;

    /* Variable*/
    private GoogleMap google_map;
    SupportMapFragment support_map_fragment;

    private GoogleApiClient google_api_client;
    private Location last_location;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest location_request;
    private LocationCallback location_request_Callback;
    public Task<Location> fused_location_provider_client_task;
    private Marker currLocationMarker;

    private boolean is_permission;
    private boolean on_activity_start = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_main_dashboard, container, false);

        if(requestSinglePermission()) {

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                support_map_fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                support_map_fragment.getMapAsync(this);

                initView();
//                getUserInfoIfLocationEnabled();

        }

        return root_view; // Return inflated fragment view.
    }

    public void getUserInfoIfLocationEnabled(){
        if(ApplicationUtils.isLocationEnabled(getActivity())){
            getUserInfo();
        }else{
            final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                    false,
                    "Live Location",
                    "Please Enable your Location."
            );

            ImageView imageview_alert_message = (ImageView) alertDialog.findViewById(R.id.imageview_alert_message);

            imageview_alert_message.setImageResource(R.drawable.ic_location_alert_message);
            Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
            ok_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.dismiss();
                    getUserInfoIfLocationEnabled();
                }
            });
        }
    }
    /* Start - initView() */
    private void initView() {

        icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker);
        ((BaseActivity)getActivity()).switch_online.setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).mToolbar_title.setText("");

        /* Listener */
        ((BaseActivity)getActivity()).switch_online.setOnCheckedChangeListener(this);

    }
    /* End - initView() */

    /* Start - getUserInfo()*/
    public void getUserInfo(){
        if (ApplicationUtils.isNetworkAvailable()) {

            /*((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Getting User Detail...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();*/

            Call<UserInfoModel> call_register_model = ((BaseActivity)getActivity()).apiInterface.getUserInfo(
                    new URLList().getUrl(0),
                    "sp_user_info",
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_user_id"),
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token"),
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_device_id")
            );

            call_register_model.enqueue(new Callback<UserInfoModel>() {
                @Override
                public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {

                    if (response.isSuccessful()) {
                        UserInfoModel response_user_info_model = response.body();


                        Log.e("Response from Server",response.body().toString());
                        if (response_user_info_model.getStatus().isStatus() && response_user_info_model.getStatus().getStatus_code() == 200) {

                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_user_info_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_user_info_model.getStatus().getAccess_token());
                            }
                            ((BaseActivity)getActivity()).userInfoModel.setData(response_user_info_model.getData());
                            ((BaseActivity)getActivity()).userInfoModel.setStatus(response_user_info_model.getStatus());

                            ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.saveSharedPrefValue(getActivity(), "pref_access_token", response_user_info_model.getStatus().getAccess_token());
                            ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.saveSharedPrefValue(getActivity(), "pref_user_phone", response_user_info_model.getData().getPhone());
                            ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.saveSharedPrefValue(getActivity(), "pref_user_id", response_user_info_model.getData().getUser_id());
                            ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.saveSharedPrefValue(getActivity(), "pref_user_first_name", response_user_info_model.getData().getFirst_name());
                            ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.saveSharedPrefValue(getActivity(), "pref_user_last_name", response_user_info_model.getData().getLast_name());
                            ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.saveSharedPrefValue(getActivity(), "pref_user_is_shop", response_user_info_model.getData().getIs_shop());

//                            ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                            isSpStatusOnline();

                        } else if (response_user_info_model.getStatus().isStatus() && response_user_info_model.getStatus().getStatus_code() != 200) {
//                            ((BaseActivity)getActivity()).mProgressDialog.dismiss();

                            if(response_user_info_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        response_user_info_model.getStatus().getStatus_message()
                                );

                                Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
                                ok_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();
                                        ((BaseActivity)getActivity()).doLogout();
                                    }
                                });
                            }else{
                                Toast.makeText(getActivity(),response_user_info_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        //   Log.d("Response", "Server contact failed");
                        System.out.println("Server contact failed");

//                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<UserInfoModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());

                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - getUserInfo()*/

    /* Start - isSpStatusOnline()*/
    public void isSpStatusOnline(){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);
            /*((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Checking your Online Status...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();*/

            Call<IsSpStatusOnlineModel> call = ((BaseActivity)getActivity()).apiInterface.isSpStatusOnline(
                    new URLList().getUrl(1),
                    "is_sp_online",
                    ((BaseActivity)getActivity()).userInfoModel.getData().getUser_id(),
                    ((BaseActivity)getActivity()).userInfoModel.getStatus().getAccess_token()
            );

            call.enqueue(new Callback<IsSpStatusOnlineModel>() {
                @Override
                public void onResponse(Call<IsSpStatusOnlineModel> call, Response<IsSpStatusOnlineModel> response) {
                    if (response.isSuccessful()) {
//                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        IsSpStatusOnlineModel response_sp_status_model = response.body();

                        if (response_sp_status_model.getStatus().isStatus() && response_sp_status_model.getStatus().getStatus_code() == 200) {
                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_sp_status_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_sp_status_model.getStatus().getAccess_token());
                            }

                            if(response_sp_status_model.getData().getOnline() == 1){
                                ((BaseActivity)getActivity()).switch_online.setChecked(true);
                                ((BaseActivity)getActivity()).switch_online.setText("Online");
                            }
                            else {
                                ((BaseActivity)getActivity()).switch_online.setChecked(false);
                                ((BaseActivity)getActivity()).switch_online.setText("Offline");
                            }

                        } else if (response_sp_status_model.getStatus().isStatus() && response_sp_status_model.getStatus().getStatus_code() != 200) {

                            if(response_sp_status_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        response_sp_status_model.getStatus().getStatus_message()
                                );

                                Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
                                ok_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();
                                        ((BaseActivity)getActivity()).doLogout();
                                    }
                                });
                            }else{
                                Toast.makeText(getActivity(),response_sp_status_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
//                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<IsSpStatusOnlineModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - isSpStatusOnline()*/

    /* Start - updateSpStatus()*/
    public void updateSpStatus(final String status){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);
            /*((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Updating Status...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();*/

            Call<SpStatusModel> call = ((BaseActivity)getActivity()).apiInterface.updateSpStatus(
                    new URLList().getUrl(1),
                    "update_sp_status",
                    ((BaseActivity)getActivity()).userInfoModel.getData().getUser_id(),
                    ((BaseActivity)getActivity()).userInfoModel.getStatus().getAccess_token(),
                    Double.toString(last_location.getLatitude()),
                    Double.toString(last_location.getLongitude()),
                    status
            );

            call.enqueue(new Callback<SpStatusModel>() {
                @Override
                public void onResponse(Call<SpStatusModel> call, Response<SpStatusModel> response) {
                    if (response.isSuccessful()) {
//                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        SpStatusModel response_sp_status_model = response.body();

                        if (response_sp_status_model.getStatus().isStatus() && response_sp_status_model.getStatus().getStatus_code() == 200) {
//                            Toast.makeText(getActivity(),response_sp_status_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();

                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_sp_status_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_sp_status_model.getStatus().getAccess_token());
                            }
                            if(status.equals("1")){
//                                Toast.makeText(getActivity(),"You are Online",Toast.LENGTH_SHORT).show();
                                ((BaseActivity)getActivity()).switch_online.setText("Online");
                            }
                            else if(status.equals("0")){
//                                Toast.makeText(getActivity(),"You are Offline",Toast.LENGTH_SHORT).show();
                                ((BaseActivity)getActivity()).switch_online.setText("Offline");
                            }


                        } else if (response_sp_status_model.getStatus().isStatus() && response_sp_status_model.getStatus().getStatus_code() != 200) {



                            if(response_sp_status_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        response_sp_status_model.getStatus().getStatus_message()
                                );

                                Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
                                ok_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();
                                        ((BaseActivity)getActivity()).doLogout();
                                    }
                                });
                            }else{
                                Toast.makeText(getActivity(),response_sp_status_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                                if(status.equals("1")){
                                    ((BaseActivity)getActivity()).switch_online.setChecked(false);
                                    ((BaseActivity)getActivity()).switch_online.setText("Offline");
                                }
                                else if(status.equals("0")){
                                    ((BaseActivity)getActivity()).switch_online.setChecked(true);
                                    ((BaseActivity)getActivity()).switch_online.setText("Online");
                                }
                            }
                        }

                    } else {
//                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<SpStatusModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
//                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - updateSpStatus()*/

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            /*...switch on..*/
            updateSpStatus("1");

        } else {
            /*...switch off..*/
            updateSpStatus("0");
        }
    }

    private boolean requestSinglePermission() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        is_permission = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if(response.isPermanentlyDenied()){
                            is_permission = false;
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
        return is_permission;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        google_map = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        google_map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        google_map.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }


        connect_google_api();

        google_map.setMyLocationEnabled(true);
        /*google_map.getUiSettings().setMyLocationButtonEnabled(false);
        google_map.getUiSettings().setRotateGesturesEnabled(false);
        google_map.getUiSettings().setScrollGesturesEnabled(false);
        google_map.getUiSettings().setZoomGesturesEnabled(false);*/

    }

    protected synchronized void builedGoogleApiClient() {


        if(google_api_client==null){
            Log.e("DEBUG", "google_api_client Null");
            google_api_client = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
//            google_api_client.connect();
            connectGoogleClient();
            location_request_Callback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        // Update UI with location data
//                    Log.e("Location","location_request_Callback onLocationResult callback");
                        if(last_location == null) {
                            Log.e("Location","fused_location_provider_client_task onSuccess callback notNull");

                            last_location = location;
                            LatLng latLng = new LatLng(last_location.getLatitude(), last_location.getLongitude());

                            MarkerOptions marker_options =new MarkerOptions().position(latLng).title("You are here!").icon(icon);
                            currLocationMarker = google_map.addMarker(marker_options);
                            google_map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18F));
                        }else{
                            last_location = location;
                        }


                    }
                }
            };

        }else{
            Log.e("DEBUG", "google_api_client Not Null");
        }

    }

    private void connectGoogleClient() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(getActivity());
        if (resultCode == ConnectionResult.SUCCESS) {
            google_api_client.connect();
        } else {
            int REQUEST_GOOGLE_PLAY_SERVICE = 988;
            googleAPI.getErrorDialog(getActivity(), resultCode, REQUEST_GOOGLE_PLAY_SERVICE);
        }
    }

    public void connect_google_api(){
        if(ApplicationUtils.isLocationEnabled(getActivity())){
            builedGoogleApiClient();
        }else{
            final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                    false,
                    "Live Location",
                    "Please Enable your Location."
            );

            ImageView imageview_alert_message = (ImageView) alertDialog.findViewById(R.id.imageview_alert_message);

            imageview_alert_message.setImageResource(R.drawable.ic_location_alert_message);
            Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
            ok_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.dismiss();
                    connect_google_api();
                }
            });
        }

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        location_request = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(10000);

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }


        /*LocationServices.FusedLocationApi.requestLocationUpdates(google_api_client, location_request, this);

        last_location = LocationServices.FusedLocationApi.getLastLocation(google_api_client);

        LatLng latLng = new LatLng(last_location.getLatitude(), last_location.getLongitude());

        MarkerOptions marker_options =new MarkerOptions().position(latLng).title("You are here!").icon(icon);
        currLocationMarker = google_map.addMarker(marker_options);
        google_map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18F));*/

        fusedLocationProviderClient.requestLocationUpdates(location_request,location_request_Callback,null);

        fused_location_provider_client_task = fusedLocationProviderClient.getLastLocation();

        fused_location_provider_client_task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.e("Location","fused_location_provider_client_task onSuccess callback");
                if(location!=null) {
                    Log.e("Location","fused_location_provider_client_task onSuccess callback notNull");

                    last_location = location;
                    LatLng latLng = new LatLng(last_location.getLatitude(), last_location.getLongitude());

                    MarkerOptions marker_options =new MarkerOptions().position(latLng).title("You are here!").icon(icon);
                    currLocationMarker = google_map.addMarker(marker_options);
                    google_map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18F));
                }
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        last_location = location;

    }


    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of MainDashboardFragment");

        getUserInfoIfLocationEnabled();
        super.onResume();
    }

}
