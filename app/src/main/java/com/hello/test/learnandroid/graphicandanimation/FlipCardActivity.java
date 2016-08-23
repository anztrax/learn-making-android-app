package com.hello.test.learnandroid.graphicandanimation;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.hello.test.learnandroid.R;
import com.hello.test.learnandroid.graphicandanimation.fragment.CardBackFragment;
import com.hello.test.learnandroid.graphicandanimation.fragment.CardFrontFragment;

public class FlipCardActivity extends AppCompatActivity {
  private boolean showingBack = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flip_card);
    FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
    frameLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        flipCard();
      }
    });

    if(savedInstanceState == null){
      getFragmentManager()
        .beginTransaction()
        .add(R.id.frameLayout, new CardFrontFragment())
        .commit();
    }
  }

  private void flipCard(){
    if(showingBack){
      showingBack = false;
      getFragmentManager().popBackStack();
    }else{
      showingBack = true;
      getFragmentManager()
        .beginTransaction()
        .setCustomAnimations(
          R.animator.flip_card_right_enter,
          R.animator.flip_card_right_exit,
          R.animator.flip_card_left_enter,
          R.animator.flip_card_left_exit)
        .replace(R.id.frameLayout, new CardBackFragment())
        .addToBackStack(null)
        .commit();
    }
  }
}
