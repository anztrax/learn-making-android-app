package com.hello.test.learnandroid.notificationandalerts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import com.hello.test.learnandroid.R;

public class FlashLightWithHeadsUpActivity extends AppCompatActivity {

  private static final String ACTION_STOP="STOP";
  private CameraManager cameraManager;
  private String mCameraId = null;
  private ToggleButton toggleLightButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flash_light_with_heads_up);
    toggleLightButton = (ToggleButton)findViewById(R.id.toggleButton);

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

  //handle response when the user pressed the notification
  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    if(ACTION_STOP.equals(intent.getAction())){
      setFlashLight(false);
    }
  }

  public void clickLight(View view){
    setFlashLight(toggleLightButton.isChecked());
    if(toggleLightButton.isChecked()){
      showNotification();
    }
  }

  public void setFlashLight(boolean enabled){
    toggleLightButton.setChecked(enabled);
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      try{
        cameraManager.setTorchMode(mCameraId,toggleLightButton.isChecked());
      }catch (CameraAccessException e){
        e.printStackTrace();
      }
    }
  }

  private void showNotification(){
    Intent activityIntent = new Intent(this,FlashLightWithHeadsUpActivity.class);
    activityIntent.setAction(ACTION_STOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,activityIntent,0);
    Notification.Builder notificationBuilder = new Notification.Builder(this)
      .setContentTitle("Flash Light")
      .setContentText("Press to turn off the flashlight")
      .setSmallIcon(R.mipmap.ic_launcher)
      .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
      .setContentIntent(pendingIntent)
      .setVibrate(new long[]{200,500,250,500,250,500})
      .setPriority(Notification.PRIORITY_MAX);

    NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(0,notificationBuilder.build());
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
}
