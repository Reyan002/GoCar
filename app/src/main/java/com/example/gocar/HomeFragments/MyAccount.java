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

public class MyAccount extends Fragment {

    private Button login;
    private SessionManager sessionManager;
    private LinearLayout llMyAccunt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.myaccount_fragment,container,false);

        llMyAccunt=rootView.findViewById(R.id.loginLinearLayoutMyAccount);
        sessionManager=new SessionManager(getContext());
        if(!sessionManager.isLoggedIn()){
            llMyAccunt.setVisibility(View.VISIBLE);
        }

        login=rootView.findViewById(R.id.loginBtnMyAccout);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        return rootView;
    }
}