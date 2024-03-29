package com.nguyenthithao.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    ArrayAdapter<String>contactAdapter;
    String TAG="FIREBASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
        addEvents();
    }

    private void addEvents() {
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = contactAdapter.getItem(position);
                String key = data.split("\n")[0];
                Intent intent = new Intent(MainActivity.this, DetailContactActivity.class);
                intent.putExtra("KEY", key);
                startActivity(intent);
            }
        });
    }

    private void addViews() {
        lvContact=findViewById(R.id.lvContact);
        contactAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        lvContact.setAdapter(contactAdapter);
        loadData();
    }

    private void loadData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactAdapter.clear();
                for (DataSnapshot data: snapshot.getChildren())
                {
                    String key = data.getKey();
                    String value = data.getValue().toString();
                    contactAdapter.add(key + "\n" + value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void openInsertContact(View view) {
        Intent intent = new Intent(MainActivity.this, InsertContactActivity.class);
        startActivity(intent);
    }
}