package com.behtreen.spapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.model.JobsHistorySpModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JobsHistoryDetailAdapter extends RecyclerView.Adapter<JobsHistoryDetailHolder> {

    private ArrayList<JobsHistorySpModel.DataBean.JobsBean> jobs_history_items;
    private Context context;


    public JobsHistoryDetailAdapter(Context context, ArrayList<JobsHistorySpModel.DataBean.JobsBean> jobs_history_items) {

        this.context = context;
        this.jobs_history_items = jobs_history_items;

    }

    @Override
    public JobsHistoryDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row_view = LayoutInflater.from(context).inflate(R.layout.item_jobs_history_activity, parent, false);

        JobsHistoryDetailHolder viewHolder = new JobsHistoryDetailHolder(row_view, jobs_history_items);

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
    public void onBindViewHolder(JobsHistoryDetailHolder holder, final int position) {

        holder.text_view_jobs_history_job_id.setText(jobs_history_items.get(position).getId());
        holder.text_view_jobs_history_job_address.setText(jobs_history_items.get(position).getAddress());


        String datetime_from=jobs_history_items.get(position).getJob_date()+" "+jobs_history_items.get(position).getJob_time();

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        Date date = null;
        try
        {
            date = form.parse(datetime_from);
        }
        catch (ParseException e)
        {
            e.printStackTrace();

            form = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            try {
                date = form.parse(datetime_from);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMM dd, h:mm aa");
        String newDateStr = postFormater.format(date);

        holder.text_view_jobs_history_job_date.setText(" - "+newDateStr);

        /*holder.TextViewPackageName.setText(jobs_history_items.get(position).getPackage_name());
        holder.TextViewAllPackagesDaysNights.setText(jobs_history_items.get(position).getPackage_days() + "/" + jobs_history_items.get(position).getPackage_nights());


        if (jobs_history_items.get(position).getPackage_hotel() != null) {
            if (jobs_history_items.get(position).getPackage_hotel().size() > 0) {
                holder.TextViewAllPackagesHotel.setText(jobs_history_items.get(position).getPackage_hotel().get(0).getHotel_name() + " PKR");
            } else {
                holder.TextViewAllPackagesHotel.setText("Not Available");
            }
        } else {
            holder.TextViewAllPackagesHotel.setText("Not Available");
        }

        if (jobs_history_items.get(position).getPackage_price() != null) {
            if (jobs_history_items.get(position).getPackage_price().size() > 0) {
                holder.TextViewAllPackagesPrice.setText(jobs_history_items.get(position).getPackage_price().get(0).getPrice() + " PKR");
            } else {
                holder.TextViewAllPackagesPrice.setText("0 PKR");
            }
        } else {
            holder.TextViewAllPackagesPrice.setText("0 PKR");
        }


        holder.LinearLayoutAllPackages.setVisibility(View.VISIBLE);*/

    }

    @Override
    public int getItemCount() {

        return this.jobs_history_items.size();
    }
}
