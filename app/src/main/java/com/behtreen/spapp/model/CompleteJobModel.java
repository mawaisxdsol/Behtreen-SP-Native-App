package com.behtreen.spapp.model;

public class CompleteJobModel {

    /**
     * status : {"status":true,"status_code":200,"status_message":"Job Completed Successfully.","error_type":"","access_token":"XpG78HV4mLPbNId"}
     * data : {}
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
         * status_message : Job Completed Successfully.
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
    }
}
