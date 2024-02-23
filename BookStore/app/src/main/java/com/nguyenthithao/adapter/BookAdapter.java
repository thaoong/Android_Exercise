package com.nguyenthithao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.bookstore.R;
import com.nguyenthithao.model.Book;

public class BookAdapter extends ArrayAdapter<Book> {
    Activity context;
    int resource;
    public BookAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=context.getLayoutInflater().inflate(resource, null);
        TextView txtBookId = view.findViewById(R.id.txtBookID);
        TextView txtBookName = view.findViewById(R.id.txtBookName);
        TextView txtUnitPrice = view.findViewById(R.id.txtUnitPrice);

        Book book = getItem(position);
        txtBookId.setText(book.getBookID());
        txtBookName.setText(book.getBookName());
        txtUnitPrice.setText(book.getUnitPrice()+"");

        return view;
    }
}
