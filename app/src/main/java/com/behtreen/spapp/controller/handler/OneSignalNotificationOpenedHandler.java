package com.behtreen.spapp.controller.handler;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.behtreen.spapp.view.activities.BaseActivity;
import com.behtreen.spapp.view.activities.SplashActivity;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OneSignalNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler{

    private Application mContext;

    public OneSignalNotificationOpenedHandler(Application context) {
        mContext = context;
    }
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        Log.e("Notification Opened","Notification Opened Handler Called!");

        JSONObject response_notification = result.notification.payload.additionalData;
        if(result.notification.isAppInFocus){
            Log.e("Notification Opened","Notification Opened Handler InApp Called!");


            if(response_notification != null && response_notification.has("notification_type")){
                ApplicationUtils.onNotificationRecevied(response_notification);
            }
        }else{
            startApp(response_notification);
        }
    }

    private void startApp(JSONObject response_notification) {
        Intent intent = new Intent(mContext, SplashActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("notification_data", response_notification.toString());
        mContext.startActivity(intent);
    }
}
