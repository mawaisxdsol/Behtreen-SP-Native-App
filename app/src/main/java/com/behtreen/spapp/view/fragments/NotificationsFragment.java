package com.behtreen.spapp.view.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.controller.utilities.AppConstant;
import com.behtreen.spapp.controller.utilities.AppSingletons;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.controller.utilities.URLList;
import com.behtreen.spapp.model.SpNotificationsModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.behtreen.spapp.view.adapter.SpNotificationsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    /* Root view of Fragment*/
    View root_view;

    /* Model classes*/
    private SpNotificationsModel spNotificationsModel;

    /* Elements */
    private RecyclerView recycler_view_active_jobs;
    private RecyclerView.LayoutManager recylerViewLayoutManager;
    private SpNotificationsAdapter sp_notifications_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_notifications, container, false);

        spNotificationsModel = AppSingletons.getNotificationsSp();

        initView();
        getSpNotifications();

        return root_view; // Return inflated fragment view.
    }

    /* Start - initView() */
    private void initView() {

        ((BaseActivity)getActivity()).switch_online.setVisibility(View.GONE);
        recycler_view_active_jobs = root_view.findViewById(R.id.recycler_view_notifications);
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_active_jobs.setLayoutManager(recylerViewLayoutManager);

        ((BaseActivity)getActivity()).mToolbar_title.setText("Notifications");

        /* Listener */
//        button_add_location.setOnClickListener(this);

    }
    /* End - initView() */


    /* Start - getSpNotifications()*/
    public void getSpNotifications(){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);
            ((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Getting Notifications List...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();

            Call<SpNotificationsModel> call = ((BaseActivity)getActivity()).apiInterface.getSpNotificationsList(
                    new URLList().getUrl(4),
                    "get_sp_notifications",
                    ((BaseActivity)getActivity()).userInfoModel.getData().getUser_id(),
                    ((BaseActivity)getActivity()).userInfoModel.getStatus().getAccess_token()
            );

            call.enqueue(new Callback<SpNotificationsModel>() {
                @Override
                public void onResponse(Call<SpNotificationsModel> call, Response<SpNotificationsModel> response) {
                    if (response.isSuccessful()) {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        SpNotificationsModel response_jobs_history_model = response.body();

                        if (response_jobs_history_model.getStatus().isStatus() && response_jobs_history_model.getStatus().getStatus_code() == 200) {

                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_jobs_history_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_jobs_history_model.getStatus().getAccess_token());
                            }
                            ArrayList<SpNotificationsModel.DataBean.NotificationsBean> all_packages_response_items = response_jobs_history_model.getData().getNotifications();
                            sp_notifications_adapter = new SpNotificationsAdapter(getActivity(), all_packages_response_items);
                            recycler_view_active_jobs.setAdapter(sp_notifications_adapter);

                            spNotificationsModel.setStatus(response_jobs_history_model.getStatus());
                            spNotificationsModel.setData(response_jobs_history_model.getData());


                        } else if (response_jobs_history_model.getStatus().isStatus() && response_jobs_history_model.getStatus().getStatus_code() != 200) {

                            if(response_jobs_history_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        response_jobs_history_model.getStatus().getStatus_message()
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
                                Toast.makeText(getActivity(),response_jobs_history_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<SpNotificationsModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - getSpNotifications()*/

}
