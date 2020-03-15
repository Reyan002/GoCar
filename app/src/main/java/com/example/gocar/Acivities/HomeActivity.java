package com.example.gocar.Acivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.HomeFragments.Home;
import com.example.gocar.HomeFragments.MyAccount;
import com.example.gocar.HomeFragments.MyBooking;
import com.example.gocar.HomeFragments.MyCars;
import com.example.gocar.HomeFragments.MyHistory;
import com.example.gocar.Location.AppLocationService;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.example.gocar.SessionManager.SessionManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HomeActivity extends AppCompatActivity {


    private TextView filter,addCar;
    private Boolean check;
    private Switch near,seat,range;

    private Button one,three,seven,ten;

    private Button twos,fours,sixs,sevens;

    private Button half,full,t_thous,f_thous;

    private SessionManager sessionManager;
    private AppLocationService appLocationService;
    private Location gpsLocation;
    private double lat,lon;

    private LinearLayout  linearNear,linearSeat,LinearRange;
    private FusedLocationProviderClient client;
    private RelativeLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        appLocationService = new AppLocationService(
                HomeActivity.this);
        requestPermission();
        client= LocationServices.getFusedLocationProviderClient(this);
        gpsLocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();

          lat=latitude;
          lon=longitude;


        } else {
            showSettingsAlert();
        }


        linearLayout=findViewById(R.id.linear_home);
        filter=findViewById(R.id.filter_btn);

        addCar=findViewById(R.id.add_car_btn);
        sessionManager=new SessionManager(this);
//        Toast.makeText(HomeActivity.this, String.valueOf(lat+"-"+lon), Toast.LENGTH_SHORT).show();
getSupportActionBar().isHideOnContentScrollEnabled();
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_history, R.id.navigation_myaccount,R.id.navigation_mybooking,R.id.navigation_mycars)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//


        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddCarActivity.class));
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.filter_layout, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //NearBy
                //0nekm
                one=dialogView.findViewById(R.id.one_km);
                one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DemoClass.km="1";
                        one.setBackgroundColor(Color.parseColor("#3399ff"));
                        one.setTextColor(Color.parseColor("#ECECF3"));

                        three.setBackgroundColor(Color.parseColor("#ECECF3"));
                        three.setTextColor(Color.parseColor("#3399ff"));

                        seven.setBackgroundColor(Color.parseColor("#ECECF3"));
                        seven.setTextColor(Color.parseColor("#3399ff"));

                        ten.setBackgroundColor(Color.parseColor("#ECECF3"));
                        ten.setTextColor(Color.parseColor("#3399ff"));

                    }
                });
                three=dialogView.findViewById(R.id.two_km);
                three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.km="3";
                        three.setBackgroundColor(Color.parseColor("#3399ff"));
                        three.setTextColor(Color.parseColor("#ECECF3"));

                        one.setBackgroundColor(Color.parseColor("#ECECF3"));
                        one.setTextColor(Color.parseColor("#3399ff"));

                        seven.setBackgroundColor(Color.parseColor("#ECECF3"));
                        seven.setTextColor(Color.parseColor("#3399ff"));

                        ten.setBackgroundColor(Color.parseColor("#ECECF3"));
                        ten.setTextColor(Color.parseColor("#3399ff"));

                    }
                });
                seven=dialogView.findViewById(R.id.three_km);
                seven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.km="7";
                        seven.setBackgroundColor(Color.parseColor("#3399ff"));
                        seven.setTextColor(Color.parseColor("#ECECF3"));

                        three.setBackgroundColor(Color.parseColor("#ECECF3"));
                        three.setTextColor(Color.parseColor("#3399ff"));

                        one.setBackgroundColor(Color.parseColor("#ECECF3"));
                        one.setTextColor(Color.parseColor("#3399ff"));

                        ten.setBackgroundColor(Color.parseColor("#ECECF3"));
                        ten.setTextColor(Color.parseColor("#3399ff"));
                    }
                });
                ten=dialogView.findViewById(R.id.four_km);
                ten.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.km="10";
                        ten.setBackgroundColor(Color.parseColor("#3399ff"));
                        ten.setTextColor(Color.parseColor("#ECECF3"));

                        three.setBackgroundColor(Color.parseColor("#ECECF3"));
                        three.setTextColor(Color.parseColor("#3399ff"));

                        seven.setBackgroundColor(Color.parseColor("#ECECF3"));
                        seven.setTextColor(Color.parseColor("#3399ff"));

                        one.setBackgroundColor(Color.parseColor("#ECECF3"));
                        one.setTextColor(Color.parseColor("#3399ff"));

                    }
                });


                twos=dialogView.findViewById(R.id.two_seats);
                fours=dialogView.findViewById(R.id.four_seats);
                sixs=dialogView.findViewById(R.id.six_seats);
                sevens=dialogView.findViewById(R.id.seven_seats);
                twos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.cap="2";
                        twos.setBackgroundColor(Color.parseColor("#3399ff"));
                        twos.setTextColor(Color.parseColor("#ECECF3"));

                        fours.setBackgroundColor(Color.parseColor("#ECECF3"));
                        fours.setTextColor(Color.parseColor("#3399ff"));

                        sixs.setBackgroundColor(Color.parseColor("#ECECF3"));
                        sixs.setTextColor(Color.parseColor("#3399ff"));

                        sevens.setBackgroundColor(Color.parseColor("#ECECF3"));
                        sevens.setTextColor(Color.parseColor("#3399ff"));
                    }
                });
                fours.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DemoClass.cap="4";
                        fours.setBackgroundColor(Color.parseColor("#3399ff"));
                        fours.setTextColor(Color.parseColor("#ECECF3"));

                        twos.setBackgroundColor(Color.parseColor("#ECECF3"));
                        twos.setTextColor(Color.parseColor("#3399ff"));

                        sixs.setBackgroundColor(Color.parseColor("#ECECF3"));
                        sixs.setTextColor(Color.parseColor("#3399ff"));

                        sevens.setBackgroundColor(Color.parseColor("#ECECF3"));
                        sevens.setTextColor(Color.parseColor("#3399ff"));
                    }
                });
                sixs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.cap="6";
                        sixs.setBackgroundColor(Color.parseColor("#3399ff"));
                        sixs.setTextColor(Color.parseColor("#ECECF3"));

                        fours.setBackgroundColor(Color.parseColor("#ECECF3"));
                        fours.setTextColor(Color.parseColor("#3399ff"));

                        twos.setBackgroundColor(Color.parseColor("#ECECF3"));
                        twos.setTextColor(Color.parseColor("#3399ff"));

                        sevens.setBackgroundColor(Color.parseColor("#ECECF3"));
                        sevens.setTextColor(Color.parseColor("#3399ff"));
                    }
                });
                sevens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.cap="7";
                        sevens.setBackgroundColor(Color.parseColor("#3399ff"));
                        sevens.setTextColor(Color.parseColor("#ECECF3"));

                        fours.setBackgroundColor(Color.parseColor("#ECECF3"));
                        fours.setTextColor(Color.parseColor("#3399ff"));

                        sixs.setBackgroundColor(Color.parseColor("#ECECF3"));
                        sixs.setTextColor(Color.parseColor("#3399ff"));

                        twos.setBackgroundColor(Color.parseColor("#ECECF3"));
                        twos.setTextColor(Color.parseColor("#3399ff"));
                    }
                });

                half=dialogView.findViewById(R.id.five_ph);
                full=dialogView.findViewById(R.id.thous_ph);
                t_thous=dialogView.findViewById(R.id.two_thous_ph);
                f_thous=dialogView.findViewById(R.id.five_thous_ph);
                half.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.rph="500";
                        half.setBackgroundColor(Color.parseColor("#3399ff"));
                        half.setTextColor(Color.parseColor("#ECECF3"));

                        full.setBackgroundColor(Color.parseColor("#ECECF3"));
                        full.setTextColor(Color.parseColor("#3399ff"));

                        t_thous.setBackgroundColor(Color.parseColor("#ECECF3"));
                        t_thous.setTextColor(Color.parseColor("#3399ff"));

                        f_thous.setBackgroundColor(Color.parseColor("#ECECF3"));
                        f_thous.setTextColor(Color.parseColor("#3399ff"));
                    }
                });
                full.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.rph="1000";
                        full.setBackgroundColor(Color.parseColor("#3399ff"));
                        full.setTextColor(Color.parseColor("#ECECF3"));

                        half.setBackgroundColor(Color.parseColor("#ECECF3"));
                        half.setTextColor(Color.parseColor("#3399ff"));

                        t_thous.setBackgroundColor(Color.parseColor("#ECECF3"));
                        t_thous.setTextColor(Color.parseColor("#3399ff"));

                        f_thous.setBackgroundColor(Color.parseColor("#ECECF3"));
                        f_thous.setTextColor(Color.parseColor("#3399ff"));
                    }
                });
                t_thous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.rph="2000";
                        t_thous.setBackgroundColor(Color.parseColor("#3399ff"));
                        t_thous.setTextColor(Color.parseColor("#ECECF3"));

                        full.setBackgroundColor(Color.parseColor("#ECECF3"));
                        full.setTextColor(Color.parseColor("#3399ff"));

                        half.setBackgroundColor(Color.parseColor("#ECECF3"));
                        half.setTextColor(Color.parseColor("#3399ff"));

                        f_thous.setBackgroundColor(Color.parseColor("#ECECF3"));
                        f_thous.setTextColor(Color.parseColor("#3399ff"));
                    }
                });
                f_thous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemoClass.rph="5000";
                        f_thous.setBackgroundColor(Color.parseColor("#3399ff"));
                        f_thous.setTextColor(Color.parseColor("#ECECF3"));

                        full.setBackgroundColor(Color.parseColor("#ECECF3"));
                        full.setTextColor(Color.parseColor("#3399ff"));

                        t_thous.setBackgroundColor(Color.parseColor("#ECECF3"));
                        t_thous.setTextColor(Color.parseColor("#3399ff"));

                        half.setBackgroundColor(Color.parseColor("#ECECF3"));
                        half.setTextColor(Color.parseColor("#3399ff"));
                    }
                });

                linearNear=dialogView.findViewById(R.id.linar__near);
                LinearRange=dialogView.findViewById(R.id.linear_price);
                linearSeat=dialogView.findViewById(R.id.linear_seat);

                near=dialogView.findViewById(R.id.near_switch);
                seat=dialogView.findViewById(R.id.seat_switch);
                range=dialogView.findViewById(R.id.price_switch);

                TextView btn_ok=dialogView.findViewById(R.id.buttonOk);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                         getFiltered();
                         startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                    }
                });

                TextView btn_cancle=dialogView.findViewById(R.id.buttonCanle);
                btn_cancle.setOnClickListener(v1 -> alertDialog.dismiss());
                near.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if(isChecked){
                        linearNear.setVisibility(View.VISIBLE);
                    }
                    else{
                        linearNear.setVisibility(View.GONE);
                    }
                });
                seat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            linearSeat.setVisibility(View.VISIBLE);
                        }
                        else{
                            linearSeat.setVisibility(View.GONE);
                        }
                    }

                });

                range.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if(isChecked){
                        LinearRange.setVisibility(View.VISIBLE);
                    }
                    else{
                        LinearRange.setVisibility(View.GONE);
                    }

                });


            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Home()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        menu.add(0, 0, 0, "History").setIcon(R.drawable.ic_account_circle_black_24dp)
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        menu.add(0, 1, 0, "Settings").setIcon(R.drawable.ic_account_circle_black_24dp)
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        getMenuInflater().inflate(R.menu.home_toolbar_menu,menu);

        MenuItem logout=menu.findItem(R.id.navigation_logout);
        if(sessionManager.isLoggedIn()){

            logout.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.navigation_about_us:
//                Toast.makeText(this, "You Clicked About Us", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_logout:
                sessionManager.logoutUser();
                item.setVisible(false);
//                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectFragment = null;
                    switch (menuItem.getItemId())
                    {
                        case R.id.navigation_home:
                            filter.setVisibility(View.VISIBLE);
                            addCar.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.VISIBLE);
                            selectFragment= new Home();

                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectFragment).commit();

                            break;
                        case R.id.navigation_myaccount:
                            linearLayout.setVisibility(View.GONE);

                            selectFragment=new MyAccount();
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectFragment).commit();
                            break;
                        case R.id.navigation_mycars:
                            filter.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.VISIBLE);

                            if( sessionManager.isLoggedIn())
                            {
                                linearLayout.setVisibility(View.VISIBLE);
                                addCar.setVisibility(View.VISIBLE);
                            }

                            selectFragment= new MyCars();
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectFragment).commit();
                            break;
                        case R.id.navigation_history:
                            linearLayout.setVisibility(View.GONE);
//                            selectFragment= new MyBooking();
                            DemoClass.type="In";
                            DemoClass.typeA="";
                            startActivity(new Intent(HomeActivity.this,BookingIn.class));
                            break;
                        case R.id.navigation_mybooking:
                            linearLayout.setVisibility(View.GONE);
//                            selectFragment= new MyHistory();
                            DemoClass.type="Out";
                            startActivity(new Intent(HomeActivity.this,BookingOut.class));
                            break;


                    }

                    return true;
                }
            };

    @Override
    protected void onStart() {
        check=false;
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if(!check){
            Toast.makeText(this,"Press one more time to exit",Toast.LENGTH_SHORT).show();
            check=true;
        }
        else {
            DemoClass.lst=null;
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);}

    }

    public void getFiltered(){

//        Toast.makeText(HomeActivity.this, String.valueOf(lat+"-"+lon), Toast.LENGTH_SHORT).show();
        ApiInterface api= ApiUtils.getAPIService();

        Call<List<AllActiveVehicle> > call=api.getFilter(String.valueOf(lat),String.valueOf(lon), DemoClass.km,DemoClass.cap,DemoClass.rph);

        call.enqueue(new Callback<List<AllActiveVehicle>>() {
            @Override
            public void onResponse(Call<List<AllActiveVehicle>> call, Response<List<AllActiveVehicle>> response) {
                if(response.isSuccessful()){
                    DemoClass.lst=response.body();
                    DemoClass.filter=true;
//                    Toast.makeText(HomeActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                    DemoClass.km="";
                    DemoClass.cap="";
                    DemoClass.rph="";
                }
                else{
                    DemoClass.lst.clear();
                }
            }

            @Override
            public void onFailure(Call<List<AllActiveVehicle>> call, Throwable t) {

                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                HomeActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        HomeActivity.this.startActivity(intent);
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


}
