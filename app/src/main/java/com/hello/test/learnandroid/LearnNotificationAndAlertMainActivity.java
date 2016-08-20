package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.notificationandalerts.AlertDialogActivity;
import com.hello.test.learnandroid.notificationandalerts.CustomToastActivity;
import com.hello.test.learnandroid.notificationandalerts.FlashLightWithHeadsUpActivity;
import com.hello.test.learnandroid.notificationandalerts.LearnLightActionSoundActivity;
import com.hello.test.learnandroid.notificationandalerts.LightActionSoundReduxActivity;
import com.hello.test.learnandroid.notificationandalerts.MediaPlayerNotificationActivity;
import com.hello.test.learnandroid.notificationandalerts.ProgressActivity;

public class LearnNotificationAndAlertMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_notification_and_alert_main);
  }

  public void onClickGotoLearnLightActionSoundActivity(View view){
    Intent intent = new Intent(getApplicationContext(),LearnLightActionSoundActivity.class);
    startActivity(intent);
  }

  public void onClickCustomToastActivity(View view){
    Intent intent = new Intent(getApplicationContext(),CustomToastActivity.class);
    startActivity(intent);
  }

  public void onClickAlertDialogActivity(View view){
    Intent intent = new Intent(getApplicationContext(), AlertDialogActivity.class);
    startActivity(intent);
  }

  public void onClickProgressActivity(View view){
    Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
    startActivity(intent);
  }

  public void onClickLightSoundActionReduxAction(View view){
    Intent intent = new Intent(getApplicationContext(), LightActionSoundReduxActivity.class);
    startActivity(intent);
  }

  public void onClickMediaPlayerNotificationActivity(View view){
    Intent intent = new Intent(getApplicationContext(), MediaPlayerNotificationActivity.class);
    startActivity(intent);
  }

  public void onClickFlashLightWithHeadsUpActivity(View view){
    Intent intent = new Intent(getApplicationContext(), FlashLightWithHeadsUpActivity.class);
    startActivity(intent);
  }
}
