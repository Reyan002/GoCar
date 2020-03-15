package com.example.gocar.Acivities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gocar.Classes.DemoClass;
import com.example.gocar.Classes.FcmRequest;
import com.example.gocar.Pojo.LoginRequest;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.example.gocar.SessionManager.SessionManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText userPhone,userPass;
    private Button login;
    private TextView creatNewAccountr;
    private ApiInterface api;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager=new SessionManager(this);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_white_black_24dp);


// and in you adapter set this instance


        initialize();

       api= ApiUtils.getAPIService();
        creatNewAccountr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName=userPhone.getText().toString();
                String userPas=userPass.getText().toString();
                checkFieldsEntered();
                postToLogin(new LoginRequest(userName,userPas));
                //overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);

            }
        });
    }
    public void initialize(){
        userPhone=findViewById(R.id.userPhone);
        userPass=findViewById(R.id.userPassword);
        login=findViewById(R.id.login);
        creatNewAccountr=findViewById(R.id.createNewAccount);
    }

    boolean isPhone(EditText text){
        return (!TextUtils.isEmpty(text.getText().toString())&& Patterns.PHONE.matcher(text.getText().toString()).matches());
    }
    public void checkFieldsEntered(){
        if(TextUtils.isEmpty(userPhone.getText().toString())){
            userPhone.setError("Enter Phone");
        }
        if(TextUtils.isEmpty(userPass.getText().toString())){
            userPass.setError("Enter Password");
        }
    }
    public void postToLogin(final LoginRequest loginRequest)
    {

        Call<LoginRequest> call = api.userLogin(loginRequest);
        call.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {

                if (response.code()==200){
                    //loginFailed
//                    Toast.makeText(LoginActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    sessionManager.createLoginSession(loginRequest);
                    DemoClass.pnumber=userPhone.getText().toString();

                    Call<FcmRequest> call1=api.token(new FcmRequest(DemoClass.pnumber, FirebaseInstanceId.getInstance().getToken()));
                    call1.enqueue(new Callback<FcmRequest>() {
                        @Override
                        public void onResponse(Call<FcmRequest> call, Response<FcmRequest> response) {
                            if(response.isSuccessful()){
//                                Toast.makeText(LoginActivity.this, "Token Sent", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<FcmRequest> call, Throwable t) {

                        }
                    });

                }
                else{
//                    Toast.makeText(LoginActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}
