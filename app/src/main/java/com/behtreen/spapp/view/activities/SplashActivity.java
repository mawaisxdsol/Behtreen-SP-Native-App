package com.behtreen.spapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import com.behtreen.spapp.R;
import com.behtreen.spapp.controller.utilities.SharedPrefUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    private Context context;
    private SharedPrefUtil sharedPrefUtil;
    private String pref_loged_in;
    private String pref_access_token;
    private String pref_user_id;
    private String pref_user_phone;
    private boolean is_permission;
    JSONObject notification_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = getApplicationContext();
        sharedPrefUtil = new SharedPrefUtil();

        try {
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                if (extras.getString("notification_data") != null) {
                    Log.e("Notification", "Splash Received Before getting Intent Extra");
                    notification_result = new JSONObject(extras.getString("notification_data"));
                    Log.e("Notification", "Splash Received After getting Intent Extra");
                }
            }else{
                Log.e("Notification","Splash Received Nothing");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(requestMultiplePermission()) {
//            checkRemember();
        }


    }


    private boolean requestMultiplePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                            is_permission = true;
                            checkRemember();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                            is_permission = false;

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
        return is_permission;
    }
    private void splashThreadLogin() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
//                Intent i = new Intent(SplashActivity.this, NavigationDrawer.class);
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    private void splashThreadLogedIn() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, BaseActivity.class);
                if(notification_result!=null){
                    i.putExtra("notification_data", notification_result.toString());
                    Log.e("Notification","Loged In");
                }
                startActivity(i);

//                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    private void checkRemember() {
        pref_loged_in = sharedPrefUtil.getSharedPrefValue(context, "pref_loged_in");
        if (pref_loged_in == null || pref_loged_in.equals("0")) {
            splashThreadLogin();
            Log.e("Loged_in","Null or 0");
        } else {

            Log.e("Loged_in","Yes");

            pref_user_phone = sharedPrefUtil.getSharedPrefValue(context, "pref_user_phone");
            pref_user_id = sharedPrefUtil.getSharedPrefValue(context, "pref_user_id");
            pref_access_token = sharedPrefUtil.getSharedPrefValue(context, "pref_access_token");

            if (pref_user_phone == null || pref_user_phone.equals("")) {
                splashThreadLogin();
            }else{

                splashThreadLogedIn();
            }

        }
    }
}
