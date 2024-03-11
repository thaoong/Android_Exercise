package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.nguyenthithao.adapter.AdvancedBookAdapter;
import com.nguyenthithao.model.Book;
import com.nguyenthithao.model.Publisher;

import java.util.ArrayList;

public class PublisherBookSqliteCRUDActivity extends AppCompatActivity {
    Spinner spinnerPublisher;
    ArrayAdapter<Publisher> publisherAdapter;
    public static final String DATABASE_NAME = "BookStore.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    ListView lvBook;
    AdvancedBookAdapter advancedBookAdapter;
    EditText edtBookId, edtBookName, edtUnitPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_book_sqlite_crud);
        addViews();
        loadPublisher();
        addEvents();
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
    }

    private void loadBookByPublisher(Publisher selectedPublisher) {
        // Remember
        // (1) 1 Publisher has many books
        // (2) 1 Book belongs to a Publisher
        ArrayList<Book>books = new ArrayList<>();
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
        publisherAdapter=new ArrayAdapter<>(PublisherBookSqliteCRUDActivity.this, android.R.layout.simple_spinner_item);
        publisherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPublisher.setAdapter(publisherAdapter);

        lvBook=findViewById(R.id.lvBook);
        advancedBookAdapter=new AdvancedBookAdapter(PublisherBookSqliteCRUDActivity.this, R.layout.advanced_book_item);
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
    }

    public void processDelete(View view) {
    }
}