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
import androidx.fragment.app.FragmentTransaction;

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

import static com.example.gocar.Acivities.AddCarActivity.fragmentManager;

public class CarInfo extends Fragment {
    private FloatingActionButton btn;
    private EditText vname,vnum,vmodel,vcapacity,vcc,fuel,rph;
    private  View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.car_info, container, false);

        initialize();

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

                    DemoClass.bookingDTO.setVehicle_number( vnum.getText().toString());
                    DemoClass.bookingDTO.setVehicle_name( vname.getText().toString());
                    DemoClass.bookingDTO.setModel( Integer.parseInt(vmodel.getText().toString()));
                    DemoClass.bookingDTO.setSeating_capacity( Integer.parseInt(vcapacity.getText().toString()));
                    DemoClass.bookingDTO.setCc( Integer.parseInt(vcc.getText().toString()));
                    DemoClass.bookingDTO.setStatus( "ACTIVE");
                    DemoClass.bookingDTO.setFuel( fuel.getText().toString());
                    DemoClass.bookingDTO.setUsername(DemoClass.pnumber);
                    DemoClass.bookingDTO.setRent_per_hour( Integer.parseInt(rph.getText().toString()));

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Images numberFragment = new Images();
                    fragmentTransaction.replace(R.id.fragment_container, numberFragment, null);

                    fragmentTransaction.commit();

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

}
