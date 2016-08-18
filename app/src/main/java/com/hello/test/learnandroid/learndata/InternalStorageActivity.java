package com.hello.test.learnandroid.learndata;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hello.test.learnandroid.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {
  private final String FILENAME="testFile.txt";
  EditText mEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_internal_storage);
    mEditText = (EditText)findViewById(R.id.editText);

  }

  public void writeFile(View view){
    try{
      FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
      fileOutputStream.write(mEditText.getText().toString().getBytes());
      fileOutputStream.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }

  public void readFile(View view){
    StringBuilder stringBuilder = new StringBuilder();
    try{
      InputStream inputStream = openFileInput(FILENAME);
      if(inputStream != null){
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String newline = null;
        while((newline = bufferedReader.readLine()) != null){
          stringBuilder.append(String.format("%s\n",newline));
        }
        inputStream.close();
      }
    }catch (IOException e){
      e.printStackTrace();
    }
    mEditText.setText(stringBuilder);
  }
}
