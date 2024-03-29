package com.nguyenthithao.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class BookGalleryActivity extends AppCompatActivity {
    EditText edtPeriod;
    CheckBox chkAuto;
    Button btnLoadImage;
    ImageView imgImageFromInternet;

    String imageUrl = "https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2024/3/26/troussier4-17112404408401406674453-1711415840203405149779.jpg";
    String []arrImageURLs = {"https://salt.tikicdn.com/cache/280x280/ts/product/c4/8b/88/ea2eee19605410c02a148389522b3066.jpg",
            "https://salt.tikicdn.com/cache/280x280/ts/product/18/d3/be/38769d994acb00f58caa74f878df1ec0.jpg",
            "https://salt.tikicdn.com/cache/280x280/ts/product/4c/66/2b/ea606cc16958ebcac98e7b00a1aed9b3.jpg",
            "https://salt.tikicdn.com/cache/280x280/ts/product/28/81/a2/3814b33a6cd24f60c4ccb441b4919ec8.jpg",
            "https://salt.tikicdn.com/cache/280x280/ts/product/b4/4f/06/24839081e07c36ec46f43be6e1e47909.jpg",
            "https://salt.tikicdn.com/cache/280x280/ts/product/d9/68/90/54adee8b3573ce377e4fb50943a52f4f.jpg",
            "https://salt.tikicdn.com/cache/280x280/ts/product/80/67/af/753a66ef25086f469826f3ee4716f751.jpg",
                            };
    int step = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_gallery);
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkAuto.isChecked()==false)
                    processLoadImage();
                else
                    processLoadImageTimer();
            }
        });
    }

    private void processLoadImageTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (step>=arrImageURLs.length)
                    step = 0;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String imgURL = arrImageURLs[step];
                        ImageAsyncTask imageAsyncTask = new ImageAsyncTask();
                        imageAsyncTask.execute(imgURL);
                        if (step % 2 == 0) {
                            Animation animation = AnimationUtils.loadAnimation(BookGalleryActivity.this, R.anim.rotate_right);
                            imgImageFromInternet.setAnimation(animation);
                        } else {
                            Animation animation = AnimationUtils.loadAnimation(BookGalleryActivity.this, R.anim.rotate_left);
                            imgImageFromInternet.setAnimation(animation);
                        }
                        step = step+1;
                    }
                });

            }
        };
        step = 0;
        int period = Integer.parseInt(edtPeriod.getText().toString())*1000;
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, period);
    }

    private void processLoadImage() {
        ImageAsyncTask task = new ImageAsyncTask();
        task.execute(imageUrl);
    }

    private void addViews() {
        edtPeriod = findViewById(R.id.edtPeriod);
        chkAuto = findViewById(R.id.chkAuto);
        btnLoadImage = findViewById(R.id.btnLoadImage);
        imgImageFromInternet = findViewById(R.id.imgImageFromInternet);
    }
    class ImageAsyncTask extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imgImageFromInternet.setImageResource(R.mipmap.ic_no_image);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String link = strings[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;
            }
            catch (Exception ex)
            {
                Log.e("ERROR_LOAD_IMAGE", ex.toString());
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgImageFromInternet.setImageBitmap(bitmap);
        }
    }
}