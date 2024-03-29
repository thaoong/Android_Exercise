package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class NumberASyncTaskActivity extends AppCompatActivity {
    EditText edtNumberButton;
    Button btnDrawRandomNumber;
    TextView txtPercent;
    ProgressBar progressBarPercent;
    TextView txtSumOfRandomNumber;
    LinearLayout llButton;

    class MyButtonAsyncTask extends AsyncTask<Integer, Integer, Integer>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtPercent.setText("0%");
            progressBarPercent.setProgress(0);
            txtSumOfRandomNumber.setText("");
            llButton.removeAllViews();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int n = integers[0];
            int sum = 0;
            Random random = new Random();
            for (int i=0; i<n; i++) {
                int value = random.nextInt(500);
                int percent = i*100/n;
                publishProgress(value, percent);
                SystemClock.sleep(50);
                sum+=value;
            }
            return sum;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[0];
            int percent = values[1];
            Button btn = new Button(NumberASyncTaskActivity.this);
            btn.setText(value+"");
            btn.setWidth(100);
            btn.setHeight(50);
            llButton.addView(btn);
            txtPercent.setText(percent+"%");
            progressBarPercent.setProgress(percent);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            txtPercent.setText("100%");
            progressBarPercent.setProgress(100);
            txtSumOfRandomNumber.setText(integer+"");
            llButton.removeAllViews();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_async_task);
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnDrawRandomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(edtNumberButton.getText().toString());
                MyButtonAsyncTask task = new MyButtonAsyncTask();
                task.execute(n);
            }
        });
    }

    private void addViews() {
        edtNumberButton = findViewById(R.id.edtNumberOfButton);
        btnDrawRandomNumber = findViewById(R.id.btnRandomNumber);
        txtPercent = findViewById(R.id.txtPercent);
        progressBarPercent = findViewById(R.id.progressBarPercent);
        txtSumOfRandomNumber = findViewById(R.id.txtSumOfRandomNumber);
        llButton = findViewById(R.id.llButton);
    }
}