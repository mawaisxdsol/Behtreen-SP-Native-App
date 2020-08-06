package com.behtreen.spapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.model.SpNotificationsModel;
import com.behtreen.spapp.model.UserInfoModel;

import java.util.ArrayList;

public class SpProfileExperienceAdapter extends RecyclerView.Adapter<SpProfileExperienceHolder> {

    private ArrayList<UserInfoModel.DataBean.SpExperienceBean> notifications_items;
    private Context context;


    public SpProfileExperienceAdapter(Context context, ArrayList<UserInfoModel.DataBean.SpExperienceBean> notifications_items) {

        this.context = context;
        this.notifications_items = notifications_items;

    }

    @Override
    public SpProfileExperienceHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row_view = LayoutInflater.from(context).inflate(R.layout.item_profile_activity_experience, parent, false);

        SpProfileExperienceHolder viewHolder = new SpProfileExperienceHolder(row_view, notifications_items);

        return viewHolder;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(SpProfileExperienceHolder holder, final int position) {

        holder.textview_profile_service_name.setText(notifications_items.get(position).getService_name());
        holder.textview_profile_service_experience.setText(notifications_items.get(position).getExperience_year()+": years");


    }

    @Override
    public int getItemCount() {
        return this.notifications_items.size();
    }
}
