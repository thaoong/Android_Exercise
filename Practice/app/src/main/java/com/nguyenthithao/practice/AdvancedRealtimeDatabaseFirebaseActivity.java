package com.nguyenthithao.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenthithao.adapter.ContactAdapter;
import com.nguyenthithao.model.Contact;

public class AdvancedRealtimeDatabaseFirebaseActivity extends AppCompatActivity {
    ListView lvContact;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_realtime_database_firebase);
        addViews();
        getContactsFromFirebase();
        addEvents();
    }

    private void addEvents() {
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactAdapter.getItem(position);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("contacts");
                myRef.child(contact.getContactId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AdvancedRealtimeDatabaseFirebaseActivity.this, "Remove contact successfully", Toast.LENGTH_LONG).show();
                        contactAdapter.remove(contact);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdvancedRealtimeDatabaseFirebaseActivity.this, "Error occurs: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
    }

    private void getContactsFromFirebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("contacts");
        contactAdapter.clear();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()){
                    Contact contact = dss.getValue(Contact.class);
                    String key = dss.getKey();
                    contact.setContactId(key);
                    contactAdapter.add(contact);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void addViews() {
        lvContact = findViewById(R.id.lvContact);
        contactAdapter = new ContactAdapter(this, R.layout.contact_item);
        lvContact.setAdapter(contactAdapter);
    }
}