package com.hello.test.learnandroid.graphicandanimation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.hello.test.learnandroid.R;

public class LearnHowToMakeCompassActivity extends AppCompatActivity {
  private SensorManager sensorManager;
  private Sensor magnetometer;
  private Sensor accelerometer;
  private ImageView imageViewCompass;
  private float[] gravityValues = new float[3];
  private float[] accelerationValues = new float[3];
  private float[] rotationMatrix = new float[9];
  private float lastDirectionInDegrees = 0f;

  private SensorEventListener sensorListener = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent event) {
      calculateCompassDirection(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
  };

  private void calculateCompassDirection(SensorEvent event){
    switch (event.sensor.getType()){
      case Sensor.TYPE_ACCELEROMETER:
        accelerationValues = event.values.clone();
        break;
      case Sensor.TYPE_MAGNETIC_FIELD:
        gravityValues = event.values.clone();
        break;
    }
    boolean success = SensorManager.getRotationMatrix(rotationMatrix,null,accelerationValues,gravityValues);
    if(success){
      float[] orientationValues = new float[3];
      SensorManager.getOrientation(rotationMatrix,orientationValues);
      float azimuth = (float)Math.toDegrees(-orientationValues[0]);
      RotateAnimation rotateAnimation = new RotateAnimation(lastDirectionInDegrees,azimuth, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
      rotateAnimation.setDuration(50);
      rotateAnimation.setFillAfter(true);
      imageViewCompass.startAnimation(rotateAnimation);
      lastDirectionInDegrees = azimuth;
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    sensorManager.registerListener(sensorListener,magnetometer,SensorManager.SENSOR_DELAY_FASTEST);
    sensorManager.registerListener(sensorListener,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
  }

  @Override
  protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(sensorListener);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_how_tomake_compass);
    imageViewCompass = (ImageView)findViewById(R.id.imageViewCompass);
    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
  }


}
