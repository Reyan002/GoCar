package com.example.gocar.Pojo;
 public class Users {


        private String username;
        private String first_name;
        private String last_name;
        private String password;
        private String cnic;
        private String longitude;
        private String latitude;
        private String contact;
        private String message;

     public void setMessage(String message) {
         this.message = message;
     }

     public String getMessage() {
         return message;
     }

     public Users(String username, String first_name, String last_name, String password, String cnic, String longitude, String latitude, String contact) {
         this.username = username;
         this.first_name = first_name;
         this.last_name = last_name;
         this.password = password;
         this.cnic = cnic;
         this.longitude = longitude;
         this.latitude = latitude;
         this.contact = contact;
     }

     public String getUsername() {
            return username;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getPassword() {
            return password;
        }

        public String getCnic() {
            return cnic;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getContact() {
            return contact;
        }

        // Setter Methods



        public void setUsername(String username) {
            this.username = username;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setCnic(String cnic) {
            this.cnic = cnic;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
}
