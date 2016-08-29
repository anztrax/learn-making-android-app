package com.hello.test.learnandroid.telephonynetworkweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.hello.test.learnandroid.R;

import org.json.JSONObject;
import org.w3c.dom.Text;

//Volley to wrap HttpURLConnection
public class VolleyRequestJSONActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_volley_request_json);
  }

  public void sendJSONRequest(View view){
    final TextView textView = (TextView)findViewById(R.id.textView);
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    String url =  "http://ip.jsontest.com/";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
      Request.Method.GET,
      url,
      null,
      new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
          //it's possible to add static analysis checker at returned string :)
          textView.setText(response.toString());
        }
      }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          textView.setText(String.format("onErrorResponse() : %s",error.getMessage()));
        }
      }
    );
    requestQueue.add(jsonObjectRequest);
  }
}
