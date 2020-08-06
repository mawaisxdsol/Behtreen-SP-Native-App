package com.behtreen.spapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.model.JobsHistorySpModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JobsHistoryAdapter extends RecyclerView.Adapter<JobsHistoryHolder> {

    private ArrayList<JobsHistorySpModel.DataBean.JobsBean> jobs_history_items;
    private Context context;


    public JobsHistoryAdapter(Context context, ArrayList<JobsHistorySpModel.DataBean.JobsBean> jobs_history_items) {

        this.context = context;
        this.jobs_history_items = jobs_history_items;

    }

    @Override
    public JobsHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row_view = LayoutInflater.from(context).inflate(R.layout.item_jobs_history_activity, parent, false);

        JobsHistoryHolder viewHolder = new JobsHistoryHolder(context,row_view, jobs_history_items);

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
    public void onBindViewHolder(JobsHistoryHolder holder, final int position) {

        holder.text_view_jobs_history_job_id.setText(jobs_history_items.get(position).getId());
        holder.text_view_jobs_history_job_address.setText(jobs_history_items.get(position).getAddress());


        String datetime_from=jobs_history_items.get(position).getJob_date()+" "+jobs_history_items.get(position).getJob_time();

        holder.text_view_jobs_history_job_date.setText(" - " + ApplicationUtils.getCustomDateTime(datetime_from));


    }

    @Override
    public int getItemCount() {

        return this.jobs_history_items.size();
    }
}
