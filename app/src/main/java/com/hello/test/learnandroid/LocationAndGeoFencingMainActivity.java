package com.hello.test.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hello.test.learnandroid.locationandgeofencing.LearnGoogleMapsActivity;

public class LocationAndGeoFencingMainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location_and_geo_fencing_main);
  }

  public void onClickLearnGoogleMapsActivity(View view){
    Intent intent = new Intent(getApplicationContext(), LearnGoogleMapsActivity.class);
    startActivity(intent);
  }
}
