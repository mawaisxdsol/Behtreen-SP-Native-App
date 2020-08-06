package com.behtreen.spapp.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.behtreen.spapp.R;
import com.behtreen.spapp.model.UserInfoModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.behtreen.spapp.view.adapter.SpProfileExperienceAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    /* Root view of Fragment*/
    View root_view;

    /* Elements */
    private ImageView imageview_profile_photo;
    private TextView textview_profile_name;
    private TextView textview_profile_reg_no;
    private TextView textview_profile_user_name;
    private TextView textview_profile_mobile;
    private TextView textview_profile_address;
    private LinearLayout linearlayout_profile_terms_condtions;

    private RecyclerView recycler_view_profile_experience;
    private RecyclerView.LayoutManager recylerViewLayoutManager;
    private SpProfileExperienceAdapter sp_profile_experience_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        root_view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView();
        populateProfile();

        return root_view; // Return inflated fragment view.
    }

    /* Start - initView() */
    private void initView() {

        linearlayout_profile_terms_condtions = root_view.findViewById(R.id.linearlayout_profile_terms_condtions);
        imageview_profile_photo = root_view.findViewById(R.id.imageview_profile_photo);
        textview_profile_name = root_view.findViewById(R.id.textview_profile_name);
        textview_profile_reg_no = root_view.findViewById(R.id.textview_profile_reg_no);
        textview_profile_user_name = root_view.findViewById(R.id.textview_profile_user_name);
        textview_profile_mobile = root_view.findViewById(R.id.textview_profile_mobile);
        textview_profile_address = root_view.findViewById(R.id.textview_profile_address);

        recycler_view_profile_experience = root_view.findViewById(R.id.recycler_view_profile_experience);
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_profile_experience.setLayoutManager(recylerViewLayoutManager);

        ((BaseActivity)getActivity()).mToolbar_title.setText("Profile");

        /* Listener */
        linearlayout_profile_terms_condtions.setOnClickListener(this);

    }
    /* End - initView() */

    public void populateProfile(){
        textview_profile_name.setText(
                ((BaseActivity)getActivity()).userInfoModel.getData().getFirst_name()+" "+((BaseActivity)getActivity()).userInfoModel.getData().getLast_name()
        );
        textview_profile_reg_no.setText(((BaseActivity)getActivity()).userInfoModel.getData().getSp_code());
        textview_profile_user_name.setText(((BaseActivity)getActivity()).userInfoModel.getData().getUser_name());
        textview_profile_mobile.setText(((BaseActivity)getActivity()).userInfoModel.getData().getPhone());
        textview_profile_address.setText(((BaseActivity)getActivity()).userInfoModel.getData().getAddress());

        Glide.with(this)
                .load(((BaseActivity)getActivity()).userInfoModel.getData().getProfile_photo())
                .into(imageview_profile_photo);

        ArrayList<UserInfoModel.DataBean.SpExperienceBean> all_packages_response_items = ((BaseActivity)getActivity()).userInfoModel.getData().getSp_experience();
        sp_profile_experience_adapter = new SpProfileExperienceAdapter(getActivity(), all_packages_response_items);
        recycler_view_profile_experience.setAdapter(sp_profile_experience_adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.linearlayout_profile_terms_condtions:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment inner_fragment = new PageDataFragment();

                Bundle bundle = new Bundle();
                bundle.putString("slug", "terms");
                inner_fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
                fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
                fragmentTransaction.commit();
                break;
            default:

                break;
        }
    }

}
