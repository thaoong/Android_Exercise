package com.nguyenthithao.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.exercise11.R;
import com.nguyenthithao.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    public ContactAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(resource, null);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);

        Contact contact =getItem(position);
        txtName.setText(contact.getName());
        txtPhoneNumber.setText(contact.getPhoneNumber());
        return view;
    }
}
