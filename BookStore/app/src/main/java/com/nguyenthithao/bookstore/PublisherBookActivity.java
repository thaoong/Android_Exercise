package com.nguyenthithao.bookstore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.nguyenthithao.adapter.AdvancedBookAdapter;
import com.nguyenthithao.model.Book;
import com.nguyenthithao.model.Publisher;

public class PublisherBookActivity extends AppCompatActivity {
    Spinner spinnerPublisher;
    ArrayAdapter<Publisher> publisherAdapter;
    ListView lvBook;
    AdvancedBookAdapter advancedBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_book);
        addViews();
        makeFakeData();
        addEvents();
    }

    private void addEvents() {
        spinnerPublisher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Publisher selectedPublisher=publisherAdapter.getItem(position);
                advancedBookAdapter.clear();
                advancedBookAdapter.addAll(selectedPublisher.getBooks());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book selectedBook = advancedBookAdapter.getItem(position);
                Intent intent = new Intent(PublisherBookActivity.this, BookDetailsActivity.class);
                intent.putExtra("SELECTED_BOOK", selectedBook);
                startActivity(intent);
            }
        });
    }

    private void makeFakeData() {
        Publisher p1 = new Publisher("p1", "NXB Đại học Quốc gia HCM");
        Publisher p2 = new Publisher("p2", "NXB Kim Đồng");
        Publisher p3 = new Publisher("p3", "NXB Sự Thật");
        publisherAdapter.add(p1);
        publisherAdapter.add(p2);
        publisherAdapter.add(p3);

        Book b1 = new Book("b1", "Book1", 15, R.mipmap.ic_book1);
//        b1.setDescription("<font color='red'>Description of Book 1</font>");
        StringBuilder builder = new StringBuilder();
        builder.append("<table border = '1'>");
        builder.append("<tr>");
        builder.append("<th>#</th>");
        builder.append("<th>Specs</th>");
        builder.append("</tr>");

        builder.append("<tr>");
        builder.append("<td>1</td>");
        builder.append("<td>Spec 1</td>");
        builder.append("</tr>");

        builder.append("<tr>");
        builder.append("<td>2</td>");
        builder.append("<td>Spec 2</td>");
        builder.append("</tr>");

        builder.append("<tr>");
        builder.append("<td>3</td>");
        builder.append("<td>Spec 3</td>");
        builder.append("</tr>");

        builder.append("</table>");
        b1.setDescription(builder.toString());

        p1.getBooks().add(b1);
        b1.setPublisher(p1);

        Book b2 = new Book("b2", "Book2", 17, R.mipmap.ic_book2);
        b2.setDescription("<font color='green'>Description of Book 2</font>");
        p1.getBooks().add(b2);
        b2.setPublisher(p1);

        Book b3 = new Book("b3", "Book3", 35, R.mipmap.ic_book3);
        b3.setDescription("<font color='blue'>Description of Book 3</font>");
        p1.getBooks().add(b3);
        b3.setPublisher(p1);

        Book b4 = new Book("b4", "Book4", 12, R.mipmap.ic_book4);
        b4.setDescription("<font color='yellow'>Description of Book 4</font>");
        p2.getBooks().add(b4);
        b4.setPublisher(p2);

        Book b5 = new Book("b5", "Book5", 15, R.mipmap.ic_book5);
        b5.setDescription("<font color='pink'>Description of Book 5</font>");
        p2.getBooks().add(b5);
        b5.setPublisher(p2);
    }

    private void addViews() {
        spinnerPublisher=findViewById(R.id.spinnerPublisher);
        publisherAdapter=new ArrayAdapter<>(PublisherBookActivity.this, android.R.layout.simple_spinner_item);
        publisherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPublisher.setAdapter(publisherAdapter);
        lvBook=findViewById(R.id.lvBook);
        advancedBookAdapter=new AdvancedBookAdapter(PublisherBookActivity.this, R.layout.advanced_book_item);
        lvBook.setAdapter(advancedBookAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_book, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnuFeedBack){
            Intent intent = new Intent(PublisherBookActivity.this, FeedBackActivity.class);
            startActivityForResult(intent,1);

        }
        else if (item.getItemId()==R.id.mnuHelp) {
            String url = "https://myuel.uel.edu.vn/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.mnuAbout) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==2 && requestCode==1)
        {
            String content=data.getStringExtra("CONTENT_FEEDBACK");
            AlertDialog.Builder builder = new AlertDialog.Builder(PublisherBookActivity.this);
            builder.setTitle("Feedback");
            builder.setMessage(content);
            builder.create().show();
        }
    }
}