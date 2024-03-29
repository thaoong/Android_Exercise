package com.nguyenthithao.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.bookstore.R;
import com.nguyenthithao.model.DongAItem;

import java.util.ArrayList;
import java.util.List;

public class DongAItemAdapter extends ArrayAdapter<DongAItem> {
    Activity context;
    int resource;
    List<DongAItem> objects;

    public DongAItemAdapter(@NonNull Activity context, int resource, @NonNull List<DongAItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource, null);

        ImageView imgHinh = view.findViewById(R.id.imgHinh);
        TextView txtMuaTM = view.findViewById(R.id.txtMuaTM);
        TextView txtBanTM = view.findViewById(R.id.txtBanTM);
        TextView txtMuaCK = view.findViewById(R.id.txtMuaCK);
        TextView txtBanCK = view.findViewById(R.id.txtBanCK);

        TextView txtType = view.findViewById(R.id.txtType);

        DongAItem tigia = this.objects.get(position);
        imgHinh.setImageBitmap(tigia.getHinh());
        txtMuaCK.setText(tigia.getMuack());
        txtMuaTM.setText(tigia.getMuatienmat());
        txtBanCK.setText(tigia.getBanck());
        txtBanTM.setText(tigia.getBantienmat());
        txtType.setText(tigia.getType());

        return view;
    }
}
