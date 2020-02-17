package com.example.gocar.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.gocar.Acivities.LoginActivity;
import com.example.gocar.BookingFragment.ApprovedIn;
import com.example.gocar.BookingFragment.ApprovedOut;
import com.example.gocar.BookingFragment.PendingIn;
import com.example.gocar.BookingFragment.PendingOut;
import com.example.gocar.R;
import com.example.gocar.SessionManager.SessionManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyBooking extends Fragment {
  private Button login;
  private LinearLayout linearLayout;
  private SessionManager sessionManager;
  private Toolbar toolbar;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView= inflater.inflate(R.layout.mybooking_fragment,container,false);






    viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
     setupViewPager(viewPager);

    tabLayout = rootView.findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
    return rootView;
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());

    viewPager.setAdapter(adapter);
  }



  class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new PendingIn();
        case 1:
        default:
          return new ApprovedIn();
      }
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "Pending";
        case 1:
        default:
          return "Approved";
      }
    }
  }
}