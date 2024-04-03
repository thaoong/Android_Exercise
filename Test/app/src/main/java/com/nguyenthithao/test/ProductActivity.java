package com.nguyenthithao.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.nguyenthithao.adapter.CategoryAdapter;
import com.nguyenthithao.adapter.ProductAdapter;
import com.nguyenthithao.model.Category;
import com.nguyenthithao.model.Product;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    ProductAdapter productAdapter;
    ListView lvProduct;
    TextView tx;
    public static final String DATABASE_NAME = "test.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        addViews();
        getDataFromPreviousActivity();
    }

    private void getDataFromPreviousActivity() {
        Intent intent = getIntent();
        Category selectedCategory = (Category) intent.getSerializableExtra("SELECTED_CATEGORY");
        if (selectedCategory!=null)
        {
            loadProductByCategory(selectedCategory);
        }
    }

    private void loadProductByCategory(Category selectedCategory) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE CategoryID='"+selectedCategory.getCategoryID()+"'";
        database = openOrCreateDatabase(DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext())
        {
            String productID = cursor.getString(0);
            String productName = cursor.getString(1);
            float unitPrice = cursor.getFloat(2);
            String imageLink = cursor.getString(3);

            Product product = new Product();
            product.setProductID(productID);
            product.setProductName(productName);
            product.setUnitPrice(unitPrice);
            product.setImageLink(imageLink);
//            product.setCategoryID(selectedCategory.getCategoryID());
            products.add(product);
        }
        cursor.close();
        productAdapter.clear();
        productAdapter.addAll(products);
    }



    private void addViews() {
        tx = findViewById(R.id.textView5);
        lvProduct = findViewById(R.id.lvProduct);
        productAdapter = new ProductAdapter(ProductActivity.this, R.layout.item_product);
        lvProduct.setAdapter(productAdapter);
    }
}