package com.hello.test.learnandroid.telephonynetworkweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hello.test.learnandroid.R;

public class LearnVolleyLibraryActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_volley_library);
  }

  public void sendRequest(View view){
    final TextView textView = (TextView)findViewById(R.id.textView);
    RequestQueue queue = Volley.newRequestQueue(this);
    String url = "https://traveloka.com";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        //it's possible to add static analysis checker at returned string :)
        textView.setText(response);
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        textView.setText(String.format("onErrorResponse() : %s",error.getMessage()));
      }
    });
    queue.add(stringRequest);
  }
}
