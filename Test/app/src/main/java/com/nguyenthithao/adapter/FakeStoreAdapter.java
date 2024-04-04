package com.nguyenthithao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.model.FakeStore;
import com.nguyenthithao.test.R;

import java.util.List;

public class FakeStoreAdapter extends ArrayAdapter<FakeStore> {
    Activity context;
    int resource;
    List<FakeStore> objects;
    public FakeStoreAdapter(Activity context, int resource, List<FakeStore> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource, null);

        ImageView imgFakeProductImgae = view.findViewById(R.id.imgFakeProductImgae);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        TextView txtDescription = view.findViewById(R.id.txtDescription);
        TextView txtCategory = view.findViewById(R.id.txtCategory);
        TextView txtRate = view.findViewById(R.id.txtRate);
        TextView txtCount = view.findViewById(R.id.txtCount);

        FakeStore product =this.objects.get(position);

        imgFakeProductImgae.setImageBitmap(product.getImage());
        txtTitle.setText(product.getTitle());
        txtPrice.setText(product.getPrice()+"");
        txtDescription.setText(product.getDescription());
        txtCategory.setText(product.getCategory());
        txtRate.setText(product.getRate()+"");
        txtCount.setText(product.getCount()+"");

        return view;
    }
}
