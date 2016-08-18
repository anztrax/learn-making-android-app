package com.hello.test.learnandroid;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hello.test.learnandroid.fragment.SimpleFragment1;
import com.hello.test.learnandroid.fragment.SimpleFragment2;

public class ChangeFragmentAtRuntimeActivity extends FragmentActivity implements SimpleFragment1.OnFragmentInteractionListener, SimpleFragment2.OnFragmentInteractionListener {

  SimpleFragment1 simpleFragment1;
  SimpleFragment2 simpleFragment2;
  int showingFragment = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_change_fragment_at_runtime);
    FragmentManager fragmentManager = getSupportFragmentManager();
    simpleFragment1 = new SimpleFragment1();
    simpleFragment2 = new SimpleFragment2();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.frameLayout, simpleFragment1);
    fragmentTransaction.commit();
    showingFragment = 1;
  }

  public void switchFragment(View view){
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    switch (showingFragment){
      case 1:
        fragmentTransaction.replace(R.id.frameLayout,simpleFragment2);
        showingFragment = 2;
        break;
      case 2:
        fragmentTransaction.replace(R.id.frameLayout,simpleFragment1);
        showingFragment = 1;
        break;
    }
    fragmentTransaction.commit();
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }
}
