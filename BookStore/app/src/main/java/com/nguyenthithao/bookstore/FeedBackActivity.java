package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FeedBackActivity extends AppCompatActivity {
    EditText edtContentFeedBack;
    Button btnSubmitFeedBack;
    Intent previousIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        addViews();
        getDataFromPreviousActivity();
        addEvents();
    }

    private void addEvents() {
        String content = edtContentFeedBack.getText().toString();
        previousIntent.putExtra("CONTENT_FEEDBACK", content);
        setResult(2, previousIntent);
        finish();
    }

    private void getDataFromPreviousActivity() {
        previousIntent=getIntent();
    }

    private void addViews() {
        edtContentFeedBack=findViewById(R.id.edtContentFeebBack);
        btnSubmitFeedBack=findViewById(R.id.btnSubmitFeebBack);
    }
}