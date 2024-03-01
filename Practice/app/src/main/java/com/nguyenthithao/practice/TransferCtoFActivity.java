package com.nguyenthithao.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TransferCtoFActivity extends AppCompatActivity {
    EditText edtF, edtC;
    Button btnCtoF, btnFtoC, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_cto_factivity);
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnCtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double c = Double.parseDouble(edtC.getText().toString());
                double f = c*9/5 + 32;
                edtF.setText(String.valueOf(f));
            }
        });

        btnFtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double f = Double.parseDouble(edtF.getText().toString());
                double c = (f-32) * 5/9;
                edtC.setText(String.valueOf(c));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtF.setText("");
                edtC.setText("");
                edtF.requestFocus();
            }
        });
    }

    private void addViews() {
        edtC = findViewById(R.id.edtC);
        edtF = findViewById(R.id.edtF);
        btnCtoF = findViewById(R.id.btnCtoF);
        btnFtoC = findViewById(R.id.btnFtoC);
        btnClear = findViewById(R.id.btnClear);
    }
}