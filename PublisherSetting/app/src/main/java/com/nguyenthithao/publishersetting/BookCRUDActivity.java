package com.nguyenthithao.publishersetting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nguyenthithao.adapter.AdvancedBookAdapter;
import com.nguyenthithao.model.Book;
import com.nguyenthithao.model.Publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class BookCRUDActivity extends AppCompatActivity {
    Spinner spinnerPublisher;
    ArrayAdapter<Publisher> publisherAdapter;
    public static final String DATABASE_NAME = "BookStore.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    ListView lvBook;
    AdvancedBookAdapter advancedBookAdapter;
    EditText edtBookId, edtBookName, edtUnitPrice;

    private void copyDataBase(){
        try{
            File dbFile = getDatabasePath(DATABASE_NAME);
            if(!dbFile.exists()){
                if(CopyDBFromAsset()){
                    Toast.makeText(BookCRUDActivity.this,
                            "Copy database successful!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(BookCRUDActivity.this,
                            "Copy database fail!", Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){
            Log.e("Error: ", e.toString());
        }
    }

    private boolean CopyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
        try {
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024]; int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush();  outputStream.close(); inputStream.close();
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_crud);
        addViews();
        loadPublisher();
        addEvents();
        copyDataBase();
    }

    private void addEvents() {
        spinnerPublisher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Publisher selectedPublisher = publisherAdapter.getItem(position);
                loadBookByPublisher(selectedPublisher);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Display on book detail print
//        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Book selectedBook = advancedBookAdapter.getItem(position);
//                Intent intent = new Intent(PublisherBookSqliteCRUDActivity.this, BookDetailsActivity.class);
//                intent.putExtra("SELECTED_BOOK", selectedBook);
//                startActivity(intent);
//            }
//        });

        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = advancedBookAdapter.getItem(position);
                edtBookId.setText(book.getBookID());
                edtBookName.setText(book.getBookName());
                edtUnitPrice.setText(book.getUnitPrice()+"");
            }
        });
    }

    private void loadBookByPublisher(Publisher selectedPublisher) {
        // Remember
        // (1) 1 Publisher has many books
        // (2) 1 Book belongs to a Publisher
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE publisherID='"+selectedPublisher.getPublisherID()+"'";
        database = openOrCreateDatabase(DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext())
        {
            String bookID = cursor.getString(0);
            String bookName = cursor.getString(1);
            float unitPrice = cursor.getFloat(2);
            String description = cursor.getString(3);

            Book book = new Book();
            book.setBookID(bookID);
            book.setBookName(bookName);
            book.setUnitPrice(unitPrice);
            book.setDescription(description);
            book.setPublisher(selectedPublisher);
            books.add(book);
        }
        cursor.close();
        selectedPublisher.setBooks(books);
        advancedBookAdapter.clear();
        advancedBookAdapter.addAll(books);
    }

    private void loadPublisher() {
        database = openOrCreateDatabase(DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM Publisher", null);
        while(cursor.moveToNext()){
            String publisherID = cursor.getString(0);
            String publisherName = cursor.getString(1);
            Publisher publisher = new Publisher(publisherID, publisherName);
            publisherAdapter.add(publisher);
        }
        cursor.close();
    }

    private void addViews() {
        spinnerPublisher=findViewById(R.id.spinnerPublisher);
        publisherAdapter=new ArrayAdapter<>(BookCRUDActivity.this, android.R.layout.simple_spinner_item);
        publisherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPublisher.setAdapter(publisherAdapter);

        lvBook=findViewById(R.id.lvBook);
        advancedBookAdapter=new AdvancedBookAdapter(BookCRUDActivity.this, R.layout.book_item);
        lvBook.setAdapter(advancedBookAdapter);
        edtBookId = findViewById(R.id.edtBookId);
        edtBookName = findViewById(R.id.edtBookName);
        edtUnitPrice = findViewById(R.id.edtUnitPrice);
    }

    public void processNew(View view) {
        edtBookId.setText("");
        edtBookName.setText("");
        edtUnitPrice.setText("");
        edtBookId.requestFocus();
    }

    public void processSave(View view) {
        ContentValues record = new ContentValues();
        record.put("bookID", edtBookId.getText().toString());
        record.put("bookName", edtBookName.getText().toString());
        record.put("unitPrice", Float.parseFloat(edtUnitPrice.getText().toString()));
        Publisher publisher = (Publisher) spinnerPublisher.getSelectedItem();
        record.put("publisherID", publisher.getPublisherID());
        long result = database.insert("Book", null, record);
        if (result>0)
            loadBookByPublisher(publisher);
    }

    public void processUpdate(View view) {
        ContentValues record = new ContentValues();
        record.put("bookName", edtBookName.getText().toString());
        record.put("unitPrice", Float.parseFloat(edtUnitPrice.getText().toString()));
        Publisher publisher = (Publisher) spinnerPublisher.getSelectedItem();
        record.put("publisherID", publisher.getPublisherID());
        String bookID = edtBookId.getText().toString();
        long result = database.update("Book", record, "bookID=?", new String[]{bookID});
        if (result>0){
            loadBookByPublisher(publisher);
        }
    }

    public void processDelete(View view) {//Create builder object
        AlertDialog.Builder builder=new AlertDialog.Builder(BookCRUDActivity.this);
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
                String bookID = edtBookId.getText().toString();
                long result = database.delete("Book", "bookID=?", new String[]{bookID});
                Publisher publisher = (Publisher) spinnerPublisher.getSelectedItem();
                if (result >0){
                    loadBookByPublisher(publisher);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_publisher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnuPublisherSetting)
        {
            Intent intent = new Intent(BookCRUDActivity.this, PublisherCRUDActivity.class);
            startActivityForResult(intent,1);
        }
        return super.onOptionsItemSelected(item);
    }
}