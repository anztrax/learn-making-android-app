package com.hello.test.learnandroid.notificationandalerts;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.hello.test.learnandroid.R;

public class LightActionSoundReduxActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_light_action_sound_redux);
  }

  public void onClickLightSoundActionReduxAction(View view){
    Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
      .setContentText("Light Action Sound Redux")
      .setSmallIcon(R.mipmap.ic_launcher)
      .setContentTitle("Light , Action & Sound")
      .setSound(notificationSoundUri)
      .setLights(Color.BLUE,500,500)
      /**
       * After a 200ms delay, vibrate for 500ms then pause for another
       * 250ms and then vibrate for 500ms
       * 250ms and then vibrate for 500ms
       */
      .setVibrate(new long[]{200,500,250,500,250,500});
    Intent intent = new Intent(this,LightActionSoundReduxActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
    notificationBuilder.addAction(android.R.drawable.ic_dialog_alert,"Go to this intent gan",pendingIntent);
    NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(0,notificationBuilder.build());
  }
}
