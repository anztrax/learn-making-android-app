package com.hello.test.learnandroid.notificationandalerts;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

public class AlertDialogActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alert_dialog);
  }

  public void onClickButtonClose(View view){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Delete")
      .setMessage("Are you sure you ? ")
      .setIcon(R.mipmap.ic_launcher)
      .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          Toast.makeText(getApplicationContext(),"OK Pressed", Toast.LENGTH_SHORT).show();
        }
      })
      .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          Toast.makeText(getApplicationContext(),"Cancel Pressed",Toast.LENGTH_SHORT).show();
        }
      })
      .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          Toast.makeText(getApplicationContext(),"Neutral Pressed",Toast.LENGTH_SHORT).show();
        }
      });
    builder.create().show();
  }

  public void onClickCustomView(View view){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(R.layout.custom_dialog);
    builder.create().show();
  }
}
