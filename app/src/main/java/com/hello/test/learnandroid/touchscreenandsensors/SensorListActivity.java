package com.hello.test.learnandroid.touchscreenandsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

import java.util.ArrayList;
import java.util.List;

public class SensorListActivity extends AppCompatActivity {

  /**
   * there are 3 type of sensors :
   * Motion sensors
   * Environmental sensors
   * Position sensors
   *
   */

  private ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sensor_list);
    listView = (ListView)findViewById(R.id.listView);
    List sensorList = new ArrayList<>();

    SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

    //check is this sensor is available or not
    if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
      Toast.makeText(getApplicationContext(),"Good mate!, accelerometer is available",Toast.LENGTH_SHORT).show();
    }

    List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
    for(Sensor sensor : sensors){
      sensorList.add(sensor.getName());
    }
    ListAdapter listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sensorList);
    listView.setAdapter(listAdapter);
  }
}
