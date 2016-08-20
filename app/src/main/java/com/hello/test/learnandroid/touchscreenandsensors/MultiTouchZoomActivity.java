package com.hello.test.learnandroid.touchscreenandsensors;

import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.hello.test.learnandroid.R;

public class MultiTouchZoomActivity extends AppCompatActivity {
  //scale which mean gedein
  private ScaleGestureDetector scaleGestureDetector;
  private float mScaleFactor = 1.0f;
  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multi_touch_zoom);
    imageView = (ImageView)findViewById(R.id.imageView);
    scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    scaleGestureDetector.onTouchEvent(event);
    return true;
  }

  private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
      mScaleFactor *= scaleGestureDetector.getScaleFactor();
      mScaleFactor = Math.max(0.1f,Math.min(mScaleFactor,10.0f));
      imageView.setScaleX(mScaleFactor);
      imageView.setScaleY(mScaleFactor);
      return true;
    }
  }
}
