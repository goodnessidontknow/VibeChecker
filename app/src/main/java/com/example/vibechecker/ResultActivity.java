package com.example.vibechecker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
        mScore = (int) Math.floor(Math.random() * 101);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        VibeCheckLab.get(getApplicationContext()).addVibeCheck(new VibeCheck(mScore));

        mScale = findViewById(R.id.score_bar);
        mScale.setProgress(mScore);
        mScale.setMax(100);

        mScoreDisplay = findViewById(R.id.score_display);
        mScoreDisplay.setText(String.valueOf(mScore));
        mScoreDisplay.setTextColor(getScoreParams(this));

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

    private int getScoreParams(Context context) {
        if (mScore > 67) {
            makeToast(getString(R.string.results_green), context);
            return GREEN;
        } else if (mScore > 34) {
            makeToast(getString(R.string.results_yellow), context);
            return YELLOW;
        } else {
            makeToast(getString(R.string.results_red), context);
            return RED;
        }
    }

    private void makeToast(String p, Context context) {
        Toast toast = Toast.makeText(getApplicationContext(), p, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

}
