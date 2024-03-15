package com.nguyenthithao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.bookstore.R;
import com.nguyenthithao.model.MyContact;
import com.nguyenthithao.model.Publisher;

public class PublisherAdapter extends ArrayAdapter<Publisher> {
    Activity context;
    int resource;
    public PublisherAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View publishers_item = inflater.inflate(resource, null);
        TextView publisherId = publishers_item.findViewById(R.id.txtPublisherId);
        TextView publisherName = publishers_item.findViewById(R.id.txtPublisherName);

        Publisher publisher=getItem(position);
        publisherId.setText(publisher.getPublisherID());
        publisherName.setText(publisher.getPublisherName());
        return publishers_item;
    }
}
