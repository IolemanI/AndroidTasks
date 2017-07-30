package com.oleman.androidtasks;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    EditText searchTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task6_web);

        webView = (WebView) findViewById(R.id.webView_t6);
        Button search = (Button) findViewById(R.id.goBtn_t6);
        searchTxt = (EditText) findViewById(R.id.text_t6);

        search.setOnClickListener(this);

        Uri data = getIntent().getData();
        searchTxt.setText(data.toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.goBtn_t6:
                webView.loadUrl(searchTxt.getText().toString());
        }

    }
}
