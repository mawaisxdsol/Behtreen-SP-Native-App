package com.behtreen.spapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.model.ActiveJobsSpModel;

import java.util.ArrayList;

public class ActiveJobsAdapter extends RecyclerView.Adapter<ActiveJobsHolder> {

    private ArrayList<ActiveJobsSpModel.DataBean.JobsBean> active_jobs_items;
    private Context context;


    public ActiveJobsAdapter(Context context, ArrayList<ActiveJobsSpModel.DataBean.JobsBean> active_jobs_items) {

        this.context = context;
        this.active_jobs_items = active_jobs_items;

    }

    @Override
    public ActiveJobsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row_view = LayoutInflater.from(context).inflate(R.layout.item_active_jobs_activity, parent, false);

        ActiveJobsHolder viewHolder = new ActiveJobsHolder(context,row_view, active_jobs_items);

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
    public void onBindViewHolder(ActiveJobsHolder holder, final int position) {

        holder.text_view_active_jobs_job_id.setText(active_jobs_items.get(position).getId());
        holder.text_view_active_jobs_job_address.setText(active_jobs_items.get(position).getAddress());


        String datetime_from= active_jobs_items.get(position).getJob_date()+" "+ active_jobs_items.get(position).getJob_time();

        holder.text_view_active_jobs_job_date.setText(" - " + ApplicationUtils.getCustomDateTime(datetime_from));


    }

    @Override
    public int getItemCount() {

        return this.active_jobs_items.size();
    }
}
