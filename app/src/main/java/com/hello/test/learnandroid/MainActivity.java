package com.hello.test.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hello.test.learnandroid.notificationandalerts.LearnLightActionSoundActivity;

public class MainActivity extends Activity {
  private final String KEY_DUPLICATE = "duplicate";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void showSecondActivity(View v){
    Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
    startActivity(intent);
  }

  public void gotoCreateShortcut(View v){
    this.doMainActionIntent("com.android.launcher.action.INSTALL_SHORTCUT","Shortcut Created");
  }

  public void gotoUnInstallShortcut(View v){
    this.doMainActionIntent("com.android.launcher.action.UNINSTALL_SHORTCUT","Uninstall Shortcut");
  }

  public void onClickDataSectionBtn(View v){
    Intent intent = new Intent(getApplicationContext(),LearnDataMainActivity.class);
    startActivity(intent);
  }
  public void onClickNotificationsAndAlerts(View v){
    Intent intent = new Intent(getApplicationContext(), LearnNotificationAndAlertMainActivity.class);
    startActivity(intent);
  }

  private void doMainActionIntent(String actionString,String message){
    Intent mainActivityIntent = new Intent(this,MainActivity.class);
    mainActivityIntent.setAction(Intent.ACTION_MAIN);
    Intent intent = new Intent();

    intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,mainActivityIntent);
    intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
    intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher));
    intent.putExtra(KEY_DUPLICATE, false);

    intent.setAction(actionString);
    sendBroadcast(intent);
    Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_SHORT).show();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.menu_settings){
      Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
    }else{
      return super.onContextItemSelected(item);
    }

    //return true if you handle the callback , otherwise return super.onContextItemSelected
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main,menu);
    return true;
  }
}
