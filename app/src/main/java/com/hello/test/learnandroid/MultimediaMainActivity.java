package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.multimedia.HardwareMediaControlsActivity;
import com.hello.test.learnandroid.multimedia.PlayingSoundEffectWithSoundPoolActivity;
import com.hello.test.learnandroid.multimedia.UsingDefaultCameraAppActivity;

public class MultimediaMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multimedia_main);
  }

  public void onClickPlaySoundEffect(View view){
    Intent intent = new Intent(getApplicationContext(), PlayingSoundEffectWithSoundPoolActivity.class);
    startActivity(intent);
  }
  public void onCLickHardwareMediaControlsActivity(View view){
    Intent intent = new Intent(getApplicationContext(), HardwareMediaControlsActivity.class);
    startActivity(intent);
  }

  public void onClickUsingDefaultCameraAppActivity(View view){
    Intent intent = new Intent(getApplicationContext(), UsingDefaultCameraAppActivity.class);
    startActivity(intent);
  }
}
