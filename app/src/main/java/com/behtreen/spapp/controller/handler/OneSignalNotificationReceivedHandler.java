package com.behtreen.spapp.controller.handler;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.behtreen.spapp.controller.utilities.ApplicationUtils;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class OneSignalNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {

    private Application mContext;

    public OneSignalNotificationReceivedHandler(Application context) {
        mContext = context;
    }

    @Override
    public void notificationReceived(OSNotification notification) {
        Log.e("Notification Received","Notification Received Handler Called!");

        JSONObject response_notification = notification.payload.additionalData;
        if(notification.isAppInFocus){
            Log.e("Notification Received","Notification Received Handler InApp Called!");

            if(response_notification != null && response_notification.has("notification_type")){
                ApplicationUtils.onNotificationRecevied(response_notification);

            }
        }


    }
}
