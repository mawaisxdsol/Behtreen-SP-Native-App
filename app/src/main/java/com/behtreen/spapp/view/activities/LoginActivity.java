package com.behtreen.spapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.controller.utilities.AppConstant;
import com.behtreen.spapp.controller.utilities.AppSingletons;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.controller.utilities.SharedPrefUtil;
import com.behtreen.spapp.controller.utilities.URLList;
import com.behtreen.spapp.model.LoginModel;
import com.behtreen.spapp.model.RegisterDeviceModel;
import com.behtreen.spapp.model.UserInfoModel;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity  implements View.OnClickListener, OSSubscriptionObserver {

    private static final int READ_PHONE_STATE = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    private APIInterface apiInterface;
    private Retrofit retrofit;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private TelephonyManager telephonyManager;
    private String imeiNumber;
    private OSPermissionSubscriptionState osPermissionSubscriptionState;
    private String oneSignal_player_id;

    /* Custom Utilities*/
    private SharedPrefUtil sharedPrefUtil;

    /* Model classes*/
    private UserInfoModel userInfoModel;

    /* Elements */
    private TextView textViewResponse;
    private EditText edittext_user_name;
    private EditText edittext_password;
    private CheckBox checkbox_privacy;
    private Button button_login;

    /* Start - onCreate() */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefUtil = new SharedPrefUtil();
        apiInterface = getClient().create(APIInterface.class);
        userInfoModel = AppSingletons.getLogedinInstance();

        osPermissionSubscriptionState = OneSignal.getPermissionSubscriptionState();
        oneSignal_player_id = osPermissionSubscriptionState.getSubscriptionStatus().getUserId();
        /* Adding Observer to OneSignal sdk*/
        OneSignal.addSubscriptionObserver(this);

        telephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        imeiNumber = ApplicationUtils.getDeviceId(getApplicationContext(),this,telephonyManager);

        initView();
    }
    /* End - onCreate() */

    /* Start - onOSSubscriptionChanged() */
    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (!stateChanges.getFrom().getSubscribed() &&
                stateChanges.getTo().getSubscribed()) {

            // get player ID
            oneSignal_player_id = stateChanges.getTo().getUserId();
            Log.e("One Signal Player Id",oneSignal_player_id);
        }

        Log.i("One Signal", "onOSPermissionChanged: " + stateChanges);
    }
    /* End - onOSSubscriptionChanged() */

    /* Start - initView() */
    private void initView() {
        textViewResponse = (TextView) findViewById(R.id.textviewResponse);
        edittext_user_name = (EditText) findViewById(R.id.edittext_user_name);
        edittext_password = (EditText) findViewById(R.id.edittext_password);
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(this);
    }
    /* End - initView() */

    /* Start - getClient() */
    public Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BEHTREEN_BASE_URL_SERVICES)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }
    /* End - getClient() */

    /* Start - onClick() */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_login:

                if (edittext_user_name.getText().toString().isEmpty()) {
                    ApplicationUtils.showToast("Enter Username please!");
                } else if (edittext_password.getText().toString().isEmpty()) {
                    ApplicationUtils.showToast("Enter Password please!");
                } else if (oneSignal_player_id == null) {
                    ApplicationUtils.showToast("Connection to server failed. Retry!");
                }else {

                    if (ApplicationUtils.isNetworkAvailable()) {
                        textViewResponse.setVisibility(View.GONE);
                        mProgressDialog = new ProgressDialog(LoginActivity.this);
                        mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
                        mProgressDialog.setCancelable(false);
                        mProgressDialog.setMessage("Login in progress...");
                        mProgressDialog.setIcon(R.mipmap.ic_launcher);
                        mProgressDialog.show();

                        Call<LoginModel> call_register_model = apiInterface.doLoginGet(new URLList().getUrl(0),"sp_login", edittext_user_name.getText().toString(), edittext_password.getText().toString(), imeiNumber);

                        call_register_model.enqueue(new Callback<LoginModel>() {
                            @Override
                            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                                if (response.isSuccessful()) {
                                    LoginModel response_login_model = response.body();

                                    Log.e("Response from Server",response.body().toString());
                                    if (response_login_model.getStatus().isStatus() && response_login_model.getStatus().getStatus_code() == 200) {

                                        sharedPrefUtil.saveSharedPrefValue(getApplicationContext(), "pref_loged_in", "1");
                                        sharedPrefUtil.saveSharedPrefValue(getApplicationContext(), "pref_access_token", response_login_model.getStatus().getAccess_token());
                                        sharedPrefUtil.saveSharedPrefValue(getApplicationContext(), "pref_user_phone", response_login_model.getData().getPhone());
                                        sharedPrefUtil.saveSharedPrefValue(getApplicationContext(), "pref_user_id", response_login_model.getData().getUser_id());
                                        sharedPrefUtil.saveSharedPrefValue(getApplicationContext(), "pref_device_id", imeiNumber);

                                        mProgressDialog.dismiss();
                                        registerUserDeviceForPushNotification();


                                    } else if (response_login_model.getStatus().isStatus() && response_login_model.getStatus().getStatus_code() != 200) {
                                        textViewResponse.setText(response_login_model.getStatus().getStatus_message());
                                        textViewResponse.setVisibility(View.VISIBLE);
                                        mProgressDialog.dismiss();
                                    }
                                } else {
                                    //   Log.d("Response", "Server contact failed");
                                    System.out.println("Server contact failed");

                                    mProgressDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginModel> call, Throwable t) {
                                Log.e("Response", "Error " + t.getMessage());

                                mProgressDialog.dismiss();
                            }
                        });
                    } else {
                        ApplicationUtils.showToast("Please Connect Internet Connection");
                    }


                }

                break;
            default:

                break;
        }
    }
    /* End - onClick() */

    /* registerUserDeviceForPushNotification()*/
    public void registerUserDeviceForPushNotification(){

        if (ApplicationUtils.isNetworkAvailable()) {
            textViewResponse.setVisibility(View.GONE);
            mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Device Registration in progress...");
            mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.show();

            Log.e("params",
                    "register_sp_device"+
                    sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_user_id")+
                    sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_access_token")+
                    imeiNumber+
                    oneSignal_player_id+
                    "Android"+
                    "S"
            );
            Call<RegisterDeviceModel> call_register_model = apiInterface.registerDeviceForPushNotification(
                    new URLList().getUrl(3),
                    "register_sp_device",
                    sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_user_id"),
                    sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_access_token"),
                    imeiNumber,
                    oneSignal_player_id,
                    "Android",
                    "S"
            );

            call_register_model.enqueue(new Callback<RegisterDeviceModel>() {
                @Override
                public void onResponse(Call<RegisterDeviceModel> call, Response<RegisterDeviceModel> response) {

                    if (response.isSuccessful()) {
                        RegisterDeviceModel response_login_model = response.body();

                        Log.e("Response from Server",response.body().toString());
                        if (response_login_model.getStatus().isStatus() && response_login_model.getStatus().getStatus_code() == 200) {

                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),response_login_model.getStatus().getStatus_message(),Toast.LENGTH_LONG).show();

                            Intent to_mainDashboardActivity = new Intent(LoginActivity.this, BaseActivity.class);
                            startActivity(to_mainDashboardActivity);
                            finish();


                        } else if (response_login_model.getStatus().isStatus() && response_login_model.getStatus().getStatus_code() != 200) {
                            textViewResponse.setText(response_login_model.getStatus().getStatus_message());
                            textViewResponse.setVisibility(View.VISIBLE);
                            mProgressDialog.dismiss();
                        }
                    } else {
                        //   Log.d("Response", "Server contact failed");
                        System.out.println("Server contact failed");

                        mProgressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<RegisterDeviceModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());

                    mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - registerUserDeviceForPushNotification()*/

    /* Start - onRequestPermissionsResult() */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
                        return;
                    }
                    imeiNumber = telephonyManager.getDeviceId();
//                    ApplicationUtils.showToast(imeiNumber);
                } else {
                    ApplicationUtils.showToast("Permissions Required to use Application!");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    /* End - onRequestPermissionsResult() */
}