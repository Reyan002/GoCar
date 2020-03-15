package com.example.gocar.Acivities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.gocar.Classes.DemoClass;
import com.example.gocar.Location.AppLocationService;
import com.example.gocar.Pojo.Users;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class UpdateProfile extends AppCompatActivity {

    private static final int galleryPic=1;
    private CircleImageView profile_image_view;
    private TextView close_btn,update_btn,profile_change_btn,phone_ed;
    private EditText fname_ed,cnic,last,contact ;
    private Uri imageUri;
    private String Url="";
    private StorageReference storageReferencePicture;
    private String checker="";
    private StorageTask uploadTask;
    private String myUrl;
    private Button location;
    private TextView locationtext,lastname ;
    private AppLocationService appLocationService;
    private Location gpsLocation;
    private double lat,lon;
    private String location_text;
    private ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        appLocationService = new AppLocationService(
                UpdateProfile.this);


        requestPermission();
        location=findViewById(R.id.userLocation);
        storageReferencePicture = FirebaseStorage.getInstance().getReference().child("Profile pictures");
        profile_image_view = (CircleImageView) findViewById(R.id.setting_profile_image_change);
        close_btn = (TextView)findViewById(R.id.setting_close);
        update_btn = (TextView)findViewById(R.id.setting_update);
        locationtext = (TextView)findViewById(R.id.ulocationtext);
        profile_change_btn = (TextView)findViewById(R.id.setting_profile_image_change_btn);
//        phone_ed = (TextView)findViewById(R.id.setting_phone_number);
        fname_ed = (EditText)findViewById(R.id.setting_full_name);
        contact = (EditText)findViewById(R.id.setting_contact);
        last = (EditText)findViewById(R.id.setting_full_name_last);
        cnic = (EditText)findViewById(R.id.cnic);
        api= ApiUtils.getAPIService();
        location.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                gpsLocation = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);
                if (gpsLocation != null) {
                    double latitude = gpsLocation.getLatitude();
                    double longitude = gpsLocation.getLongitude();

                    lat=latitude;
                    lon=longitude;

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


        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

uploadImage();

            }
        });

        profile_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker="clicked";
                openGallery();
            }
        });
    }
    private void openGallery() {
        Intent galleryInten = new Intent();
        galleryInten.setAction(Intent.ACTION_GET_CONTENT);
        galleryInten.setType("image/*");

        startActivityForResult(galleryInten,galleryPic);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data);
        if(requestCode==galleryPic&&resultCode==RESULT_OK&&data!=null){
            imageUri=data.getData();
            profile_image_view.setImageURI(imageUri);
        }
    }



    private void userInfoSaved()
    {
        if(TextUtils.isEmpty(fname_ed.getText().toString())){
            Toast.makeText(this,"Name is Mandatory",Toast.LENGTH_SHORT).show();
        }
//        else  if(TextUtils.isEmpty(phone_ed.getText().toString())){
//            Toast.makeText(this,"Phone is Mandatory",Toast.LENGTH_SHORT).show();
//        }
        else if(TextUtils.isEmpty(cnic.getText().toString())){
            Toast.makeText(this,"cnic is Mandatory",Toast.LENGTH_SHORT).show();
        }
        else if(checker.equals("clicked")){

            uploadImage();

        }

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
    private void uploadImage() {
        final ProgressDialog loadingBar=new ProgressDialog(this);
        loadingBar.setTitle("Update Profile");
        loadingBar.setMessage("Please Wait, while we are updating your Account info");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        if(imageUri!=null){
            final StorageReference fileRef=storageReferencePicture
                    .child(DemoClass.pnumber +".jpg");
            uploadTask=fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();

                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUrl=task.getResult();

                        myUrl = downloadUrl.toString();

                        loadingBar.dismiss();
                        updateeReequest(DemoClass.pnumber,fname_ed.getText().toString(),last.getText().toString()
                                ,"",cnic.getText().toString(),lon ,lat,contact.getText().toString(),
                                location_text,myUrl);

                        startActivity(new Intent(UpdateProfile.this,HomeActivity.class));

                        Toast.makeText(UpdateProfile.this,"Profile Info Updated Succesfully",Toast.LENGTH_SHORT).show();

                        finish();
                    }
                    else {
                        loadingBar.dismiss();
                        Toast.makeText(UpdateProfile.this,"Error:",Toast.LENGTH_SHORT).show();

                    }
                }
            });//onFailure
        }
        else{
            Toast.makeText(UpdateProfile.this,"Image Is not Selected",Toast.LENGTH_SHORT).show();

        }

    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                UpdateProfile.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        UpdateProfile.this.startActivity(intent);
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
    public void updateeReequest(String userName,String FirstName,String LastName,String Password,String CNIC,double lon,double lat,String Contact ,String address,String dp){
        Call<Users> call = api.updateUser(new Users(userName,FirstName,LastName,Password,CNIC,lon,lat,Contact ,address,dp));

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(UpdateProfile.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(UpdateProfile.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(UpdateProfile.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
