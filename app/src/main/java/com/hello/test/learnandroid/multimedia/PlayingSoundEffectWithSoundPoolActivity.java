package com.hello.test.learnandroid.multimedia;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hello.test.learnandroid.R;

import java.util.HashMap;
import java.util.Map;

public class PlayingSoundEffectWithSoundPoolActivity extends AppCompatActivity {
  Map<Integer,Integer> hashMap = null;
  SoundPool soundPool;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_playing_sound_effect_with_sound_pool);
    final Button button1 = (Button)findViewById(R.id.button1);
    button1.setEnabled(false);
    final Button button2 = (Button)findViewById(R.id.button2);
    button2.setEnabled(false);

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
      createSoundPoolNew();
    }else{
      createSoundPoolOld();
    }

    soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
      @Override
      public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        button1.setEnabled(true);
        button2.setEnabled(true);
      }
    });

    hashMap = new HashMap<>();
    hashMap.put(1,soundPool.load(this,R.raw.sound_1,1));
    hashMap.put(2,soundPool.load(this,R.raw.sound_2,2));
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public void createSoundPoolNew(){
    AudioAttributes audioAttributes = new AudioAttributes.Builder()
      .setUsage(AudioAttributes.USAGE_MEDIA)
      .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
      .build();

    soundPool = new SoundPool.Builder()
      .setAudioAttributes(audioAttributes)
      .setMaxStreams(2)
      .build();
  }

  @SuppressWarnings("deprecation")
  public void createSoundPoolOld(){
    soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
  }

  public void onClickPlaySound1(View view){
    soundPool.play(hashMap.get(1),0.1f,0.1f,1,0,1.0f);
  }

  public void onClickPlaySound2(View view){
    soundPool.play(hashMap.get(2),0.9f,0.9f,1,1,1.0f);
  }

  @Override
  protected void onStop() {
    super.onStop();
    soundPool.release();
  }
}
