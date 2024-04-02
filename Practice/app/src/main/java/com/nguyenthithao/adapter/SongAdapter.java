package com.nguyenthithao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthithao.model.Song;
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
        } else {
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.GONE);
        }

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeProcess(song);
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislikeProcess(song);
            }
        });
        return view;
    }

    private void dislikeProcess(Song song) {
        song.setFavorite(1);
        notifyDataSetChanged();
    }

    private void likeProcess(Song song) {
        song.setFavorite(0);
        notifyDataSetChanged();
    }
}
