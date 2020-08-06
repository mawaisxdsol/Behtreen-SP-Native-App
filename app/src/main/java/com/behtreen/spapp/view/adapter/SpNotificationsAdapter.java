package com.behtreen.spapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.model.SpNotificationsModel;

import java.util.ArrayList;

public class SpNotificationsAdapter extends RecyclerView.Adapter<SpNotificationsHolder> {

    private ArrayList<SpNotificationsModel.DataBean.NotificationsBean> notifications_items;
    private Context context;


    public SpNotificationsAdapter(Context context, ArrayList<SpNotificationsModel.DataBean.NotificationsBean> notifications_items) {

        this.context = context;
        this.notifications_items = notifications_items;

    }

    @Override
    public SpNotificationsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row_view = LayoutInflater.from(context).inflate(R.layout.item_notifications_activity, parent, false);

        SpNotificationsHolder viewHolder = new SpNotificationsHolder(context, row_view, notifications_items);

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
    public void onBindViewHolder(SpNotificationsHolder holder, final int position) {

        holder.text_view_notifications_title.setText(notifications_items.get(position).getNotification_title());


        String datetime_from= notifications_items.get(position).getNotification_sent_date();

        holder.text_view_notifications_date.setText(ApplicationUtils.getCustomTimeAgo(datetime_from));


    }

    @Override
    public int getItemCount() {
        return this.notifications_items.size();
    }
}
