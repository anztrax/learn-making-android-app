package com.hello.test.learnandroid.graphicandanimation.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hello.test.learnandroid.R;


public class SliderFragment extends Fragment {
  private int imageResourceId;

  public SliderFragment(){

  }

  public void setImage(int resourceId){
    imageResourceId = resourceId;
  }

  public Bitmap loadSampledResource(int imageId, int targetHeight, int targetWidth){
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

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_slide_image,container,false);
    ImageView imageView = (ImageView)rootView.findViewById(R.id.imageView);
    Bitmap bitmap = loadSampledResource(imageResourceId,200,200);
    imageView.setImageBitmap(bitmap);
    return rootView;
  }
}
