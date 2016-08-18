package com.hello.test.learnandroid.learndata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hello.test.learnandroid.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadingResourceFilesActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reading_resource_files);
    TextView textViewRaw = (TextView)findViewById(R.id.textViewRaw);
    TextView textViewAsset = (TextView)findViewById(R.id.textViewAsset);

    //get raw file
    textViewRaw.setText(getText(this.getResources().openRawResource(R.raw.raw_text)));
    try{
      textViewAsset.setText(getText(this.getAssets().open("assets_text.txt")));
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  private String getText(InputStream inputStream){
    StringBuilder stringBuilder = new StringBuilder();
    try{
      if(inputStream != null){
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String newLine = null;
        while((newLine = bufferedReader.readLine()) != null){
          stringBuilder.append(String.format("%s\n",newLine));
        }
        inputStream.close();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }


}
