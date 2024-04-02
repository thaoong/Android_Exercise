package com.nguyenthithao.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.nguyenthithao.adapter.SongAdapter;
import com.nguyenthithao.model.Song;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class KaraokeSqliteActivity extends AppCompatActivity {
    ListView lvAllSong;
    ListView lvFavoriteSong;
    SongAdapter allSongAdapter;
    SongAdapter favoriteSongAdapter;
    TabHost tabHost;

    public static final String DATABASE_NAME = "KaraokeAriang.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    private void copyDataBase(){
        try{
            File dbFile = getDatabasePath(DATABASE_NAME);
            if(!dbFile.exists()){
                if(CopyDBFromAsset()){
                    Toast.makeText(KaraokeSqliteActivity.this,
                            "Copy database successful!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(KaraokeSqliteActivity.this,
                            "Copy database fail!", Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){
            Log.e("Error: ", e.toString());
        }
    }

    private boolean CopyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
        try {
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024]; int length;
            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush();  outputStream.close(); inputStream.close();
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaoke_sqlite);
        addViews();
        copyDataBase();
        addEvents();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("tab1"))
                {
                    loadAllSong();
                } else if (tabId.equalsIgnoreCase("tab2")) {
                    loadFavoriteSong();
                }
            }
        });
    }

    private void loadFavoriteSong() {

    }

    private void loadAllSong() {
        database = openOrCreateDatabase(DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM songs", null);
        while(cursor.moveToNext()){
            String ma = cursor.getString(0);
            String tenbaihat = cursor.getString(1);
            String tacgia = cursor.getString(3);
            int favorite = cursor.getInt(5);
            Song song = new Song(ma, tenbaihat, tacgia, favorite);
            allSongAdapter.add(song);
        }
        cursor.close();
    }

    private void addViews() {
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("All");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Favorite");
        tabHost.addTab(tab2);

        lvAllSong = findViewById(R.id.lvAllSong);
        lvFavoriteSong = findViewById(R.id.lvFavoriteSong);
        allSongAdapter = new SongAdapter(KaraokeSqliteActivity.this, R.layout.song_item);
        favoriteSongAdapter = new SongAdapter(KaraokeSqliteActivity.this, R.layout.song_item);
        lvAllSong.setAdapter(allSongAdapter);
        lvFavoriteSong.setAdapter(favoriteSongAdapter);
    }
}