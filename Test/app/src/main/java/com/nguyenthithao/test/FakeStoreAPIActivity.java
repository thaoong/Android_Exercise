package com.nguyenthithao.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.nguyenthithao.adapter.FakeStoreAdapter;
import com.nguyenthithao.model.FakeStore;
import com.nguyenthithao.task.FakeStoreGSonTask;

import java.util.ArrayList;

public class FakeStoreAPIActivity extends AppCompatActivity {
    ListView lvFakeStore;
    FakeStoreAdapter fakeStoreAdapter;
    ArrayList<FakeStore> dsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_store_apiactivity);
        addViews();
    }

    private void addViews() {
        lvFakeStore=findViewById(R.id.lvFakeStore);
        dsItem = new ArrayList<>();
        fakeStoreAdapter = new FakeStoreAdapter(FakeStoreAPIActivity.this, R.layout.item_fake_store, dsItem);
        lvFakeStore.setAdapter(fakeStoreAdapter);
        FakeStoreGSonTask task = new FakeStoreGSonTask(FakeStoreAPIActivity.this, fakeStoreAdapter);
        task.execute("https://fakestoreapi.com/products");
    }
}