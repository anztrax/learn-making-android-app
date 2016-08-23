package com.hello.test.learnandroid.graphicandanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.hello.test.learnandroid.R;

public class LoadLargeImageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_load_large_image);
    ImageView imageView = (ImageView)findViewById(R.id.imageViewThumbnail);
    imageView.setImageBitmap(loadSampledResource(R.drawable.large_image,100,100));
  }

  public Bitmap loadSampledResource(int imageId,int targetHeight, int targetWidth){
    final BitmapFactory.Options options = new BitmapFactory.Options();
    //just load the image without set image to the memory
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(getResources(), imageId,options);
    final int originalHeight = options.outHeight;
    final int originalWidth = options.outWidth;
    int inSampleSize = 1;
    while((originalHeight/(inSampleSize *2 )) > targetHeight && (originalWidth / (inSampleSize *2)) > targetWidth){
      inSampleSize *= 2;
    }
    options.inSampleSize = inSampleSize;
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(getResources(),imageId,options);
  }
}
