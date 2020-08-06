package com.behtreen.spapp.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.model.JobsHistorySpModel;

import java.util.ArrayList;

public class JobsHistoryDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    ArrayList<JobsHistorySpModel.DataBean.JobsBean> jobs_history_items;
//    private PackageDetailModel packageDetailModel;

    public TextView text_view_jobs_history_job_id;
    public TextView text_view_jobs_history_job_address;
    public TextView text_view_jobs_history_job_date;




    public JobsHistoryDetailHolder(View itemView, ArrayList<JobsHistorySpModel.DataBean.JobsBean> jobs_history_items) {

        super(itemView);
        itemView.setOnClickListener(this);
        this.jobs_history_items = jobs_history_items;

        text_view_jobs_history_job_id = (TextView) itemView.findViewById(R.id.text_view_jobs_history_job_id);
        text_view_jobs_history_job_address = (TextView) itemView.findViewById(R.id.text_view_jobs_history_job_address);
        text_view_jobs_history_job_date = (TextView) itemView.findViewById(R.id.text_view_jobs_history_job_date);




    }
    @Override
    public void onClick(View view) {


    }
}
