package com.example.pomodoroapp;

import android.os.CountDownTimer;

public class PomodoroTimer {

    // Default time will be 25 minutes -> milliseconds to fit with CountDownTimer
    private static final long DEFAULT_TIME = 25 * 60 * 1000;

    private long timeLeftInMillis = DEFAULT_TIME;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;

    private TimerListener listener;

    public PomodoroTimer(TimerListener listener) {
        this.listener = listener;
    }

    public void start() {
        if (isRunning) return;

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            // Calls every second
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                if (listener != null) {
                    listener.onTick(formatTime(timeLeftInMillis));
                }
            }

            // Calls when done
            public void onFinish() {
                isRunning = false;
                if (listener != null) {
                    listener.onFinish();
                }
            }
        }.start();

        isRunning = true;
    }

    public void pause() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            isRunning = false;
        }
    }

    public void reset() {
        pause();
        timeLeftInMillis = DEFAULT_TIME;
        if (listener != null) {
            listener.onTick(formatTime(timeLeftInMillis));
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    private String formatTime(long millis) {
        int minutes = (int) (millis / 1000) / 60;
        int seconds = (int) (millis / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public interface TimerListener {
        void onTick(String formattedTime);
        void onFinish();
    }
}
