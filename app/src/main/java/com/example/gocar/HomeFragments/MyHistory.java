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

import com.example.gocar.BookingFragment.ApprovedOut;
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

    return view;
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapterIn adapter = new ViewPagerAdapterIn(getFragmentManager());
    adapter.addFragment(new PendingOut(), "Pending");
    adapter.addFragment(new ApprovedOut(), "Approved");


    viewPager.setAdapter(adapter);
  }



  class ViewPagerAdapterIn extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapterIn(FragmentManager manager) {
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