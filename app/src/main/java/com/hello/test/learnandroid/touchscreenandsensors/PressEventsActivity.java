package com.hello.test.learnandroid.touchscreenandsensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hello.test.learnandroid.MainActivity;
import com.hello.test.learnandroid.R;

public class PressEventsActivity extends AppCompatActivity {
  private Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_press_events);
    button = (Button)findViewById(R.id.button1);
    //click event
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
      }
    });

    //on click event
    button.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        Toast.makeText(getApplicationContext(),"Long Press",Toast.LENGTH_SHORT).show();
        return true;
      }
    });

  }

}
