package com.nguyenthithao.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenthithao.model.Account;
import com.nguyenthithao.myapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;
    String Key_Preference = "LOGIN_PREFERENCE";
    String TAG="FIREBASE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void processLogin(View view) {
        String userName=binding.edtUserName.getText().toString();
        String pwd=binding.edtPassword.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference accountsRef = database.getReference("Accounts");

        Query query = accountsRef.orderByChild("username").equalTo(userName).limitToFirst(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot accountSnapshot = dataSnapshot.getChildren().iterator().next();
                    String usn = (String) accountSnapshot.child("username").getValue();
                    String p = (String) accountSnapshot.child("password").getValue();
                    if (pwd.equals(p)) {
                        Intent intent=new Intent(LoginActivity.this, ProductActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                        sharedPreferences=getSharedPreferences(Key_Preference, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("USER_NAME", userName);
                        editor.putString("PASSWORD", pwd);
                        editor.putBoolean("SAVED", binding.chkSaveInfo.isChecked());
                        editor.commit();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                    // Tài khoản không tồn tại trong Realtime Database
                    // Xử lý tương ứng
                    // ...
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());

            }
        });
    }
}