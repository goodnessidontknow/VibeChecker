package com.example.vibechecker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private static final String SCORE = "score";
    public static final int GREEN = Color.rgb(102,204,0);
    public static final int YELLOW = Color.rgb(230, 191, 0);
    public static final int RED = Color.RED;
    private static final String TOASTED = "toasted";

    private static Integer mScore;

    private boolean mToasted;

    private ProgressBar mScale;
    private TextView mScoreDisplay;
    private Button mCheckAgain, mVibeHelp, mViewPrevious;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(SCORE, 0);
            mToasted = savedInstanceState.getBoolean(TOASTED);
        } else {
            mScore = (int) Math.floor(Math.random() * 101);
            VibeCheckLab.get(getApplicationContext()).addVibeCheck(new VibeCheck(mScore));
        }

        mScale = findViewById(R.id.score_bar);
        mScale.setProgress(mScore);
        mScale.setMax(100);

        mScoreDisplay = findViewById(R.id.score_display);
        mScoreDisplay.setText(String.valueOf(mScore));
        mScoreDisplay.setTextColor(getScoreParams(this, savedInstanceState != null));

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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(SCORE, mScore);
        savedInstanceState.putBoolean(TOASTED, mToasted);
    }

    private int getScoreParams(Context context, boolean savedInstanceState) {
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
        if (!mToasted) {
            Toast toast = Toast.makeText(getApplicationContext(), p, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 0);
            View view = toast.getView();
            view.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            TextView text = view.findViewById(android.R.id.message);
            text.setTextColor(getResources().getColor(R.color.black));
            toast.show();
            mToasted = true;
        }
    }

}
