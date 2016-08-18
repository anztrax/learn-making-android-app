package com.hello.test.learnandroid.learndata;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorageActivity extends AppCompatActivity {
  private static String TAG = "PermissionDemo";
  private final String FILE_NAME= "testFile.txt";
  EditText mEditText;
  private static final int REQUEST_WRITE_STORAGE = 112;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_external_storage);
    mEditText = (EditText)findViewById(R.id.editText);
    requestPermission();
  }

  private void requestPermission(){
    int permission = ContextCompat.checkSelfPermission(this,
      Manifest.permission.WRITE_EXTERNAL_STORAGE);

    if(permission != PackageManager.PERMISSION_GRANTED){
      Log.i(TAG,"Permission to write at external storage denied");
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Permission to access the SD-CARD is required for this app")
          .setTitle("Permission required");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

          public void onClick(DialogInterface dialog, int id) {
            Log.i(TAG, "Clicked");
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
      new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
      REQUEST_WRITE_STORAGE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode){
      case REQUEST_WRITE_STORAGE:{
        if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
          Log.i(TAG, "Permission has been denied by user");
        }else{
          Log.i(TAG, "Permission has been granted by user");
        }
        return;
      }
    }
  }

  public boolean isExternalStorageWriteable(){
    if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
      return true;
    }
    return false;
  }

  public boolean isExternalStorageReadable(){
    if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
      Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
      return true;
    }
    return false;
  }

  public void writeFile(View view){
    if(isExternalStorageWriteable()){
      try{
        File textFile = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        FileOutputStream fileOutputStream = new FileOutputStream(textFile);
        fileOutputStream.write(mEditText.getText().toString().getBytes());
        fileOutputStream.close();
      }catch (IOException e){
        e.printStackTrace();
        Toast.makeText(getApplicationContext(),"Error writing file ", Toast.LENGTH_SHORT).show();
      }
    }else{
      Toast.makeText(getApplicationContext(),"Cannot write External Storage",Toast.LENGTH_SHORT).show();
    }
  }

  public void readFile(View view){
    StringBuilder stringBuilder = new StringBuilder();
    if(isExternalStorageReadable()){
      try{
        File textFile = new File(Environment.getExternalStorageDirectory(),FILE_NAME);
        FileInputStream fileInputStream = new FileInputStream(textFile);
        if(fileInputStream != null){
          InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
          BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
          String newLine = null;
          while((newLine = bufferedReader.readLine()) !=  null){
            stringBuilder.append(String.format("%s\n",newLine));
          }
          fileInputStream.close();
        }
        mEditText.setText(stringBuilder);
      }catch (IOException e){
        e.printStackTrace();
        Toast.makeText(getApplicationContext(),"Error reading file",Toast.LENGTH_SHORT).show();
      }
    }else{
      Toast.makeText(getApplicationContext(),"Cannot read external storage",Toast.LENGTH_SHORT).show();
    }
  }
}
