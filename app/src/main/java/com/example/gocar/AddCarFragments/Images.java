package com.example.gocar.AddCarFragments;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gocar.Acivities.HomeActivity;
import com.example.gocar.Adapters.GalleryAdapter;
import com.example.gocar.Classes.DemoClass;
import com.example.gocar.Classes.VehicleRequest;
import com.example.gocar.R;
import com.example.gocar.Rest.ApiInterface;
import com.example.gocar.Rest.ApiUtils;
import com.example.gocar.Rest.listener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class Images extends Fragment {
    private Button btn;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    private ArrayList<Uri> mArrayUri;
    private FloatingActionButton fab;
    private ArrayList<String > images;
    private ApiInterface api;
    private int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images, container, false);


        btn = view.findViewById(R.id.btn);
        gvGallery =  view.findViewById(R.id.gv);
        fab=view.findViewById(R.id.add_car_next_2);

     mArrayUri = new ArrayList<Uri>();
//        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("http://72.255.61.208:9001/api/v1/")
//                .baseUrl("http://192.168.43.76:9001/api/v1/")
//
//                .addConverterFactory(GsonConverterFactory.create( ))
//                .build();
//        api=retrofit.create(ApiInterface.class);
        api= ApiUtils.getAPIService();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                images=new ArrayList<>();
//                 images=uploadImages();
uploadImages(new listener() {
    @Override
    public void onComplete(List<String> result) {
        i=0;
        postVehicle(DemoClass.bookingDTO.getVehicle_number(),
                DemoClass.bookingDTO.getVehicle_name(),
                DemoClass.bookingDTO.getModel(),
                DemoClass.bookingDTO.getSeating_capacity(),
                DemoClass.bookingDTO.getCc(),
                DemoClass.bookingDTO.getStatus(),
                DemoClass.bookingDTO.getFuel(),
                DemoClass.bookingDTO.getUsername(),
                DemoClass.bookingDTO.getRent_per_hour(),
                result );

    }
});

               // Toast.makeText(getContext(), String.valueOf( images.size() ) , Toast.LENGTH_SHORT).show();

            }

        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContext().getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), mImageUri);


                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getContext(),mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();

                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);

                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContext().getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getContext(),mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(getContext(), "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void postVehicle(String vehicle_number,String Vehicle_name, int Model, int capacity,int cc,String  status,String fuel,String username, float rph,List<String> images){

        Call<VehicleRequest> call = api.vehiclePost(new VehicleRequest(vehicle_number,Vehicle_name,Model,capacity,cc,status,fuel,username,rph,images));
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
public void uploadImages(final listener listener){
        final ArrayList<String> images1=new ArrayList<>();
    final StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("CarImages");
     mArrayUri.forEach( x->{
         Uri individualImages=x;
         final StorageReference storageReference1=storageReference.child("Images"+individualImages.getLastPathSegment());
         storageReference1.putFile(individualImages).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                 storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                     @Override
                     public void onSuccess(Uri uri) {


                         String url=String.valueOf(uri);

                         images1.add(url);
                         i++;
                         if(i==mArrayUri.size() ) listener.onComplete(images1);
                         Toast.makeText(getContext(), String.valueOf(images1.size()), Toast.LENGTH_SHORT).show();

                         Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });
     });


//    return images1;
}

}
