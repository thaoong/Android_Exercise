package com.nguyenthithao.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenthithao.adapter.ProductAdapter;
import com.nguyenthithao.model.Product;

public class ProductActivity extends AppCompatActivity {
    ListView lvProduct;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        addViews();
        getProductsFromFirebase();
    }

    private void getProductsFromFirebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Products");
        productAdapter.clear();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()){
                    Product product = dss.getValue(Product.class);
                    String key = dss.getKey();
                    product.setID(key);
                    productAdapter.add(product);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void addViews() {
        lvProduct = findViewById(R.id.lvProduct);
        productAdapter = new ProductAdapter(this, R.layout.item_product);
        lvProduct.setAdapter(productAdapter);
    }
}