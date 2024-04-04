package com.nguyenthithao.task;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.nguyenthithao.adapter.FakeStoreAdapter;
import com.nguyenthithao.model.FakeStore;
import com.nguyenthithao.model.FakeStoreGSon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FakeStoreGSonTask extends AsyncTask<String, Void, ArrayList<FakeStore>> {
    Activity context;
    FakeStoreAdapter fakeStoreAdapter;

    public FakeStoreGSonTask(Activity context, FakeStoreAdapter fakeStoreAdapter)
    {
        this.context = context;
        this.fakeStoreAdapter = fakeStoreAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.fakeStoreAdapter.clear();
    }

    @Override
    protected void onPostExecute(ArrayList<FakeStore> fakeStores) {
        super.onPostExecute(fakeStores);
        this.fakeStoreAdapter.clear();
        this.fakeStoreAdapter.addAll(fakeStores);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<FakeStore> doInBackground(String... strings) {
        ArrayList<FakeStore> dsItem = new ArrayList<>();
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
//            String gson = builder.toString().replace("(", "").replace(")", "");
            String gson = builder.toString();

            Log.d("DATA_FAKESTORE", gson);
            FakeStoreGSon fakeStoreGSon = new Gson().fromJson(gson, FakeStoreGSon.class);
            for (FakeStore fakeStore:fakeStoreGSon.getItems()) {
                url = new URL(fakeStore.getImageurl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                connection.setRequestProperty("Accept","*/*");
                Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                fakeStore.setImage(bitmap);
            }
            dsItem=fakeStoreGSon.getItems();
        }
        catch (Exception ex) {
            Log.e("LOI", ex.toString());
        }
        return dsItem;    }
}
