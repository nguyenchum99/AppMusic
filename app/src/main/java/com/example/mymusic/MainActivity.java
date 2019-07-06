package com.example.mymusic;


import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.MenuItemHoverListener;
import android.view.Gravity;
import android.view.Menu;

import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavigationView navigationOption;

    ListView lvSong;
    ArrayList<Song> songs;
    MusicAdapter musicAdapter;

    MediaPlayer mediaPlayer;
    static int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //drawer menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_album:
                        menuItem.setChecked(true);
                        displayMessage("Albums...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_song:
                        menuItem.setChecked(true);
                        displayMessage("Songs...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_artist:
                        menuItem.setChecked(true);

                        displayMessage("Artists...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_playlist:
                        menuItem.setChecked(true);
                        displayMessage("Playlists...");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_snapdragon:
                        menuItem.setChecked(true);
                        displayMessage("Another...");
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });

//        navigationOption = (NavigationView) findViewById(R.id.navigation_option);
//
//        navigationOption.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.nav_add_playlist:
//                        menuItem.setChecked(true);
//                        Toast.makeText(MainActivity.this, "Add Playlist", Toast.LENGTH_SHORT).show();
//                        return true;
//
//                    case R.id.nav_go_album:
//                        menuItem.setChecked(true);
//                        Toast.makeText(MainActivity.this, "Go to Album", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.nav_go_artist:
//                        menuItem.setChecked(true);
//                        Toast.makeText(MainActivity.this, "Go to Artist", Toast.LENGTH_SHORT).show();
//                        return true;
//                }
//                return false;
//            }
//        });

        lvSong = (ListView) findViewById(R.id.lv_list_song);

        musicAdapter = new MusicAdapter(this,addSong());
        lvSong.setAdapter(musicAdapter);

        // click listview
        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                    pos = position;
                    startActivity(intent);

            }
        });


    }

    // ham add bai hat
    public ArrayList<Song> addSong(){
        songs = new ArrayList<>();
        songs.add(new Song("Độ ta không độ nàng","Thai thinh",R.mipmap.download,R.raw.dotakhongdonang));
        songs.add(new Song("Đừng yêu nữa,em mệt rồi","Min",R.mipmap.download,R.raw.dung_yeu_nua_em_met_roi));
        songs.add(new Song("Sao em vô tình","abc",R.mipmap.download,R.raw.sao_em_vo_tinh));
        songs.add(new Song("Anh ơi anh ở lại","chipu",R.mipmap.download,R.raw.anh_oi_anh_o_lai));
        songs.add(new Song("Đau để trưởng thành","onlyc",R.mipmap.download,R.raw.dau_de_truong_thah));
        songs.add(new Song("Ai là người thương em","aquan ap",R.mipmap.download,R.raw.ai_la_nguoi_thuong_em));
        return songs;
    }

//
//    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "Action View Expand..", Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "Action View Collapsed..", Toast.LENGTH_LONG).show();
                return true;
            }
        };


        return super.onCreateOptionsMenu(menu);
    }


    private void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // nhan nut menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}


