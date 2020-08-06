package com.behtreen.spapp.view.fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.controller.utilities.AppConstant;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.controller.utilities.URLList;
import com.behtreen.spapp.model.PageDataModel;
import com.behtreen.spapp.view.activities.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageDataFragment extends Fragment {

    /* Root view of Fragment*/
    View root_view;

    /* Elements */
    private WebView webview_page_data_detail;

    private String slug;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_page_data, container, false);

        initView();

        if (this.getArguments() != null) {
            slug = this.getArguments().getString("slug");

            getPageData(slug);

        }
        return root_view; // Return inflated fragment view.
    }

    /* Start - initView() */
    private void initView() {

        ((BaseActivity)getActivity()).switch_online.setVisibility(View.GONE);
        webview_page_data_detail = root_view.findViewById(R.id.webview_page_data_detail);
        webview_page_data_detail.setBackgroundColor(Color.TRANSPARENT);


    }
    /* End - initView() */

    /* Start - getPageData()*/
    public void getPageData(String slug){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getActivity()).apiInterface = ((BaseActivity)getActivity()).getClient().create(APIInterface.class);
            ((BaseActivity)getActivity()).mProgressDialog = new ProgressDialog(getActivity());
            ((BaseActivity)getActivity()).mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            ((BaseActivity)getActivity()).mProgressDialog.setCancelable(false);
            ((BaseActivity)getActivity()).mProgressDialog.setMessage("Getting Data...");
            ((BaseActivity)getActivity()).mProgressDialog.setIcon(R.mipmap.ic_launcher);
            ((BaseActivity)getActivity()).mProgressDialog.show();

            Call<PageDataModel> call_page_data_model = ((BaseActivity)getActivity()).apiInterface.getPageDataCall(
                    new URLList().getUrl(5),
                    "get_page_data",
                    slug
            );

            call_page_data_model.enqueue(new Callback<PageDataModel>() {
                @Override
                public void onResponse(Call<PageDataModel> call, Response<PageDataModel> response) {
                    if (response.isSuccessful()) {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        PageDataModel response_page_data_model = response.body();


                        if (response_page_data_model.getStatus().isStatus() && response_page_data_model.getStatus().getStatus_code() == 200) {

                            String pre_html = "<html><body>"+ response_page_data_model.getData().getData().getContent()+"</body></html>";
                            webview_page_data_detail.loadData(
                                    pre_html,
                                    "text/html",
                                    "UTF-8"
                            );

                            ((BaseActivity)getActivity()).mToolbar_title.setText(response_page_data_model.getData().getData().getTitle());
                        } else {
                            Toast.makeText(getActivity(),response_page_data_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();

                            String pre_html = "<html><body> Nothing found.</body></html>";
                            webview_page_data_detail.loadData(
                                    pre_html,
                                    "text/html",
                                    "UTF-8"
                            );

                        }

                    } else {
                        ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                        Toast.makeText(getActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<PageDataModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    ((BaseActivity)getActivity()).mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - getPageData()*/

}
