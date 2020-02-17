package com.example.gocar.Rest;

import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.BookingDTO;
import com.example.gocar.Classes.FcmRequest;
import com.example.gocar.Classes.VehicleRequest;
import com.example.gocar.Pojo.LoginRequest;
import com.example.gocar.Pojo.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {

    String JSONURL = "";
//
//    @GET("users")
//    Call<Users> getUser(@Query("username") String username);
//    @Headers("Content-Type: application/json")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("users")
      Call<Users> signupUser(@Body Users users);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("bookings/response")
    Call<BookingDTO> bookingResponse(@Body BookingDTO bookingDTO,@Query("action") int i);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET("guest/vehicles/filter")
    Call<List<AllActiveVehicle>> getFilter(@Query("lat") String lat,@Query("long") String lon,@Query("dist") String dist,@Query("sc") String sc,@Query("range") String range );


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("login")

    Call<LoginRequest> userLogin(@Body LoginRequest loginRequest);
    @POST("notification/register")
    Call<FcmRequest> token(@Body FcmRequest fcmRequest);

    @Headers({ "Content-Type: application/json"})
 @POST("profile/vehicles")

    Call<VehicleRequest> vehiclePost(@Body VehicleRequest vehicleRequest);
    @Headers({ "Content-Type: application/json"})
    @POST("bookings")

    Call<BookingDTO> bookingRequest(@Body BookingDTO bookingDTO);

  @Headers({ "Content-Type: application/json"})
    @GET("guest/vehicles")

    Call<List<AllActiveVehicle>> guestVehicle( );

    @Headers({ "Content-Type: application/json"})
    @GET("profile/vehicles")
  Call<List<AllActiveVehicle>> myVehicles(@Query("username") String username);

    @Headers({ "Content-Type: application/json"})
    @GET("bookings/seller")
    Call<List<BookingDTO>> BookingAsASeller (@Query("username") String username , @Query("status") String status);
    @Headers({ "Content-Type: application/json"})
    @GET("bookings/customer")
    Call<List<BookingDTO>> BookingOutAsACustomer (@Query("username") String username, @Query("status") String status);

    @Headers({ "Content-Type: application/json"})
    @GET("users")
    Call<Users> getUser(@Query("username") String username);

    @Headers({ "Content-Type: application/json"})
    @PUT("profile")
    Call<Users> updateUser(@Body Users users );


}