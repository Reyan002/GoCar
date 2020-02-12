package com.example.gocar.Pojo;
 public class Users {


     private String username;
     private String first_name;
     private String last_name;
     private String password;
     private String cnic;
     private Float longitude;
     private Float latitude;
     private String address;
     private String contact;
     private String dp;

     public Users(String username, String first_name, String last_name, String password, String cnic, Float longitude, Float latitude, String address, String contact, String dp) {
         this.username = username;
         this.first_name = first_name;
         this.last_name = last_name;
         this.password = password;
         this.cnic = cnic;
         this.longitude = longitude;
         this.latitude = latitude;
         this.address = address;
         this.contact = contact;
         this.dp = dp;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
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

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public String getCnic() {
         return cnic;
     }

     public void setCnic(String cnic) {
         this.cnic = cnic;
     }

     public Float getLongitude() {
         return longitude;
     }

     public void setLongitude(Float longitude) {
         this.longitude = longitude;
     }

     public Float getLatitude() {
         return latitude;
     }

     public void setLatitude(Float latitude) {
         this.latitude = latitude;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getContact() {
         return contact;
     }

     public void setContact(String contact) {
         this.contact = contact;
     }

     public String getDp() {
         return dp;
     }

     public void setDp(String dp) {
         this.dp = dp;
     }
 }
