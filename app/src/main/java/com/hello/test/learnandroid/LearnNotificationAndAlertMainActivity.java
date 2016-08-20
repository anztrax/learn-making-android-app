package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.notificationandalerts.CustomToastActivity;
import com.hello.test.learnandroid.notificationandalerts.LearnLightActionSoundActivity;

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
}
