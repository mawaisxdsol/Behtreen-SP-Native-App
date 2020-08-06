package com.behtreen.spapp.model;

import java.util.ArrayList;
import java.util.List;

public class UserInfoModel {

    /**
     * status : {"status":true,"status_code":200,"status_message":"User Found.","error_type":"","access_token":"4Kbk6XaceU2iuJr"}
     * data : {"user_id":"18","user_name":"sp-ibrahim","parent_sp":"21","device_id":"913314d24ddbb697","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":null,"cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"5","status":"1","online":"1","status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":"","user_code":"18","sp_experience":[{"id":"37","service_provider_id":"18","service_id":"3","parent_service_id":"0","experience_year":"3","service_name":"Mason"},{"id":"45","service_provider_id":"18","service_id":"1","parent_service_id":"0","experience_year":"5","service_name":"Carpenter"}]}
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
         * status_message : User Found.
         * error_type :
         * access_token : 4Kbk6XaceU2iuJr
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
        /**
         * user_id : 18
         * user_name : sp-ibrahim
         * parent_sp : 21
         * device_id : 913314d24ddbb697
         * first_name : Ibrahim
         * last_name : Ghani
         * father_name : Abdul Wahab Ghani
         * profile_photo : null
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
         * user_code : 18
         * sp_experience : [{"id":"37","service_provider_id":"18","service_id":"3","parent_service_id":"0","experience_year":"3","service_name":"Mason"},{"id":"45","service_provider_id":"18","service_id":"1","parent_service_id":"0","experience_year":"5","service_name":"Carpenter"}]
         */

        private String user_id;
        private String user_name;
        private String parent_sp;
        private String device_id;
        private String first_name;
        private String last_name;
        private String father_name;
        private Object profile_photo;
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
        private Object franchise_notes;
        private String shop_name;
        private String reg_no;
        private String user_code;
        private ArrayList<SpExperienceBean> sp_experience;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
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

        public Object getProfile_photo() {
            return profile_photo;
        }

        public void setProfile_photo(Object profile_photo) {
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

        public Object getFranchise_notes() {
            return franchise_notes;
        }

        public void setFranchise_notes(Object franchise_notes) {
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

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public ArrayList<SpExperienceBean> getSp_experience() {
            return sp_experience;
        }

        public void setSp_experience(ArrayList<SpExperienceBean> sp_experience) {
            this.sp_experience = sp_experience;
        }

        public static class SpExperienceBean {
            /**
             * id : 37
             * service_provider_id : 18
             * service_id : 3
             * parent_service_id : 0
             * experience_year : 3
             * service_name : Mason
             */

            private String id;
            private String service_provider_id;
            private String service_id;
            private String parent_service_id;
            private String experience_year;
            private String service_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getService_provider_id() {
                return service_provider_id;
            }

            public void setService_provider_id(String service_provider_id) {
                this.service_provider_id = service_provider_id;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getParent_service_id() {
                return parent_service_id;
            }

            public void setParent_service_id(String parent_service_id) {
                this.parent_service_id = parent_service_id;
            }

            public String getExperience_year() {
                return experience_year;
            }

            public void setExperience_year(String experience_year) {
                this.experience_year = experience_year;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }
        }
    }
}
