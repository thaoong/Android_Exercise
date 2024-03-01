package com.nguyenthithao.exercise21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import com.nguyenthithao.adapter.SongAdapter;
import com.nguyenthithao.model.Song;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvAllSong;
    ListView lvFavoriteSong;
    ArrayList<Song> allSongList;
    ArrayList<Song> favoriteSongList;
    SongAdapter allSongAdapter;
    SongAdapter favoriteSongAdapter;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
        makeFakeData();
        addEvents();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("tab1"))
                {
                    allSongAdapter.notifyDataSetChanged();
                } else if (tabId.equalsIgnoreCase("tab2")) {
                    favoriteSongProcess();
                }
            }
        });
    }

    private void favoriteSongProcess() {
        favoriteSongAdapter.clear();
        for (int i = 0; i < allSongAdapter.getCount(); i++) {
            Song song = allSongAdapter.getItem(i);
            if (song.isLiked())
            {
                favoriteSongAdapter.add(song);
            }
            favoriteSongAdapter.notifyDataSetChanged();
        }
    }

    private void makeFakeData() {
        allSongAdapter.add(new Song("53101", "Em của ngày hôm qua", "Sơn Tùng M-TP", false));
        allSongAdapter.add(new Song("53102", "Người hãy quên em đi", "Mỹ Tâm", false));
        allSongAdapter.add(new Song("53103", "Sai", "Mỹ Tâm", false));
        allSongAdapter.add(new Song("53104", "Champagne problems", "Taylor Swift", true));
        allSongAdapter.add(new Song("53105", "Từ đó", "Phan Mạnh Quỳnh", false));
        allSongAdapter.add(new Song("53105", "Flower", "Miley Cyrus", false));
        allSongAdapter.notifyDataSetChanged();
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
        allSongAdapter = new SongAdapter(MainActivity.this, R.layout.list_view);
        favoriteSongAdapter = new SongAdapter(MainActivity.this, R.layout.list_view);
        lvAllSong.setAdapter(allSongAdapter);
        lvFavoriteSong.setAdapter(favoriteSongAdapter);
    }
}