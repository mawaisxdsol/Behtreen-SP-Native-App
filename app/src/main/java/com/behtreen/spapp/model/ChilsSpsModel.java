package com.behtreen.spapp.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChilsSpsModel {

    /**
     * status : {"status":true,"status_code":200,"status_message":"SPs Found Successfully.","error_type":"","access_token":"JNdYHn8w6tqUIKg"}
     * data : {"child_sp":[{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"19","device_id":"357412071374181","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/https://dev-behtreen-bucket.s3.ap-south-1.amazonaws.com/sp/1575999684_ijaz.jpg","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"abx","latitude":"31.4669428","longitude":"74.2592426","visit_fee":"","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"4","status":"1","online":"1","fingerprint":null,"status_notes":"New","sp_code":"SP--18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":"","shop_name":"","reg_no":"","is_login":"1","nadra_check":"0","police_check":"0"}]}
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
         * status_message : SPs Found Successfully.
         * error_type :
         * access_token : JNdYHn8w6tqUIKg
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
        private ArrayList<ChildSpBean> child_sp;

        public ArrayList<ChildSpBean> getChild_sp() {
            return child_sp;
        }

        public void setChild_sp(ArrayList<ChildSpBean> child_sp) {
            this.child_sp = child_sp;
        }

        public static class ChildSpBean {
            /**
             * id : 18
             * username : sp-ibrahim
             * password : MTIzNDU2
             * parent_sp : 19
             * device_id : 357412071374181
             * first_name : Ibrahim
             * last_name : Ghani
             * father_name : Abdul Wahab Ghani
             * profile_photo : https://admin.behtreen.com/uploads/sp/https://dev-behtreen-bucket.s3.ap-south-1.amazonaws.com/sp/1575999684_ijaz.jpg
             * cnic : 35202-3709136-5
             * dob : 1968-10-31 00:00:00
             * phone : 923344192076
             * address : abx
             * latitude : 31.4669428
             * longitude : 74.2592426
             * visit_fee :
             * registration_date : 2019-09-05 02:41:06
             * service_provider_type : general
             * company_id : 4
             * status : 1
             * online : 1
             * fingerprint : null
             * status_notes : New
             * sp_code : SP--18
             * city : LHR
             * country : PK
             * is_shop : 0
             * franchise_notes :
             * shop_name :
             * reg_no :
             * is_login : 1
             * nadra_check : 0
             * police_check : 0
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
            private Object fingerprint;
            private String status_notes;
            private String sp_code;
            private String city;
            private String country;
            private String is_shop;
            private String franchise_notes;
            private String shop_name;
            private String reg_no;
            private String is_login;
            private String nadra_check;
            private String police_check;

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

            public Object getFingerprint() {
                return fingerprint;
            }

            public void setFingerprint(Object fingerprint) {
                this.fingerprint = fingerprint;
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

            public String getIs_login() {
                return is_login;
            }

            public void setIs_login(String is_login) {
                this.is_login = is_login;
            }

            public String getNadra_check() {
                return nadra_check;
            }

            public void setNadra_check(String nadra_check) {
                this.nadra_check = nadra_check;
            }

            public String getPolice_check() {
                return police_check;
            }

            public void setPolice_check(String police_check) {
                this.police_check = police_check;
            }
        }
    }
}
