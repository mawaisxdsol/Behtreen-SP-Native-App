package com.behtreen.spapp.model;

public class PageDataModel {

    /**
     * status : {"status":true,"status_code":200,"status_message":"Detail found.","error_type":"","access_token":""}
     * data : {"data":{"id":"6","title":"Frequently Ask Question","content":"<h3>What if I&rsquo;m not available during one of the delivery times?<\/h3>\r\n\r\n<p>No worries! If you&rsquo;ll be home relatively soon, we can leave it at your door in a special cooler, or schedule a separate delivery time. Contact us at (512) 787-2031 in Austin or (312) 765-0413 for any special requests or questions.<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<h3>Should I warm up the products in the microwave?<\/h3>\r\n\r\n<p>Nope! That would defeat the purpose of the species-appropriate diet. Don&rsquo;t worry, your pup will be more than happy eating our premium, all-natural dog food at the temperature it&rsquo;s delivered.<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<h3>How long will the raw dog food last? Should I freeze it?<\/h3>\r\n\r\n<p>We recommend no more than 3 to 4 days. Freezing is ok, but not preferred for the most enjoyable raw-food experience for your pet.<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<h3>Is this diet good for any dog, or are there any special considerations?<\/h3>\r\n\r\n<p>The Chow Hound raw food diet is a healthy, balanced, species-appropriate diet that is good for all dogs. Of course, always reach out to your vet for advice if you are concerned about a sensitive stomach, diabetes, or other prior food-related issue.<\/p>\r\n","slug":"faqs","created_date":"2018-10-08 17:16:24"}}
     */

    private StatusBean status;
    private DataBeanX data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * status : true
         * status_code : 200
         * status_message : Detail found.
         * error_type :
         * access_token :
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

    public static class DataBeanX {
        /**
         * data : {"id":"6","title":"Frequently Ask Question","content":"<h3>What if I&rsquo;m not available during one of the delivery times?<\/h3>\r\n\r\n<p>No worries! If you&rsquo;ll be home relatively soon, we can leave it at your door in a special cooler, or schedule a separate delivery time. Contact us at (512) 787-2031 in Austin or (312) 765-0413 for any special requests or questions.<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<h3>Should I warm up the products in the microwave?<\/h3>\r\n\r\n<p>Nope! That would defeat the purpose of the species-appropriate diet. Don&rsquo;t worry, your pup will be more than happy eating our premium, all-natural dog food at the temperature it&rsquo;s delivered.<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<h3>How long will the raw dog food last? Should I freeze it?<\/h3>\r\n\r\n<p>We recommend no more than 3 to 4 days. Freezing is ok, but not preferred for the most enjoyable raw-food experience for your pet.<\/p>\r\n\r\n<p>&nbsp;<\/p>\r\n\r\n<h3>Is this diet good for any dog, or are there any special considerations?<\/h3>\r\n\r\n<p>The Chow Hound raw food diet is a healthy, balanced, species-appropriate diet that is good for all dogs. Of course, always reach out to your vet for advice if you are concerned about a sensitive stomach, diabetes, or other prior food-related issue.<\/p>\r\n","slug":"faqs","created_date":"2018-10-08 17:16:24"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 6
             * title : Frequently Ask Question
             * content : <h3>What if I&rsquo;m not available during one of the delivery times?</h3>

             <p>No worries! If you&rsquo;ll be home relatively soon, we can leave it at your door in a special cooler, or schedule a separate delivery time. Contact us at (512) 787-2031 in Austin or (312) 765-0413 for any special requests or questions.</p>

             <p>&nbsp;</p>

             <h3>Should I warm up the products in the microwave?</h3>

             <p>Nope! That would defeat the purpose of the species-appropriate diet. Don&rsquo;t worry, your pup will be more than happy eating our premium, all-natural dog food at the temperature it&rsquo;s delivered.</p>

             <p>&nbsp;</p>

             <h3>How long will the raw dog food last? Should I freeze it?</h3>

             <p>We recommend no more than 3 to 4 days. Freezing is ok, but not preferred for the most enjoyable raw-food experience for your pet.</p>

             <p>&nbsp;</p>

             <h3>Is this diet good for any dog, or are there any special considerations?</h3>

             <p>The Chow Hound raw food diet is a healthy, balanced, species-appropriate diet that is good for all dogs. Of course, always reach out to your vet for advice if you are concerned about a sensitive stomach, diabetes, or other prior food-related issue.</p>
             * slug : faqs
             * created_date : 2018-10-08 17:16:24
             */

            private String id;
            private String title;
            private String content;
            private String slug;
            private String created_date;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getCreated_date() {
                return created_date;
            }

            public void setCreated_date(String created_date) {
                this.created_date = created_date;
            }
        }
    }
}
