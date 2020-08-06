package com.behtreen.spapp.view.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.controller.utilities.AppConstant;
import com.behtreen.spapp.controller.utilities.AppSingletons;
import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.controller.utilities.SharedPrefUtil;
import com.behtreen.spapp.controller.utilities.URLList;
import com.behtreen.spapp.model.AcceptNewJobModel;
import com.behtreen.spapp.model.IsSpStatusOnlineModel;
import com.behtreen.spapp.model.LogoutModel;
import com.behtreen.spapp.model.SpStatusModel;
import com.behtreen.spapp.model.UserInfoModel;
import com.behtreen.spapp.view.fragments.ActiveJobsFragment;
import com.behtreen.spapp.view.fragments.JobBalanceFragment;
import com.behtreen.spapp.view.fragments.JobsHistoryFragment;
import com.behtreen.spapp.view.fragments.MainDashboardFragment;
import com.behtreen.spapp.view.fragments.NotificationsFragment;
import com.behtreen.spapp.view.fragments.PageDataFragment;
import com.behtreen.spapp.view.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAnalytics mFirebaseAnalytics;

    public APIInterface apiInterface;
    public Retrofit retrofit;
    public Context mContext;
    public ProgressDialog mProgressDialog;

    private int current_activity;

    /* Custom Utilities*/
    public ApplicationUtils applicationUtils;


    /* Model classes*/
    public UserInfoModel userInfoModel;

    /* Elements */
    protected FrameLayout mainLayout;
    static Toolbar mToolbar;
    public TextView mToolbar_title;
    public Switch switch_online;
    static DrawerLayout mDrawerLayout;
    static ActionBarDrawerToggle mActionBarDrawerToggle;
    static NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        applicationUtils = (ApplicationUtils)this.getApplicationContext();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        current_activity=0;
        apiInterface = getClient().create(APIInterface.class);

        userInfoModel = AppSingletons.getLogedinInstance();

        AppToolBar();
        FragmentManager fm= getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish();
                }

            }
        });

        changeFragment(0);
    }

    public void setFirebaseAnalyticsLog(String id, String name, String type){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    /* Start - changeFragment() */
    /**
     * This method is used to Load Fragment against id as given below in List.
     * 0 = MainDashboardFragment
     * 1 = JobHistoryFragment
     * 2 = ActiveJobsFragment
     * 3 = ProfileFragment
     * 4 = NotificationsFragment
     * 5 = PageDataFragment (Bundle help)
     * 6 = PageDataFragment (Bundle legal)
     * 7 = JobBalanceFragment
     * @param id This is the first paramter to load Fragment.
     */
    public void changeFragment(int id){
        if(id<1){

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment inner_fragment = new MainDashboardFragment();

            fragmentTransaction.add(R.id.framelayout_activity_base_content, inner_fragment);
            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();

        }else if(id==1) {
            Fragment inner_fragment = new JobsHistoryFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);

            if(fragmentManager.getBackStackEntryCount()>1){
                clearBackstackTillHomeFragment();
            }

            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();

        }
        else if(id==2) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment inner_fragment = new ActiveJobsFragment();
            fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);

            if(fragmentManager.getBackStackEntryCount()>1){
                clearBackstackTillHomeFragment();
            }
            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();

        }
        else if(id==3) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment inner_fragment = new ProfileFragment();
            fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
            if(fragmentManager.getBackStackEntryCount()>1){
                clearBackstackTillHomeFragment();
            }
            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();

        }
        else if(id==4) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment inner_fragment = new NotificationsFragment();
            fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
            if(fragmentManager.getBackStackEntryCount()>1){
                clearBackstackTillHomeFragment();
            }
            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();

        }
        else if(id==5){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment inner_fragment = new PageDataFragment();

            Bundle bundle = new Bundle();
            bundle.putString("slug", "sp_help");
            inner_fragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
            if(fragmentManager.getBackStackEntryCount()>1){
                clearBackstackTillHomeFragment();
            }
            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();
        }
        else if(id==6) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment inner_fragment = new PageDataFragment();

            Bundle bundle = new Bundle();
            bundle.putString("slug", "sp_legal");
            inner_fragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
            if(fragmentManager.getBackStackEntryCount()>1){
                clearBackstackTillHomeFragment();
            }
            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();
        }
        else if(id==7) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment inner_fragment = new JobBalanceFragment();

            Bundle bundle = new Bundle();
            bundle.putString("slug", "sp_legal");
            inner_fragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.framelayout_activity_base_content, inner_fragment);
            if(fragmentManager.getBackStackEntryCount()>1){
                clearBackstackTillHomeFragment();
            }
            fragmentTransaction.addToBackStack(inner_fragment.getClass().getName());
            fragmentTransaction.commit();
        }
    }





    public void updateAccessToken(String access_token){
        applicationUtils.sharedPrefUtil.saveSharedPrefValue(getApplicationContext(), "pref_access_token", access_token);
    }

    /* Start - getClient() */
    public Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BEHTREEN_BASE_URL_SERVICES)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }
    /* End - getClient() */


    /* Start - AppToolBar() */
    public void AppToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch_online = (Switch) mToolbar.findViewById(R.id.switch_online);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mToolbar_title = mToolbar.findViewById(R.id.text_view_include_toolbar);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.msg_drawer_open,R.string.msg_drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu(); //
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                invalidateOptionsMenu();
            }
        };


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mNavigationView.setNavigationItemSelectedListener(this);
//        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }
    /* End - AppToolBar() */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav mDrawerLayout indicator touch event
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            Log.e("Hamburger","Clicked");
            /*if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                //drawer is open
                Log.e("Drawer","Already Opened");
                mDrawerLayout.closeDrawer(GravityCompat.START,true);
            }*/
            return true;
        }


        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.menu_nav_home){
            //back_Home();
            changeFragment(0);
        }
        else if(id == R.id.menu_nav_jobs_history){
            setFirebaseAnalyticsLog("menu_nav_jobs_history","Job History","Button");
            changeFragment(1);
        }
        else if(id == R.id.menu_nav_payments){
            setFirebaseAnalyticsLog("menu_nav_payments","Payments","Button");
            changeFragment(7);
        }
        else if(id == R.id.menu_nav_active_jobs){
            setFirebaseAnalyticsLog("menu_nav_active_jobs","Active Jobs","Button");
            changeFragment(2);
        }
        else if(id == R.id.menu_nav_notifications){
            setFirebaseAnalyticsLog("menu_nav_notifications","Notifications","Button");
            changeFragment(4);
        }
        else if(id == R.id.menu_nav_help){
            setFirebaseAnalyticsLog("menu_nav_help","Help","Button");
            changeFragment(5);
        }
        else if(id == R.id.menu_nav_legal){
            setFirebaseAnalyticsLog("menu_nav_legal","Legal","Button");
            changeFragment(6);
        }
        else if(id == R.id.menu_nav_my_profile){
            setFirebaseAnalyticsLog("menu_nav_my_profile","Profile","Button");
            changeFragment(3);
        }
        else if(id == R.id.menu_nav_logout){
            setFirebaseAnalyticsLog("menu_nav_logout",userInfoModel.getData().getUser_id()+" Logout","Button");
            doLogout();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void back_Home() {
        Intent intent = new Intent(this, BaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

    protected boolean isActivityRunning(Class activityClass)
    {
        ActivityManager activityManager = (ActivityManager) getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (activityClass.getCanonicalName().equalsIgnoreCase(task.baseActivity.getClassName()))
                return true;
        }

        return false;
    }

    public boolean isRunning(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                return true;
        }

        return false;
    }

    /* Start - doLogout()*/
    public void doLogout(){
        if (ApplicationUtils.isNetworkAvailable()) {
            apiInterface = getClient().create(APIInterface.class);
            mProgressDialog = new ProgressDialog(BaseActivity.this);
            mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("You are being Log off...");
            mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.show();

            Call<LogoutModel> call_logout_model = apiInterface.doLogoutGet(
                    new URLList().getUrl(0),
                    "sp_logout",
                    applicationUtils.sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_user_id"),
                    applicationUtils.sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_access_token"),
                    applicationUtils.sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_device_id")
            );

            call_logout_model.enqueue(new Callback<LogoutModel>() {
                @Override
                public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                    if (response.isSuccessful()) {
                        mProgressDialog.dismiss();
                        LogoutModel response_logout_model = response.body();


                        if (response_logout_model.getStatus().isStatus() && response_logout_model.getStatus().getStatus_code() == 200) {
                            if(applicationUtils.sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_access_token").equals(response_logout_model.getStatus().getAccess_token()) == false){
                                updateAccessToken(response_logout_model.getStatus().getAccess_token());
                            }
                            applicationUtils.sharedPrefUtil.clearSharedPrefValue(getApplicationContext());
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        } else if (response_logout_model.getStatus().isStatus() && response_logout_model.getStatus().getStatus_code() != 200) {

                            if(response_logout_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        "You are being Logged Out automatically"
                                );

                                Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
                                ok_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();

                                        applicationUtils.sharedPrefUtil.clearSharedPrefValue(getApplicationContext());
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(),response_logout_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }


                        }

                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<LogoutModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");

            applicationUtils.sharedPrefUtil.clearSharedPrefValue(getApplicationContext());
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
    /* End - doLogout()*/

    /* Start - acceptNewJob()*/
    public void acceptNewJob(String job_id, String child_sp){
        if (ApplicationUtils.isNetworkAvailable()) {
            apiInterface = getClient().create(APIInterface.class);
            mProgressDialog = new ProgressDialog(BaseActivity.this);
            mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Accepting Job...");
            mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.show();

            Call<AcceptNewJobModel> call_logout_model = apiInterface.acceptNewJob(
                    new URLList().getUrl(2),
                    "sp_accept_job",
                    applicationUtils.sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_user_id"),
                    applicationUtils.sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_access_token"),
                    job_id,
                    child_sp
            );

            call_logout_model.enqueue(new Callback<AcceptNewJobModel>() {
                @Override
                public void onResponse(Call<AcceptNewJobModel> call, Response<AcceptNewJobModel> response) {
                    if (response.isSuccessful()) {
                        mProgressDialog.dismiss();
                        AcceptNewJobModel response_new_job_model = response.body();


                        if (response_new_job_model.getStatus().isStatus() && response_new_job_model.getStatus().getStatus_code() == 200) {
                            if(applicationUtils.sharedPrefUtil.getSharedPrefValue(getApplicationContext(),"pref_access_token").equals(response_new_job_model.getStatus().getAccess_token()) == false){
                                updateAccessToken(response_new_job_model.getStatus().getAccess_token());
                            }

                            Log.e("accept",Integer.toString(response_new_job_model.getStatus().getStatus_code()));
                            ApplicationUtils.showCustomDialog(true,"Pending","Job Pending for Approval from Customer.");

                        } else if (response_new_job_model.getStatus().isStatus() && response_new_job_model.getStatus().getStatus_code() != 200) {

                            if(response_new_job_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        "You are being Logged Out automatically"
                                );

                                Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
                                ok_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();

                                        applicationUtils.sharedPrefUtil.clearSharedPrefValue(getApplicationContext());
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(),response_new_job_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }


                        }

                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<AcceptNewJobModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - acceptNewJob()*/

    private void clearBackstackTillHomeFragment(){
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        if(backStackCount>1) {
            for (int i = 0; i < (backStackCount - 1); i++) {

                getSupportFragmentManager().popBackStack();

            } /* end of for */
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        super.onRestart();
    }

    @Override
    protected void onResume () {
        super .onResume() ;
        applicationUtils .setCurrentActivity( this ) ;

        try {
            if (getIntent().getStringExtra("notification_data") != null) {
                JSONObject notification_result = new JSONObject(getIntent().getStringExtra("notification_data"));
                getIntent().removeExtra("notification_data");
                ApplicationUtils.onNotificationRecevied(notification_result);
                Log.e("Notification","BaseActivity Received");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause () {
        clearReferences() ;
        mDrawerLayout.closeDrawer(GravityCompat.START);
        applicationUtils .setCurrentActivity( this ) ;
        super .onPause() ;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy () {
        clearReferences() ;
        super .onDestroy() ;
    }
    private void clearReferences () {
        Activity currActivity = applicationUtils .getCurrentActivity() ;
        if ( this .equals(currActivity))
            applicationUtils .setCurrentActivity( null ) ;
    }
}