package com.example.vibechecker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class HelpDialogFragment extends DialogFragment {

    private TextView link1;
    private TextView link2;
    private TextView link3;

    private final String LINK1 = "https://www.amazon.com/dp/B000DWMYQ8/ref=cm_sw_em_r_mt_dp_21VFHD1S7Y7YB2X4JB2P";
    private final String LINK2 = "https://www.amazon.com/dp/B07KWNH1S1/ref=cm_sw_em_r_mt_dp_9MTYQ3H7YWJDSPS4PDH6";
    private final String LINK3 = "https://www.amazon.com/Personalized-Doctorate-Certificate-Customized-Christmas/dp/B00PM1VFQU/ref=sr_1_1?dchild=1&keywords=fake+college+certificate&qid=1622507436&s=movies-tv&sr=1-1-catcorr";

    public static HelpDialogFragment newInstance() {
        return new HelpDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.video, null);

        link1 = view.findViewById(R.id.link1);
        link1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINK1));
            startActivity(intent);
        });

        link2 = view.findViewById(R.id.link2);
        link2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINK2));
            startActivity(intent);
        });

        link3 = view.findViewById(R.id.link3);
        link3.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINK3));
            startActivity(intent);
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        builder.setTitle(R.string.vibe_help_title)
                .setView(view)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> dismiss());
        return builder.create();

    }

}
