package com.example.pomodoroapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton, pauseButton, resetButton;

    private PomodoroTimer pomodoroTimer;

    private Button musicButton;
    private MusicPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        /* ====== Timer ===== */
        pomodoroTimer = new PomodoroTimer(new PomodoroTimer.TimerListener() {
            @Override
            public void onTick(String formattedTime) {
                runOnUiThread(() -> timerTextView.setText(formattedTime));
            }

            @Override
            public void onFinish() {
                runOnUiThread(() -> timerTextView.setText("00:00"));
            }
        });

        /* ====== Music ===== */
        musicButton = findViewById(R.id.musicButton);
        musicPlayer = new MusicPlayer();

        musicButton.setOnClickListener(v -> {
            if (musicPlayer.isPlaying()) {
                musicPlayer.pauseMusic();
                musicButton.setText("Play Music");
            } else {
                musicPlayer.startMusic(this);
                musicButton.setText("Pause Music");
            }
        });

        startButton.setOnClickListener(v -> pomodoroTimer.start());
        pauseButton.setOnClickListener(v -> pomodoroTimer.pause());
        resetButton.setOnClickListener(v -> pomodoroTimer.reset());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayer.stopMusic();
    }
}
