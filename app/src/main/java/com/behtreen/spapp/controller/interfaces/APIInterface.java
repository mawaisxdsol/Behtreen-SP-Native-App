package com.behtreen.spapp.controller.interfaces;


import com.behtreen.spapp.model.AcceptNewJobModel;
import com.behtreen.spapp.model.ActiveJobsSpModel;
import com.behtreen.spapp.model.CancelJobModel;
import com.behtreen.spapp.model.ChilsSpsModel;
import com.behtreen.spapp.model.CompleteJobModel;
import com.behtreen.spapp.model.IsSpStatusOnlineModel;
import com.behtreen.spapp.model.JobsHistorySpModel;
import com.behtreen.spapp.model.LoginModel;
import com.behtreen.spapp.model.LogoutModel;
import com.behtreen.spapp.model.PageDataModel;
import com.behtreen.spapp.model.RegisterDeviceModel;
import com.behtreen.spapp.model.SpArrivedModel;
import com.behtreen.spapp.model.SpJobBalanceModel;
import com.behtreen.spapp.model.SpNotificationsModel;
import com.behtreen.spapp.model.SpStatusModel;
import com.behtreen.spapp.model.UserInfoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface APIInterface {

    @GET
    Call<LoginModel> doLoginGet(
            @Url String url,
            @Query("process") String process,
            @Query("username") String user_name,
            @Query("password") String user_password,
            @Query("device_id") String device_id
    );

    @GET
    Call<LogoutModel> doLogoutGet(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("device_id") String device_id
    );

    @GET
    Call<UserInfoModel> getUserInfo(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("device_id") String device_id
    );

    @GET
    Call<SpStatusModel> updateSpStatus(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude,
            @Query("status") String status
    );

    @GET
    Call<IsSpStatusOnlineModel> isSpStatusOnline(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token
    );

    @GET
    Call<JobsHistorySpModel> getJobsHistory(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token
    );

    @GET
    Call<ActiveJobsSpModel> getActiveJobs(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token
    );

    @GET
    Call<SpNotificationsModel> getSpNotificationsList(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token
    );

    @GET
    Call<AcceptNewJobModel> acceptNewJob(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("job_id") String job_id,
            @Query("child_sp") String child_sp
    );

    @GET
    Call<ChilsSpsModel> getChildSps(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("job_id") String job_id
    );

    @GET
    Call<SpArrivedModel> spArrived(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("job_id") String job_id
    );

    @GET
    Call<CompleteJobModel> completeJob(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("job_id") String job_id,
            @Query("job_time") String job_time
    );

    @GET
    Call<CancelJobModel> cancelJob(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("job_id") String job_id,
            @Query("job_time") String job_time,
            @Query("paid") boolean paid
    );

    @GET
    Call<PageDataModel> getPageDataCall(
            @Url String url,
            @Query("process") String process,
            @Query("slug") String slug
    );


    @GET
    Call<SpJobBalanceModel> getSpJobBalance(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token
    );

    @GET
    Call<RegisterDeviceModel> registerDeviceForPushNotification(
            @Url String url,
            @Query("process") String process,
            @Query("user_id") String user_id,
            @Query("access_token") String access_token,
            @Query("device_id") String device_id,
            @Query("device_token") String device_token,
            @Query("device_type") String device_type,
            @Query("app") String app
    );

}
