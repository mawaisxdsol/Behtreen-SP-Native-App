package com.behtreen.spapp.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.utilities.AppSingletons;
import com.behtreen.spapp.model.SpNotificationsModel;
import com.behtreen.spapp.view.activities.BaseActivity;

public class NotificationsDetailFragment extends Fragment {

    /* Root view of Fragment*/
    View root_view;

    /* Model classes*/
    private SpNotificationsModel spNotificationsModel;

    /* Elements */
    private WebView webview_notifications_detail;

    /*Variable*/
    int position=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_notifications_detail, container, false);

        spNotificationsModel = AppSingletons.getNotificationsSp();

        initView();



        if (this.getArguments() != null) {
//            position = Integer.toString(bundle.getInt("position"));
            position = this.getArguments().getInt("position");
            populateDetail(position);

        }

        return root_view; // Return inflated fragment view.
    }
    private void populateDetail(int position) {
        webview_notifications_detail.loadData(
                spNotificationsModel.getData().getNotifications().get(position).getNotification_description(),
                "text/html",
                "UTF-8"
        );
    }


    /* Start - initView() */
    private void initView() {

        ((BaseActivity)getActivity()).switch_online.setVisibility(View.GONE);
        webview_notifications_detail = root_view.findViewById(R.id.webview_notifications_detail);


        ((BaseActivity)getActivity()).mToolbar_title.setText("Notification Detail");


    }
    /* End - initView() */


}
