package com.example.gocar.BookingFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.gocar.Adapters.MycarsAdapter;
import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApprovedIn extends Fragment {
    private ApiInterface api;
    public ApprovedIn() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://72.255.61.208:9001/api/v1/")
                .baseUrl("http://192.168.0.112:9001/api/v1/")

                .addConverterFactory(GsonConverterFactory.create( ))
                .build();
        api=retrofit.create(ApiInterface.class);
        return inflater.inflate(R.layout.lyout_approved, container, false);
    }
    public void getApprovedAsASeller(){

        Call<List<AllActiveVehicle>> call = api.BookingAsASeller("12","Approved") ;
        call.enqueue(new Callback<List<AllActiveVehicle>>() {
            @Override
            public void onResponse(Call<List<AllActiveVehicle>> call, Response<List<AllActiveVehicle>> response) {

                if(response.isSuccessful()){

                    Toast.makeText(getContext(), response.body().get(1).getStatus(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                }
  }

            @Override
            public void onFailure(Call<List<AllActiveVehicle>> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
