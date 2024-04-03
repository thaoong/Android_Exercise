package com.nguyenthithao.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.nguyenthithao.adapter.CategoryAdapter;
import com.nguyenthithao.model.Category;
import com.nguyenthithao.test.databinding.ActivityCategoryBinding;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    CategoryAdapter categoryAdapter;
    public static final String DATABASE_NAME = "test.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_category);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadCategory();
        addEvents();
    }

    private void addEvents() {
        binding.lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category selectedCategory = categoryAdapter.getItem(position);
                Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
                intent.putExtra("SELECTED_CATEGORY", selectedCategory);
                startActivity(intent);
            }
        });
    }

    private void loadCategory() {
        ArrayList<Category> categories = new ArrayList<>();

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM Category", null);
        while(cursor.moveToNext()){
            String categoryID = cursor.getString(0);
            String categoryName = cursor.getString(1);
            Category category = new Category();
            category.setCategoryID(categoryID);
            category.setCategoryName(categoryName);
            categories.add(category);
        }
        cursor.close();
        categoryAdapter = new CategoryAdapter(CategoryActivity.this, R.layout.item_category);
        binding.lvCategory.setAdapter(categoryAdapter);
        categoryAdapter.clear();
        categoryAdapter.addAll(categories);
    }


}