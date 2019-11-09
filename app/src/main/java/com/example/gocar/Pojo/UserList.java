package com.example.gocar.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserList {

    @SerializedName("ServicesList")
    @Expose
    private ArrayList<Users> busines = null;

    public ArrayList<Users> getEmployeeNew() {
        return busines;
    }

    public void setEmployee(ArrayList<Users> busines) {
        this.busines = busines;
    }
}
