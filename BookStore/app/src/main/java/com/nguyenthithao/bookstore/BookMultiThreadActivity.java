package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenthithao.model.Book;

import java.util.Random;

public class BookMultiThreadActivity extends AppCompatActivity {
    EditText edtNumberOfBook;
    Button btnCreateBook;
    TextView txtPercent;
    ProgressBar progressBarPercent;
    LinearLayout llBook;
    int numberOfBook;
    int percent = 0;
    Book book;
    Handler handler = new Handler();
    Runnable foregroundThread;
    Thread backgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_multi_thread);
        addViews();
        addEvents();
        assignThread();
    }

    private void assignThread() {
        foregroundThread = new Runnable() {
            @Override
            public void run() {
                txtPercent.setText(percent +"%");
                progressBarPercent.setProgress(percent);
                if (percent==100)
                    return;;
                Button button = new Button(BookMultiThreadActivity.this);
                button.setText(book.toString());
                button.setWidth(300);
                button.setHeight(50);
                button.setTag(book);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button selectedButton = (Button)v;
                        selectedButton.setTextColor(Color.RED);
                        Book selectedBook = (Book) selectedButton.getTag();
                        Toast.makeText(
                                BookMultiThreadActivity.this, selectedBook.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                llBook.addView(button);
            }
        };
        backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i=0; i<numberOfBook; i++)
                {
                    book = new Book();
                    book.setBookID("ID " +i);
                    book.setBookName("Name" +i);
                    book.setUnitPrice(20+random.nextInt(500));
                    // bắn book và percent về foreground thread
                    percent = i*100/numberOfBook;
                    handler.post(foregroundThread);
                    SystemClock.sleep(50);
                }
                percent = 100;
                handler.post(foregroundThread);
            }
        });
    }

    private void addEvents() {
        btnCreateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processCreatingBook();
            }
        });
    }

    private void processCreatingBook() {
        llBook.removeAllViews();
        percent = 0;
        numberOfBook = Integer.parseInt(edtNumberOfBook.getText().toString());
        if (backgroundThread.isAlive()==false)
            backgroundThread.start();
    }

    private void addViews() {
        edtNumberOfBook = findViewById(R.id.edtNumberOfBook);
        btnCreateBook = findViewById(R.id.btnCreateBook);
        txtPercent = findViewById(R.id.txtPercent);
        progressBarPercent = findViewById(R.id.progressBarPercent);
        llBook = findViewById(R.id.llBook);
    }
}