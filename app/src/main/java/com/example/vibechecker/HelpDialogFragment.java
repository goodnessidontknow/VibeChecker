package com.example.vibechecker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class HelpDialogFragment extends DialogFragment {

    private WebView webView;

    public static HelpDialogFragment newInstance() {
        return new HelpDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.video, null);

        webView = view.findViewById(R.id.webView);

        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("https://www.youtube.com/watch?v=ARezg1D9Zd0?autoplay=1&vq=small");
        webView.setWebChromeClient(new WebChromeClient());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.vibe_help_title)
                .setMessage(format( R.string.vibe_help_message1,
                                    R.string.vibe_help_message2,
                                    R.string.vibe_help_message3,
                                    R.string.vibe_help_message4,
                                    R.string.vibe_help_message5,
                                    R.string.vibe_help_message6,
                                    R.string.vibe_help_message7))
                .setView(view)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> dismiss());
        return builder.create();

    }

    private CharSequence format(int... strings) {
        String string = getString(strings[0]) + "\n\n";
        for (int i = 1; i < strings.length; i++) {
            string = string + "\t- " + getString(strings[i]);
            if (i != strings.length - 1) {
                string = string + "\n";
            }
        }
        return string;
    }
}
