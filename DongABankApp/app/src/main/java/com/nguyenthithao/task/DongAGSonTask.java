package com.nguyenthithao.task;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.nguyenthithao.adapter.ItemAdapter;
import com.nguyenthithao.model.DongAGSon;
import com.nguyenthithao.model.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DongAGSonTask extends AsyncTask<String, Void, ArrayList<Item>> {
    Activity context;
    ItemAdapter adapterItem;

    public DongAGSonTask(Activity context, ItemAdapter adapterItem)
    {
        this.context = context;
        this.adapterItem = adapterItem;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.adapterItem.clear();
    }
    @Override
    protected void onPostExecute(ArrayList<Item> items) {
        super.onPostExecute(items);
        this.adapterItem.clear();
        this.adapterItem.addAll(items);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Item> doInBackground(String... strings) {
        ArrayList<Item> dsItem = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("Accept","*/*");
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            StringBuilder builder = new StringBuilder();
            while (line!=null){
                builder.append(line);
                line=bufferedReader.readLine();
            }
            bufferedReader.close();
            String gson = builder.toString().replace("(", "").replace(")", "");
            Log.d("DATA_DONGA", gson);
            DongAGSon dongAGSon = new Gson().fromJson(gson, DongAGSon.class);
            for (Item item:dongAGSon.getItems()) {
                url = new URL(item.getImageurl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                connection.setRequestProperty("Accept","*/*");
                Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                item.setHinh(bitmap);
            }
            dsItem=dongAGSon.getItems();
        }
        catch (Exception ex) {
            Log.e("LOI", ex.toString());
        }
        return dsItem;
    }
}