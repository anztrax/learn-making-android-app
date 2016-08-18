package com.hello.test.learnandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hello.test.learnandroid.R;

public class DetailFragment extends Fragment{
  public static String KEY_COUNTRY_NAME="KEY_COUNTRY_NAME";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.detail_fragment, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Bundle bundle = getArguments();
    if(bundle != null && bundle.containsKey(KEY_COUNTRY_NAME)){
      showSelectedCountry(bundle.getString(KEY_COUNTRY_NAME));
    }
  }

  public void showSelectedCountry(String countryName){
    ((TextView)getView().findViewById(R.id.textViewCountryName)).setText(countryName);
  }
}
