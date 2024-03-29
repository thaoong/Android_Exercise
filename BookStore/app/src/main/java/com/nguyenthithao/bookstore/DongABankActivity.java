package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.nguyenthithao.adapter.DongAItemAdapter;
import com.nguyenthithao.model.DongAItem;
import com.nguyenthithao.task.DongAGSonTask;

import java.util.ArrayList;

public class DongABankActivity extends AppCompatActivity {
    ListView lvTiGia;
    DongAItemAdapter adapterItem;
    ArrayList<DongAItem> dsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_abank);
        addViews();
    }

    private void addViews() {
        lvTiGia=findViewById(R.id.lvTiGia);
        dsItem = new ArrayList<>();
        adapterItem = new DongAItemAdapter(DongABankActivity.this, R.layout.item_dong_a, dsItem);
        lvTiGia.setAdapter(adapterItem);
        DongAGSonTask task = new DongAGSonTask(DongABankActivity.this, adapterItem);
        task.execute("https://dongabank.com.vn/exchange/export");
    }
}