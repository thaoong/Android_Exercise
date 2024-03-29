package com.nguyenthithao.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.dongabankapp.R;
import com.nguyenthithao.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    Activity context;
    int resource;
    List<Item> objects;
    public ItemAdapter(Activity context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource, null);

        ImageView imgHinh = view.findViewById(R.id.imgHinh);
        TextView txtMuaTM = view.findViewById(R.id.txtMuaTM);
        TextView txtBanTM = view.findViewById(R.id.txtBanTM);
        TextView txtMuaCK = view.findViewById(R.id.txtMuaCK);
        TextView txtBanCK = view.findViewById(R.id.txtBanCK);

        TextView txtType = view.findViewById(R.id.txtType);

        Item tigia = this.objects.get(position);
        imgHinh.setImageBitmap(tigia.getHinh());
        txtMuaCK.setText(tigia.getMuack());
        txtMuaTM.setText(tigia.getMuatienmat());
        txtBanCK.setText(tigia.getBanck());
        txtBanTM.setText(tigia.getBantienmat());
        txtType.setText(tigia.getType());

        return view;
    }
}
