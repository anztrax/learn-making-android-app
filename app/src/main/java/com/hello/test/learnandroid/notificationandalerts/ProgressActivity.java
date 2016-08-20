package com.hello.test.learnandroid.notificationandalerts;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.R;

public class ProgressActivity extends AppCompatActivity {
  private ProgressDialog progressDialog;
  final int THIRTY_SECOND = 3*1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_progress);
  }

  public void startProgress(View view){
    progressDialog = new ProgressDialog(ProgressActivity.this);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setMessage("Doing Something ...");
    progressDialog.setCancelable(false);
    progressDialog.show();
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        progressDialog.dismiss();
      }
    },THIRTY_SECOND);
  }

  public void onClickStartProgress(View view){
    startProgress(view);
  }
}
