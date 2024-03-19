package com.nguyenthithao.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenthithao.model.MyContact;

import java.util.Random;

public class MyContactMultiThreadActivity extends AppCompatActivity {

    EditText edtNumberOfContact;
    Button btnLoadContact;
    TextView txtPercent;
    ProgressBar progressBarPercent;
    ListView lvContact;
    ArrayAdapter<MyContact> contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact_multi_thread);
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnLoadContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //processLoadingContact();
                processMultiThreadLoadingContact();
            }
        });
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            // Update UI here, cause it's Main Thread/UI Thread
            // gói tin nó lưu trong đối tượng message
            int percent = msg.arg1;
            txtPercent.setText(percent+"%");
            progressBarPercent.setProgress(percent);
            if (percent==100)
            {
                Toast.makeText(MyContactMultiThreadActivity.this, "Finished", Toast.LENGTH_LONG).show();
                return false;
            }
            MyContact contact = (MyContact) msg.obj;
            contactAdapter.add(contact);
            return false;
        }
    });

    private void processMultiThreadLoadingContact() {
        int n = Integer.parseInt(edtNumberOfContact.getText().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i=0; i<n; i++){
                    String name = "name "+i;
                    String phone = "098";
                    for (int j=0; j<7; j++){
                        phone+=random.nextInt(10);
                    }
                    MyContact contact = new MyContact(name, phone);
                    Message message = handler.obtainMessage();
                    message.obj = contact;
                    int percent = i*100/n;
                    message.arg1 = percent;
                    // send data to main thread (ui thread)
                    handler.sendMessage(message);
                    SystemClock.sleep(100);
                }
                Message message = handler.obtainMessage();
                int percent = 100;
                message.arg1 = percent;
                // send data to main thread (ui thread)
                handler.sendMessage(message);
            }
        });
        // active background thread
        thread.start();
    }

    private void processLoadingContact() {
        contactAdapter.clear();
        int n = Integer.parseInt(edtNumberOfContact.getText().toString());
        Random random = new Random();
        for (int i=0; i<n; i++){
            String name = "name "+i;
            String phone = "098";
            for (int j=0; j<7; j++){
                phone+=random.nextInt(10);
            }
            MyContact contact = new MyContact(name, phone);
            contactAdapter.add(contact);
        }
    }

    private void addViews() {
        edtNumberOfContact = findViewById(R.id.edtNumberOfContact);
        btnLoadContact = findViewById(R.id.btnLoadContact);
        txtPercent = findViewById(R.id.txtPercent);
        progressBarPercent = findViewById(R.id.progressBarPercent);
        lvContact = findViewById(R.id.lvContact);
        contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvContact.setAdapter(contactAdapter);
    }
}