package com.example.gocar.Classes;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.gocar.Acivities.HomeActivity;
import com.example.gocar.Acivities.LoginActivity;
import com.example.gocar.Acivities.SignUpActivity;
import com.example.gocar.BookingFragment.ApprovedOut;
import com.example.gocar.BookingFragment.PendingIn;
import com.example.gocar.HomeFragments.MyBooking;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        // ...


        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

           Map<String, String> data= remoteMessage.getData();
           String type=data.get("type");
//            Toast.makeText(this, type, Toast.LENGTH_SHORT).show();
           switch (type){
               case "receive":
                   //startActivity(new Intent(this, LoginActivity.class));

                   break;
               case "accept":
                   //startActivity(new Intent(this, SignUpActivity.class));
                   break;
               case "decline":
                   startActivity(new Intent(this, HomeActivity.class));
                   break;



               default:
                   Toast.makeText(this, "Hello G", Toast.LENGTH_SHORT).show();

           }

            NotificationCompat.Builder notifyBuilder=new NotificationCompat.Builder(this);



            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(0,notifyBuilder.build()) ;




        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }


    }
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        tokenG(token);
    }


    public void tokenG(String token){


        ApiInterface api= ApiUtils.getAPIService();

        if(DemoClass.pnumber!=null&&DemoClass.pnumber.isEmpty()) {
            Call<FcmRequest> call = api.token(new FcmRequest(DemoClass.pnumber, token));
            call.enqueue(new Callback<FcmRequest>() {
                @Override
                public void onResponse(Call<FcmRequest> call, Response<FcmRequest> response) {
                    if (response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Error NOt", Toast.LENGTH_SHORT).show();
                    }
//                Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<FcmRequest> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
