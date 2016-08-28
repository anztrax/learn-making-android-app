package com.hello.test.learnandroid.multimedia;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import com.hello.test.learnandroid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Camera2APIActivity extends AppCompatActivity {

  private CameraDevice cameraDevice = null;
  private CaptureRequest.Builder captureRequestBuilder = null;
  private CameraCaptureSession cameraCaptureSession = null;
  private TextureView textureView = null;
  private Size previewSize = null;
  private static final int REQUEST_CAMERA = 1190;
  private static String TAG = "PermissionRequest";

  //this is for requestPermission
  private void requestPermission(){
    int permission = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

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
    setContentView(R.layout.activity_camera2_api);
    requestPermission();
    textureView = (TextureView)findViewById(R.id.textureView);
    textureView.setSurfaceTextureListener(surfaceTextureListener);
  }

  @Override
  protected void onPause() {
    super.onPause();
    if(cameraDevice != null){
      cameraDevice.close();
      cameraDevice = null;
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if(textureView.isAvailable()){
      openCamera();
    }else{
      textureView.setSurfaceTextureListener(surfaceTextureListener);
    }
  }

  public void openCamera(){
    CameraManager cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);
    try{
      String cameraId = cameraManager.getCameraIdList()[0];
      CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
      StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
      previewSize = map.getOutputSizes(SurfaceTexture.class)[0];
      cameraManager.openCamera(cameraId,stateCallback,null);

    }catch (CameraAccessException e){
      e.printStackTrace();
    }catch (SecurityException e){
      e.printStackTrace();
    }
  }

  public void startPreview(CameraCaptureSession session){
    cameraCaptureSession = session;
    captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

    HandlerThread backgroundThread = new HandlerThread("CameraPreview");
    backgroundThread.start();
    Handler backgroundHandler = new Handler(backgroundThread.getLooper());
    try{
      cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null,backgroundHandler);
    }catch (CameraAccessException e){
      e.printStackTrace();
    }
  }

  private File getPictureFile(){
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());  //change long / millis to yyyyMMdd_HHmmss
    String filename = String.format("PHOTO_%s.jpg",timestamp);
    File pictureFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),filename);
    return pictureFile;
  }

  public void takeAPicture(View view){
    if(cameraDevice == null){
      return;
    }
    CameraManager cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);
    try{
      CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraDevice.getId());
      StreamConfigurationMap configurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
      if(configurationMap == null){
        return;
      }

      Size largest = Collections.max(Arrays.asList(configurationMap.getOutputSizes(ImageFormat.JPEG)),new CompareSizesByArea());
      ImageReader imageReader = ImageReader.newInstance(largest.getWidth(),largest.getHeight(),ImageFormat.JPEG,1);
      List<Surface> outputSurfaces = new ArrayList<Surface>(2);
      outputSurfaces.add(imageReader.getSurface());
      outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));

      final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
      captureBuilder.addTarget(imageReader.getSurface());
      captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
      ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
          Image image = null;
          try{
            image = reader.acquireLatestImage();
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.capacity()];
            buffer.get(bytes);
            OutputStream outputStream = new FileOutputStream(getPictureFile());
            outputStream.write(bytes);
            outputStream.close();
          }catch (FileNotFoundException e){
            e.printStackTrace();
          }catch (IOException e){
            e.printStackTrace();
          }finally {
            if(image != null){
              image.close();
            }
          }
        }
      };

      HandlerThread backgroundThread = new HandlerThread("CameraPreview");
      backgroundThread.start();
      final Handler backgroundHandler = new Handler(backgroundThread.getLooper());
      imageReader.setOnImageAvailableListener(readerListener,backgroundHandler);
      final CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
          super.onCaptureCompleted(session, request, result);
          Toast.makeText(Camera2APIActivity.this, "Picture Saved", Toast.LENGTH_SHORT).show();
          startPreview(session);
        }
      };
      cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(CameraCaptureSession session) {
          try{
            session.capture(captureBuilder.build(), captureCallback,backgroundHandler);
          }catch (CameraAccessException e){
            e.printStackTrace();
          }
        }

        @Override
        public void onConfigureFailed(CameraCaptureSession session) {}
      },backgroundHandler);

    }catch (CameraAccessException e){
      e.printStackTrace();
    }
  }

  private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
      openCamera();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {}

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
      return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {}
  };

  //preview state callback
  private CameraCaptureSession.StateCallback previewStateCallback = new CameraCaptureSession.StateCallback() {
    @Override
    public void onConfigured(CameraCaptureSession session) {
      startPreview(session);
    }

    @Override
    public void onConfigureFailed(CameraCaptureSession session) {

    }
  };

  //this is on state callback of camera device
  private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
    @Override
    public void onOpened(CameraDevice camera) {
      cameraDevice = camera;
      SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
      if(surfaceTexture == null){
        return ;
      }
      surfaceTexture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
      Surface surface = new Surface(surfaceTexture);
      try{
        captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
      }catch (CameraAccessException e){
        e.printStackTrace();
      }

      captureRequestBuilder.addTarget(surface);
      try{
        cameraDevice.createCaptureSession(Arrays.asList(surface), previewStateCallback,null);
      }catch (CameraAccessException e){
        e.printStackTrace();
      }
    }

    @Override
    public void onDisconnected(CameraDevice camera) {}

    @Override
    public void onError(CameraDevice camera, int error) {}
  };

  static class CompareSizesByArea implements Comparator<Size>{
    @Override
    public int compare(Size lhs, Size rhs) {
      return Long.signum((long)lhs.getWidth() * lhs.getHeight() - (long)rhs.getWidth() * rhs.getHeight());
    }
  }
}
