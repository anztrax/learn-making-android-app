package com.hello.test.learnandroid.multimedia;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hello.test.learnandroid.R;

import java.io.File;
import java.text.SimpleDateFormat;

public class UsingDefaultCameraAppActivity extends AppCompatActivity {
  final int PHOTO_RESULT = 1;
  private Uri mLastPhotoUri = null;
  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_using_default_camera_app);
    imageView = (ImageView)findViewById(R.id.imageView);
  }

  private Uri createFileUri(){
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());  //change long / millis to yyyyMMdd_HHmmss
    String filename = String.format("PHOTO_%s.jpg",timestamp);
    return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),filename));

  }

  public void onClickTakePicture(View view){
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if(takePictureIntent.resolveActivity(getPackageManager()) != null){
      mLastPhotoUri = createFileUri();
      takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mLastPhotoUri);
      startActivityForResult(takePictureIntent, PHOTO_RESULT);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode == PHOTO_RESULT && resultCode == RESULT_OK){
      //set image bitmap and get path from last photo uri
      imageView.setImageBitmap(BitmapFactory.decodeFile(mLastPhotoUri.getPath()));

      /**
       * //if you don't care about the URL you can get data from data
       * if (data != null) {
       *  imageView.setImageBitmap((Bitmap) data.getExtras().get("data"));
       * }
       */

      /**
       * or you can get full resolution image
       * if (data != null) {
       *  try {
       *    imageView.setImageBitmap(MediaStore.Images.Media. getBitmap(getContentResolver(),
       *    Uri.parse(data.toUri(Intent.URI_ALLOW_UNSAFE))));
       *    } catch (IOException e) {
       *    e.printStackTrace();
       *    }
       *  }
       */
    }
  }
}
