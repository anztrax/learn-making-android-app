package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.graphicandanimation.FlipCardActivity;
import com.hello.test.learnandroid.graphicandanimation.LearnHowToMakeCompassActivity;
import com.hello.test.learnandroid.graphicandanimation.LearnSimpleImageSliderActivity;
import com.hello.test.learnandroid.graphicandanimation.LoadLargeImageActivity;
import com.hello.test.learnandroid.graphicandanimation.TransitionAnimationActivity;
import com.hello.test.learnandroid.graphicandanimation.ZoomAnimationWithCustomTransitionActivity;

public class LearnGraphicAndAnimationMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_graphic_and_animation);
  }

  public void onClickLoadImageActivity(View view){
    Intent intent = new Intent(LearnGraphicAndAnimationMainActivity.this, LoadLargeImageActivity.class);
    startActivity(intent);
  }

  public void onClickTransitionAnimationActivity(View view){
    Intent intent = new Intent(LearnGraphicAndAnimationMainActivity.this,TransitionAnimationActivity.class);
    startActivity(intent);
  }

  public void onClickLearnHowToMakeCompassActivity(View view){
    Intent intent = new Intent(LearnGraphicAndAnimationMainActivity.this,LearnHowToMakeCompassActivity.class);
    startActivity(intent);
  }

  public void onClickLearnSimpleImageSliderActivity(View view){
    Intent intent = new Intent(LearnGraphicAndAnimationMainActivity.this, LearnSimpleImageSliderActivity.class);
    startActivity(intent);
  }

  public void onClickFlipCardActivity(View view){
    Intent intent = new Intent(getApplicationContext(),FlipCardActivity.class);
    startActivity(intent);
  }

  public void onClickZoomAnimationWithCustomTransitionActivity(View view){
    Intent intent = new Intent(getApplicationContext(), ZoomAnimationWithCustomTransitionActivity.class);
    startActivity(intent);
  }
}
