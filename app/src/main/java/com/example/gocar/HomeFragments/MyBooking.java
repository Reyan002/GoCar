package com.example.gocar.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gocar.Acivities.LoginActivity;
import com.example.gocar.R;
import com.example.gocar.SessionManager.SessionManager;

public class MyBooking extends Fragment {
    private Button login;
    private LinearLayout linearLayout;
    private SessionManager sessionManager;
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

        return rootView;
    }
}
