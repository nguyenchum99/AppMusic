package com.example.mymusic;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MusicAdapter extends BaseAdapter{

    private Context context;
   // private int resource;
    private ArrayList<Song> arrSong;


    public MusicAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        //this.resource = resource;
        this.arrSong = arrSong;
    }

    public int getCount(){
        return arrSong.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = (Song) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.song,null);
        ImageView imgSong = (ImageView) convertView.findViewById(R.id.img_view_song);
        TextView tvSongTitle= (TextView) convertView.findViewById(R.id.tv_song_title);
        TextView tvArtist = (TextView) convertView.findViewById(R.id.tv_artist);

        tvSongTitle.setText(song.getTitle());
        tvArtist.setText(song.getArtist());
        //holder.imgSong.setId(song.getId());
        imgSong.setImageResource(song.getIdImage());

        return convertView;
    }

}
