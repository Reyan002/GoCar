package com.example.gocar.Acivities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.hotspot2.ConfigParser;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gocar.Location.AppLocationService;
import com.example.gocar.Location.LocationAddress;
import com.example.gocar.Pojo.Users;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.example.gocar.Rest.RetrofitClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SignUpActivity extends AppCompatActivity {

    private EditText userName,Phone,Pass,Conpass,cnic;
    private Button location,locationAddres;
    private TextView locationtext;
    private AppLocationService appLocationService;
   private Location gpsLocation;
   private String lat,lon;
   private String location_text;

   private ApiInterface api;
    private FloatingActionButton register;

        private FusedLocationProviderClient client;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);


            appLocationService = new AppLocationService(
                    SignUpActivity.this);

            initialize();
            requestPermission();
            Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://72.255.61.208:9001/api/v1/")
                    .baseUrl("http://192.168.0.110:9001/api/v1/")

                .addConverterFactory(GsonConverterFactory.create( ))
                .build();
        api=retrofit.create(ApiInterface.class);



        client= LocationServices.getFusedLocationProviderClient(this);
        location.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                  gpsLocation = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);
               if (gpsLocation != null) {
                double latitude = gpsLocation.getLatitude();
                double longitude = gpsLocation.getLongitude();

                lat=String.valueOf(latitude);
                lon=String.valueOf(longitude);

//                   locationtext.setText(getAddress(getApplicationContext(),latitude,longitude));
                locationtext.setText(getCompleteAddressString(latitude,longitude));
                   location_text=locationtext.getText().toString();
//                   try {
//                       Geocoder geo = new Geocoder(SignUpActivity.this.getApplicationContext(), Locale.getDefault());
//                       List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
//                       if (addresses.isEmpty()) {
//                           locationtext.setText("Waiting for Location");
//                       } else {
//                           if (addresses.size() > 0) {
//                               locationtext.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() + ", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
//                               //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
//                           location.setEnabled(false);
//                           }
//                       }
//                   } catch (Exception e) {
//                       e.printStackTrace(); // getFromLocation() may sometimes fail
//                   }

                //                    String result = "Latitude: " + gpsLocation.getLatitude() +
//                            " Longitude: " + gpsLocation.getLongitude();
//                    locationtext.setText(result);
//                    LocationAddress locationAddress = new LocationAddress();
//                    locationAddress.getAddressFromLocation(latitude, longitude,
//                            getApplicationContext(), new GeocoderHandler());
//                    location.setText("Location Picked");
//
                } else {
                    showSettingsAlert();
                }

            }
        });


        cnic.addTextChangedListener( new TextWatcher() {
            boolean isEdiging;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }


            @Override
            public void afterTextChanged(Editable s) {
                if(isEdiging) return;
                isEdiging = true;
                // removing old dashes
                StringBuilder sb = new StringBuilder();
                sb.append(s.toString().replace("-", ""));

                if (sb.length()> 5)
                    sb.insert(5, "-");
                if (sb.length()> 13)
                    sb.insert(13, "-");
                if(sb.length()> 15)
                    sb.delete(15, sb.length());

                s.replace(0, s.length(), sb.toString());
                isEdiging = false;
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(userName)) {
                    userName.setError("Name is Required");
                }

                if (isEmpty(Pass)) {
                    Pass.setError("Password Required");
                }
                if (isEmpty(Conpass)) {
                    Conpass.setError("Confirm Password Required");
                }
                else {

                    //overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    postToSignUp(userName.getText().toString(),"","",Pass.getText().toString(),cnic.getText().toString(),lon,lat,"",location_text);
                    Toast.makeText(SignUpActivity.this, "Wellcom New User", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void initialize(){
        locationtext=findViewById(R.id.locationtext);
        cnic=findViewById(R.id.register_CNIC);
        userName=findViewById(R.id.register_username);

        Pass=findViewById(R.id.register_userPass);
        Conpass =findViewById(R.id.register_userPassConfirm);
        register=findViewById(R.id.registerUser);
        location=findViewById(R.id.register_userLocation);
        }
        boolean isEmail(EditText text){
            CharSequence email=text.getText().toString();
            return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
        }
        boolean isPhone(EditText number){
        CharSequence text=number.getText().toString();
        return (!TextUtils.isEmpty(text)&&Patterns.PHONE.matcher(text).matches());
        }
        boolean isEmpty(EditText text){
            return TextUtils.isEmpty(text.getText().toString());
        }

        public void checkDataEnterned() {
            if (isEmpty(userName)) {
                userName.setError("Name is Required");
            }
            if (isPhone(Phone)) {
                Phone.setError("Please Enter Valid Phone Number");
            }
            if (isEmpty(Pass)) {
                Pass.setError("Password Required");
            }
            if (isEmpty(Conpass)) {
                Conpass.setError("Confirm Password Required");
            }
        }

        public void requestPermission(){
            ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
        }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                SignUpActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        SignUpActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }


    public String getAddress(Context context, double LATITUDE, double LONGITUDE) {

        String strAdd="";
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {



                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                strAdd=address+","+city+","+state+","+country+","+postalCode+","+knownName;
//                Log.d(TAG, "getAddress:  address" + address);
//                Log.d(TAG, "getAddress:  city" + city);
//                Log.d(TAG, "getAddress:  state" + state);
//                Log.d(TAG, "getAddress:  postalCode" + postalCode);
//                Log.d(TAG, "getAddress:  knownName" + knownName);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strAdd;
    }


    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction", "Canont get Address!");
        }
        return strAdd;
    }

    public void postToSignUp(String userName,String FirstName,String LastName,String Password,String CNIC,String lon,String lat,String Contact ,String address){


        Call<Users> call = api.signupUser(new Users(userName,FirstName,LastName,Password,CNIC,lon,lat,Contact ,address));
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.code()==400){
                    //user credential errors
                    Toast.makeText(SignUpActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                }
                if(response.code()==409){
                    //already exist
                    Toast.makeText(SignUpActivity.this, response.message(), Toast.LENGTH_SHORT).show();


                }

                if( response.code()==200){
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                }
                else{
                    Toast.makeText(SignUpActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                Toast.makeText(SignUpActivity.this,  t.getMessage()   , Toast.LENGTH_SHORT).show();

            }
        });

    }
}
