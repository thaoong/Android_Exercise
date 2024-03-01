package com.nguyenthithao.exercise11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.nguyenthithao.adapter.ContactAdapter;
import com.nguyenthithao.model.Contact;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
        makeFakeData();
    }

    private void makeFakeData() {
        contactAdapter.add(new Contact("2-MBBank - Phạm Thị Xuân Diệu", "0976543210"));
        contactAdapter.add(new Contact("4-Hưng Gia - Nguyễn Thị Phương Oanh", "0884328977"));
        contactAdapter.add(new Contact("5-Hưng Gia - CTY TNHH PHAM LAM", "07765487335"));
        contactAdapter.add(new Contact("6-Hưng Gia - THAI ANH THU", "0976543210"));
    }

    private void addViews() {
        lvContact = findViewById(R.id.lvPhoneBook);
        contactAdapter = new ContactAdapter(MainActivity.this, R.layout.list_item);
        lvContact.setAdapter(contactAdapter);
    }
}