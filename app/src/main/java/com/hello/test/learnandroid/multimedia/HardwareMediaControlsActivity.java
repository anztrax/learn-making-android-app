package com.hello.test.learnandroid.multimedia;

import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

public class HardwareMediaControlsActivity extends AppCompatActivity {

  private MediaSessionCompat.Callback mediaSessionCallback = new MediaSessionCompat.Callback() {
    @Override
    public void onPlay() {
      super.onPlay();
      Toast.makeText(getApplicationContext(),"onPlay()",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
      super.onPause();
      Toast.makeText(getApplicationContext(),"onPause()",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSkipToNext() {
      super.onSkipToNext();
      Toast.makeText(getApplicationContext(),"onSkipToNext()",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSkipToPrevious() {
      super.onSkipToPrevious();
      Toast.makeText(getApplicationContext(),"onSkipToPrevious()",Toast.LENGTH_SHORT).show();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hardware_media_controls);
    MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this,getApplication().getPackageName());
    mediaSessionCompat.setCallback(mediaSessionCallback);
    mediaSessionCompat.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS);
    mediaSessionCompat.setActive(true);
    PlaybackStateCompat state = new PlaybackStateCompat.Builder().
      setActions(
        PlaybackStateCompat.ACTION_PLAY | PlaybackStateCompat.ACTION_PLAY_PAUSE | PlaybackStateCompat.ACTION_PAUSE |
          PlaybackStateCompat.ACTION_SKIP_TO_NEXT | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
      ).build();
    mediaSessionCompat.setPlaybackState(state);
  }
}
