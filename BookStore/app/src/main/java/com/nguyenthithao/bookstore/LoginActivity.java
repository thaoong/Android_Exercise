package com.nguyenthithao.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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
    Dialog dialog=null;

    ImageView imgLogo;
    SharedPreferences sharedPreferences;
    String Key_Preference = "LOGIN_PREFERENCE";
    CheckBox chkSaveLoginInfor;

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

        imgLogo = findViewById(R.id.imgLogo);
        registerForContextMenu(imgLogo);
        chkSaveLoginInfor=findViewById(R.id.chkSaveInfo);
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
//            Intent intent=new Intent(LoginActivity.this, AdvancedListBookObjectActivity.class);
            Intent intent=new Intent(LoginActivity.this, PublisherBookActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Log in successfull!", Toast.LENGTH_SHORT).show();
            sharedPreferences=getSharedPreferences(Key_Preference, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_NAME", userName);
            editor.putString("PASSWORD", pwd);
            editor.putBoolean("SAVED", chkSaveLoginInfor.isChecked());
            editor.commit();
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu_logo, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnuUpdateNewVersion)
        {

        } else if (item.getItemId()==R.id.mnuSharing) {
            
        } else if (item.getItemId()==R.id.mnuDialUpCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:0374328977");
            intent.setData(uri);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.mnuDirectCall) {
            //theem confirm call permission tujw ddoonjg
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri uri = Uri.parse("tel:0374328977");
            intent.setData(uri);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }
}