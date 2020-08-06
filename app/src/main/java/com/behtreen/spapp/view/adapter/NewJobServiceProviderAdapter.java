package com.behtreen.spapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.model.NewJobChildServiceProviderModel;

import java.util.ArrayList;

public class NewJobServiceProviderAdapter extends RecyclerView.Adapter<NewJobServiceProviderAdapter.NewJobServiceProviderHolder> {

    private ArrayList<NewJobChildServiceProviderModel.ChildSpBean> new_job_service_providers_items;
    private Context context;
    private int lastCheckedPosition = -1;


    public NewJobServiceProviderAdapter(Context context, ArrayList<NewJobChildServiceProviderModel.ChildSpBean> new_job_service_providers_items) {

        this.context = context;
        this.new_job_service_providers_items = new_job_service_providers_items;

    }

    @Override
    public NewJobServiceProviderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row_view = LayoutInflater.from(context).inflate(R.layout.item_new_job_service_providers, parent, false);

        NewJobServiceProviderHolder viewHolder = new NewJobServiceProviderHolder(row_view, new_job_service_providers_items);

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

    public int getLastCheckedPosition() {
        return lastCheckedPosition;
    }

    @Override
    public void onBindViewHolder(NewJobServiceProviderHolder holder, final int position) {

        holder.text_view_new_job_service_provider_name.setText(new_job_service_providers_items.get(position).getFirst_name());
        holder.text_view_new_job_service_provider_reg_no.setText(new_job_service_providers_items.get(position).getSp_code());
        holder.radiobutton_new_job_service_provider.setChecked(position == lastCheckedPosition);


    }

    @Override
    public int getItemCount() {

        return this.new_job_service_providers_items.size();
    }

    public class NewJobServiceProviderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ArrayList<NewJobChildServiceProviderModel.ChildSpBean> new_job_service_providers_items;
//    private PackageDetailModel packageDetailModel;

        public ImageView imageview_new_job_service_provider;
        public TextView text_view_new_job_service_provider_name;
        public TextView text_view_new_job_service_provider_reg_no;
        public RadioButton radiobutton_new_job_service_provider;




        public NewJobServiceProviderHolder(View itemView, ArrayList<NewJobChildServiceProviderModel.ChildSpBean> new_job_service_providers_items) {

            super(itemView);
            itemView.setOnClickListener(this);
            this.new_job_service_providers_items = new_job_service_providers_items;

            imageview_new_job_service_provider = (ImageView) itemView.findViewById(R.id.imageview_new_job_service_provider);
            text_view_new_job_service_provider_name = (TextView) itemView.findViewById(R.id.text_view_new_job_service_provider_name);
            text_view_new_job_service_provider_reg_no = (TextView) itemView.findViewById(R.id.text_view_new_job_service_provider_reg_no);
            radiobutton_new_job_service_provider = (RadioButton) itemView.findViewById(R.id.radiobutton_new_job_service_provider);

            radiobutton_new_job_service_provider.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            lastCheckedPosition = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
