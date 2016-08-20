package com.hello.test.learnandroid.touchscreenandsensors;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

public class CommonGestureActivity extends AppCompatActivity {

  /**
   * Gather the movement data
   * Analyze the data to determine whether it matches a known gesture
   */

  private GestureDetectorCompat mGestureDetector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_common_gesture);
    mGestureDetector = new GestureDetectorCompat(this,new GestureListener());
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    mGestureDetector.onTouchEvent(event);
    return super.onTouchEvent(event);
  }

  private class GestureListener extends GestureDetector.SimpleOnGestureListener{
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
      Toast.makeText(CommonGestureActivity.this,"onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
      return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
      Toast.makeText(CommonGestureActivity.this,"onDoubleTap", Toast.LENGTH_SHORT).show();
      return super.onDoubleTap(e);
    }
  }

}


