package com.hello.test.learnandroid.telephonynetworkweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.hello.test.learnandroid.R;

import org.w3c.dom.Text;

public class LearnPhoneStateActivity extends AppCompatActivity {

  private PhoneStateListener phoneStateListener = new PhoneStateListener(){
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
      String phoneState = incomingNumber;
      switch (state){
        case TelephonyManager.CALL_STATE_IDLE:
          phoneState += "CALL_STATE_IDLE\n";
          break;
        case TelephonyManager.CALL_STATE_RINGING:
          phoneState += "CALL_STATE_RINGING\n";
          break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
          phoneState += "CALL_STATE_OFFHOOK\n";
      }
      TextView textView = (TextView)findViewById(R.id.textView);
      textView.append(phoneState);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_phone_state);
    final TelephonyManager telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
    telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
  }
}
