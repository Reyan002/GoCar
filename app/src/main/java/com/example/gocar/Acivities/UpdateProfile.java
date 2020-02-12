package com.example.gocar.Acivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gocar.Classes.DemoClass;
import com.example.gocar.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {

    private static final int galleryPic=1;
    private CircleImageView profile_image_view;
    private TextView close_btn,update_btn,profile_change_btn,phone_ed;
    private EditText fname_ed,adress_ed;
    private Uri imageUri;
    private String Url="";
    private StorageReference storageReferencePicture;
    private String checker="";
    private StorageTask uploadTask;
    private String myUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        storageReferencePicture = FirebaseStorage.getInstance().getReference().child("Profile pictures");
        profile_image_view = (CircleImageView) findViewById(R.id.setting_profile_image_change);
        close_btn = (TextView)findViewById(R.id.setting_close);
        update_btn = (TextView)findViewById(R.id.setting_update);
        profile_change_btn = (TextView)findViewById(R.id.setting_profile_image_change_btn);
        phone_ed = (TextView)findViewById(R.id.setting_phone_number);
        fname_ed = (EditText)findViewById(R.id.setting_full_name);
        adress_ed = (EditText)findViewById(R.id.setting_address);



        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker.equals("clicked")){
                    userInfoSaved();
                }
                else{
                  //  updateOnlyUserInfo();
                }
            }
        });

        profile_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker="clicked";
                openGallery();
            }
        });
    }
    private void openGallery() {
        Intent galleryInten = new Intent();
        galleryInten.setAction(Intent.ACTION_GET_CONTENT);
        galleryInten.setType("image/*");

        startActivityForResult(galleryInten,galleryPic);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data);
        if(requestCode==galleryPic&&resultCode==RESULT_OK&&data!=null){
            imageUri=data.getData();
            profile_image_view.setImageURI(imageUri);
        }
    }



    private void userInfoSaved()
    {
        if(TextUtils.isEmpty(fname_ed.getText().toString())){
            Toast.makeText(this,"Name is Mandatory",Toast.LENGTH_SHORT).show();
        }
//        else  if(TextUtils.isEmpty(phone_ed.getText().toString())){
//            Toast.makeText(this,"Phone is Mandatory",Toast.LENGTH_SHORT).show();
//        }
        else if(TextUtils.isEmpty(adress_ed.getText().toString())){
            Toast.makeText(this,"Adress is Mandatory",Toast.LENGTH_SHORT).show();
        }
        else if(checker.equals("clicked")){

            uploadImage();

        }

    }

    private void uploadImage() {
        final ProgressDialog loadingBar=new ProgressDialog(this);
        loadingBar.setTitle("Update Profile");
        loadingBar.setMessage("Please Wait, while we are updating your Account info");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        if(imageUri!=null){
            final StorageReference fileRef=storageReferencePicture
                    .child(DemoClass.pnumber +".jpg");
            uploadTask=fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();

                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUrl=task.getResult();

                        myUrl = downloadUrl.toString();

                        loadingBar.dismiss();

                        startActivity(new Intent(UpdateProfile.this,HomeActivity.class));

                        Toast.makeText(UpdateProfile.this,"Profile Info Updated Succesfully",Toast.LENGTH_SHORT).show();

                        finish();
                    }
                    else {
                        loadingBar.dismiss();
                        Toast.makeText(UpdateProfile.this,"Error:",Toast.LENGTH_SHORT).show();

                    }
                }
            });//onFailure
        }
        else{
            Toast.makeText(UpdateProfile.this,"Image Is not Selected",Toast.LENGTH_SHORT).show();

        }

    }


}
