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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyCars extends Fragment {


    private Button login;
    private FloatingActionButton add_car;
    private LinearLayout linearLayout;
    private SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.mycars_fragment,container,false);
        sessionManager=new SessionManager(getContext());
        linearLayout=root.findViewById(R.id.loginLinearLayoutMyCar);
        if(!sessionManager.isLoggedIn())
        {
            linearLayout.setVisibility(View.VISIBLE);
        }
         login=root.findViewById(R.id.loginMycar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
return root;

    }
}
