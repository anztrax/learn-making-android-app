package com.hello.test.learnandroid.telephonynetworkweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hello.test.learnandroid.R;

public class LearnWebViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //this is for redirect to application
//    WebView webView = new WebView(this);
//    setContentView(webView);
//    webView.loadUrl("https://traveloka.com");

    WebView webView = new WebView(this);
    webView.setWebViewClient(new WebViewClient());
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setBuiltInZoomControls(true);
    setContentView(webView);
    webView.loadUrl("https://traveloka.com");

  }
}
