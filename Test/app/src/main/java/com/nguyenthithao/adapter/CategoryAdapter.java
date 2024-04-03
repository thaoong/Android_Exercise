package com.nguyenthithao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.model.Category;
import com.nguyenthithao.test.R;

public class CategoryAdapter extends ArrayAdapter<Category> {
    Activity context;
    int resource;
    public CategoryAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View category_item = inflater.inflate(resource, null);
        TextView CategoryName = category_item.findViewById(R.id.txtCategoryName);

        Category category =getItem(position);
        CategoryName.setText(category.getCategoryName());

        return category_item;
    }
}
