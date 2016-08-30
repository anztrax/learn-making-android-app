package com.hello.test.learnandroid.telephonynetworkweb;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.hello.test.learnandroid.R;

public class NetworkImageViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_network_image_view);
    NetworkImageView networkImageView = (NetworkImageView)findViewById(R.id.networkImageView);
    String url = "http://vignette1.wikia.nocookie.net/mightyno9/images/f/fb/BeckTheMightyGuy.png/revision/latest?cb=20160517132403";
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
      private final LruCache<String,Bitmap> cache = new LruCache<>(20);

      @Override
      public Bitmap getBitmap(String url) {
        return cache.get(url);
      }

      @Override
      public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url,bitmap);
      }
    });
    networkImageView.setImageUrl(url,imageLoader);
  }
}
