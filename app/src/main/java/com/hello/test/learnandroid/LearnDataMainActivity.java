package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hello.test.learnandroid.learndata.ExternalStorageActivity;
import com.hello.test.learnandroid.learndata.InternalStorageActivity;
import com.hello.test.learnandroid.learndata.LearnPreferencesActivity;
import com.hello.test.learnandroid.learndata.LearnUsingSQLIteActivity;
import com.hello.test.learnandroid.learndata.ReadingResourceFilesActivity;

public class LearnDataMainActivity extends AppCompatActivity{
  Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_data_main);
    button = (Button)findViewById(R.id.buttonExternalData);
    button.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"Testing gan",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ExternalStorageActivity.class);
        startActivity(intent);
      }
    });
  }

  public void onClickTryPreferences(View view){
    Intent intent = new Intent(getApplicationContext(), LearnPreferencesActivity.class);
    startActivity(intent);
  }
  public void onClickInternalStorageActivity(View view){
    Intent intent = new Intent(getApplicationContext(), InternalStorageActivity.class);
    startActivity(intent);
  }

  public void onClickReadingResourceFile(View view){
    Intent intent = new Intent(getApplicationContext(), ReadingResourceFilesActivity.class);
    startActivity(intent);
  }

  public void onClickLearnUsingSQLIteActivity(View view){
    Intent intent = new Intent(getApplicationContext(), LearnUsingSQLIteActivity.class);
    startActivity(intent);
  }
}
