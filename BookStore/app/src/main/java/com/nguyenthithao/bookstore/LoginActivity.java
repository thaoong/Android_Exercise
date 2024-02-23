package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;

    TextView txtMessage;
    ImageView imgFooter;
    int count_exit = 0;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addViews();
        addEvents();
    }

    private void addEvents() {
        imgFooter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //process for delete image
                dialog.show();
                return false;
            }
        });
    }

    private void addViews() {
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        txtMessage=findViewById(R.id.txtMessage);
        imgFooter=findViewById(R.id.imgFooter);

        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.deleting_custom);

        ImageView imgRemove = dialog.findViewById(R.id.imgRemove);
        ImageView imgCancel = dialog.findViewById(R.id.imgCancel);

        imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFooter.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void exitApp(View view) {
        //finish();
        confirmExit();
    }

    void confirmExit(){
        //Create builder object
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        //set title:
        builder.setTitle("Confirm Exit");
        //set message
        builder.setMessage("Are you sure want to exit?");
        //set icon
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //set actions button for user interaction
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //Create Dialog object;
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        //Show dialog
        dialog.show();
    }

    public void openMainActivity(View view) {

        String userName=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        if (userName.equalsIgnoreCase("admin") && pwd.equals("123"))
        {
//            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//            Intent intent=new Intent(LoginActivity.this, SimpleListBookActivity.class);
//            Intent intent=new Intent(LoginActivity.this, SimpleListBookObjectActivity.class);
            Intent intent=new Intent(LoginActivity.this, AdvancedListBookObjectActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Log in successfull!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Alarm login failed
            txtMessage.setText("Login failed, please check your account again");
            Toast.makeText(LoginActivity.this, "Log in failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        count_exit++;
        if (count_exit>=3)
        {
            confirmExit();
            count_exit=0;
        }
    }
}