package com.behtreen.spapp.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.utilities.AppSingletons;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.model.JobsHistorySpModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.bumptech.glide.Glide;

public class JobsHistoryDetailFragment extends Fragment {

    /* Root view of Fragment*/
    View root_view;

    /* Model classes*/
    private JobsHistorySpModel jobsHistorySpModel;


    private ImageView image_view_jobs_history_location;
    private TextView text_view_job_history_date_time;
    private TextView text_view_job_history_job_type;
    private TextView text_view_job_history_address;
    private TextView text_view_job_history_additional_charges;
    private TextView text_view_job_history_status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_jobs_history_detail, container, false);

        jobsHistorySpModel = AppSingletons.getSpJobsHistory();

        initView();



        if (this.getArguments() != null) {
//            position = Integer.toString(bundle.getInt("position"));
            populateDetail(this.getArguments().getInt("position"));

        }

        return root_view; // Return inflated fragment view.
    }

    /* Start - initView() */
    private void initView() {

        ((BaseActivity)getActivity()).switch_online.setVisibility(View.GONE);
        image_view_jobs_history_location = (ImageView)root_view.findViewById(R.id.image_view_jobs_history_location);
        text_view_job_history_date_time = (TextView)root_view.findViewById(R.id.text_view_job_history_date_time);
        text_view_job_history_job_type = (TextView)root_view.findViewById(R.id.text_view_job_history_job_type);
        text_view_job_history_address = (TextView)root_view.findViewById(R.id.text_view_job_history_address);
        text_view_job_history_additional_charges = (TextView)root_view.findViewById(R.id.text_view_job_history_additional_charges);
        text_view_job_history_status = (TextView)root_view.findViewById(R.id.text_view_job_history_status);

        /* Listener */
//        button_add_location.setOnClickListener(this);

    }
    /* End - initView() */

    /* Start - populateDetail() */
    public void populateDetail(int position){
        text_view_job_history_date_time.setText(
                ApplicationUtils.getCustomDateTime(jobsHistorySpModel.getData().getJobs().get(position).getJob_date()+" "+jobsHistorySpModel.getData().getJobs().get(position).getJob_time())
        );

        text_view_job_history_job_type.setText(
                ((ApplicationUtils.stringEmpty(jobsHistorySpModel.getData().getJobs().get(position).getJob_type()))?"Not Available":jobsHistorySpModel.getData().getJobs().get(position).getJob_type())
        );

        text_view_job_history_address.setText(
                ((ApplicationUtils.stringEmpty(jobsHistorySpModel.getData().getJobs().get(position).getAddress()))?"Not Available":jobsHistorySpModel.getData().getJobs().get(position).getAddress())
        );


        text_view_job_history_additional_charges.setText(
                ((ApplicationUtils.stringEmpty(jobsHistorySpModel.getData().getJobs().get(position).getJob_charges()))?"No Charges":jobsHistorySpModel.getData().getJobs().get(position).getJob_charges())
        );

        text_view_job_history_status.setText(
                ((ApplicationUtils.stringEmpty(jobsHistorySpModel.getData().getJobs().get(position).getStatus_name()))?"Not Available":jobsHistorySpModel.getData().getJobs().get(position).getStatus_name())
        );

        ((BaseActivity)getActivity()).mToolbar_title.setText(
                "Job ID # "+
                        jobsHistorySpModel.getData().getJobs().get(position).getId()+
                        " - "+
                        jobsHistorySpModel.getData().getJobs().get(position).getService_name()
        );


        String url_static_map_image = "https://maps.googleapis.com/maps/api/staticmap?center="+
                jobsHistorySpModel.getData().getJobs().get(position).getJob_latitude()+
                ","+
                jobsHistorySpModel.getData().getJobs().get(position).getJob_longitude() +
                "&zoom=15&size=600x300&maptype=roadmap&markers=color:0x9e4fff%7Clabel:B%7C"+
                jobsHistorySpModel.getData().getJobs().get(position).getJob_latitude()+
                ","+
                jobsHistorySpModel.getData().getJobs().get(position).getJob_longitude() +
                "&key="+
                getString(R.string.google_maps_key);

        Glide.with(this)
                .load(url_static_map_image)
                .into(image_view_jobs_history_location);
    }
    /* End - populateDetail() */

}
