package com.nguyenthithao.dongabankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.nguyenthithao.adapter.ItemAdapter;
import com.nguyenthithao.model.Item;
import com.nguyenthithao.task.DongAGSonTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTiGia;
    ItemAdapter adapterItem;
    ArrayList<Item> dsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
    }

    private void addViews() {
        lvTiGia=findViewById(R.id.lvTiGia);
        dsItem = new ArrayList<>();
        adapterItem = new ItemAdapter(MainActivity.this, R.layout.item_dong_a, dsItem);
        lvTiGia.setAdapter(adapterItem);
        DongAGSonTask task = new DongAGSonTask(MainActivity.this, adapterItem);
        task.execute("https://dongabank.com.vn/exchange/export");
    }
}