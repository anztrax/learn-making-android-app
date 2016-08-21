package com.hello.test.learnandroid.touchscreenandsensors;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

public class ReadingDeviceOrientationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reading_device_orientation);
    int rotation = getWindowManager().getDefaultDisplay().getRotation();
    //the value in Surface.ROTATION_0 - Surface.ROTATION_270
  }

  public void checkOrientation(View view){
    int orientation = getResources().getConfiguration().orientation;
    switch (orientation){
      case Configuration.ORIENTATION_LANDSCAPE:
        Toast.makeText(getApplicationContext(),"Orientation is landscape",Toast.LENGTH_SHORT).show();
        break;
      case Configuration.ORIENTATION_PORTRAIT:
        Toast.makeText(getApplicationContext(),"Orientation is portrait",Toast.LENGTH_SHORT).show();
        break;
      case Configuration.ORIENTATION_UNDEFINED:
        Toast.makeText(getApplicationContext(),"Orientation is undefined",Toast.LENGTH_SHORT).show();
        break;

    }
  }
}
