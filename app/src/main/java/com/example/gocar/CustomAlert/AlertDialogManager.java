package com.example.gocar.CustomAlert;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.gocar.R;

public class AlertDialogManager {
    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null) {
            // Setting alert dialog icon
            // alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);


            // Setting OK Button
            alertDialog.setButton(1,"OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        // Showing Alert Message
        alertDialog.show();
    }
}
