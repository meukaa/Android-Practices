package ru.mirea.migalma.mireaproject2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class WebViewFragment extends Fragment {

    private WebView webView;
    private EditText etUrl;
    private Button btnLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);


        webView = view.findViewById(R.id.webView);
        etUrl = view.findViewById(R.id.etUrl);
        btnLoad = view.findViewById(R.id.btnLoad);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        webView.loadUrl("https://metanit.com/");

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etUrl.getText().toString().trim();


                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url;
                }


                webView.loadUrl(url);
            }
        });

        return view;
    }
}