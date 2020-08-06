package com.behtreen.spapp.model;

import java.util.ArrayList;
import java.util.List;

public class JobsHistorySpModel {

    /**
     * status : {"status":true,"status_code":200,"status_message":"Jobs Found Successfully.","error_type":"","access_token":"LI7MySmkB3YFAWC"}
     * data : {"jobs":[{"id":"1455","service_id":"1","user_id":"67","job_type":"instant","can_apply":null,"address":"Service Rd, Block J 3 Phase 2 Johar Town, Lahore, Punjab, Pakistan","job_latitude":"31.4668939","job_longitude":"74.259225","request_date":"2019-10-30 21:00:55","company_area":"4","status":"3","user_job_completed":"0","job_date":"2019-10-30","job_time":"09:00:55 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-10-30 16:01:27","service_name":"Carpenter","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1441","service_id":"1","user_id":"76","job_type":"instant","can_apply":null,"address":"1 Abdul Haque Rd, Trade Centre Commercial Area Phase 2 Johar Town, Lahore, Punjab, Pakistan","job_latitude":"31.465678","job_longitude":"74.26020679999999","request_date":"2019-10-15 21:50:38","company_area":"4","status":"3","user_job_completed":"0","job_date":"2019-10-15","job_time":"09:50:38 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-10-15 16:50:59","service_name":"Carpenter","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1419","service_id":"3","user_id":"72","job_type":"instant","can_apply":null,"address":"KF 04 ","job_latitude":"31.4672701","job_longitude":"74.26590439999995","request_date":"2019-10-10 22:22:31","company_area":"4","status":"5","user_job_completed":"0","job_date":"2019-10-10","job_time":"10:22:31 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-10-10 17:22:59","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1419","service_id":"3","user_id":"72","job_type":"instant","can_apply":null,"address":"KF 04 ","job_latitude":"31.4672701","job_longitude":"74.26590439999995","request_date":"2019-10-10 22:22:31","company_area":"4","status":"5","user_job_completed":"0","job_date":"2019-10-10","job_time":"10:22:31 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-10-10 17:22:59","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1403","service_id":"3","user_id":"52","job_type":"instant","can_apply":null,"address":"Unnamed Road, Shadman II Shadman 2 Shadman, Lahore, Punjab 54000, Pakistan","job_latitude":"31.5301153","job_longitude":"74.3329522","request_date":"2019-09-28 12:31:21","company_area":"5","status":"5","user_job_completed":"1","job_date":"2019-09-28","job_time":"12:31:21 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-09-28 07:31:28","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1396","service_id":"3","user_id":"52","job_type":"instant","can_apply":null,"address":"Service Rd, Block J 3 Phase 2 Johar Town, Lahore, Punjab, Pakistan","job_latitude":"31.4668829","job_longitude":"74.2592229","request_date":"2019-09-27 16:10:47","company_area":"4","status":"3","user_job_completed":"0","job_date":"2019-09-27","job_time":"04:10:47 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-09-27 11:11:00","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1380","service_id":"3","user_id":"52","job_type":"appointment","can_apply":null,"address":"Capt. Salman Sarwar Shaheed Rd, Babar Block Garden Town, Lahore, Punjab, Pakistan","job_latitude":"31.4972637","job_longitude":"74.32331","request_date":"2019-09-26 09:54:59","company_area":"","status":"6","user_job_completed":"0","job_date":"2019-09-26","job_time":"04:00 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-09-26 04:55:10","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1366","service_id":"3","user_id":"52","job_type":"appointment","can_apply":null,"address":"Service Rd, Block J 3 Phase 2 Johar Town, Lahore, Punjab, Pakistan","job_latitude":"31.4668944","job_longitude":"74.2592139","request_date":"2019-09-25 17:49:22","company_area":"4","status":"5","user_job_completed":"0","job_date":"2019-09-26","job_time":"09:30 AM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-09-25 12:49:39","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1360","service_id":"3","user_id":"52","job_type":"instant","can_apply":null,"address":"Service Rd, Block J 3 Phase 2 Johar Town, Lahore, Punjab, Pakistan","job_latitude":"31.4668887","job_longitude":"74.2592075","request_date":"2019-09-25 17:41:31","company_area":"4","status":"5","user_job_completed":"1","job_date":"2019-09-25","job_time":"05:41:31 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-09-25 12:41:42","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1358","service_id":"3","user_id":"52","job_type":"appointment","can_apply":null,"address":"Service Rd, Block J 3 Phase 2 Johar Town, Lahore, Punjab, Pakistan","job_latitude":"31.4668814","job_longitude":"74.2592138","request_date":"2019-09-25 17:36:02","company_area":"4","status":"3","user_job_completed":"0","job_date":"2019-09-25","job_time":"11:59 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-09-25 12:36:13","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}},{"id":"1356","service_id":"3","user_id":"52","job_type":"appointment","can_apply":null,"address":"Service Rd, Block J 3 Phase 2 Johar Town, Lahore, Punjab, Pakistan","job_latitude":"31.4668808","job_longitude":"74.259213","request_date":"2019-09-25 17:31:19","company_area":"4","status":"5","user_job_completed":"1","job_date":"2019-09-25","job_time":"11:45 PM","job_company":"5","job_completed_time":null,"cancel_charges":"0","job_accept_lat":null,"job_charges":null,"job_accept_lng":null,"cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2019-09-25 12:31:30","service_name":"Mason","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}}]}
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
         * status_message : Jobs Found Successfully.
         * error_type :
         * access_token : LI7MySmkB3YFAWC
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
        private ArrayList<JobsBean> jobs;

        public ArrayList<JobsBean> getJobs() {
            return jobs;
        }

        public void setJobs(ArrayList<JobsBean> jobs) {
            this.jobs = jobs;
        }

        public static class JobsBean {
            /**
             * id : 1455
             * service_id : 1
             * user_id : 67
             * job_type : instant
             * can_apply : null
             * address : Service Rd, Block J 3 Phase 2 Johar Town, Lahore, Punjab, Pakistan
             * job_latitude : 31.4668939
             * job_longitude : 74.259225
             * request_date : 2019-10-30 21:00:55
             * company_area : 4
             * status : 3
             * user_job_completed : 0
             * job_date : 2019-10-30
             * job_time : 09:00:55 PM
             * job_company : 5
             * job_completed_time : null
             * cancel_charges : 0
             * job_accept_lat : null
             * job_charges : null
             * job_accept_lng : null
             * cancelled_after_time : 0
             * job_cancelled_time : null
             * accepted_date : 2019-10-30 16:01:27
             * service_name : Carpenter
             * service_provider : {"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"21","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":""}
             */

            private String id;
            private String service_id;
            private String user_id;
            private String job_type;
            private String can_apply;
            private String address;
            private String job_latitude;
            private String job_longitude;
            private String request_date;
            private String company_area;
            private String status;
            private String user_job_completed;
            private String job_date;
            private String job_time;
            private String job_company;
            private String job_completed_time;
            private String cancel_charges;
            private String job_accept_lat;
            private String job_charges;
            private String job_accept_lng;
            private String cancelled_after_time;
            private String job_cancelled_time;
            private String accepted_date;
            private String service_name;
            private String status_name;
            private ServiceProviderBean service_provider;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getJob_type() {
                return job_type;
            }

            public void setJob_type(String job_type) {
                this.job_type = job_type;
            }

            public String getCan_apply() {
                return can_apply;
            }

            public void setCan_apply(String can_apply) {
                this.can_apply = can_apply;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getJob_latitude() {
                return job_latitude;
            }

            public void setJob_latitude(String job_latitude) {
                this.job_latitude = job_latitude;
            }

            public String getJob_longitude() {
                return job_longitude;
            }

            public void setJob_longitude(String job_longitude) {
                this.job_longitude = job_longitude;
            }

            public String getRequest_date() {
                return request_date;
            }

            public void setRequest_date(String request_date) {
                this.request_date = request_date;
            }

            public String getCompany_area() {
                return company_area;
            }

            public void setCompany_area(String company_area) {
                this.company_area = company_area;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUser_job_completed() {
                return user_job_completed;
            }

            public void setUser_job_completed(String user_job_completed) {
                this.user_job_completed = user_job_completed;
            }

            public String getJob_date() {
                return job_date;
            }

            public void setJob_date(String job_date) {
                this.job_date = job_date;
            }

            public String getJob_time() {
                return job_time;
            }

            public void setJob_time(String job_time) {
                this.job_time = job_time;
            }

            public String getJob_company() {
                return job_company;
            }

            public void setJob_company(String job_company) {
                this.job_company = job_company;
            }

            public String getJob_completed_time() {
                return job_completed_time;
            }

            public void setJob_completed_time(String job_completed_time) {
                this.job_completed_time = job_completed_time;
            }

            public String getCancel_charges() {
                return cancel_charges;
            }

            public void setCancel_charges(String cancel_charges) {
                this.cancel_charges = cancel_charges;
            }

            public String getJob_accept_lat() {
                return job_accept_lat;
            }

            public void setJob_accept_lat(String job_accept_lat) {
                this.job_accept_lat = job_accept_lat;
            }

            public String getJob_charges() {
                return job_charges;
            }

            public void setJob_charges(String job_charges) {
                this.job_charges = job_charges;
            }

            public String getJob_accept_lng() {
                return job_accept_lng;
            }

            public void setJob_accept_lng(String job_accept_lng) {
                this.job_accept_lng = job_accept_lng;
            }

            public String getCancelled_after_time() {
                return cancelled_after_time;
            }

            public void setCancelled_after_time(String cancelled_after_time) {
                this.cancelled_after_time = cancelled_after_time;
            }

            public String getJob_cancelled_time() {
                return job_cancelled_time;
            }

            public void setJob_cancelled_time(String job_cancelled_time) {
                this.job_cancelled_time = job_cancelled_time;
            }

            public String getAccepted_date() {
                return accepted_date;
            }

            public void setAccepted_date(String accepted_date) {
                this.accepted_date = accepted_date;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public ServiceProviderBean getService_provider() {
                return service_provider;
            }

            public void setService_provider(ServiceProviderBean service_provider) {
                this.service_provider = service_provider;
            }

            public static class ServiceProviderBean {
                /**
                 * id : 18
                 * username : sp-ibrahim
                 * password : MTIzNDU2
                 * parent_sp : 21
                 * device_id : 357412071374181
                 * first_name : Ibrahim
                 * last_name : Ghani
                 * father_name : Abdul Wahab Ghani
                 * profile_photo : https://admin.behtreen.com/uploads/sp/
                 * cnic : 35202-3709136-5
                 * dob : 1968-10-31 00:00:00
                 * phone : 923344192076
                 * address : 315-A Sector B, Eden Lane Villas One Raiwind Road
                 * latitude : 31.4669023
                 * longitude : 74.259221
                 * visit_fee :
                 * registration_date : 2019-09-05 02:41:06
                 * service_provider_type : general
                 * company_id : 5
                 * status : 1
                 * online : 1
                 * status_notes : New
                 * sp_code : SP-F5-LHR-18
                 * city : LHR
                 * country : PK
                 * is_shop : 0
                 * franchise_notes : null
                 * shop_name :
                 * reg_no :
                 */

                private String id;
                private String username;
                private String password;
                private String parent_sp;
                private String device_id;
                private String first_name;
                private String last_name;
                private String father_name;
                private String profile_photo;
                private String cnic;
                private String dob;
                private String phone;
                private String address;
                private String latitude;
                private String longitude;
                private String visit_fee;
                private String registration_date;
                private String service_provider_type;
                private String company_id;
                private String status;
                private String online;
                private String status_notes;
                private String sp_code;
                private String city;
                private String country;
                private String is_shop;
                private String franchise_notes;
                private String shop_name;
                private String reg_no;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getParent_sp() {
                    return parent_sp;
                }

                public void setParent_sp(String parent_sp) {
                    this.parent_sp = parent_sp;
                }

                public String getDevice_id() {
                    return device_id;
                }

                public void setDevice_id(String device_id) {
                    this.device_id = device_id;
                }

                public String getFirst_name() {
                    return first_name;
                }

                public void setFirst_name(String first_name) {
                    this.first_name = first_name;
                }

                public String getLast_name() {
                    return last_name;
                }

                public void setLast_name(String last_name) {
                    this.last_name = last_name;
                }

                public String getFather_name() {
                    return father_name;
                }

                public void setFather_name(String father_name) {
                    this.father_name = father_name;
                }

                public String getProfile_photo() {
                    return profile_photo;
                }

                public void setProfile_photo(String profile_photo) {
                    this.profile_photo = profile_photo;
                }

                public String getCnic() {
                    return cnic;
                }

                public void setCnic(String cnic) {
                    this.cnic = cnic;
                }

                public String getDob() {
                    return dob;
                }

                public void setDob(String dob) {
                    this.dob = dob;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getLatitude() {
                    return latitude;
                }

                public void setLatitude(String latitude) {
                    this.latitude = latitude;
                }

                public String getLongitude() {
                    return longitude;
                }

                public void setLongitude(String longitude) {
                    this.longitude = longitude;
                }

                public String getVisit_fee() {
                    return visit_fee;
                }

                public void setVisit_fee(String visit_fee) {
                    this.visit_fee = visit_fee;
                }

                public String getRegistration_date() {
                    return registration_date;
                }

                public void setRegistration_date(String registration_date) {
                    this.registration_date = registration_date;
                }

                public String getService_provider_type() {
                    return service_provider_type;
                }

                public void setService_provider_type(String service_provider_type) {
                    this.service_provider_type = service_provider_type;
                }

                public String getCompany_id() {
                    return company_id;
                }

                public void setCompany_id(String company_id) {
                    this.company_id = company_id;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getOnline() {
                    return online;
                }

                public void setOnline(String online) {
                    this.online = online;
                }

                public String getStatus_notes() {
                    return status_notes;
                }

                public void setStatus_notes(String status_notes) {
                    this.status_notes = status_notes;
                }

                public String getSp_code() {
                    return sp_code;
                }

                public void setSp_code(String sp_code) {
                    this.sp_code = sp_code;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getIs_shop() {
                    return is_shop;
                }

                public void setIs_shop(String is_shop) {
                    this.is_shop = is_shop;
                }

                public String getFranchise_notes() {
                    return franchise_notes;
                }

                public void setFranchise_notes(String franchise_notes) {
                    this.franchise_notes = franchise_notes;
                }

                public String getShop_name() {
                    return shop_name;
                }

                public void setShop_name(String shop_name) {
                    this.shop_name = shop_name;
                }

                public String getReg_no() {
                    return reg_no;
                }

                public void setReg_no(String reg_no) {
                    this.reg_no = reg_no;
                }
            }
        }
    }
}
