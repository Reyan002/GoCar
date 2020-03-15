package com.example.gocar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Acivities.HomeActivity;
import com.example.gocar.Classes.BookingDTO;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAdapter  extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
   private List<BookingDTO> myCarList;
    private Context context;
    private ApiInterface api= ApiUtils.getAPIService();

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
        if (cars.getImage()==null || cars.getImage().isEmpty()) {
            holder.imageView.setImageResource(R.drawable.ic_account_circle_black_24dp);
//                        dp.setImageResource(R.drawable.active_dots);
        } else{
            Picasso.get().load(cars.getImage()).into(holder.imageView );
        }

        holder.range.setText(cars.getStart()+"-"+cars.getEnd());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptRequest(new BookingDTO(cars.getBookingId(),cars.getVehicleNumber(),
                        cars.getSellerId(),cars.getCustomerId(),cars.getStart(),cars.getEnd()
                ,cars.getRentPerHour(),cars.getBookingTime(),""),1);
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejrctRequest(new BookingDTO(cars.getBookingId(),cars.getVehicleNumber(),
                        cars.getSellerId(),cars.getCustomerId(),cars.getStart(),cars.getEnd()
                        ,cars.getRentPerHour(),cars.getBookingTime(),""),0);
            }
        });

    }

    public void acceptRequest(BookingDTO bookingDTO,int i){

        Call<BookingDTO> call = api.bookingResponse(bookingDTO,i);
        call.enqueue(new Callback<BookingDTO>() {
            @Override
            public void onResponse(Call<BookingDTO> call, Response<BookingDTO> response) {
                if (response.isSuccessful()) {

//                    context.startActivity(new Intent(context, HomeActivity.class));                }
                }
            }
            @Override
            public void onFailure(Call<BookingDTO> call, Throwable t) {

            }
        });

    }
    public void rejrctRequest(BookingDTO bookingDTO,int i){
        Call<BookingDTO> call = api.bookingResponse(bookingDTO,i);
        call.enqueue(new Callback<BookingDTO>() {
            @Override
            public void onResponse(Call<BookingDTO> call, Response<BookingDTO> response) {
                if(response.isSuccessful()){


//                    context.startActivity(new Intent(context, HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<BookingDTO> call, Throwable t) {



            }
        });
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
        public Button accept,reject;
        public CircleImageView imageView;


        public BookingViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
             carRate = (TextView) itemView.findViewById(R.id.car_name_booking);
            carName = (TextView) itemView.findViewById(R.id.car_loc);
            requestedBy = (TextView) itemView.findViewById(R.id.requested_by);
            range = (TextView) itemView.findViewById(R.id.range);
            reprh = (TextView) itemView.findViewById(R.id.rperh);
            accept =  itemView.findViewById(R.id.accept);
            reject =  itemView.findViewById(R.id.decline);
            imageView=itemView.findViewById(R.id.booking_image);
            if(DemoClass.type.equals("In")&& DemoClass.typeA.equals("")){

                accept.setVisibility(View.VISIBLE);
            }

        }

    }

    //
}

