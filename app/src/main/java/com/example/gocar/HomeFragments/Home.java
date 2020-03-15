package com.example.gocar.HomeFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Adapters.MycarsAdapter;
import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.Classes.FcmRequest;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.example.gocar.SessionManager.SessionManager;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private MycarsAdapter mycarsAdapter;
    private List<AllActiveVehicle> myListcar;
    private RecyclerView.LayoutManager layoutManager;
    private SessionManager sessionManager;
    private ApiInterface api;
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Root= inflater.inflate(R.layout.home_fragment,container,false);
        textView=Root.findViewById(R.id.filterresult);
//        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("http://72.255.61.208:9001/api/v1/")
//                .baseUrl("http://192.168.43.76:9001/api/v1/")
//
//                .addConverterFactory(GsonConverterFactory.create( ))
//                .build();
//        api=retrofit.create(ApiInterface.class);
        api= ApiUtils.getAPIService();
        recyclerView = Root.findViewById(R.id.recyclerview);

        textView.setVisibility(View.GONE);


        getAllProperty();



        return Root;
    }


    public void getAllProperty(){




        Call<List<AllActiveVehicle>> call = api.guestVehicle() ;
        call.enqueue(new Callback<List<AllActiveVehicle>>() {
            @Override
            public void onResponse(Call<List<AllActiveVehicle>> call, Response<List<AllActiveVehicle>> response) {

                if(response.isSuccessful()){
                    if(!DemoClass.lst.isEmpty())
                    {  layoutManager = new GridLayoutManager(context,2);
                        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setLayoutManager(layoutManager);
                        mycarsAdapter=new MycarsAdapter(DemoClass.lst,context);
                        recyclerView.setAdapter(mycarsAdapter);
                        textView.setVisibility(View.GONE);

                    }
                    else if(DemoClass.filter){

                        DemoClass.filter=false;
                        textView.setVisibility(View.VISIBLE);
                    }
                    else{
                        myListcar=response.body();

                        textView.setVisibility(View.GONE);
                        layoutManager = new GridLayoutManager(context,2);
                        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setLayoutManager(layoutManager);
                        mycarsAdapter=new MycarsAdapter(myListcar,context);
                        recyclerView.setAdapter(mycarsAdapter);
                    }


                }
                else{
//                    Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();
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
