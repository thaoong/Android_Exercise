package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.nguyenthithao.adapter.BookAdapter;
import com.nguyenthithao.model.Book;

public class AdvancedListBookObjectActivity extends AppCompatActivity {

    EditText edtBookID, edtBookName, edtUnitPrice;
    Button btnInsert, btnUpdate, btnDelete;
    ListView lvBook;
//    ArrayAdapter<Book> bookAdapter;
    BookAdapter bookAdapter;
    Book selectedBook = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_list_book_object);
        addViews();
        makeFakeData();
        addEvents();
    }

    private void addEvents() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                String bookId=edtBookID.getText().toString();
                String bookName=edtBookName.getText().toString();
                float unitPrice=Float.parseFloat(edtUnitPrice.getText().toString());
                book.setBookID(bookId);
                book.setBookName(bookName);
                book.setUnitPrice(unitPrice);

                bookAdapter.add(book);
                edtBookID.setText("");
                edtBookName.setText("");
                edtUnitPrice.setText("");
                edtBookID.requestFocus();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedBook==null)return;
                String bookId=edtBookID.getText().toString();
                String bookName=edtBookName.getText().toString();
                float unitPrice=Float.parseFloat(edtUnitPrice.getText().toString());
                selectedBook.setBookID(bookId);
                selectedBook.setBookName(bookName);
                selectedBook.setUnitPrice(unitPrice);
                bookAdapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDelete();
            }
        });

        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedBook = bookAdapter.getItem(position);
                edtBookID.setText(selectedBook.getBookID());
                edtBookName.setText(selectedBook.getBookName());
                edtUnitPrice.setText(selectedBook.getUnitPrice()+"");
            }
        });
    }

    private void processDelete() {
        if (selectedBook==null)return;
        bookAdapter.remove(selectedBook);
        selectedBook=null;
    }

    private void makeFakeData() {
        Book b1 = new Book("Book1", "Basic mobile Programming", 100);
        bookAdapter.add(b1);
        bookAdapter.add(new Book("Book2", "Machine Learning", 200));
        bookAdapter.add(new Book("Book3", "Algorithms", 300));

    }

    private void addViews() {
        edtBookID = findViewById(R.id.edtBookID);
        edtBookName = findViewById(R.id.edtBookName);
        edtUnitPrice = findViewById(R.id.edtUnitPrice);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvBook = findViewById(R.id.lvBook);
//        bookAdapter = new ArrayAdapter<>(AdvancedListBookObjectActivity.this, android.R.layout.simple_list_item_1);
        bookAdapter = new BookAdapter(AdvancedListBookObjectActivity.this, R.layout.book_item);
        lvBook.setAdapter(bookAdapter);
    }
}