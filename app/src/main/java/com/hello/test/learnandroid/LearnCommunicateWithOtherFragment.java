package com.hello.test.learnandroid;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.hello.test.learnandroid.fragment.DetailFragment;
import com.hello.test.learnandroid.fragment.MasterFragment;

public class LearnCommunicateWithOtherFragment extends FragmentActivity {
  boolean dualPane;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_communicate_with_other_fragment);

    MasterFragment masterFragment = null;
    FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
    if(frameLayout != null){
      dualPane = false;
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      masterFragment = (MasterFragment)getSupportFragmentManager().findFragmentByTag("MASTER");
      if(masterFragment == null){
        masterFragment = new MasterFragment();
        fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
      }
      DetailFragment detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
      if(detailFragment != null){
        fragmentTransaction.remove(detailFragment);
      }
      fragmentTransaction.commit();
    }else{
      dualPane = true;
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      masterFragment = (MasterFragment)getSupportFragmentManager().findFragmentById(R.id.frameLayoutMaster);
      if(masterFragment == null){
        masterFragment = new MasterFragment();
        fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
      }
      DetailFragment detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
      if(detailFragment == null){
        detailFragment = new DetailFragment();
        fragmentTransaction.add(R.id.frameLayoutDetail, detailFragment);
      }
      fragmentTransaction.commit();
    }
    masterFragment.setmOnMasterSelectedListener(new MasterFragment.OnMasterSelectedListener() {
      @Override
      public void onItemSelected(String countryName) {
        sendCountryName(countryName);
      }
    });
  }

  private void sendCountryName(String countryName){
    DetailFragment detailFragment;
    if(dualPane){
      //two pane layout
      detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
      detailFragment.showSelectedCountry(countryName);
    }else{
      //single page layout
      detailFragment = new DetailFragment();
      Bundle bundle = new Bundle();
      bundle.putString(DetailFragment.KEY_COUNTRY_NAME, countryName);
      detailFragment.setArguments(bundle);

      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.frameLayout, detailFragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
    }
  }
}
