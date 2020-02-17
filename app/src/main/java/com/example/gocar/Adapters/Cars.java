package com.example.gocar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Acivities.CarDetailsActivity;
import com.example.gocar.Classes.AllActiveVehicle;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Cars extends RecyclerView.Adapter<Cars.MyCarViewHolder>  {

    private List<AllActiveVehicle> vehicles;
    private Context context;

    public Cars(List<AllActiveVehicle> vehicles, Context context) {
        this.vehicles = vehicles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cars_layout,
                parent, false);
        return new Cars.MyCarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCarViewHolder holder, int position) {
        final AllActiveVehicle cars= vehicles.get(position);
        Picasso.get().load(cars.getImages().get(0)).into(holder.imageView);
        holder.carName.setText(cars.getVehicle_name());
        holder.carRate.setText(cars.getRent_per_hour());
        holder.carAdres.setText(cars.getAddress());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), CarDetailsActivity.class);
                DemoClass.images=cars.getImages();
                intent.putExtra("vid",cars.getVehicle_number());
                intent.putExtra("vname",cars.getVehicle_name());
                intent.putExtra("username",cars.getUsername());
                intent.putExtra("address",cars.getAddress());
//                intent.putExtra("long",Long);
//                intent.putExtra("lat",Lat);
//                intent.putExtra("contact",Contact);
//                intent.putExtra("flname",FLname);
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
        return vehicles.size();
    }

    public class MyCarViewHolder extends RecyclerView.ViewHolder{
        public TextView carName;
        public ImageView imageView;
        private View view;
        public TextView carRate;
        public TextView carAdres;
        public CardView cardView;

        public MyCarViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            cardView = itemView.findViewById(R.id.cvMyCar);
            carRate = (TextView) itemView.findViewById(R.id.rphMyCar);
            imageView =  itemView.findViewById(R.id.imageMyCar);
            carName = (TextView) itemView.findViewById(R.id.nameMyCar);
            carAdres = (TextView) itemView.findViewById(R.id.addRessMyCarr);
        }

    }
}
