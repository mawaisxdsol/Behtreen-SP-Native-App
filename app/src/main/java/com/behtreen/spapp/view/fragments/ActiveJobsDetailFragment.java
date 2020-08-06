package com.behtreen.spapp.view.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.controller.utilities.AppConstant;
import com.behtreen.spapp.controller.utilities.AppSingletons;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.controller.utilities.URLList;
import com.behtreen.spapp.model.ActiveJobsSpModel;
import com.behtreen.spapp.model.CancelJobModel;
import com.behtreen.spapp.model.CompleteJobModel;
import com.behtreen.spapp.model.SpArrivedModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.behtreen.spapp.view.activities.LoginActivity;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveJobsDetailFragment extends Fragment implements View.OnClickListener{

    /* Root view of Fragment*/
    View root_view;

    /* Model classes*/
    private ActiveJobsSpModel activeJobsSpModel;


    /* Elements */
    private ImageView image_view_active_jobs_location;
    private TextView text_view_active_jobs_date_time;
    private TextView text_view_active_jobs_job_type;
    private TextView text_view_active_jobs_address;
    private TextView text_view_active_jobs_additional_charges;
    private TextView text_view_active_jobs_status;
    private Button button_job_confirm_arrival;
    private Button button_job_complete;
    private Button button_job_cancel;

    /* Variable*/
    int position=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_active_jobs_detail, container, false);

        activeJobsSpModel = AppSingletons.getSpActiveJobs();

        initView();



        if (this.getArguments() != null) {
//            position = Integer.toString(bundle.getInt("position"));
            position = this.getArguments().getInt("position");
            populateDetail(position);

        }

        return root_view; // Return inflated fragment view.
    }

    /* Start - initView() */
    private void initView() {

        ((BaseActivity)getActivity()).switch_online.setVisibility(View.GONE);
        image_view_active_jobs_location = (ImageView)root_view.findViewById(R.id.image_view_active_jobs_location);
        text_view_active_jobs_date_time = (TextView)root_view.findViewById(R.id.text_view_active_jobs_date_time);
        text_view_active_jobs_job_type = (TextView)root_view.findViewById(R.id.text_view_active_jobs_job_type);
        text_view_active_jobs_address = (TextView)root_view.findViewById(R.id.text_view_active_jobs_address);
        text_view_active_jobs_additional_charges = (TextView)root_view.findViewById(R.id.text_view_active_jobs_additional_charges);
        text_view_active_jobs_status = (TextView)root_view.findViewById(R.id.text_view_active_jobs_status);
        button_job_confirm_arrival = (Button)root_view.findViewById(R.id.button_job_confirm_arrival);
        button_job_complete = (Button)root_view.findViewById(R.id.button_job_complete);
        button_job_cancel = (Button)root_view.findViewById(R.id.button_job_cancel);

        /* Listener */
        button_job_confirm_arrival.setOnClickListener(this);
        button_job_complete.setOnClickListener(this);
        button_job_cancel.setOnClickListener(this);

    }
    /* End - initView() */

    /* Start - populateDetail() */
    public void populateDetail(int position){
        text_view_active_jobs_date_time.setText(
                ApplicationUtils.getCustomDateTime(activeJobsSpModel.getData().getJobs().get(position).getJob_date()+" "+ activeJobsSpModel.getData().getJobs().get(position).getJob_time())
        );

        text_view_active_jobs_job_type.setText(
                ((ApplicationUtils.stringEmpty(activeJobsSpModel.getData().getJobs().get(position).getJob_type()))?"Not Available": activeJobsSpModel.getData().getJobs().get(position).getJob_type())
        );

        text_view_active_jobs_address.setText(
                ((ApplicationUtils.stringEmpty(activeJobsSpModel.getData().getJobs().get(position).getAddress()))?"Not Available": activeJobsSpModel.getData().getJobs().get(position).getAddress())
        );


        text_view_active_jobs_additional_charges.setText(
                ((ApplicationUtils.stringEmpty(activeJobsSpModel.getData().getJobs().get(position).getJob_charges()))?"No Charges": activeJobsSpModel.getData().getJobs().get(position).getJob_charges())
        );

        text_view_active_jobs_status.setText(
                "Accepted"
        );

        ((BaseActivity)getActivity()).mToolbar_title.setText(
                "Job ID # "+
                        activeJobsSpModel.getData().getJobs().get(position).getId()+
                        " - "+
                        activeJobsSpModel.getData().getJobs().get(position).getService_name()
        );


        String url_static_map_image = "https://maps.googleapis.com/maps/api/staticmap?center="+
                activeJobsSpModel.getData().getJobs().get(position).getJob_latitude()+
                ","+
                activeJobsSpModel.getData().getJobs().get(position).getJob_longitude() +
                "&zoom=15&size=600x300&maptype=roadmap&markers=color:0x9e4fff%7Clabel:B%7C"+
                activeJobsSpModel.getData().getJobs().get(position).getJob_latitude()+
                ","+
                activeJobsSpModel.getData().getJobs().get(position).getJob_longitude() +
                "&key="+
                getString(R.string.google_maps_key);

        Glide.with(this)
                .load(url_static_map_image)
                .into(image_view_active_jobs_location);
    }
    /* End - populateDetail() */

    /* Start - confirmSpArrival()*/
    public void confirmSpArrival(String job_id){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);


            Call<SpArrivedModel> call_complete_job_model = ((BaseActivity)getActivity()).apiInterface.spArrived(
                    new URLList().getUrl(2),
                    "sp_arrived",
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_user_id"),
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token"),
                    job_id
            );

            call_complete_job_model.enqueue(new Callback<SpArrivedModel>() {
                @Override
                public void onResponse(Call<SpArrivedModel> call, Response<SpArrivedModel> response) {
                    if (response.isSuccessful()) {
                        SpArrivedModel response_complete_job_model = response.body();


                        if (response_complete_job_model.getStatus().isStatus() && response_complete_job_model.getStatus().getStatus_code() == 200) {
                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_complete_job_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_complete_job_model.getStatus().getAccess_token());
                            }

                            Log.e("Complete",Integer.toString(response_complete_job_model.getStatus().getStatus_code()));

                            button_job_confirm_arrival.setVisibility(View.GONE);

                        } else if (response_complete_job_model.getStatus().isStatus() && response_complete_job_model.getStatus().getStatus_code() != 200) {

                            if(response_complete_job_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        "You are being Logged Out automatically"
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
                                Toast.makeText(getActivity(),response_complete_job_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }


                        }

                    } else {
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<SpArrivedModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - confirmSpArrival()*/

    /* Start - completeActiveJob()*/
    public void completeActiveJob(String job_id, String job_time){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);
            ((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Completing Job...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();

            Call<CompleteJobModel> call_complete_job_model = ((BaseActivity)getActivity()).apiInterface.completeJob(
                    new URLList().getUrl(2),
                    "sp_complete_job",
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_user_id"),
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token"),
                    job_id,
                    job_time
            );

            call_complete_job_model.enqueue(new Callback<CompleteJobModel>() {
                @Override
                public void onResponse(Call<CompleteJobModel> call, Response<CompleteJobModel> response) {
                    if (response.isSuccessful()) {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        CompleteJobModel response_complete_job_model = response.body();


                        if (response_complete_job_model.getStatus().isStatus() && response_complete_job_model.getStatus().getStatus_code() == 200) {
                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_complete_job_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_complete_job_model.getStatus().getAccess_token());
                            }

                            Log.e("Complete",Integer.toString(response_complete_job_model.getStatus().getStatus_code()));

                            getActivity().getSupportFragmentManager().popBackStack();

                        } else if (response_complete_job_model.getStatus().isStatus() && response_complete_job_model.getStatus().getStatus_code() != 200) {

                            if(response_complete_job_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        "You are being Logged Out automatically"
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
                                Toast.makeText(getActivity(),response_complete_job_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }


                        }

                    } else {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<CompleteJobModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - completeActiveJob()*/

    /* Start - cancelActiveJob()*/
    public void cancelActiveJob(final String job_id, final String job_time, final boolean paid){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);
            ((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Cancel Job in progress...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();

            Call<CancelJobModel> call_cancel_job_model = ((BaseActivity)getActivity()).apiInterface.cancelJob(
                    new URLList().getUrl(2),
                    "sp_cancel_job",
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_user_id"),
                    ((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token"),
                    job_id,
                    job_time,
                    paid
            );

            call_cancel_job_model.enqueue(new Callback<CancelJobModel>() {
                @Override
                public void onResponse(Call<CancelJobModel> call, Response<CancelJobModel> response) {
                    if (response.isSuccessful()) {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        CancelJobModel response_cancel_job_model = response.body();


                        if (response_cancel_job_model.getStatus().isStatus() && response_cancel_job_model.getStatus().getStatus_code() == 200) {
                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_cancel_job_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_cancel_job_model.getStatus().getAccess_token());
                            }

                            Log.e("Cancel",Integer.toString(response_cancel_job_model.getStatus().getStatus_code()));

                            getActivity().getSupportFragmentManager().popBackStack();

                        } else if (response_cancel_job_model.getStatus().isStatus() && response_cancel_job_model.getStatus().getStatus_code() != 200) {

                            if(response_cancel_job_model.getStatus().getError_type().equals("overtimeError")){
                                final AlertDialog alertDialog = ApplicationUtils.showCustomConfirmDialog(
                                        false,
                                        "Are you Sure!",
                                        response_cancel_job_model.getStatus().getStatus_message()
                                );

                                Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
                                Button cancel_button = (Button) alertDialog.findViewById(R.id.button_cancel);
                                ok_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();

                                        cancelActiveJob(
                                                job_id,
                                                job_time,
                                                true
                                        );

                                    }
                                });
                                cancel_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();
                                    }
                                });
                            }
                            else if(response_cancel_job_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        "You are being Logged Out automatically"
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
                                Toast.makeText(getActivity(),response_cancel_job_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }


                        }

                    } else {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<CancelJobModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - cancelActiveJob()*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_job_complete:
                completeActiveJob(
                        activeJobsSpModel.getData().getJobs().get(position).getId(),
                        activeJobsSpModel.getData().getJobs().get(position).getJob_date()+" "+activeJobsSpModel.getData().getJobs().get(position).getJob_time()
                );
                break;
            case R.id.button_job_cancel:
                cancelActiveJob(
                        activeJobsSpModel.getData().getJobs().get(position).getId(),
                        (activeJobsSpModel.getData().getJobs().get(position).getJob_date()+" "+activeJobsSpModel.getData().getJobs().get(position).getJob_time()).trim(),
                        false
                );
                break;
            case R.id.button_job_confirm_arrival:
                confirmSpArrival(
                        activeJobsSpModel.getData().getJobs().get(position).getId()
                );
                break;
            default:
                break;

        }

    }

}
