package com.behtreen.spapp.view.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.controller.utilities.AppConstant;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.controller.utilities.URLList;
import com.behtreen.spapp.model.SpJobBalanceModel;
import com.behtreen.spapp.view.activities.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobBalanceFragment extends Fragment {

    /* Root view of Fragment*/
    View root_view;

    private TextView textview_job_balance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_job_balance, container, false);

        initView();
        getJobBalance();

        return root_view; // Return inflated fragment view.
    }

    /* Start - initView() */
    private void initView() {

        ((BaseActivity)getActivity()).switch_online.setVisibility(View.GONE);
        textview_job_balance = root_view.findViewById(R.id.textview_job_balance);

        ((BaseActivity)getActivity()).mToolbar_title.setText("Payments");

        /* Listener */
//        linearlayout_profile_terms_condtions.setOnClickListener(this);

    }
    /* End - initView() */

    /* Start - getJobBalance()*/
    public void getJobBalance(){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);
            ((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Getting Data...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();

            Call<SpJobBalanceModel> call_page_data_model = ((BaseActivity)getActivity()).apiInterface.getSpJobBalance(
                    new URLList().getUrl(6),
                    "get_sp_job_balance",
                    ((BaseActivity)getActivity()).userInfoModel.getData().getUser_id(),
                    ((BaseActivity)getActivity()).userInfoModel.getStatus().getAccess_token()
            );

            call_page_data_model.enqueue(new Callback<SpJobBalanceModel>() {
                @Override
                public void onResponse(Call<SpJobBalanceModel> call, Response<SpJobBalanceModel> response) {
                    if (response.isSuccessful()) {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        SpJobBalanceModel response_page_data_model = response.body();


                        if (response_page_data_model.getStatus().isStatus() && response_page_data_model.getStatus().getStatus_code() == 200) {

                            if(((BaseActivity)getActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(getActivity(),"pref_access_token").equals(response_page_data_model.getStatus().getAccess_token()) == false){
                                ((BaseActivity)getActivity()).updateAccessToken(response_page_data_model.getStatus().getAccess_token());
                            }

                            textview_job_balance.setText(response_page_data_model.getData().getJobs());


                        } else if (response_page_data_model.getStatus().isStatus() && response_page_data_model.getStatus().getStatus_code() != 200) {

                            if(response_page_data_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        response_page_data_model.getStatus().getStatus_message()
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
                                Toast.makeText(getActivity(),response_page_data_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<SpJobBalanceModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - getJobBalance()*/

}
