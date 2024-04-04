package com.nguyenthithao.nguyenthithao_k214111963_232mi2702;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nguyenthithao.nguyenthithao_k214111963_232mi2702.databinding.ActivityAddProductBinding;

public class AddProductActivity extends AppCompatActivity {
    ActivityAddProductBinding binding;
    public static final String DATABASE_NAME = "Apsara.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_product);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Product");
    }

    public void processSave(View view) {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        ContentValues record = new ContentValues();
        record.put("ID", binding.edtProductID.getText().toString());
        record.put("ProductCode", binding.edtProductCode.getText().toString());
        record.put("ProductName", binding.edtProductName.getText().toString());
        record.put("UnitPrice", binding.edtUnitPrice.getText().toString());

        long result = database.insert("Products", null, record);
        if (result>0)
        {
            Toast.makeText(AddProductActivity.this, "Add product successfully!", Toast.LENGTH_LONG).show();
            // After adding the product
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
        }
        else
        {
            Toast.makeText(AddProductActivity.this, "Add product failed!", Toast.LENGTH_LONG).show();
        }
    }
}