package com.example.vibechecker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ResultActivity extends AppCompatActivity {

    public static int GREEN = Color.rgb(102,204,0);
    public static int YELLOW = Color.rgb(230, 191, 0);
    public static int RED = Color.RED;

    private static Integer mScore;

    private ProgressBar mScale;
    private TextView mScoreDisplay;
    private Button mCheckAgain, mVibeHelp, mViewPrevious;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mScore = (int) Math.floor(Math.random() * 101);

        VibeCheckLab.get(getApplicationContext()).addVibeCheck(new VibeCheck(mScore));

        mScale = findViewById(R.id.score_bar);
        mScale.setProgress(mScore);
        mScale.setMax(100);

        mScoreDisplay = findViewById(R.id.score_display);
        mScoreDisplay.setTextColor(getScoreColour(mScore));
        mScoreDisplay.setText(mScore.toString());


        mCheckAgain = findViewById(R.id.check_again);
        mCheckAgain.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
            startActivity(intent);
        });

        mVibeHelp = findViewById(R.id.vibe_help);
        mVibeHelp.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            HelpDialogFragment dialog = HelpDialogFragment.newInstance();
            dialog.show(fm, "HELP_DIALOG");
        });

        mViewPrevious = findViewById(R.id.view_previous);
        mViewPrevious.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), VibeListActivity.class);
            startActivity(intent);
        });

    }

    public static int getScoreColour(int mScore) {
        if (mScore > 67) {
            return GREEN;
        } else if (mScore > 34) {
            return YELLOW;
        } else {
            return RED;
        }
    }

}
