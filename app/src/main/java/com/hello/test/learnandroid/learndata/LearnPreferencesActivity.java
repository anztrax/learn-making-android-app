package com.hello.test.learnandroid.learndata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

public class LearnPreferencesActivity extends AppCompatActivity {
  private final String NAME="NAME";
  private EditText mEditTextName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_preferences);
    TextView textView = (TextView)findViewById(R.id.textView);
    mEditTextName = (EditText)findViewById(R.id.editTextName);
    SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
    String name = sharedPreferences.getString(NAME,null);
    if(name == null){
      textView.setText("Hello");
    }else{
      textView.setText("Welcome back " + name + "!");
    }
    mEditTextName = (EditText)findViewById(R.id.editTextName);
  }

  public void onSave(View view){
    String name = mEditTextName.getText().toString();
    SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
    editor.putString(NAME,name);
    editor.commit();
    Toast.makeText(getApplicationContext(),String.format("name : %s",name),Toast.LENGTH_SHORT).show();
  }
}
