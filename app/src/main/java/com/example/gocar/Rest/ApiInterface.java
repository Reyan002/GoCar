package com.example.gocar.Rest;

import com.example.gocar.Pojo.LoginRequest;
import com.example.gocar.Pojo.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    String JSONURL = "";
//
//    @GET("users")
//    Call<Users> getUser(@Query("username") String username);
    @Headers("Content-Type: application/json")
    @POST("users")
      Call<Users> signupUser(@Body Users users);
//    Call<Users> signupUser(@Field("username") String username,
//                        @Field("first_name") String first_name,
//                        @Field("last_name") String last_name,
//                        @Field("password") String password,
//                        @Field("cnic") String cnic,
//                        @Field("longitude") String longitude,
//                        @Field("latitude") String latitude,
//                        @Field("contact") String contact );
//@Headers({"Content-Type: application/json","Accept: application/json"})

@POST("login")
    Call<LoginRequest> userLogin(@Body LoginRequest loginRequest);
}