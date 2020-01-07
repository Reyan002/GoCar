package com.example.gocar.BookingFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.BookingDTO;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApprovedOut extends Fragment {
    private ApiInterface api;
    public ApprovedOut() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =inflater.inflate(R.layout.lyout_approved, container, false);


        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://72.255.61.208:9001/api/v1/")
                .baseUrl("http://192.168.0.109:9001/api/v1/")

                .addConverterFactory(GsonConverterFactory.create( ))
                .build();
        api=retrofit.create(ApiInterface.class);
        getApprovedAsACustomer();
        return view;
    }
    public void getApprovedAsACustomer(){

        Call<List<BookingDTO>> call = api.BookingOutAsACustomer("12","Approved") ;
        call.enqueue(new Callback<List<BookingDTO>>() {
            @Override
            public void onResponse(Call<List<BookingDTO>> call, Response<List<BookingDTO>> response) {

                if(response.isSuccessful()){
                    Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookingDTO>> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
