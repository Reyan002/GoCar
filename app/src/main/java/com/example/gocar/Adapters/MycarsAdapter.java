package com.example.gocar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Acivities.CarDetailsActivity;
import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;

import java.util.ArrayList;
import java.util.List;

public class MycarsAdapter extends RecyclerView.Adapter<MycarsAdapter.ViewHolder> {
    List<AllActiveVehicle> myCarList;
    Context context;

    public MycarsAdapter(List<AllActiveVehicle> myCarList, Context context) {
        this.myCarList = myCarList;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cars,
                parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      final AllActiveVehicle cars= myCarList.get(position);
      String details=cars.getUsername();
      String[] detailsArray =details.split("#");
      final String address=detailsArray[4];
      final String Long=detailsArray[3];
      final String Lat=detailsArray[2];
      final String Contact=detailsArray[1];
      final String FLname=detailsArray[0];
        holder.carName.setText(cars.getVehicle_name());
        holder.carRate.setText( cars.getRent_per_hour() +" PKR");
        holder.carAdres.setText(address);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), CarDetailsActivity.class);
                DemoClass.images=cars.getImages();
                intent.putExtra("vid",cars.getVehicle_number());
                intent.putExtra("vname",cars.getVehicle_name());
                intent.putExtra("address",address);
                intent.putExtra("long",Long);
                intent.putExtra("lat",Lat);
                intent.putExtra("contact",Contact);
                intent.putExtra("flname",FLname);
                intent.putExtra("cc",cars.getCc());
                intent.putExtra("fuel",cars.getFuel());
                intent.putExtra("model",cars.getModel());
                intent.putExtra("rph",cars.getRent_per_hour());
                intent.putExtra("capacity",cars.getSeating_capacity());
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return myCarList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView carName;
        private View view;
        public TextView carRate;
        public TextView carAdres;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
           cardView = itemView.findViewById(R.id.car_entity);
           carRate = (TextView) itemView.findViewById(R.id.rate_car);
           carName = (TextView) itemView.findViewById(R.id.car_name);
           carAdres = (TextView) itemView.findViewById(R.id.car_adrees);
        }

    }

    //
}

