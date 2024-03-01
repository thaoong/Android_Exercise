package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenthithao.model.Book;

public class BookDetailsActivity extends AppCompatActivity {
    ImageView imgBook;
    TextView txtBookId, txtBookName, txtUnitPrice, txtDescription;
    WebView webViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        addViews();
        getDataFromPreviousActivity();
    }

    private void getDataFromPreviousActivity() {
        // get started intent from previous activity:
        Intent intent = getIntent();
        // get data from started intent
        Book selectedBook = (Book) intent.getSerializableExtra("SELECTED_BOOK");
        if (selectedBook!=null)
        {
         imgBook.setImageResource(selectedBook.getImageID());
         txtBookId.setText(selectedBook.getBookID());
         txtBookName.setText(selectedBook.getBookName());
         txtUnitPrice.setText(selectedBook.getUnitPrice()+"");
         // cuz description is a html format
         Spanned spannedDescription = Html.fromHtml(selectedBook.getDescription(), 1);
         txtDescription.setText(spannedDescription);

         String encodeHtml = Base64.encodeToString(
                 selectedBook.getDescription().getBytes(),
                 Base64.NO_PADDING);
         webViewDescription.loadData(encodeHtml, "text/html", "base64");
        }
    }

    private void addViews() {
        imgBook = findViewById(R.id.imgBook);
        txtBookId = findViewById(R.id.txtBookId);
        txtBookName = findViewById(R.id.txtBookName);
        txtUnitPrice = findViewById(R.id.txtUnitPrice);
        txtDescription = findViewById(R.id.txtDescription);
        webViewDescription = findViewById(R.id.webviewDescription);
    }
}