package com.hello.test.learnandroid.listener;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
  private AppCompatActivity activity;
  public GestureListener(AppCompatActivity activity){
    this.activity = activity;
  }

  private void hideSystemUI() {
    activity.getWindow().getDecorView().setSystemUiVisibility(
      View.SYSTEM_UI_FLAG_IMMERSIVE |
        View.SYSTEM_UI_FLAG_FULLSCREEN |
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    );
  }

  private void showSystemUI(){
    activity.getWindow().getDecorView().setSystemUiVisibility(
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    );
  }

  @Override
  public boolean onDown(MotionEvent e) {
    return true;
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return true;
  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    if(activity.getSupportActionBar()!= null && activity.getSupportActionBar().isShowing()){
      hideSystemUI();
    }else{
      showSystemUI();
    }

    return true;
  }
}
