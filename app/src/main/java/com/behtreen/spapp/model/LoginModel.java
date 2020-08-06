package com.behtreen.spapp.model;

public class LoginModel {


    /**
     * status : {"status":true,"status_code":200,"status_message":"User Found.","error_type":"","access_token":"lvmc5KkaHMRZjSP"}
     * data : {"user_id":"18","user_name":"sp-ibrahim","phone":"923344192076","parent_sp":"21","device_id":"913314d24ddbb697"}
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
         * access_token : lvmc5KkaHMRZjSP
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
         * phone : 923344192076
         * parent_sp : 21
         * device_id : 913314d24ddbb697
         */

        private String user_id;
        private String user_name;
        private String phone;
        private String parent_sp;
        private String device_id;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
    }
}
