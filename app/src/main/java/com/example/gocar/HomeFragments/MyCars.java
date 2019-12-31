package com.example.gocar.HomeFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Acivities.LoginActivity;
import com.example.gocar.Adapters.MycarsAdapter;
import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.Cars;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.SessionManager.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCars extends Fragment {


    private Button login;
    private FloatingActionButton add_car;
    private LinearLayout linearLayout;
    private SessionManager sessionManager;
    private RecyclerView recyclerView;
    private MycarsAdapter mycarsAdapter;
    private List<AllActiveVehicle> myListcar;
    private ApiInterface api;


    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.mycars_fragment,container,false);
        sessionManager=new SessionManager(getContext());
        linearLayout=root.findViewById(R.id.loginLinearLayoutMyCar);
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://72.255.61.208:9001/api/v1/")
                .baseUrl("http://192.168.0.108:9001/api/v1/")

                .addConverterFactory(GsonConverterFactory.create( ))
                .build();
        api=retrofit.create(ApiInterface.class);




        recyclerView = root.findViewById(R.id.mycars);
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


        if(sessionManager.isLoggedIn()) {
            recyclerView.setVisibility(View.VISIBLE);

            getMyProperty();

        }
        return root;

    }



    public void getMyProperty(){




        Call<List<AllActiveVehicle>> call = api.myVehicles(DemoClass.pnumber) ;
        call.enqueue(new Callback<List<AllActiveVehicle>>() {
            @Override
            public void onResponse(Call<List<AllActiveVehicle>> call, Response<List<AllActiveVehicle>> response) {

                if(response.isSuccessful()){
                    myListcar=response.body();
                    //layoutManager = new GridLayoutManager(context,2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mycarsAdapter=new MycarsAdapter(myListcar,context);
                    recyclerView.setAdapter(mycarsAdapter);
                }
                else{
                    Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                }


//                for (AllActiveVehicle allActiveVehicle :myListcar){
//
//                    allActiveVehicle.getRent_per_hour();
//                    allActiveVehicle.getVehicle_name();
//                    allActiveVehicle.getOwner();
//
//
//
//
//
//                }















            }

            @Override
            public void onFailure(Call<List<AllActiveVehicle>> call, Throwable t) {

            }
        });


    }
}
