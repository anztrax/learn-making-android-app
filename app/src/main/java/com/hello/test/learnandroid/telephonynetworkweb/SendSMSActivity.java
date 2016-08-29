package com.hello.test.learnandroid.telephonynetworkweb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SendSMSActivity extends AppCompatActivity {
  final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
  Button buttonSend;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send_sms);
    buttonSend = (Button)findViewById(R.id.buttonSend);
    buttonSend.setEnabled(false);

    if(checkPermission(Manifest.permission.SEND_SMS)){
      buttonSend.setEnabled(true);
    }else{
      ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode){
      case SEND_SMS_PERMISSION_REQUEST_CODE:{
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
          buttonSend.setEnabled(true);
        }
        return;
      }
    }
  }

  private boolean checkPermission(String permission){
    int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
    return (permissionCheck == PackageManager.PERMISSION_GRANTED);
  }

  public void sendSMS(View view){
    String phoneNumber = ((EditText)findViewById(R.id.editTextNumber)).getText().toString();
    String message = ((EditText)findViewById(R.id.editTextMsg)).getText().toString();

    if(phoneNumber == null || phoneNumber.length() == 0 || message == null || message.length() == 0){
      return;
    }

    if(checkPermission(Manifest.permission.SEND_SMS)){
      SmsManager smsManager = SmsManager.getDefault();
      ArrayList<String> messages = smsManager.divideMessage(message);
      smsManager.sendMultipartTextMessage(phoneNumber,null,messages,null,null);
//      smsManager.sendTextMessage(phoneNumber,null,message,null,null);  <- this is for send 1 single message
    }else{
      Toast.makeText(SendSMSActivity.this,"No Permission",Toast.LENGTH_SHORT).show();
    }
  }
}
