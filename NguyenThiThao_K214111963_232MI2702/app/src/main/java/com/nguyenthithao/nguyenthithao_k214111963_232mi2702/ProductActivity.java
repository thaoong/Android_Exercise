package com.nguyenthithao.nguyenthithao_k214111963_232mi2702;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nguyenthithao.adapter.ProductAdapter;
import com.nguyenthithao.model.Product;
import com.nguyenthithao.nguyenthithao_k214111963_232mi2702.databinding.ActivityProductBinding;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    ProductAdapter productAdapter;
    public static final String DATABASE_NAME = "Apsara.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    private static final int REQUEST_CODE_ADD_PRODUCT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadProducts();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List of Products");
    }

    private void loadProducts() {
        ArrayList<Product> products = new ArrayList<>();

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM Products", null);
        while(cursor.moveToNext()){
            String ID = cursor.getString(0);
            String productCode = cursor.getString(1);
            String productName = cursor.getString(2);
            float unitPrice = cursor.getFloat(3);
            String imageLink = cursor.getString(4);

            Product product = new Product();
            product.setID(ID);
            product.setProductCode(productCode);
            product.setProductName(productName);
            product.setUnitPrice(unitPrice);
            product.setImageLink(imageLink);
            products.add(product);
        }
        cursor.close();
        productAdapter = new ProductAdapter(ProductActivity.this, R.layout.item_product);
        binding.lvProduct.setAdapter(productAdapter);
        productAdapter.clear();
        productAdapter.addAll(products);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuAdd)
        {
            Intent intent = new Intent(ProductActivity.this, AddProductActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT);
        }
        if(item.getItemId()==R.id.mnuAbout)
        {
            Intent intent = new Intent(ProductActivity.this, AboutActivity.class);
            startActivityForResult(intent,1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_PRODUCT && resultCode == RESULT_OK) {
            // Reload the list of products
            loadProducts();
        }
    }
}