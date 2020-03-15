package com.example.gocar.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Acivities.HomeActivity;
import com.example.gocar.Classes.BookingDTO;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAdpterApproved extends RecyclerView.Adapter<BookingAdpterApproved.BookingViewHolder> {
    List<BookingDTO> myCarList;
    Context context;
    private ApiInterface api= ApiUtils.getAPIService();

    public BookingAdpterApproved(List<BookingDTO> myCarList, Context context) {
        this.myCarList = myCarList;
        this.context = context;
    }

    @NonNull
    @Override

    public BookingAdpterApproved.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_approved_layout,
                parent, false);
        return new BookingAdpterApproved.BookingViewHolder(view);  }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        final BookingDTO cars= myCarList.get(position);
//        String details=cars.getUsername();
//        String[] detailsArray =details.split("#");
//        final String address=detailsArray[4];
//        final String Long=detailsArray[3];
//        final String Lat=detailsArray[2];
//        final String Contact=detailsArray[1];
//        final String FLname=detailsArray[0];


        if (cars.getImage()==null || cars.getImage().isEmpty()) {

            holder.imageView.setImageResource(R.drawable.ic_account_circle_black_24dp);
        } else{
            Picasso.get().load(cars.getImage()).into(holder.imageView);
        }
        holder.carName.setText(cars.getVehicleNumber());
        holder.reprh.setText( cars.getRentPerHour() +" PKR");
        holder.requestedBy.setText(cars.getSellerId());
        holder.range.setText(cars.getStart()+"-"+cars.getEnd());
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"+cars.getSellerId()));
                 v.getContext().startActivity(sendIntent);
            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(Build.VERSION.SDK_INT > 22)
                    {
                        if (ActivityCompat.checkSelfPermission( v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling

                            ActivityCompat.requestPermissions((Activity) v.getContext(), new String[]{Manifest.permission.CALL_PHONE}, 101);

                            return;
                        }

                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" +cars.getSellerId()));
                        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(callIntent);

                    }
                    else {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + cars.getSellerId()));
                        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(callIntent);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

    }



//    public void acceptRequest(BookingDTO bookingDTO,int i){
//
//        Call<BookingDTO> call = api.bookingResponse(bookingDTO,i);
//        call.enqueue(new Callback<BookingDTO>() {
//            @Override
//            public void onResponse(Call<BookingDTO> call, Response<BookingDTO> response) {
//                if(response.isSuccessful()){
//
//                    context.startActivity(new Intent(context, HomeActivity.class));
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BookingDTO> call, Throwable t) {
//
//            }
//        });
//
//    }
//    public void rejrctRequest(BookingDTO bookingDTO,int i){
//        Call<BookingDTO> call = api.bookingResponse(bookingDTO,i);
//        call.enqueue(new Callback<BookingDTO>() {
//            @Override
//            public void onResponse(Call<BookingDTO> call, Response<BookingDTO> response) {
//                if(response.isSuccessful()){
//                    context.startActivity(new Intent(context, HomeActivity.class));
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BookingDTO> call, Throwable t) {
//
//            }
//        });
//    }
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
        public CircleImageView imageView;
        public Button accept,reject;


        public BookingViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            carRate = (TextView) itemView.findViewById(R.id.car_name_bookingA);
            carName = (TextView) itemView.findViewById(R.id.car_locA);
            requestedBy = (TextView) itemView.findViewById(R.id.requested_byA);
            range = (TextView) itemView.findViewById(R.id.rangeA);
            reprh = (TextView) itemView.findViewById(R.id.rperhA);
            accept =  itemView.findViewById(R.id.acceptA);
            reject =  itemView.findViewById(R.id.declineA);
            imageView =  itemView.findViewById(R.id.booking_imageA);
        }

    }

    //
}
