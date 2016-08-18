package com.hello.test.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SecondActivity extends Activity {
  private final int MENU_DOWNLOAD = 1;
  private final int MENU_SETTINGS = 2;
  private boolean showDownloadMenu = false;
  private PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
      switch (item.getItemId()){
        case R.id.menu_reply:
          Toast.makeText(SecondActivity.this,"Reply",Toast.LENGTH_SHORT).show();
          return true;
        case R.id.menu_reply_all:
          Toast.makeText(SecondActivity.this,"Reply All",Toast.LENGTH_SHORT).show();
          return true;
        case R.id.menu_forward:
          Toast.makeText(SecondActivity.this,"Forward",Toast.LENGTH_SHORT).show();
          return true;
        default:
          return false;
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_automatic_theme_selector);
  }

  public void onClickToggleMenuBar(View view){
    showDownloadMenu = !showDownloadMenu;
    //tell the option menu, or menu is no longer valid so FORCE refresh the menu
    invalidateOptionsMenu();
  }

  public void onClickGotToContextMenuActivity(View view){
    Intent intent = new Intent(SecondActivity.this,LearnContextMenuActivity.class);
    startActivity(intent);
  }

  public void onClickGotToContextBatchActivity(View view){
    Intent intent = new Intent(SecondActivity.this,LearnContextualBatchActivity.class);
    startActivity(intent);
  }

  public void onClickGotoSimpleFragmentActivity(View view){
    Intent intent = new Intent(SecondActivity.this,LearnFragmentActivity.class);
    startActivity(intent);
  }

  public void onClickGotoLearnChangeAtRuntimeFragmentActivity(View view){
    Intent intent = new Intent(SecondActivity.this,ChangeFragmentAtRuntimeActivity.class);
    startActivity(intent);
  }

  public void onClickLearnCommunicateWithOtherFragmentActivity(View view){
    Intent intent = new Intent(SecondActivity.this,LearnCommunicateWithOtherFragment.class);
    startActivity(intent);
  }

  public void onClickDefaultSearchable(View view){
    Intent intent = new Intent(SecondActivity.this,SearchableMenuActivity.class);
    startActivity(intent);
  }

  public void gotoImmersiveModeActivity(View view){
    Intent intent = new Intent(SecondActivity.this,ImmersiveModeActivity.class);
    startActivity(intent);
  }

  public void showPopUpMenu(View view){
    PopupMenu popupMenu = new PopupMenu(SecondActivity.this,view);
    popupMenu.inflate(R.menu.popup_menu);
    popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
    popupMenu.show();
  }

  //use on prepare option menu to update / change menu visibility
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    MenuItem menuItem = menu.findItem(MENU_DOWNLOAD);
    menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menuItem.setVisible(showDownloadMenu);
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(0,MENU_DOWNLOAD,0,R.string.menu_download);
    menu.add(0,MENU_SETTINGS,1,R.string.menu_settings);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case MENU_DOWNLOAD:
        Toast.makeText(this, R.string.menu_download, Toast.LENGTH_SHORT).show();
        break;
      case MENU_SETTINGS:
        Toast.makeText(this, R.string.menu_settings, Toast.LENGTH_SHORT).show();
        break;
      default:
        return super.onContextItemSelected(item);
    }
    return true;
  }
}
