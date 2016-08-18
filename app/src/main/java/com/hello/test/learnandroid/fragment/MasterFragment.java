package com.hello.test.learnandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by andrew on 16/08/16.
 */
public class MasterFragment extends ListFragment{
  public interface OnMasterSelectedListener{
    public void onItemSelected(String countryName);
  }

  private OnMasterSelectedListener mOnMasterSelectedListener = null;
  public void setmOnMasterSelectedListener(OnMasterSelectedListener onMasterSelectedListener){
    mOnMasterSelectedListener = onMasterSelectedListener;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    String[] countries = new String[]{"China","France","Germany","India","Russia"};
    ListAdapter countryAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,countries);
    setListAdapter(countryAdapter);
    getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mOnMasterSelectedListener != null){
          mOnMasterSelectedListener.onItemSelected(((TextView)view).getText().toString());
        }
      }
    });
  }
}
