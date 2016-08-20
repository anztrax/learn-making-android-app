package com.hello.test.learnandroid.notificationandalerts;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

public class CustomToastActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom_toast);
  }

  public void onClickShowCustomToast(View view){
    LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View layout = inflater.inflate(R.layout.toast_layout,null);
    ((TextView)layout.findViewById(android.R.id.message)).setText("Custom Toast");
    Toast toast = new Toast(this);
    toast.setGravity(Gravity.CENTER,0,0);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setView(layout);
    toast.show();
  }
}
