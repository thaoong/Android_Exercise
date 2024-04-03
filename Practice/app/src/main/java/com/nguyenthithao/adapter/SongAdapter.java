package com.nguyenthithao.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.model.Song;
import com.nguyenthithao.practice.KaraokeSqliteActivity;
import com.nguyenthithao.practice.R;

public class SongAdapter extends ArrayAdapter<Song> {
    Activity context;
    int resource;
    public SongAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(resource, null);
        TextView txtSongID = view.findViewById(R.id.txtSongID);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtSinger = view.findViewById(R.id.txtSinger);

        ImageButton btnLike = view.findViewById(R.id.btnLike);
        ImageButton btnDislike = view.findViewById(R.id.btnDislike);

        Song song = getItem(position);
        txtSongID.setText(song.getMa());
        txtName.setText(song.getTenbaihat());
        txtSinger.setText(song.getTacgia());
        if (song.getFavorite() == 1) {
            btnLike.setVisibility(View.GONE);
            btnDislike.setVisibility(View.VISIBLE);
        } else if(song.getFavorite() == 0) {
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.GONE);
        }

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeProcess(song);
                btnLike.setVisibility(View.GONE);
                btnDislike.setVisibility(View.VISIBLE);
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislikeProcess(song);
                removeSong(position);
            }
        });
        return view;
    }

    private void dislikeProcess(Song song) {
        ContentValues row = new ContentValues();
        row.put("favorite", 0);
        KaraokeSqliteActivity.database.update("songs", row, "ma=?", new String[]{song.getMa()});
    }

    private void removeSong(int position) {
        remove(getItem(position));
        notifyDataSetChanged();
    }

    private void likeProcess(Song song) {
        ContentValues row = new ContentValues();
        row.put("favorite", 1);
        KaraokeSqliteActivity.database.update("songs", row, "ma=?", new String[]{song.getMa()});
    }
}
