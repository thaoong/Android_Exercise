package com.nguyenthithao.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenthithao.model.Account;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    MediaPlayer mediaPlayer;
    public static final String DATABASE_NAME = "BookStore.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    private void copyDataBase(){
        try{
            File dbFile = getDatabasePath(DATABASE_NAME);
            if(!dbFile.exists()){
                if(CopyDBFromAsset()){
                    Toast.makeText(LoginActivity.this,
                            "Copy database successful!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "Copy database fail!", Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){
            Log.e("Error: ", e.toString());
        }
    }

    private boolean CopyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
        try {
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024]; int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush();  outputStream.close(); inputStream.close();
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addViews();
        addEvents();
        readLoginInformation();
        copyDataBase();

        // Tạo audio
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getAssets().openFd("sound.mp3");
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
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
        //if (userName.equalsIgnoreCase("admin") && pwd.equals("123"))
        if (loginSystem(userName, pwd)!=null)
        {
//            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//            Intent intent=new Intent(LoginActivity.this, SimpleListBookActivity.class);
//            Intent intent=new Intent(LoginActivity.this, SimpleListBookObjectActivity.class);
//            Intent intent=new Intent(LoginActivity.this, AdvancedListBookObjectActivity.class);
//            Intent intent=new Intent(LoginActivity.this, PublisherBookActivity.class);
//            Intent intent=new Intent(LoginActivity.this, PublisherBookSqliteActivity.class);
            Intent intent=new Intent(LoginActivity.this, PublisherBookSqliteCRUDActivity.class);
//            Intent intent=new Intent(LoginActivity.this, MyContactActivity.class);
//              Intent intent=new Intent(LoginActivity.this, MyContactAdvancedActivity.class);

            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Log in successfull!", Toast.LENGTH_SHORT).show();
            sharedPreferences=getSharedPreferences(Key_Preference, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_NAME", userName);
            editor.putString("PASSWORD", pwd);
            editor.putBoolean("SAVED", chkSaveLoginInfor.isChecked());
            editor.commit();

            // Tắt nhạc khi đăng nhập thành công
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
        else
        {
            //Alarm login failed
            txtMessage.setText("Login failed, please check your account again");
            Toast.makeText(LoginActivity.this, "Log in failed!", Toast.LENGTH_SHORT).show();
        }
    }

    void readLoginInformation()
    {
        sharedPreferences=getSharedPreferences(Key_Preference, MODE_PRIVATE);
        String userName = sharedPreferences.getString("USER_NAME", "");
        String pwd = sharedPreferences.getString("PASSWORD", "");
        boolean saved = sharedPreferences.getBoolean("SAVED", false);
        if (saved)
        {
            edtUserName.setText(userName);
            edtPassword.setText(pwd);
        }
        else {
            edtUserName.setText("");
            edtPassword.setText("");
        }
        chkSaveLoginInfor.setChecked(saved);
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
        if (item.getItemId()==R.id.mnuUpdateNewVersion)
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

    public Account loginSystem(String userName, String pwd)
    {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String sql = "select * from Account where UserName='"+userName+"' and PassWord='"+pwd+"'";
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToNext())
        {
            String usn = cursor.getString(1);
            String p = cursor.getString(2);
            Account ac = new Account(usn, p);
            cursor.close();
            return ac;
        }
        cursor.close();
        return null;
    }
}