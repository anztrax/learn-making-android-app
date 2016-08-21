package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.touchscreenandsensors.CommonGestureActivity;
import com.hello.test.learnandroid.touchscreenandsensors.LearnReadingSensorDataActivity;
import com.hello.test.learnandroid.touchscreenandsensors.MultiTouchZoomActivity;
import com.hello.test.learnandroid.touchscreenandsensors.PressEventsActivity;
import com.hello.test.learnandroid.touchscreenandsensors.SensorListActivity;
import com.hello.test.learnandroid.touchscreenandsensors.SwipeToRefreshActivity;

public class TouchScreenAndSensorsMainActivity extends AppCompatActivity {

  /**
   * gyroscope, magnetic, gravity, pressure, and/or temperature sensors, not to mention the touchscreen
   * shaking, rotation, tilt, and so on.
   * sensor abilities in Android, using the Android Sensor Framework.
   *
   */

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_touch_screen_and_sensors_main);
  }

  public void onClickPressEventsActivity(View view){
    Intent intent = new Intent(getApplicationContext(), PressEventsActivity.class);
    startActivity(intent);
  }

  public void onCommonGestureActivity(View view){
    Intent intent = new Intent(getApplicationContext(), CommonGestureActivity.class);
    startActivity(intent);
  }

  public void onClickMultiTouchZoomActivity(View view){
    Intent intent = new Intent(getApplicationContext(), MultiTouchZoomActivity.class);
    startActivity(intent);
  }

  public void onClickSwipeToRefreshActivity(View view){
    Intent intent = new Intent(getApplicationContext(), SwipeToRefreshActivity.class);
    startActivity(intent);
  }

  public void onClickSensorListActivity(View view){
    startActivityGenerator(SensorListActivity.class);
  }

  public void onClickLearnReadingSensorDataActivity(View view){
    startActivityGenerator(LearnReadingSensorDataActivity.class);
  }

  private void startActivityGenerator(Class clazz){
    Intent intent = new Intent(getApplicationContext(), clazz);
    startActivity(intent);
  }

}
