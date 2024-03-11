package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class FeedBackActivity extends AppCompatActivity {
    EditText edtContentFeedback;
    Button btnSubmitFeedback;
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
        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtContentFeedback.getText().toString();
                previousIntent.putExtra("CONTENT_FEEDBACK", content);
                setResult(2, previousIntent);
                finish();
            }
        });
    }

    private void getDataFromPreviousActivity() {
        previousIntent = getIntent();
    }

    private void addViews() {
        edtContentFeedback=findViewById(R.id.edtContentFeebBack);
        btnSubmitFeedback=findViewById(R.id.btnSubmitFeebBack);
    }
}