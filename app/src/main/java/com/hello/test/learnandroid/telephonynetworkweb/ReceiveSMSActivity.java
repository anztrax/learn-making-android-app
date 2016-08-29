package com.hello.test.learnandroid.telephonynetworkweb;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hello.test.learnandroid.R;

public class ReceiveSMSActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recieve_sms);
    if(!checkPermission(Manifest.permission.RECEIVE_MMS)){
      ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, 0);
    }else{
      ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, 0);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode){
      case 0:{
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
        return;
      }
    }
  }

  private boolean checkPermission(String permission){
    int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
    return (permissionCheck == PackageManager.PERMISSION_GRANTED);
  }
}
