package com.hello.test.learnandroid.graphicandanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.hello.test.learnandroid.R;

public class TransitionAnimationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transition_animation);
  }

  public void onClickAnimate(View view){
    ViewGroup root = (ViewGroup)findViewById(R.id.activity_transition_animation);
    Scene scene = Scene.getSceneForLayout(root,R.layout.activity_transition_animation_end,this);
    Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_move);
    TransitionManager.go(scene,transition);
  }
}
