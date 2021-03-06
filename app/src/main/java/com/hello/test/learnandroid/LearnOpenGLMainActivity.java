package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.opengles.CreatingMovementActivity;
import com.hello.test.learnandroid.opengles.DrawSimpleShapeActivity;
import com.hello.test.learnandroid.opengles.ProjectionAndCameraActivity;
import com.hello.test.learnandroid.opengles.RotateRectangleWithUserInputActivity;
import com.hello.test.learnandroid.opengles.SimpleOpenGL;

public class LearnOpenGLMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_open_glmain);
  }

  public void onClickSimpleOpenGLSetup(View view){
    Intent intent = new Intent(getApplicationContext(), SimpleOpenGL.class);
    startActivity(intent);
  }

  public void onClickDrawSimpleShapeActivity(View view){
    Intent intent = new Intent(getApplicationContext(), DrawSimpleShapeActivity.class);
    startActivity(intent);
  }

  public void onClickProjectionAndCameraActivity(View view){
    Intent intent = new Intent(getApplicationContext(), ProjectionAndCameraActivity.class);
    startActivity(intent);
  }

  public void onClickCreatingMovementActivity(View view){
    Intent intent = new Intent(getApplicationContext(), CreatingMovementActivity.class);
    startActivity(intent);
  }
  public void onClickRotateRectangleWithUserInputActivity(View view){
    Intent intent = new Intent(getApplicationContext(),RotateRectangleWithUserInputActivity.class);
    startActivity(intent);
  }
}
