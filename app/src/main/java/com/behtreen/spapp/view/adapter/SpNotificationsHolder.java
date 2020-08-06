package com.behtreen.spapp.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.model.SpNotificationsModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.behtreen.spapp.view.fragments.ActiveJobsDetailFragment;
import com.behtreen.spapp.view.fragments.NotificationsDetailFragment;

import java.util.ArrayList;

public class SpNotificationsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    ArrayList<SpNotificationsModel.DataBean.NotificationsBean> notifications_items;
    private Context context;
//    private PackageDetailModel packageDetailModel;

    public TextView text_view_notifications_title;
    public TextView text_view_notifications_date;




    public SpNotificationsHolder(Context context, View itemView, ArrayList<SpNotificationsModel.DataBean.NotificationsBean> notifications_items) {

        super(itemView);
        itemView.setOnClickListener(this);

        this.context = context;
        this.notifications_items = notifications_items;

        text_view_notifications_title = (TextView) itemView.findViewById(R.id.text_view_notifications_title);
        text_view_notifications_date = (TextView) itemView.findViewById(R.id.text_view_notifications_date);

    }
    @Override
    public void onClick(View view) {

        FragmentManager fragmentManager = ((BaseActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment inner_fragment = new NotificationsDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position",this.getPosition());

        inner_fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
        fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
        fragmentTransaction.commit();

    }
}
