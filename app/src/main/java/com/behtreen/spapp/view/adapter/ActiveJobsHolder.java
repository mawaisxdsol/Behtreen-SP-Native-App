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
import com.behtreen.spapp.model.ActiveJobsSpModel;
import com.behtreen.spapp.model.JobsHistorySpModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.behtreen.spapp.view.fragments.ActiveJobsDetailFragment;

import java.util.ArrayList;

public class ActiveJobsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    ArrayList<ActiveJobsSpModel.DataBean.JobsBean> active_jobs_items;
    private Context context;
//    private PackageDetailModel packageDetailModel;

    public TextView text_view_active_jobs_job_id;
    public TextView text_view_active_jobs_job_address;
    public TextView text_view_active_jobs_job_date;




    public ActiveJobsHolder(Context context, View itemView, ArrayList<ActiveJobsSpModel.DataBean.JobsBean> active_jobs_items) {

        super(itemView);
        itemView.setOnClickListener(this);

        this.context = context;
        this.active_jobs_items = active_jobs_items;

        text_view_active_jobs_job_id = (TextView) itemView.findViewById(R.id.text_view_active_jobs_job_id);
        text_view_active_jobs_job_address = (TextView) itemView.findViewById(R.id.text_view_active_jobs_job_address);
        text_view_active_jobs_job_date = (TextView) itemView.findViewById(R.id.text_view_active_jobs_job_date);

    }
    @Override
    public void onClick(View view) {

        FragmentManager fragmentManager = ((BaseActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment inner_fragment = new ActiveJobsDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position",this.getPosition());

        inner_fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
        fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
        fragmentTransaction.commit();

    }
}
