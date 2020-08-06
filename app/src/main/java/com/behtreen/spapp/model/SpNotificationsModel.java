package com.behtreen.spapp.model;

import java.util.ArrayList;
import java.util.List;

public class SpNotificationsModel {

    /**
     * status : {"status":true,"status_code":200,"status_message":"Notifications found Successfully.","error_type":"","access_token":"XpG78HV4mLPbNId"}
     * data : {"notifications":[{"id":"8","notification_title":"Test Notification to SP","notification_description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit lacus sit amet commodo ornare. Nulla facilisi. In cursus nisi nec nibh consectetur, sit amet auctor augue ullamcorper. Morbi a accumsan ex.","notification_to":"Service Providers","franchise_ids":null,"sp_ids":"All","user_ids":null,"notification_status":"unread","notification_sent_date":"2019-09-12 16:39:24"},{"id":"7","notification_title":"Test Notification","notification_description":"Hello its a test message...","notification_to":"Users","franchise_ids":"F4-LHR,F5-LHR","sp_ids":"All","user_ids":"All","notification_status":"unread","notification_sent_date":"2019-09-12 16:37:33"},{"id":"6","notification_title":"Test Notification","notification_description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit lacus sit amet commodo ornare. Nulla facilisi. In cursus nisi nec nibh consectetur, sit amet auctor augue ullamcorper. Morbi a accumsan ex.","notification_to":"Franchises","franchise_ids":"All","sp_ids":null,"user_ids":null,"notification_status":"unread","notification_sent_date":"2019-09-12 15:36:29"},{"id":"5","notification_title":"Test Notification","notification_description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit lacus sit amet commodo ornare. Nulla facilisi. In cursus nisi nec nibh consectetur, sit amet auctor augue ullamcorper. Morbi a accumsan ex.","notification_to":"Franchises","franchise_ids":"F4-LHR,F5-LHR","sp_ids":null,"user_ids":null,"notification_status":"unread","notification_sent_date":"2019-09-12 15:36:17"},{"id":"4","notification_title":"Test Notification","notification_description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit lacus sit amet commodo ornare. Nulla facilisi. In cursus nisi nec nibh consectetur, sit amet auctor augue ullamcorper. Morbi a accumsan ex.","notification_to":"Service Providers","franchise_ids":null,"sp_ids":"All","user_ids":null,"notification_status":"unread","notification_sent_date":"2019-09-12 15:36:00"}]}
     */

    private StatusBean status;
    private DataBean data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * status : true
         * status_code : 200
         * status_message : Notifications found Successfully.
         * error_type :
         * access_token : XpG78HV4mLPbNId
         */

        private boolean status;
        private int status_code;
        private String status_message;
        private String error_type;
        private String access_token;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public int getStatus_code() {
            return status_code;
        }

        public void setStatus_code(int status_code) {
            this.status_code = status_code;
        }

        public String getStatus_message() {
            return status_message;
        }

        public void setStatus_message(String status_message) {
            this.status_message = status_message;
        }

        public String getError_type() {
            return error_type;
        }

        public void setError_type(String error_type) {
            this.error_type = error_type;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }
    }

    public static class DataBean {
        private ArrayList<NotificationsBean> notifications;

        public ArrayList<NotificationsBean> getNotifications() {
            return notifications;
        }

        public void setNotifications(ArrayList<NotificationsBean> notifications) {
            this.notifications = notifications;
        }

        public static class NotificationsBean {
            /**
             * id : 8
             * notification_title : Test Notification to SP
             * notification_description : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla blandit lacus sit amet commodo ornare. Nulla facilisi. In cursus nisi nec nibh consectetur, sit amet auctor augue ullamcorper. Morbi a accumsan ex.
             * notification_to : Service Providers
             * franchise_ids : null
             * sp_ids : All
             * user_ids : null
             * notification_status : unread
             * notification_sent_date : 2019-09-12 16:39:24
             */

            private String id;
            private String notification_title;
            private String notification_description;
            private String notification_to;
            private Object franchise_ids;
            private String sp_ids;
            private Object user_ids;
            private String notification_status;
            private String notification_sent_date;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNotification_title() {
                return notification_title;
            }

            public void setNotification_title(String notification_title) {
                this.notification_title = notification_title;
            }

            public String getNotification_description() {
                return notification_description;
            }

            public void setNotification_description(String notification_description) {
                this.notification_description = notification_description;
            }

            public String getNotification_to() {
                return notification_to;
            }

            public void setNotification_to(String notification_to) {
                this.notification_to = notification_to;
            }

            public Object getFranchise_ids() {
                return franchise_ids;
            }

            public void setFranchise_ids(Object franchise_ids) {
                this.franchise_ids = franchise_ids;
            }

            public String getSp_ids() {
                return sp_ids;
            }

            public void setSp_ids(String sp_ids) {
                this.sp_ids = sp_ids;
            }

            public Object getUser_ids() {
                return user_ids;
            }

            public void setUser_ids(Object user_ids) {
                this.user_ids = user_ids;
            }

            public String getNotification_status() {
                return notification_status;
            }

            public void setNotification_status(String notification_status) {
                this.notification_status = notification_status;
            }

            public String getNotification_sent_date() {
                return notification_sent_date;
            }

            public void setNotification_sent_date(String notification_sent_date) {
                this.notification_sent_date = notification_sent_date;
            }
        }
    }
}
