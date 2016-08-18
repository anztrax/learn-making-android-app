package com.hello.test.learnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LearnContextMenuActivity extends Activity {
  //this use action mode
  ActionMode actionMode;

  private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
      mode.getMenuInflater().inflate(R.menu.context_menu,menu);
      mode.setTitle("Specific Image");
      return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
      return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
      switch (item.getItemId()){
        case R.id.menu_cast:
          Toast.makeText(LearnContextMenuActivity.this,"Cast",Toast.LENGTH_SHORT).show();
          mode.finish();
          return true;
        case R.id.menu_print:
          Toast.makeText(LearnContextMenuActivity.this,"Print",Toast.LENGTH_SHORT).show();
          mode.finish();
          return true;
        default:
          return false;
      }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
      actionMode = null;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_context_menu);
    ImageView imageView = (ImageView)findViewById(R.id.imageView);
    imageView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        if(actionMode != null) return false;
        actionMode = startActionMode(mActionModeCallback);
        return true;
      }
    });
  }
}
