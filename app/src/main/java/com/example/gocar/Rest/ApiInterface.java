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
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    @GET("bookings/customer")
    Call<List<BookingDTO>> BookingAsASeller (@Query("username") String username , @Query("status") String status);
    @Headers({ "Content-Type: application/json"})
    @GET("bookings/seller")
    Call<List<BookingDTO>> BookingOutAsACustomer (@Query("username") String username, @Query("status") String status);

}