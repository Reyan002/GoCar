package com.example.gocar.Acivities;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gocar.Adapters.GalleryAdapter;
import com.example.gocar.AddCarFragments.CarInfo;
import com.example.gocar.R;

import java.util.ArrayList;
import java.util.List;

public class AddCarActivity extends AppCompatActivity {



    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        getSupportActionBar().hide();

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CarInfo numberFragment = new CarInfo();
            fragmentTransaction.add(R.id.fragment_container, numberFragment, null);
            fragmentTransaction.commit();
        }


    }


}
