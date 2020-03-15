package com.example.gocar.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gocar.Acivities.LoginActivity;
import com.example.gocar.Acivities.UpdateProfile;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.Pojo.Users;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.example.gocar.SessionManager.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccount extends Fragment {

    private Button login,update;
    private SessionManager sessionManager;
    private LinearLayout llMyAccunt,pll;

    private TextView name,contact,cnic;
    private CircleImageView imageView;

    private ApiInterface api;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.myaccount_fragment,container,false);

        name=rootView.findViewById(R.id.pname);
        contact=rootView.findViewById(R.id.pcon);
        cnic=rootView.findViewById(R.id.pcnic);
        imageView=rootView.findViewById(R.id.ppick);
        api= ApiUtils.getAPIService();
        llMyAccunt=rootView.findViewById(R.id.loginLinearLayoutMyAccount);
        update=rootView.findViewById(R.id.pupdate);
        pll=rootView.findViewById(R.id.pll);
        sessionManager=new SessionManager(getContext());
        if(!sessionManager.isLoggedIn()){
            llMyAccunt.setVisibility(View.VISIBLE);
        }
        else {
            pll.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
            getUserProfile();
        }

        login=rootView.findViewById(R.id.loginBtnMyAccout);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UpdateProfile.class));
            }
        });
        return rootView;
    }
    public void getUserProfile(){
        Call<Users> call=api.getUser(DemoClass.pnumber);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    if (response.body().getDp()==null || response.body().getDp().isEmpty()) {
                        imageView.setImageResource(R.drawable.ic_account_circle_black_24dp);
                    } else{
                        Picasso.get().load(response.body().getDp()).into(imageView);
                    }

//                    Toast.makeText(getContext(), "Jeyo", Toast.LENGTH_SHORT).show();
                    name.setText(response.body().getFirst_name()+response.body().getLast_name());
                    cnic.setText(response.body().getCnic());
                    contact.setText(response.body().getContact());
                }
                else{
//                    Toast.makeText(getContext(),  "Mar jao", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }
}