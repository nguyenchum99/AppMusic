package com.example.mymusic;


import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import  android.widget.ImageView;

import org.w3c.dom.Text;

class PlayerActivity extends MainActivity {

    TextView tvSongName;
    Button btnPause;
    Button btnPlay;
    Button btnPrevious;
    Button btnShuffle;
    Button btnNext;
    Button btnRepeat;
    SeekBar seekBar;
    TextView tvTimeStart;
    TextView tvTimeDuration;
    ImageView imgSongPlayer;

    int countClickRepeat = 0;
    int randomElement = 0;
    int i = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getWidget();
        playingMusic();
        //autoMoveSong();
        playerHandler();


    }

    // hàm chơi nhạc
    public void playingMusic() {

        Toast.makeText(PlayerActivity.this, "position " + pos, Toast.LENGTH_SHORT).show();
        tvSongName.setText(songs.get(pos).getTitle());
        imgSongPlayer.setBackgroundResource(R.mipmap.music);
        mediaPlayer = MediaPlayer.create(PlayerActivity.this, songs.get(pos).getId());
        mediaPlayer.start();
        setTimeTotal();
        updateTimeSong();
    }

    // ham kiem tra bai hat da phat het -> tu dong chuyen xuong bai tiep theo
    public void autoMoveSong() {

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Toast.makeText(PlayerActivity.this, "position " + pos, Toast.LENGTH_SHORT).show();
                tvSongName.setText(songs.get(pos).getTitle());
                mediaPlayer = MediaPlayer.create(PlayerActivity.this, songs.get(pos).getId());
                mediaPlayer.start();
                setTimeTotal();
                updateTimeSong();
            }
        });
  }

        //hàm xử lý nút button
        public void playerHandler(){

            //click nut pause
            btnPause.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    // neu dang phat an vao pause -> dung -> doi sang hinh play
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        btnPause.setBackgroundResource(R.drawable.ic_play);
                    } else {
                        // neu dang nguwng phat -> an vao play
                        mediaPlayer.start();
                        btnPause.setBackgroundResource(R.drawable.ic_pause);
                    }
                    // setTimeTotal();
                    // updateTimeSong();
                }

            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        pos++;
                        if (pos > songs.size() - 1) {
                            pos = 0;
                            playingMusic();
                        } else {
                            playingMusic();
                        }
                    }

                }
            });

            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        pos--;
                        if (pos < 0) {
                            pos = songs.size() - 1;
                            playingMusic();
                        } else {
                            playingMusic();
                        }
                    }
                }
            });


            btnRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countClickRepeat++;
                    // xu ly su kien phat het danh sach cho den khi nao an nut dung
                    if (countClickRepeat == 1) {
                        btnRepeat.setBackgroundResource(R.drawable.ic_repeat_black);
                        // repeatSong();
                        // autoMoveSong();
                    }
                    // phat chi 1 bai
                    if (countClickRepeat == 2) {
                        btnRepeat.setBackgroundResource(R.drawable.ic_repeat_one);
                        playOneSong();
                    }
                    if (countClickRepeat == 3) {
                        btnRepeat.setBackgroundResource(R.drawable.ic_repeat);
                        countClickRepeat = 0;

                        // autoMoveSong();
                    }

                }
            });
//
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = getRandomElement();
                btnShuffle.setBackgroundResource(R.drawable.ic_repeat_black);
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playingMusic();
                    }
                });

                btnPrevious.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playingMusic();
                    }
                });

            }
        });
//
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            });

        }

   //hàm xử lý chạy ngẫu nhiên bài hát
    public void shuffleSong(){
        pos = getRandomElement();
        playingMusic();
        //setTimeTotal();
        //updateTimeSong();
    }

    // hàm xử lý chỉ phát 1 bài
    public void playOneSong(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                    tvSongName.setText(songs.get(pos).getTitle());
                    mediaPlayer = MediaPlayer.create(PlayerActivity.this, songs.get(pos).getId());
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    setTimeTotal();
                    updateTimeSong();
            }
        });
    }
//
//    //hàm phát nhạc repeat
//    public void repeatSong(){
//        autoMoveSong();
//        if(pos == songs.size() -1){
//            pos = 0;
//            playingMusic();
//            autoMoveSong();
//        }
//
//    }
//
//    //hàm lấy bài hát ngẫu nhiên
    public int getRandomElement(){
        Random random = new Random();
        randomElement = random.nextInt(songs.size());

        return randomElement;
    }

   //hàm khởi tạo
    public void getWidget() {
        tvSongName = (TextView) findViewById(R.id.tvsongname);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnPrevious = (Button) findViewById(R.id.btn_previous);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnShuffle = (Button) findViewById(R.id.btn_shuffle);
        btnRepeat = (Button) findViewById(R.id.btn_repeat);
        tvTimeDuration = (TextView) findViewById(R.id.tv_time_duration);
        tvTimeStart = (TextView) findViewById(R.id.tv_time_to_start);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        imgSongPlayer = (ImageView) findViewById(R.id.img_song_player);
    }


   //hàm tính thời lượng bài hát
    private void setTimeTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTimeDuration.setText(simpleDateFormat.format(mediaPlayer.getDuration()));

        seekBar.setMax(mediaPlayer.getDuration());

    }

    // hàm update thời gian trên seekbar
    private void updateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                tvTimeStart.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));

                //update seekbar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);

            }
        }, 100);
    }

}

