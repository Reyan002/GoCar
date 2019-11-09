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
import com.example.gocar.Classes.Cars;
import com.example.gocar.R;

import java.util.ArrayList;

public class MycarsAdapter extends RecyclerView.Adapter<MycarsAdapter.ViewHolder> {
    ArrayList<Cars> myCarList;
    Context context;

    public MycarsAdapter(ArrayList<Cars> myCarList, Context context) {
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

      Cars cars= myCarList.get(position);
        holder.carName.setText(cars.getName());
        holder.carRate.setText("Rs."+String.valueOf(cars.getRate_per_time()));
        holder.carAdres.setText(cars.getLocation());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), CarDetailsActivity.class));
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

