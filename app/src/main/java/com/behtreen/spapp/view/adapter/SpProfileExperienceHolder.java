package com.behtreen.spapp.view.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.model.SpNotificationsModel;
import com.behtreen.spapp.model.UserInfoModel;

import java.util.ArrayList;

public class SpProfileExperienceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    ArrayList<UserInfoModel.DataBean.SpExperienceBean> notifications_items;
//    private PackageDetailModel packageDetailModel;

    public TextView textview_profile_service_name;
    public TextView textview_profile_service_experience;




    public SpProfileExperienceHolder(View itemView, ArrayList<UserInfoModel.DataBean.SpExperienceBean> notifications_items) {

        super(itemView);
        itemView.setOnClickListener(this);
        this.notifications_items = notifications_items;

        textview_profile_service_name = (TextView) itemView.findViewById(R.id.textview_profile_service_name);
        textview_profile_service_experience = (TextView) itemView.findViewById(R.id.textview_profile_service_experience);

    }
    @Override
    public void onClick(View view) {


    }
}
