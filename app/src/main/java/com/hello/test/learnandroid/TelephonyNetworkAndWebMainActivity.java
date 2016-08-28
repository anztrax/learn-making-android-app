package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.telephonynetworkweb.LearnPhoneStateActivity;
import com.hello.test.learnandroid.telephonynetworkweb.MakePhoneCallActivity;

public class TelephonyNetworkAndWebMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_telephony_network_and_web_main);
  }

  public void onClickMakePhoneCallActivity(View view){
    Intent intent = new Intent(getApplicationContext(), MakePhoneCallActivity.class);
    startActivity(intent);
  }

  public void onClickLearnPhoneStateActivity(View view){
    Intent intent = new Intent(getApplicationContext(), LearnPhoneStateActivity.class);
    startActivity(intent);
  }
}
