package com.example.gocar.Acivities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gocar.BookingFragment.ApprovedIn;
import com.example.gocar.BookingFragment.ApprovedOut;
import com.example.gocar.BookingFragment.PendingIn;
import com.example.gocar.BookingFragment.PendingOut;
import com.example.gocar.R;
import com.example.gocar.SessionManager.SessionManager;
import com.google.android.material.tabs.TabLayout;

public class BookingOut extends AppCompatActivity {

    private FrameLayout simpleFrameLayout;
    private TabLayout tabLayout;
    private SessionManager sessionManager;

    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_out  );
        simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout1);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout1);
        sessionManager = new SessionManager(this);

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
                        fragment = new PendingOut();
                        break;
                    case 1:
                        fragment = new ApprovedOut();
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout1, fragment);
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
