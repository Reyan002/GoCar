package com.example.gocar.Acivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;
import com.example.gocar.SessionManager.SessionManager;

public class SplashScreen extends AppCompatActivity {

    private ImageView imageView;
    private EditText textView;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final SessionManager sessionManager=new SessionManager(this);
        next=findViewById(R.id.next);
        textView=findViewById(R.id.fullscreen_content);
        imageView=findViewById(R.id.logo);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(textView.getText().toString())) {
                    if (sessionManager.isLoggedIn()) {


                        DemoClass.IP = textView.getText().toString();
                        DemoClass.pnumber = sessionManager.getUserDetails();
                        startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                    }
                    DemoClass.IP = textView.getText().toString();
                    startActivity(new Intent(SplashScreen.this, HomeActivity.class));

                }
                Toast.makeText(SplashScreen.this, "Please Provide IP", Toast.LENGTH_SHORT).show();
            }

        });

//        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim);
//        imageView.startAnimation(animation);
//
//        textView.startAnimation(animation);
//
//
//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(sessionManager.isLoggedIn()) {
//
//                    DemoClass.pnumber=sessionManager.getUserDetails();
//                    startActivity(new Intent(SplashScreen.this, HomeActivity.class));
//                }
//                startActivity(new Intent(SplashScreen.this, HomeActivity.class));
//            }
//        },2000);


    }
}