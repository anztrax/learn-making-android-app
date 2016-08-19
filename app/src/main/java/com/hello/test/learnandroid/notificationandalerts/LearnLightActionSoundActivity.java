package com.hello.test.learnandroid.notificationandalerts;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import com.hello.test.learnandroid.R;

public class LearnLightActionSoundActivity extends AppCompatActivity {
  private CameraManager cameraManager;
  private String mCameraId = null;
  private ToggleButton toggleLightButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_light_action_sound);
    toggleLightButton = (ToggleButton)findViewById(R.id.buttonLights);
    //toggle flash light only for android M
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      cameraManager = (CameraManager)this.getSystemService(Context.CAMERA_SERVICE);
      mCameraId = getCameraId();
      if(mCameraId == null){
        toggleLightButton.setEnabled(false);
      }else{
        toggleLightButton.setEnabled(true);
      }
    }else{
      toggleLightButton.setEnabled(false);
    }
  }

  private String getCameraId(){
    try{
      String[] ids = cameraManager.getCameraIdList();
      for(String id : ids){
        CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(id);
        Boolean flashAvailable = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        Integer facingDirection = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
        if(flashAvailable != null && flashAvailable && facingDirection != null && facingDirection == CameraCharacteristics.LENS_FACING_BACK){
          return id;
        }
      }
    }catch (CameraAccessException e){
      e.printStackTrace();
    }
    return null;
  }

  public void clickLights(View view){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      try{
        cameraManager.setTorchMode(mCameraId,toggleLightButton.isChecked());
      }catch (CameraAccessException e){
        e.printStackTrace();
      }
    }
  }

  public void clickVibrate(View view){
    ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(1000);
  }

  public void clickSound(View view){
    Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),notificationSoundUri);
    ringtone.play();
  }
}
