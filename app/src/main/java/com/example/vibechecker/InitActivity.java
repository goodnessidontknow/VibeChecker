package com.example.vibechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class InitActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        mButton = findViewById(R.id.check_vibe);

        mButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
            startActivity(intent);
        });
    }
}