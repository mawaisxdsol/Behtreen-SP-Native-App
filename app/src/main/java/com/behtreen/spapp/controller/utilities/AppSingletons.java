package com.behtreen.spapp.controller.utilities;


import com.behtreen.spapp.model.ActiveJobsSpModel;
import com.behtreen.spapp.model.JobsHistorySpModel;
import com.behtreen.spapp.model.SpNotificationsModel;
import com.behtreen.spapp.model.UserInfoModel;

public class AppSingletons {

    private static AppSingletons singleton = new AppSingletons();
    private static UserInfoModel userLoginModel = new UserInfoModel();
    private static JobsHistorySpModel jobsHistorySpModel = new JobsHistorySpModel();
    private static ActiveJobsSpModel activeJobsSpModel = new ActiveJobsSpModel();
    private static SpNotificationsModel notificationsSpModel = new SpNotificationsModel();

    private AppSingletons() { }

    public static AppSingletons getInstance() {
        return singleton;
    }

    public static UserInfoModel getLogedinInstance() { return userLoginModel; }

    public static JobsHistorySpModel getSpJobsHistory() { return jobsHistorySpModel; }

    public static ActiveJobsSpModel getSpActiveJobs() { return activeJobsSpModel; }

    public static SpNotificationsModel getNotificationsSp() { return notificationsSpModel; }

}
