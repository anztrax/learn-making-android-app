package com.hello.test.learnandroid.graphicandanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.hello.test.learnandroid.R;

public class ZoomAnimationWithCustomTransitionActivity extends AppCompatActivity {
  Animator currentAnimator;
  ImageView imageViewExpanded;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zoom_animation_with_custom_transition);
    final ImageView imageViewThumbnail = (ImageView)findViewById(R.id.imageViewThumbnail);
    imageViewThumbnail.setImageBitmap(loadSampledResource(R.drawable.raindrop, 100, 100));
    imageViewThumbnail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        zoomFromThumbnail((ImageView)v);
      }
    });
    imageViewExpanded = (ImageView)findViewById(R.id.imageViewExpanded);
    imageViewExpanded.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageViewExpanded.setVisibility(View.GONE);
        imageViewExpanded.setImageBitmap(null);
        imageViewThumbnail.setVisibility(View.VISIBLE);
      }
    });
  }

  private void zoomFromThumbnail(final ImageView imageViewThumbnail){
    if(currentAnimator != null){
      currentAnimator.cancel();
    }

    final Rect startBounds = new Rect();
    final Rect finalBounds = new Rect();
    final Point globalOffset = new Point();

    imageViewThumbnail.getGlobalVisibleRect(startBounds);
    findViewById(R.id.frameLayout).getGlobalVisibleRect(finalBounds,globalOffset);
    imageViewExpanded.setImageBitmap(loadSampledResource(R.drawable.raindrop,finalBounds.height(),finalBounds.width()));

    startBounds.offset(-globalOffset.x, -globalOffset.y);
    finalBounds.offset(-globalOffset.x, -globalOffset.y);

    float startScale;
    if((float) finalBounds.width() / finalBounds.height() > (float) startBounds.width() / startBounds.height()){
      startScale = (float)startBounds.height()/ finalBounds.height();
      float startWidth = startScale * finalBounds.width();
      float deltaWidth = (startWidth - startBounds.width()) / 2;
      startBounds.left -= deltaWidth;
      startBounds.right += deltaWidth;
    }else{
      startScale = (float)startBounds.width()/ finalBounds.width();
      float startHeight  = startScale * finalBounds.height();
      float deltaHeight = (startHeight - startBounds.height()) / 2;
      startBounds.top -= deltaHeight;
      startBounds.bottom += deltaHeight;
    }

    imageViewThumbnail.setVisibility(View.GONE);
    imageViewExpanded.setVisibility(View.VISIBLE);
    imageViewExpanded.setPivotX(0f);
    imageViewExpanded.setPivotY(0f);

    //set in animator
    AnimatorSet animatorSet =new AnimatorSet();
    animatorSet
      .play(ObjectAnimator.ofFloat(imageViewExpanded, View.X, startBounds.left, startBounds.left))
      .with(ObjectAnimator.ofFloat(imageViewExpanded, View.Y, startBounds.top, finalBounds.top))
      .with(ObjectAnimator.ofFloat(imageViewExpanded, View.SCALE_X, startScale, 1f))
      .with(ObjectAnimator.ofFloat(imageViewExpanded, View.SCALE_Y, startScale, 1f));
    animatorSet.setDuration(R.integer.config_shortAnimTime);
    animatorSet.setInterpolator(new DecelerateInterpolator());
    animatorSet.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        currentAnimator = null;
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        currentAnimator = null;
      }
    });
    animatorSet.start();
    currentAnimator= animatorSet;
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
}
