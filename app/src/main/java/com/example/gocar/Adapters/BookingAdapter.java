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
import com.example.gocar.Classes.BookingDTO;
import com.example.gocar.R;

import java.util.List;

public class BookingAdapter  extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    List<BookingDTO> myCarList;
    Context context;

    public BookingAdapter(List<BookingDTO> myCarList, Context context) {
        this.myCarList = myCarList;
        this.context = context;
    }

    @NonNull
    @Override

    public  BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_layout,
                parent, false);
        return new BookingViewHolder(view);  }

    @Override
    public void onBindViewHolder(@NonNull  BookingViewHolder holder, int position) {

        final BookingDTO cars= myCarList.get(position);
//        String details=cars.getUsername();
//        String[] detailsArray =details.split("#");
//        final String address=detailsArray[4];
//        final String Long=detailsArray[3];
//        final String Lat=detailsArray[2];
//        final String Contact=detailsArray[1];
//        final String FLname=detailsArray[0];
        holder.carName.setText(cars.getVehicleNumber());
        holder.reprh.setText( cars.getRentPerHour() +" PKR");
        holder.requestedBy.setText(cars.getCustomerId());
        holder.range.setText(cars.getStart()+"-"+cars.getEnd());

    }

    @Override
    public int getItemCount() {
        return myCarList.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder{
        public TextView carName;
        private View view;
        public TextView carRate;
         public TextView reprh;
        public TextView requestedBy;
        public TextView range;


        public BookingViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
             carRate = (TextView) itemView.findViewById(R.id.car_name_booking);
            carName = (TextView) itemView.findViewById(R.id.car_loc);
            requestedBy = (TextView) itemView.findViewById(R.id.requested_by);
            range = (TextView) itemView.findViewById(R.id.range);
            reprh = (TextView) itemView.findViewById(R.id.rperh);
        }

    }

    //
}

