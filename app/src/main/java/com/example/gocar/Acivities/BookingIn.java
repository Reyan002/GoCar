package com.example.gocar.Acivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gocar.BookingFragment.ApprovedIn;
import com.example.gocar.BookingFragment.PendingIn;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;
import com.example.gocar.SessionManager.SessionManager;
import com.google.android.material.tabs.TabLayout;

public class BookingIn extends AppCompatActivity {
   private  FrameLayout simpleFrameLayout;
   private RelativeLayout relativeLayout;
    private TabLayout tabLayout;
    private Button login;
    private LinearLayout linearLayout;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_in);

        sessionManager=new SessionManager(this);
        simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        linearLayout= findViewById(R.id.loginLinearLayoutMyBooking);
        if(!sessionManager.isLoggedIn()){
            linearLayout.setVisibility(View.VISIBLE);
        }
        login= findViewById(R.id.loginbtnbooking);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingIn.this, LoginActivity.class));
            }
        });
        if(sessionManager.isLoggedIn()){

            simpleFrameLayout.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
        }


// get the reference of FrameLayout and TabLayout

// Create a new Tab named "First"
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Pending"); // set the Text for the first Tab
//        firstTab.setIcon(R.drawable.ic_launcher); // set an icon for the
// first tab
        tabLayout.addTab(firstTab); // add  the tab at in the TabLayout
// Create a new Tab named "Second"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Approved"); // set the Text for the second Tab
//        secondTab.setIcon(R.drawable.ic_launcher); // set an icon for the second tab
        tabLayout.addTab(secondTab); // add  the tab  in the TabLayout

// perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new PendingIn();
                        DemoClass.typeA="";
                        break;
                    case 1:
                        fragment = new ApprovedIn();
                        DemoClass.typeA="app_in";
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
