package com.nguyenthithao.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nguyenthithao.model.Account;
import com.nguyenthithao.test.databinding.ActivityLoginBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;
    String Key_Preference = "LOGIN_PREFERENCE";
    public static final String DATABASE_NAME = "test.sqlite";
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
        //setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        copyDataBase();
        readLoginInformation();
    }

    public Account loginSystem(String userName, String pwd)
    {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String sql = "select * from Account where UserName='"+userName+"' and Password='"+pwd+"'";
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

    public void processLogin(View view) {
        String userName=binding.edtUserName.getText().toString();
        String pwd=binding.edtPassword.getText().toString();
        if (loginSystem(userName, pwd)!=null)
        {

            Intent intent=new Intent(LoginActivity.this, CategoryActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Log in successfully", Toast.LENGTH_SHORT).show();
            sharedPreferences=getSharedPreferences(Key_Preference, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_NAME", userName);
            editor.putString("PASSWORD", pwd);
            editor.putBoolean("SAVED", binding.chkSaveInfo.isChecked());
            editor.commit();
        }
        else
        {
            //Alarm login failed
//            String contentLoginFailed = getResources().getString(R.string.strContentLoginFailed);
//            txtMessage.setText(contentLoginFailed);
//            String loginFailed = getResources().getString(R.string.strCaptionLoginFail);
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
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
            binding.edtUserName.setText(userName);
            binding.edtPassword.setText(pwd);
        }
        else {
            binding.edtUserName.setText("");
            binding.edtPassword.setText("");
        }
        binding.chkSaveInfo.setChecked(saved);
    }
}