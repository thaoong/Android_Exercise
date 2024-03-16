package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenthithao.adapter.PublisherAdapter;
import com.nguyenthithao.model.Publisher;

import java.util.ArrayList;

public class PublisherCRUDActivity extends AppCompatActivity {
    EditText edtPublisherId, edtPublisherName;
    ListView lvPublisher;
    PublisherAdapter publisherAdapter;
    public static final String DATABASE_NAME = "BookStore.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_crudactivity);
        addViews();
        loadPublisher();
        addEvents();
    }

    private void addEvents() {
        lvPublisher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Publisher publisher = publisherAdapter.getItem(position);
                edtPublisherId.setText(publisher.getPublisherID());
                edtPublisherName.setText(publisher.getPublisherName());
            }
        });
    }

    private void loadPublisher() {
        ArrayList<Publisher> publishers = new ArrayList<>();

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM Publisher", null);
        while(cursor.moveToNext()){
            String publisherID = cursor.getString(0);
            String publisherName = cursor.getString(1);

            Publisher publisher = new Publisher();
            publisher.setPublisherID(publisherID);
            publisher.setPublisherName(publisherName);
            publishers.add(publisher);
        }
        cursor.close();
        publisherAdapter.clear();
        publisherAdapter.addAll(publishers);
    }

    public void processNew(View view) {
        edtPublisherId.setText("");
        edtPublisherName.setText("");
        edtPublisherId.requestFocus();
    }

    public void processSave(View view) {
        ContentValues record = new ContentValues();
        record.put("publisherID", edtPublisherId.getText().toString());
        record.put("publisherName", edtPublisherName.getText().toString());
        long result = database.insert("Publisher", null, record);
        if (result>0)
        {
            loadPublisher();
            Toast.makeText(PublisherCRUDActivity.this, "Add publisher successful!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(PublisherCRUDActivity.this, "Add publisher fail!", Toast.LENGTH_LONG).show();
        }
    }

    public void processUpdate(View view) {
        ContentValues record = new ContentValues();
        record.put("publisherID", edtPublisherId.getText().toString());
        record.put("publisherName", edtPublisherName.getText().toString());
        String publisherID = edtPublisherId.getText().toString();
        long result = database.update("Publisher", record, "publisherID=?", new String[]{publisherID});
        if (result>0){
            loadPublisher();
            Toast.makeText(PublisherCRUDActivity.this, "Update publisher successful!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(PublisherCRUDActivity.this, "Update publisher fail!", Toast.LENGTH_LONG).show();
        }
    }

    public void processDelete(View view) {//Create builder object
        AlertDialog.Builder builder=new AlertDialog.Builder(PublisherCRUDActivity.this);
        //set title:
        builder.setTitle("Confirm Delete");
        //set message
        builder.setMessage("Are you sure want to delete?");
        //set icon
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //set actions button for user interaction
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String publisherID = edtPublisherId.getText().toString();
                long result = database.delete("Publisher", "publisherID=?", new String[]{publisherID});
                if (result >0){
                    loadPublisher();
                    Toast.makeText(PublisherCRUDActivity.this, "Delete publisher successful!", Toast.LENGTH_LONG).show();
                    processNew(view);
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //Create Dialog object;
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        //Show dialog
        dialog.show();
    }

    private void addViews() {
        edtPublisherId = findViewById(R.id.edtBookId);
        edtPublisherName = findViewById(R.id.edtBookName);
        lvPublisher = findViewById(R.id.lvBook);
        publisherAdapter = new PublisherAdapter(PublisherCRUDActivity.this, R.layout.publishers_item);
        lvPublisher.setAdapter(publisherAdapter);
    }
}