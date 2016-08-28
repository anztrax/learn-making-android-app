package com.hello.test.learnandroid.multimedia;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import com.hello.test.learnandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class CameraAPIActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener{

  private static String TAG = "PermissionDemo";
  private static final int REQUEST_CAMERA = 115;

  @Deprecated
  private Camera camera;
  private TextureView textureView;

  //camera callback
  Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
      try{
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());  //change long / millis to yyyyMMdd_HHmmss
        String filename = String.format("PHOTO_%s.jpg",timestamp);
        File pictureFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),filename);

        FileOutputStream fileOutputStream = new FileOutputStream(pictureFile.getPath());
        fileOutputStream.write(data);
        fileOutputStream.close();
      }catch (IOException e){
        e.printStackTrace();
      }
    }
  };

  private void requestPermission(){
    int permission = ContextCompat.checkSelfPermission(this,
      Manifest.permission.CAMERA);

    if(permission != PackageManager.PERMISSION_GRANTED){
      Log.i(TAG,"Permission to write at external storage denied");
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.CAMERA)) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Permission to access the SD-CARD is required for this app")
          .setTitle("Permission required");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

          public void onClick(DialogInterface dialog, int id) {
            Log.i(TAG, "Clicked");
            makeRequest();
          }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
      }else{
        makeRequest();
      }
    }
  }

  protected void makeRequest() {
    ActivityCompat.requestPermissions(this,
      new String[]{Manifest.permission.CAMERA},
      REQUEST_CAMERA);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode){
      case REQUEST_CAMERA:{
        if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
          Log.i(TAG, "Permission has been denied by user");
        }else{
          Log.i(TAG, "Permission has been granted by user");
        }
        return;
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera_api);
    textureView = (TextureView)findViewById(R.id.textureView);
    textureView.setSurfaceTextureListener(this);
    requestPermission();
  }

  public void takePicture(View view){
    if(camera != null){
      camera.takePicture(null,null,pictureCallback);
    }
  }

  @Override
  public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
    camera = Camera.open();
    if(camera != null){
      try{
        camera.setPreviewTexture(surface);
        camera.startPreview();
      }catch (IOException e){
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
    if(camera != null){
      camera.stopPreview();
      camera.release();
    }
    return true;
  }

  @Override
  public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

  }

  @Override
  public void onSurfaceTextureUpdated(SurfaceTexture surface) {

  }
}
