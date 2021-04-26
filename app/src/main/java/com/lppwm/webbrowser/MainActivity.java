package com.lppwm.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView searchInput = findViewById(R.id.search_input);
        ImageButton searchButton = findViewById(R.id.search_button);

        webView = (WebView) findViewById(R.id.main_webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String research = searchInput.getText().toString().replaceAll("\\s+", "");
                ArrayList<Character> searchCharacters = new ArrayList<>();

                for(int i = 0; i < research.length(); i++) {
                    searchCharacters.add(research.charAt(i));
                }

                Collections.shuffle(searchCharacters);
                String randomSearch = "";

                for(Character character: searchCharacters) {
                    randomSearch = randomSearch.concat(character.toString());
                }

                webView.loadUrl("https://" + randomSearch + ".com");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}