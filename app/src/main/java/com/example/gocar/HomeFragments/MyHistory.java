package com.example.gocar.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.gocar.BookingFragment.ApprovedIn;
import com.example.gocar.BookingFragment.ApprovedOut;
import com.example.gocar.BookingFragment.PendingIn;
import com.example.gocar.BookingFragment.PendingOut;
import com.example.gocar.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyHistory extends Fragment {
  private TabLayout tabLayout;
  private ViewPager viewPager;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.history_fragment,container,false);

    viewPager = (ViewPager) view.findViewById(R.id.viewpagerIn);
   setupViewPager(viewPager);

    tabLayout = view.findViewById(R.id.tabIn);
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
    return view;
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapterIn adapter = new ViewPagerAdapterIn(getFragmentManager());


    viewPager.setAdapter(adapter);
  }



  class ViewPagerAdapterIn extends FragmentPagerAdapter {
    public ViewPagerAdapterIn(FragmentManager fm) {
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