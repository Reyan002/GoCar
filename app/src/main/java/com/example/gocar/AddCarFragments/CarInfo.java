package com.example.gocar.AddCarFragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gocar.Acivities.HomeActivity;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.Classes.VehicleRequest;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarInfo extends Fragment {
    private FloatingActionButton btn;
    private EditText vname,vnum,vmodel,vcapacity,vcc,fuel,rph;
    private  View view;
    private ApiInterface api;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.car_info, container, false);

        initialize();
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://72.255.61.208:9001/api/v1/")
                .baseUrl("http://192.168.0.108:9001/api/v1/")

                .addConverterFactory(GsonConverterFactory.create( ))
                .build();
        api=retrofit.create(ApiInterface.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AddCarActivity.fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, new Images(), null)
//                        .commit();

                if(!TextUtils.isEmpty(vnum.getText().toString())&&!TextUtils.isEmpty(vname.getText().toString())&&
                        !TextUtils.isEmpty(vmodel.getText().toString())&&!TextUtils.isEmpty(vcapacity.getText().toString())&&
                        !TextUtils.isEmpty(vcc.getText().toString())&&!TextUtils.isEmpty(fuel.getText().toString())&&
                        !TextUtils.isEmpty(rph.getText().toString()) ) {
                    postVehicle( vnum.getText().toString(), vname.getText().toString(), Integer.parseInt(vmodel.getText().toString())
                            , Integer.parseInt(vcapacity.getText().toString()), Integer.parseInt(vcc.getText().toString())
                            , "ACTIVE", fuel.getText().toString(), DemoClass.pnumber, Integer.parseInt(rph.getText().toString()) );
                }
                else{
                    Toast.makeText(getContext(), "Please Fill All the required Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return view;
    }

    private void initialize(){
        vname=view.findViewById(R.id.et_vname);
        vnum=view.findViewById(R.id.et_vnum);
        vmodel=view.findViewById(R.id.et_model);
        vcc=view.findViewById(R.id.et_vcc);
        vcapacity=view.findViewById(R.id.et_capacity);
        fuel=view.findViewById(R.id.et_fuel);
        rph=view.findViewById(R.id.et_rph);

        btn=view.findViewById(R.id.add_car_next_1);
    }
    public void postVehicle(String vehicle_number,String Vehicle_name, int Model, int capacity,int cc,String  status,String fuel,String username, float rph){

        Call<VehicleRequest> call = api.vehiclePost(new VehicleRequest(vehicle_number,Vehicle_name,Model,capacity,cc,status,fuel,username,rph));
        call.enqueue(new Callback<VehicleRequest>() {
            @Override
            public void onResponse(Call<VehicleRequest> call, Response<VehicleRequest> response) {
                if(response.isSuccessful()  ){
                    Toast.makeText(getContext(), "Vehicle Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), HomeActivity.class));
                }
                else{
                    Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VehicleRequest> call, Throwable t) {

                Toast.makeText(getContext(),  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
