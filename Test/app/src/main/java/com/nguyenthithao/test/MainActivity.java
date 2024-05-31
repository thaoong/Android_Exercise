package com.nguyenthithao.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ai.client.generativeai.java.ChatFutures;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.android.material.textfield.TextInputEditText;
import com.nguyenthithao.model.GeminiPro;
import com.nguyenthithao.model.ResponseCallback;
import com.nguyenthithao.test.databinding.ActivityMainBinding;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {
    //ActivityMainBinding binding;
    private TextInputEditText queryEditText;
    private Button sendQueryButton;
    private ProgressBar progressBar;
    private LinearLayout chatBodyContainer;
    private ChatFutures chatModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        chatModel = getChatModel();

        queryEditText = findViewById(R.id.queryEditText);
        sendQueryButton = findViewById(R.id.sendPromptButton);
        progressBar = findViewById(R.id.sendPromptProgressBar);
        chatBodyContainer = findViewById(R.id.chatResponseLayout);

        sendQueryButton.setOnClickListener(v -> {
            String query = queryEditText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);

            queryEditText.setText("");
            populateChatBody("You", query, getDate());

            GeminiPro.getResponse(chatModel, query, new ResponseCallback() {
                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.GONE);
                    populateChatBody("GeminiPro", response, getDate());
                }

                @Override
                public void onError(Throwable throwable) {
                    populateChatBody("GeminiPro", "Sorry, I'm having trouble understanding that. Please try again.", getDate());
                    progressBar.setVisibility(View.GONE);
                }
            });
        });
    }

    private ChatFutures getChatModel() {
        GeminiPro model = new GeminiPro();
        GenerativeModelFutures modelFutures = model.getModel();

        return modelFutures.startChat();
    }

    public void populateChatBody(String userName, String message, String date) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chat_message_block, null);

        TextView userAgentName = view.findViewById(R.id.userAgentNameTextView);
        TextView userAgentMessage = view.findViewById(R.id.userAgentMessageTextView);
        TextView dateTextView = view.findViewById(R.id.dateTextView);

        userAgentName.setText(userName);
        userAgentMessage.setText(message);
        dateTextView.setText(date);

        chatBodyContainer.addView(view);

        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }

    private String getDate() {
        Instant instant = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm").withZone(ZoneId.systemDefault());

        return formatter.format(instant);
    }
}