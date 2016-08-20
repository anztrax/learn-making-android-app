package com.hello.test.learnandroid.touchscreenandsensors;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hello.test.learnandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwipeToRefreshActivity extends AppCompatActivity {
  private SwipeRefreshLayout swipeRefreshLayout;
  private ListView listView;
  private List<String> mArrayList = new ArrayList<>();
  private int mRefreshCount = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_swipe_to_refresh);
    swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.activity_swipe_to_refresh);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        refreshList();
      }
    });

    listView = (ListView)findViewById(android.R.id.list);
    String[] countries = new String[]{"China","France","Germany","India","Russia"};
    mArrayList = new ArrayList<String>(Arrays.asList(countries));
    ListAdapter countryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mArrayList);
    listView.setAdapter(countryAdapter);
  }

  public void refreshList(){
    mRefreshCount++;
    mArrayList.add("Refresh: " + mRefreshCount);
    ListAdapter mCountryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mArrayList);
    listView.setAdapter(mCountryAdapter);
    swipeRefreshLayout.setRefreshing(false);
  }
}
