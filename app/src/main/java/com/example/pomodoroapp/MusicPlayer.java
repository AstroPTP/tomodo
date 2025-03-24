package com.example.pomodoroapp;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {

    private MediaPlayer mediaPlayer;

    public void startMusic(Context context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.focus_music);
            mediaPlayer.setLooping(true); // Loop the music
        }
        mediaPlayer.start();
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }
}
