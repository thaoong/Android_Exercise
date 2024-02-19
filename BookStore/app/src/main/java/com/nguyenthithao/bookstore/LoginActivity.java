package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;

    TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addViews();
    }

    private void addViews() {
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        txtMessage=findViewById(R.id.txtMessage);
    }

    public void exitApp(View view) {
        finish();
    }

    public void openMainActivity(View view) {

        String userName=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        if (userName.equalsIgnoreCase("admin") && pwd.equals("123"))
        {
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            //Alarm login failed
            txtMessage.setText("Login failed, please check your account again");
        }
    }
}