package com.behtreen.spapp.model;

import java.util.ArrayList;
import java.util.List;

public class ActiveJobsSpModel {

    /**
     * status : {"status":true,"status_code":200,"status_message":"Active jobs found.","error_type":"","access_token":"XpG78HV4mLPbNId"}
     * data : {"jobs":[{"id":"2245","service_id":"1","user_id":"82","job_type":"instant","can_apply":null,"address":"3, J 3 Block Block J Phase 2 Johar Town, Lahore, Punjab 54000, Pakistan Lahore","job_latitude":"31.465663555459198","job_longitude":"74.25858099013567","request_date":"2020-01-21 12:45:07","company_area":"4","status":"2","user_job_completed":"0","job_date":"2020-01-21","job_time":"12:45:07 PM","job_company":"4","job_completed_time":null,"cancel_charges":"0","job_accept_lat":"","job_charges":"0","job_accept_lng":"","cancelled_after_time":"0","job_cancelled_time":null,"accepted_date":"2020-01-21 12:45:07","service_name":"Carpenter","service_provider":{"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"0","device_id":"351875092318377","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/https://dev-behtreen-bucket.s3.ap-south-1.amazonaws.com/sp/1575999684_ijaz.jpg","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"0","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"4","status":"1","online":"1","fingerprint":null,"status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":"","is_login":"1","helpline":"0300-9486968"}}]}
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
         * status_message : Active jobs found.
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
        private ArrayList<JobsBean> jobs;

        public ArrayList<JobsBean> getJobs() {
            return jobs;
        }

        public void setJobs(ArrayList<JobsBean> jobs) {
            this.jobs = jobs;
        }

        public static class JobsBean {
            /**
             * id : 2245
             * service_id : 1
             * user_id : 82
             * job_type : instant
             * can_apply : null
             * address : 3, J 3 Block Block J Phase 2 Johar Town, Lahore, Punjab 54000, Pakistan Lahore
             * job_latitude : 31.465663555459198
             * job_longitude : 74.25858099013567
             * request_date : 2020-01-21 12:45:07
             * company_area : 4
             * status : 2
             * user_job_completed : 0
             * job_date : 2020-01-21
             * job_time : 12:45:07 PM
             * job_company : 4
             * job_completed_time : null
             * cancel_charges : 0
             * job_accept_lat :
             * job_charges : 0
             * job_accept_lng :
             * cancelled_after_time : 0
             * job_cancelled_time : null
             * accepted_date : 2020-01-21 12:45:07
             * service_name : Carpenter
             * service_provider : {"id":"18","username":"sp-ibrahim","password":"MTIzNDU2","parent_sp":"0","device_id":"351875092318377","first_name":"Ibrahim","last_name":"Ghani","father_name":"Abdul Wahab Ghani","profile_photo":"https://admin.behtreen.com/uploads/sp/https://dev-behtreen-bucket.s3.ap-south-1.amazonaws.com/sp/1575999684_ijaz.jpg","cnic":"35202-3709136-5","dob":"1968-10-31 00:00:00","phone":"923344192076","address":"315-A Sector B, Eden Lane Villas One Raiwind Road","latitude":"31.4669023","longitude":"74.259221","visit_fee":"0","registration_date":"2019-09-05 02:41:06","service_provider_type":"general","company_id":"4","status":"1","online":"1","fingerprint":null,"status_notes":"New","sp_code":"SP-F5-LHR-18","city":"LHR","country":"PK","is_shop":"0","franchise_notes":null,"shop_name":"","reg_no":"","is_login":"1","helpline":"0300-9486968"}
             */

            private String id;
            private String service_id;
            private String user_id;
            private String job_type;
            private Object can_apply;
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
            private Object job_completed_time;
            private String cancel_charges;
            private String job_accept_lat;
            private String job_charges;
            private String job_accept_lng;
            private String cancelled_after_time;
            private Object job_cancelled_time;
            private String accepted_date;
            private String service_name;
            private ServiceProviderBean service_provider;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public Object getCan_apply() {
                return can_apply;
            }

            public void setCan_apply(Object can_apply) {
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

            public Object getJob_completed_time() {
                return job_completed_time;
            }

            public void setJob_completed_time(Object job_completed_time) {
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

            public Object getJob_cancelled_time() {
                return job_cancelled_time;
            }

            public void setJob_cancelled_time(Object job_cancelled_time) {
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
                 * parent_sp : 0
                 * device_id : 351875092318377
                 * first_name : Ibrahim
                 * last_name : Ghani
                 * father_name : Abdul Wahab Ghani
                 * profile_photo : https://admin.behtreen.com/uploads/sp/https://dev-behtreen-bucket.s3.ap-south-1.amazonaws.com/sp/1575999684_ijaz.jpg
                 * cnic : 35202-3709136-5
                 * dob : 1968-10-31 00:00:00
                 * phone : 923344192076
                 * address : 315-A Sector B, Eden Lane Villas One Raiwind Road
                 * latitude : 31.4669023
                 * longitude : 74.259221
                 * visit_fee : 0
                 * registration_date : 2019-09-05 02:41:06
                 * service_provider_type : general
                 * company_id : 4
                 * status : 1
                 * online : 1
                 * fingerprint : null
                 * status_notes : New
                 * sp_code : SP-F5-LHR-18
                 * city : LHR
                 * country : PK
                 * is_shop : 0
                 * franchise_notes : null
                 * shop_name :
                 * reg_no :
                 * is_login : 1
                 * helpline : 0300-9486968
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
                private Object franchise_notes;
                private String shop_name;
                private String reg_no;
                private String is_login;
                private String helpline;

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

                public String getIs_login() {
                    return is_login;
                }

                public void setIs_login(String is_login) {
                    this.is_login = is_login;
                }

                public String getHelpline() {
                    return helpline;
                }

                public void setHelpline(String helpline) {
                    this.helpline = helpline;
                }
            }
        }
    }
}
