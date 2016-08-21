package com.hello.test.learnandroid.touchscreenandsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hello.test.learnandroid.R;

public class LearnReadingSensorDataActivity extends AppCompatActivity {

  /**
   * 4 environment censors :
   * - Humidity
   * - Light
   * - Pressure
   * - Temperature
   *
   * position censors :
   * - Geomagnetic Field
   * - Proximity
   *
   * MOTION SENSORS :
   * - Accelerometer
   * - Gyroscope
   * - Gravity
   * - Linear acceleration
   * - Rotation vector
   */

  private SensorManager sensorManager;
  private Sensor sensor;
  private TextView textView;
  private SensorEventListener sensorEventListener = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent event) {
      textView.setText(String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
  };

  @Override
  protected void onResume() {
    super.onResume();
    sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(sensorEventListener);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_reading_sensor_data);
    textView = (TextView)findViewById(R.id.textView);
    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
  }


}
