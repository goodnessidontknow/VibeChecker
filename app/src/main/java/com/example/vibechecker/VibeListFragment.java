package com.example.vibechecker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.List;

import static com.example.vibechecker.ResultActivity.*;

public class VibeListFragment extends Fragment {

    private static final String VIBE_CHECK = "vibe_check";
    private static final String TAG = "VibeListFragment";
    private RecyclerView mVibeRecyclerView;
    private VibeAdapter mAdapter;
    private Button mButton;
    private VibeCheckLab mVibeCheckLab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            String json = savedInstanceState.getString(VIBE_CHECK);
            if(!json.isEmpty()) {
                Gson gson = new Gson();
                mVibeCheckLab = gson.fromJson(json, VibeCheckLab.class);
            }        }
        mVibeCheckLab = VibeCheckLab.get(getActivity());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        Gson gson = new Gson();
        String json = gson.toJson(mVibeCheckLab);
        savedInstanceState.putString(VIBE_CHECK, json);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_based_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check_your_vibe:
                Intent intent = LoadingActivity.newIntent(getActivity());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mVibeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mVibeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        View empty = view.findViewById(R.id.custom_empty_view);
        mButton = empty.findViewById(R.id.add_based_button);
        mButton.setOnClickListener(view1 -> {
            Intent intent = LoadingActivity.newIntent(getActivity());
            startActivity(intent);
        });
        empty.setVisibility(getVibeChecks().isEmpty() ? View.VISIBLE : View.INVISIBLE);
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        View empty = getView().findViewById(R.id.custom_empty_view);
        empty.setVisibility(getVibeChecks().isEmpty() ? View.VISIBLE : View.INVISIBLE);
        updateUI();
    }

    private class VibeHolder extends RecyclerView.ViewHolder {

        private VibeCheck mVibeCheck;
        private TextView mTitleTextView;
        private TextView mDateTextView;


        public VibeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_vibe, parent, false));
            mTitleTextView = itemView.findViewById(R.id.vibe_score);
            mDateTextView = itemView.findViewById(R.id.vibe_date);
        }
        public void bind(VibeCheck vibeCheck) {
            mVibeCheck = vibeCheck;
            int score = mVibeCheck.getScore();
            mTitleTextView.setText(Integer.toString(score));

            if (score > 67) {
                mTitleTextView.setTextColor(GREEN);
            } else if (score > 34) {
                mTitleTextView.setTextColor(YELLOW);
            } else {
                mTitleTextView.setTextColor(RED);
            }

            mDateTextView.setText(DateFormat.getDateInstance().format(mVibeCheck.getDate()));

            if (score > 67) {
                mDateTextView.setTextColor(GREEN);
            } else if (score > 34) {
                mDateTextView.setTextColor(YELLOW);
            } else {
                mDateTextView.setTextColor(RED);
            }
        }
    }


    private class VibeAdapter extends RecyclerView.Adapter<VibeHolder> {
        private List<VibeCheck> mVibeChecks;

        public VibeAdapter(List<VibeCheck> crimes) {
            mVibeChecks = crimes;
        }

        @NonNull
        @Override
        public VibeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new VibeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull VibeHolder holder, int position) {
            VibeCheck vibeCheck = mVibeChecks.get(position);
            holder.bind(vibeCheck);
        }

        @Override
        public int getItemCount() {
            return mVibeChecks.size();
        }
    }

    private void updateUI(){
        List<VibeCheck> vibeChecks = getVibeChecks();

        if (mAdapter == null) {
            mAdapter = new VibeAdapter(vibeChecks);
            mVibeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private List<VibeCheck> getVibeChecks() {
        VibeCheckLab vibeCheckLab = VibeCheckLab.get(getActivity());
        return vibeCheckLab.getVibeChecks();
    }

}
