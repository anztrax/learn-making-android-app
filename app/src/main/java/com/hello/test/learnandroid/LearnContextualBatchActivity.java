package com.hello.test.learnandroid;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LearnContextualBatchActivity extends ListActivity {

  private AbsListView.MultiChoiceModeListener mMultiChoiceModeListener = new AbsListView.MultiChoiceModeListener() {
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
      MenuInflater menuInflater = mode.getMenuInflater();
      menuInflater.inflate(R.menu.menu_for_context_batch, menu);
      return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
      return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
      //handle menu selections
      switch (item.getItemId()){
        case R.id.menu_move:
          Toast.makeText(LearnContextualBatchActivity.this,"Move", Toast.LENGTH_SHORT).show();
          mode.finish();
          return true;
        case R.id.menu_delete:
          Toast.makeText(LearnContextualBatchActivity.this,"Delete", Toast.LENGTH_SHORT).show();
          mode.finish();
          return true;
        default:
          return false;
      }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_contextual_batch);

    String[] countries = new String[]{"China","France","Indonesia","Malaysia","Singapore","Laos","Thailand"};
    ListAdapter countryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, countries);
    setListAdapter(countryAdapter);

    //to show that kind of things using MODE MULTIPLE MODAL
    getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    getListView().setMultiChoiceModeListener(mMultiChoiceModeListener);
    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((ListView)parent).setItemChecked(position,true);
      }
    });
  }

}
