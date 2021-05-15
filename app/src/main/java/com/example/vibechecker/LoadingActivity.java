package com.example.vibechecker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity {

    Timer timer;

    ProgressBar mLoading;
    TextView mStatus;
    Button mButton;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LoadingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_loading);

        mLoading = findViewById(R.id.progressBar);

        mStatus = findViewById(R.id.checking_vibe);

        mButton = findViewById(R.id.see_results);
        mButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            startActivity(intent);
        });
        mButton.setVisibility(View.INVISIBLE);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    mLoading.setVisibility(View.GONE);
                    mStatus.setText(R.string.vibe_ready);
                    mButton.setVisibility(View.VISIBLE);
                });
            }
        }, (long) ((Math.random() * 6) + 1) * 1000);
    }

}
