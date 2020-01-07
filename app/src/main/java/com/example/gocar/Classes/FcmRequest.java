package com.example.gocar.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FcmRequest {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("token")
    @Expose
    private String token;

    public FcmRequest(String username, String password) {
        this.username = username;
        this.token = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return token;
    }
    public void setPassword(String password) {
        this.token = password;
    }

}
