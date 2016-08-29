package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.telephonynetworkweb.LearnPhoneStateActivity;
import com.hello.test.learnandroid.telephonynetworkweb.LearnVolleyLibraryActivity;
import com.hello.test.learnandroid.telephonynetworkweb.LearnWebViewActivity;
import com.hello.test.learnandroid.telephonynetworkweb.MakePhoneCallActivity;
import com.hello.test.learnandroid.telephonynetworkweb.ReceiveSMSActivity;
import com.hello.test.learnandroid.telephonynetworkweb.SendSMSActivity;
import com.hello.test.learnandroid.telephonynetworkweb.broadcastreceiver.SMSBroadcastReceiver;

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

  public void onClickSendSMSActivity(View view){
    Intent intent = new Intent(getApplicationContext(), SendSMSActivity.class);
    startActivity(intent);
  }
  public void onClickSMSBoardcastReceiver(View view){
    Intent intent = new Intent(getApplicationContext(), ReceiveSMSActivity.class);
    startActivity(intent);
  }
  public void onClickLearnWebViewActivity(View view){
    Intent intent = new Intent(getApplicationContext(), LearnWebViewActivity.class);
    startActivity(intent);
  }
  public void onClickLearnVolleyLibraryActivity(View view){
    Intent intent = new Intent(getApplicationContext(), LearnVolleyLibraryActivity.class);
    startActivity(intent);
  }
}
