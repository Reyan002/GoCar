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

    sessionManager=new SessionManager(getContext());
    linearLayout=rootView.findViewById(R.id.loginLinearLayoutMyBooking);
    if(!sessionManager.isLoggedIn()){
      linearLayout.setVisibility(View.VISIBLE);
    }
    login=rootView.findViewById(R.id.loginbtnbooking);
    login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(getContext(), LoginActivity.class));
      }
    });




    viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
    //setupViewPager(viewPager);

    tabLayout = rootView.findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);

    return rootView;
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
    adapter.addFragment(new PendingOut(), "Pending");
    adapter.addFragment(new ApprovedOut(), "Approved");


    viewPager.setAdapter(adapter);
  }



  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }
}