package com.hello.test.learnandroid.telephonynetworkweb;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hello.test.learnandroid.R;

public class MakePhoneCallActivity extends AppCompatActivity {
  private static final int REQUEST_CALL_PHONE = 1191;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestPermission();
    setContentView(R.layout.activity_make_phone_call);
  }

  private void requestPermission(){
    int permission = ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE);

    if(permission != PackageManager.PERMISSION_GRANTED){
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.CALL_PHONE)) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Permission to access the Dial Phone is required for this app")
          .setTitle("Permission required");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            makeRequest();
          }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
      }else{
        makeRequest();
      }
    }
  }

  protected void makeRequest() {
    ActivityCompat.requestPermissions(this,
      new String[]{Manifest.permission.CALL_PHONE},
      REQUEST_CALL_PHONE);
  }

  private boolean checkPermission(String permission) {
    int permissionCheck = ContextCompat.checkSelfPermission(
      this, permission);
    return (permissionCheck == PackageManager.PERMISSION_GRANTED);
  }

  public void dialPhone(View view){
    if(checkPermission(Manifest.permission.CALL_PHONE)){
      Intent intent = new Intent(Intent.ACTION_CALL);
      intent.setData(Uri.parse("tel:083807692385"));
      startActivity(intent);
    }
  }
}
