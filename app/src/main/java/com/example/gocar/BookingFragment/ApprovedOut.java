package com.example.gocar.BookingFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Adapters.BookingAdapter;
import com.example.gocar.Adapters.BookingAdpterApproved;
import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.BookingDTO;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApprovedOut extends Fragment {
    private ApiInterface api;
    private RecyclerView recyclerView;
    private BookingAdpterApproved mycarsAdapter;
    private List<BookingDTO> myListcar;
    private Context context;
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

        recyclerView = view.findViewById(R.id.rrBookingInAP);


        api= ApiUtils.getAPIService();
        getApprovedAsACustomer();
        return view;
    }
    public void getApprovedAsACustomer(){

        Call<List<BookingDTO>> call = api.BookingOutAsACustomer(DemoClass.pnumber,"Approved") ;
        call.enqueue(new Callback<List<BookingDTO>>() {
            @Override
            public void onResponse(Call<List<BookingDTO>> call, Response<List<BookingDTO>> response) {

                if(response.isSuccessful()){
//                    Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();

                    myListcar=response.body();
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    mycarsAdapter=new BookingAdpterApproved(myListcar,context);
                    recyclerView.setAdapter(mycarsAdapter);
                    mycarsAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<List<BookingDTO>> call, Throwable t) {

//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
