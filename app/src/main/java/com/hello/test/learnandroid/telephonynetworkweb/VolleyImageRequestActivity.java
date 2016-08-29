package com.hello.test.learnandroid.telephonynetworkweb;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.hello.test.learnandroid.R;

import org.w3c.dom.Text;

//Volley is appropriate for Singleton Design Pattern

public class VolleyImageRequestActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_volley_image_request);
  }

  public void sendRequest(View view){
    final ImageView imageView = (ImageView) findViewById(R.id.imageView);
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    String url = "http://mightyno9.com/images/about/mighty_beck.png";
    ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
      @Override
      public void onResponse(Bitmap response) {
        imageView.setImageBitmap(response);
      }
    }, 0, 0, ImageView.ScaleType.FIT_CENTER, null, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
      }
    });
    requestQueue.add(imageRequest);
  }
}
