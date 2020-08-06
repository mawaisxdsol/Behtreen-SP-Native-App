package com.behtreen.spapp.controller.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Muhammad Awais Qureshi .
 */

public class SharedPrefUtil {
    static String PREFS_NAME = "pref";

    /*
     * This Method is used to get data of Saved Shared Preference against Preference Title.
     * @params Context mContext
     * @params String sharedPrefTitle
     */
    public String getSharedPrefValue(Context mContext, String sharedPrefTitle) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,
                0);
        return settings.getString(sharedPrefTitle, null);
    }

    /*
     * This Method is used to save data to Shared Preference against Preference Title.
     * @params Context mContext
     * @params String sharedPrefTitle
     */
    public void saveSharedPrefValue(Context mContext, String sharedPrefTitle,
                                    String sharedPrefValue) {
        try {
            SharedPreferences settings = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            final Editor editor = settings.edit();
            editor.putString(sharedPrefTitle, sharedPrefValue);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * This Method is used to remove data of Saved Shared Preference against Preference Title.
     * @params Context mContext
     * @params String sharedPrefTitle
     */
    public void removeSharedPrefValue(Context mContext, String sharedPrefTitle) {
        try {
            SharedPreferences settings = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            Editor editor = settings.edit();
            editor.remove(sharedPrefTitle);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * This Method is used to clear complete data of Saved Shared Preference.
    * @params Context mContext
    */
    public void clearSharedPrefValue(Context mContext){
        try {
            SharedPreferences settings = mContext.getSharedPreferences(
                    PREFS_NAME, 0);
            Editor editor = settings.edit();
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
