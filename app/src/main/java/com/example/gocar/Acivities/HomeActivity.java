package com.example.gocar.Acivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import com.example.gocar.HomeFragments.Home;
import com.example.gocar.HomeFragments.MyAccount;
import com.example.gocar.HomeFragments.MyBooking;
import com.example.gocar.HomeFragments.MyCars;
import com.example.gocar.HomeFragments.MyHistory;
import com.example.gocar.R;
import com.example.gocar.SessionManager.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    private TextView filter,addCar;
    private Boolean check;
    private Switch near,seat,range;

    private Button one,three,seven,ten;

    private SessionManager sessionManager;


    private LinearLayout  linearNear,linearSeat,LinearRange;

    private RelativeLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linearLayout=findViewById(R.id.linear_home);
        filter=findViewById(R.id.filter_btn);

        addCar=findViewById(R.id.add_car_btn);
        sessionManager=new SessionManager(this);
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
                        Toast.makeText(getApplicationContext(),"You Click Ok",Toast.LENGTH_SHORT).show();
                    }
                });

                near.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            linearNear.setVisibility(View.VISIBLE);
                        }
                        else{
                            linearNear.setVisibility(View.GONE);
                        }
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

                range.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            LinearRange.setVisibility(View.VISIBLE);
                        }
                        else{
                            LinearRange.setVisibility(View.GONE);
                        }

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
                Toast.makeText(this, "You Clicked About Us", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_logout:
                sessionManager.logoutUser();
                item.setVisible(false);
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
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
                            break;
                        case R.id.navigation_myaccount:
                            linearLayout.setVisibility(View.GONE);

                            selectFragment=new MyAccount();
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
                            break;
                        case R.id.navigation_history:
                            linearLayout.setVisibility(View.GONE);

                            selectFragment= new MyHistory();
                            break;
                        case R.id.navigation_mybooking:
                            linearLayout.setVisibility(View.GONE);

                            selectFragment= new MyBooking();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectFragment).commit();
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
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);}

    }



}
