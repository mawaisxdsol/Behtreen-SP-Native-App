package com.behtreen.spapp.controller.utilities;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.handler.OneSignalNotificationOpenedHandler;
import com.behtreen.spapp.controller.handler.OneSignalNotificationReceivedHandler;
import com.behtreen.spapp.controller.interfaces.APIInterface;
import com.behtreen.spapp.model.ChilsSpsModel;
import com.behtreen.spapp.model.NewJobChildServiceProviderModel;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.behtreen.spapp.view.adapter.ActiveJobsAdapter;
import com.behtreen.spapp.view.adapter.NewJobServiceProviderAdapter;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApplicationUtils extends Application {
    private static boolean Logs = true;
    private static String TAG;

    private static Context mContext = null;
    private static Activity mCurrentActivity = null;
    public static SharedPrefUtil sharedPrefUtil;
    Application application;
    private static ApplicationUtils mInstance;

    private static NewJobServiceProviderAdapter newJobServiceProviderAdapter;

    public ApplicationUtils() {
        mInstance = this;
    }


    @Override
    public void onCreate() {

        mContext = getApplicationContext();
        application = this;
        sharedPrefUtil = new SharedPrefUtil();
        TAG = getAppContext().getResources().getString(R.string.app_name);


        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new OneSignalNotificationOpenedHandler(application))
                .setNotificationReceivedHandler(new OneSignalNotificationReceivedHandler(application))
                .init();
        super.onCreate();
    }

    public static synchronized ApplicationUtils getmInstance(){
        return mInstance;
    }

    public static Activity getCurrentActivity () {
        return mCurrentActivity ;
    }
    public void setCurrentActivity (Activity mCurrentActivity) {
        this . mCurrentActivity = mCurrentActivity ;
    }

    /* Start - onNotificationRecevied() */
    public static void onNotificationRecevied(JSONObject response_notification){

        Log.e("ApplicationUtils","onNotificationReceived");
        OneSignal.clearOneSignalNotifications();

//        JSONObject response_notification = notification.payload.additionalData;

        if(response_notification != null && response_notification.has("notification_type")){
            String notification_type = response_notification.optString("notification_type");

            Log.e("Notification Response",response_notification.toString());

            if(notification_type.equals("job_notification")){
                JSONObject job_notification_job_data = response_notification.optJSONObject("job_data");
                try {
                    Log.e("JSONObject",job_notification_job_data.getString("job_type"));


                    if(job_notification_job_data.getString("job_type").equals("instant")){
                        if(sharedPrefUtil.getSharedPrefValue(mContext,"pref_user_is_shop").equals("1")){
                            getShopChildSps(
                                    response_notification.optString("job_id"),
                                    true,
                                    job_notification_job_data.getString("address"),
                                    job_notification_job_data.getString("request_date"),
                                    job_notification_job_data.getString("service_category")
                            );
                        }
                        else {
                            showNewJobDialog(
                                    true,
                                    response_notification.optString("job_id"),
                                    job_notification_job_data.getString("address"),
                                    job_notification_job_data.getString("request_date"),
                                    job_notification_job_data.getString("service_category"),
                                    null
                            );
                        }
                    }
                    else {
                        if(sharedPrefUtil.getSharedPrefValue(mContext,"pref_user_is_shop").equals("1")){
                            getShopChildSps(
                                    response_notification.optString("job_id"),
                                    false,
                                    job_notification_job_data.getString("address"),
                                    job_notification_job_data.getString("request_date"),
                                    job_notification_job_data.getString("service_category")
                            );
                        }else {
                            showNewJobDialog(
                                    false,
                                    response_notification.optString("job_id"),
                                    job_notification_job_data.getString("address"),
                                    job_notification_job_data.getString("request_date"),
                                    job_notification_job_data.getString("service_category"),
                                    null
                            );
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(notification_type.equals("user_job_confirmation")){
                Log.e("user_job_confirmation","Job confirmed by User");
                JSONObject job_notification_job_data = response_notification.optJSONObject("job_data");
                String confirmation = response_notification.optString("confirmation");
                try {

                    if(confirmation.equals("accepted")){
                        showJobConfirmedDialog(
                                job_notification_job_data.getString("address"),
                                job_notification_job_data.getString("request_date"),
                                job_notification_job_data.getString("service_category")
                        );
                    }else if(confirmation.equals("rejected")){
                        showCustomDialog(false,response_notification.optString("title"),response_notification.optString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(notification_type.equals("user_job_cancel")){
                JSONObject job_notification_job_data = response_notification.optJSONObject("job_data");
                try {


                    showJobCanceledDialog(
                            job_notification_job_data.getString("address"),
                            job_notification_job_data.getString("request_date"),
                            job_notification_job_data.getString("service_category")
                    );


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(notification_type.equals("first_notification_for_schedule_sp") || notification_type.equals("second_notification_for_schedule_sp")){

                showCustomDialog(
                        true,
                        response_notification.optString("title"),
                        response_notification.optString("message")
                );
            }


        }

    }
    /* End - onNotificationRecevied() */

    /* Start - showNewJobDialog() */
    /**
     * Populates new Alert Dialogue for New Job to Accept when there
     * is notification for any New Job Published by Customer through
     * public Behtreen Application.
     *
     * @param is_instant true - If New Job notification is for instant. false - If New Job notification is for appointment
     * @param job_id the ID of New Job.
     * @param address the address from where New Job posted.
     * @param date the date on which New Job is Posted.
     * @param type the Category Type for which New Job is Posted e.g Carpenter, Electrician...
     * @param child_sp the JSONObject of child service providers in case of Shop.
     * @return nothing
     */
    public static void showNewJobDialog(final boolean is_instant, final String job_id, String address, String date, String type, final ArrayList<ChilsSpsModel.DataBean.ChildSpBean> child_sp) {

//        Log.e("showNewJobDialog()","Called");
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = ((BaseActivity) base_activity_context).findViewById(android.R.id.content);
        ViewGroup viewGroup = getCurrentActivity().findViewById(android.R.id.content);
        ArrayList<NewJobChildServiceProviderModel.ChildSpBean> list_sp_childs;


        View dialogView = null;
        //then we will inflate the custom alert dialog xml that we created
        if(is_instant){
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_new_job_instant, viewGroup, false);
        }else{
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_new_job_appointment, viewGroup, false);
        }

        LinearLayout linearlayout_new_job_child_service_providers = (LinearLayout) dialogView.findViewById(R.id.linearlayout_new_job_child_service_providers);
        RecyclerView recycler_view_new_job_service_providers = (RecyclerView) dialogView.findViewById(R.id.recycler_view_new_job_service_providers);
        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(mContext);
        recycler_view_new_job_service_providers.setLayoutManager(recylerViewLayoutManager);

        Button button_accept_job = (Button) dialogView.findViewById(R.id.button_accept_job);
        Button button_cancel_job = (Button) dialogView.findViewById(R.id.button_cancel_job);
        TextView text_view_new_job_address = (TextView) dialogView.findViewById(R.id.text_view_new_job_address);
        TextView text_view_new_job_date_time = (TextView) dialogView.findViewById(R.id.text_view_new_job_date_time);
        TextView text_view_new_job_type = (TextView) dialogView.findViewById(R.id.text_view_new_job_type);

        if(!address.equals("")){
            text_view_new_job_address.setText(address);
            text_view_new_job_date_time.setText(date);
            text_view_new_job_type.setText(type);
        }

        if(sharedPrefUtil.getSharedPrefValue(mContext,"pref_user_is_shop").equals("1")){

        }
        if(child_sp!= null && child_sp.size()>0){
            list_sp_childs = new ArrayList<NewJobChildServiceProviderModel.ChildSpBean>();

            for(int i=0; i<child_sp.size(); i++){

                NewJobChildServiceProviderModel.ChildSpBean childSpBean = new NewJobChildServiceProviderModel.ChildSpBean();


                childSpBean.setId(child_sp.get(i).getId());
                childSpBean.setFirst_name(child_sp.get(i).getFirst_name());
                childSpBean.setSp_code(child_sp.get(i).getSp_code());
                childSpBean.setProfile_photo(child_sp.get(i).getProfile_photo());

                list_sp_childs.add(childSpBean);

            }

            newJobServiceProviderAdapter = new NewJobServiceProviderAdapter(mContext, list_sp_childs);
            recycler_view_new_job_service_providers.setAdapter(newJobServiceProviderAdapter);

            linearlayout_new_job_child_service_providers.setVisibility(View.VISIBLE);


        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        Log.e("showNewJobDialog","Opened");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        button_accept_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(child_sp != null && child_sp.size()>0){
//                    newJobServiceProviderAdapter.getLastCheckedPosition();
                    Log.e("Last Checked Postition",Integer.toString(newJobServiceProviderAdapter.getLastCheckedPosition()));


                    //child_sp.getJSONObject(newJobServiceProviderAdapter.getLastCheckedPosition()).getString("id");
                    if(newJobServiceProviderAdapter.getLastCheckedPosition()>=0) {
                        alertDialog.dismiss();
                        ((BaseActivity) getCurrentActivity()).acceptNewJob(
                                job_id,
                                child_sp.get(newJobServiceProviderAdapter.getLastCheckedPosition()).getId()
                        );
                    }else {
                        Toast.makeText(mContext,"Select Service Provider",Toast.LENGTH_LONG).show();
                    }

                }else{
                    alertDialog.dismiss();
                    ((BaseActivity) getCurrentActivity()).acceptNewJob(job_id,"");
                }

            }
        });

        button_cancel_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    /* End - showNewJobDialog() */

    /* Start - getShopChildSps()*/
    public static void getShopChildSps(final String job_id, final boolean is_instant_job, final String address, final String request_date, final String service_category){
        if (ApplicationUtils.isNetworkAvailable()) {
            ((BaseActivity)getCurrentActivity()).apiInterface = ((BaseActivity)getCurrentActivity()).getClient().create(APIInterface.class);
            final ProgressDialog mProgressDialog = new ProgressDialog(getCurrentActivity());
            mProgressDialog.setTitle(AppConstant.BEHTREEN_APP_NAME);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Getting your Childs...");
            mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.show();

            Call<ChilsSpsModel> call = ((BaseActivity)getCurrentActivity()).apiInterface.getChildSps(
                    new URLList().getUrl(2),
                    "sp_childs_available",
                    ((BaseActivity)getCurrentActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(mContext,"pref_user_id"),
                    ((BaseActivity)getCurrentActivity()).applicationUtils.sharedPrefUtil.getSharedPrefValue(mContext,"pref_access_token"),
                    job_id
            );

            call.enqueue(new Callback<ChilsSpsModel>() {
                @Override
                public void onResponse(Call<ChilsSpsModel> call, Response<ChilsSpsModel> response) {
                    if (response.isSuccessful()) {
                        mProgressDialog.dismiss();
                        ChilsSpsModel response_shop_child_sps_model = response.body();

                        if (response_shop_child_sps_model.getStatus().isStatus() && response_shop_child_sps_model.getStatus().getStatus_code() == 200) {

                            showNewJobDialog(
                                    is_instant_job,
                                    job_id,
                                    address,
                                    request_date,
                                    service_category,
                                    response_shop_child_sps_model.getData().getChild_sp()
                            );

                        } else if (response_shop_child_sps_model.getStatus().isStatus() && response_shop_child_sps_model.getStatus().getStatus_code() != 200) {

                            if(response_shop_child_sps_model.getStatus().getError_type().equals("authorizationError")){

                                final AlertDialog alertDialog = ApplicationUtils.showCustomDialogAction(
                                        false,
                                        "Authentication Failed",
                                        response_shop_child_sps_model.getStatus().getStatus_message()
                                );

                                Button ok_button = (Button) alertDialog.findViewById(R.id.button_ok);
                                ok_button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        alertDialog.dismiss();
                                        ((BaseActivity)getCurrentActivity()).doLogout();
                                    }
                                });
                            }else{
                                Toast.makeText(getCurrentActivity(),response_shop_child_sps_model.getStatus().getStatus_message(),Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        mProgressDialog.dismiss();
                        Toast.makeText(getCurrentActivity(),"Server Connection Failed",Toast.LENGTH_SHORT).show();
                        Log.d("Response", "Server Connection Failed");
                    }
                }

                @Override
                public void onFailure(Call<ChilsSpsModel> call, Throwable t) {
                    Log.e("Response", "Error " + t.getMessage());
                    mProgressDialog.dismiss();
                }
            });
        } else {
            ApplicationUtils.showToast("Please Connect Internet Connection");
        }
    }
    /* End - getShopChildSps()*/

    /* Start - showJobConfirmedDialog() */
    public static void showJobConfirmedDialog(String address, String date, String type) {
        Log.e("showNewJobDialog()","Called");
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = ((BaseActivity) base_activity_context).findViewById(android.R.id.content);
        ViewGroup viewGroup = getCurrentActivity().findViewById(android.R.id.content);

        View dialogView;
        //then we will inflate the custom alert dialog xml that we created
        dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_job_confirm, viewGroup, false);


        Button button_job_confirmed = (Button) dialogView.findViewById(R.id.button_job_confirmed);
        TextView text_view_new_job_address = (TextView) dialogView.findViewById(R.id.text_view_new_job_address);
        TextView text_view_new_job_date_time = (TextView) dialogView.findViewById(R.id.text_view_new_job_date_time);
        TextView text_view_new_job_type = (TextView) dialogView.findViewById(R.id.text_view_new_job_type);

        if(!address.equals("")){
            text_view_new_job_address.setText(address);
            text_view_new_job_date_time.setText(date);
            text_view_new_job_type.setText(type);
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        button_job_confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });

    }

    /* End - showJobConfirmedDialog() */

    /* Start - showJobCanceledDialog() */
    public static void showJobCanceledDialog(String address, String date, String type) {
        Log.e("showJobCanceledDialog()","Called");
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = ((BaseActivity) base_activity_context).findViewById(android.R.id.content);
        ViewGroup viewGroup = getCurrentActivity().findViewById(android.R.id.content);

        View dialogView;
        //then we will inflate the custom alert dialog xml that we created
        dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_job_canceled, viewGroup, false);


        Button button_job_confirmed = (Button) dialogView.findViewById(R.id.button_job_confirmed);
        TextView text_view_new_job_address = (TextView) dialogView.findViewById(R.id.text_view_new_job_address);
        TextView text_view_new_job_date_time = (TextView) dialogView.findViewById(R.id.text_view_new_job_date_time);
        TextView text_view_new_job_type = (TextView) dialogView.findViewById(R.id.text_view_new_job_type);

        if(!address.equals("")){
            text_view_new_job_address.setText(address);
            text_view_new_job_date_time.setText(date);
            text_view_new_job_type.setText(type);
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        button_job_confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });

    }

    /* End - showJobCanceledDialog() */

    /* Start - showCustomDialog() */
    /**
     * This method is used to show Custom Alert Dialog.
     * @param is_success This is the first paramter to check either to show Successful Layout or not
     * @param message  This is the second parameter to display given message
     */
    public static void showCustomDialog(final boolean is_success, String title,String message) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = getCurrentActivity().findViewById(android.R.id.content);

        View dialogView;
        if(is_success){
            //then we will inflate the custom alert dialog xml that we created
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_successful_authenticate, viewGroup, false);
        }else{
            //then we will inflate the custom alert dialog xml that we created
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_fail_authenticate, viewGroup, false);
        }


        Button ok_button = (Button) dialogView.findViewById(R.id.button_ok);
        TextView textview_alert_message = (TextView) dialogView.findViewById(R.id.textview_alert_message);
        TextView textview_alert_message_title = (TextView) dialogView.findViewById(R.id.textview_alert_message_title);

        if(!message.equals("")){
            textview_alert_message_title.setText(title);
            textview_alert_message.setText(message);
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_success){
                    alertDialog.dismiss();
                }else{
                    alertDialog.dismiss();
                }


            }
        });

    }

    /* End - showCustomDialog() */

    /* Start - showCustomDialogResponse() */
    /**
     * This method is used to show Custom Alert Dialog.
     * Set a listener to be invoked when the Ok button of the dialog is pressed.
     * @param is_success This is the first paramter to check either to show Successful Layout or not
     * @param message  This is the second parameter to display given message
     * @return AlertDialog This returns alertDialog to implement Click listener to Ok button.
     */

    public static AlertDialog showCustomDialogAction(final boolean is_success, String title, String message) {


        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = getCurrentActivity().findViewById(android.R.id.content);

        View dialogView;
        if(is_success){
            //then we will inflate the custom alert dialog xml that we created
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_successful_authenticate, viewGroup, false);
        }else{
            //then we will inflate the custom alert dialog xml that we created
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_fail_authenticate, viewGroup, false);
        }


        TextView textview_alert_message_title = (TextView) dialogView.findViewById(R.id.textview_alert_message_title);
        TextView textview_alert_message = (TextView) dialogView.findViewById(R.id.textview_alert_message);

        if(!title.equals("")){
            textview_alert_message_title.setText(title);
        }

        if(!message.equals("")){
            textview_alert_message.setText(message);
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();






        /*ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_success){

                    alertDialog.dismiss();

                }else{
                    alertDialog.dismiss();
                }



            }
        });*/

        return alertDialog;
    }

    /* End - showCustomDialogResponse() */

    /* Start - showCustomConfirmDialog() */
    /**
     * This method is used to show Custom Alert Dialog.
     * Set a listener to be invoked when the Ok button of the dialog is pressed.
     * @param is_success This is the first paramter to check either to show Successful Layout or not
     * @param message  This is the second parameter to display given message
     * @return AlertDialog This returns alertDialog to implement Click listener to Ok button.
     */

    public static AlertDialog showCustomConfirmDialog(final boolean is_success, String title, String message) {


        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = getCurrentActivity().findViewById(android.R.id.content);

        View dialogView;
        if(is_success){
            //then we will inflate the custom alert dialog xml that we created
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_confirm_successful_authenticate, viewGroup, false);
        }else{
            //then we will inflate the custom alert dialog xml that we created
            dialogView = LayoutInflater.from(getCurrentActivity()).inflate(R.layout.custom_alert_confirm_fail_authenticate, viewGroup, false);
        }


        TextView textview_alert_message_title = (TextView) dialogView.findViewById(R.id.textview_alert_message_title);
        TextView textview_alert_message = (TextView) dialogView.findViewById(R.id.textview_alert_message);

        if(!title.equals("")){
            textview_alert_message_title.setText(title);
        }

        if(!message.equals("")){
            textview_alert_message.setText(message);
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        return alertDialog;
    }

    /* End - showCustomConfirmDialog() */

    public static Context getAppContext() {
        return mContext;
    }

    public static void showLogs(String msg) {
        if (Logs) {
            Log.d(ApplicationUtils.TAG, msg);
        }
    }

    public static void showToast(String msg) {
        if (!stringEmpty(msg))
            Toast.makeText(getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean stringEmpty(String str) {
        return !(str != null && !str.isEmpty() && str.length() > 0);
    }

    public static boolean isUrlValid(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    /**
     * @param datetime - 2019-09-26 04:00 pm
     * @return Oct 10, 10:22 PM
     */
    public static String getCustomDateTime(String datetime){

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        Date date = null;
        try
        {
            date = form.parse(datetime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();

            form = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            try {
                date = form.parse(datetime);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMM dd, h:mm aa");
        String newDateStr="";
        try{
            newDateStr = postFormater.format(date);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        String final_date = newDateStr.replace("am", "AM").replace("pm","PM");

        return final_date;
    }

    /**
     * @param datetime - 2019-09-26 04:00
     * @return Oct 10, 10:22 PM
     */
    public static String getCustomDateTime24To12(String datetime){

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try
        {
            date = form.parse(datetime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();

            form = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            try {
                date = form.parse(datetime);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMM dd, h:mm aa");
        String newDateStr = postFormater.format(date);

        String final_date = newDateStr.replace("am", "AM").replace("pm","PM");

        return final_date;
    }

    /**
     * @param datetime - 2019-09-26 04:00
     * @return 2 Days Ago
     */
    public static String getCustomTimeAgo(String datetime){

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try
        {
            date = form.parse(datetime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();

            form = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
            try {
                date = form.parse(datetime);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        String final_date = (DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS)).toString();

        return final_date;
    }

    /* Start - getDeviceId() */
    public static String getDeviceId(Context mContext, Activity mActivity,TelephonyManager mTelephonyManager) {
        String imeiNumber = "";
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
            ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.RECEIVE_SMS},124);
            ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.READ_SMS},123);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imeiNumber = mTelephonyManager.getImei();
            } else {
                imeiNumber = mTelephonyManager.getDeviceId();
            }
        }


        return imeiNumber;
    }
    /* End - getDeviceId() */



    public static ProgressDialog getProgressDialog(Context mContext) {
        if (mContext != null) {
            ProgressDialog progressDialog = new ProgressDialog(mContext, android.R.style.Theme_Holo_Light_Dialog);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setMessage("Loading please wait...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            return progressDialog;
        } else {
            return null;
        }
    }

    public static ProgressDialog getProgressDialog(Context mContext, String txtMsg) {
        ProgressDialog progressDialog = new ProgressDialog(mContext, android.R.style.Theme_Holo_Light_Dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setMessage(txtMsg);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static void showProgressDialog(ProgressDialog pd) {
        if (pd != null && !pd.isShowing())
            pd.show();
    }

    public static void dismissProgressDialog(ProgressDialog pd) {
        if (pd != null && pd.isShowing())
            pd.dismiss();
    }


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * is network connected
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     * Get whether or not any network connection is present (eg. wifi, 3G, etc.).
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
        if (info == null) return false;
        for (int i = 0; i < info.length; i++)
            if (info[i].getState() == NetworkInfo.State.CONNECTED) return true;
        return false;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Return true if Location on device is Enabled by User.
     */
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }


    public static void requestFocus(View view, FragmentActivity activity) {
        if (activity != null && view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(FragmentActivity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getAppContext().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public static void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getAppContext().getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }


//    public static void getSizeOfImageView(final ImageView iv, final ICallBack iCallBack) {
//        ViewTreeObserver vto = iv.getViewTreeObserver();
//        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            public boolean onPreDraw() {
//                iv.getViewTreeObserver().removeOnPreDrawListener(this);
//                int finalHeight = iv.getMeasuredHeight();
//                int finalWidth = iv.getMeasuredWidth();
//                if (finalHeight > 0 && finalWidth > 0) {
//                    ModelImageViewSize size = new ModelImageViewSize(finalWidth, finalHeight);
//
//                    if (iCallBack != null) {
//                        iCallBack.onCallBack(null, size, null, 0);
//                    }
//                }
//                return true;
//            }
//        });
//    }

    //    public static void loadImages(final ImageView iv, final String imgUrl) {
//        getSizeOfImageView(iv, new ICallBack() {
//            @Override
//            public void onCallBack(Object ref, Object data, Object action, int status) {
//                ModelImageViewSize size = (ModelImageViewSize) data;
//                Glide.with(getAppContext()).load(imgUrl).centerCrop().placeholder(R.drawable.twitter_icon).crossFade().override(size.getWidth(), size.getHeight()).centerCrop().into(iv);
//            }
//        });
//    }
    //Random number generator;
    public static int randomNumber() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }


    public static String user_No_Format(String string) {
        String sub_string = string.substring(1, 11);
        String user_number = "92" + sub_string;
        return user_number;
    }

}
