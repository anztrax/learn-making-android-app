package com.hello.test.learnandroid.graphicandanimation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.hello.test.learnandroid.R;
import com.hello.test.learnandroid.graphicandanimation.fragment.SliderFragment;

public class LearnSimpleImageSliderActivity extends AppCompatActivity {
  private final int PAGE_COUNT = 4;
  private ViewPager mViewPager;
  private PagerAdapter pagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_simple_imageslider);
    mViewPager = (ViewPager)findViewById(R.id.viewPager);
    pagerAdapter = new SliderAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(pagerAdapter);
  }

  @Override
  public void onBackPressed() {
    if(mViewPager.getCurrentItem() == 0){
      super.onBackPressed();
    }else{
      mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
    }
  }



  //slider adapter ?
  private class SliderAdapter extends FragmentStatePagerAdapter{
    public SliderAdapter(FragmentManager fragmentManager){
      super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
      SliderFragment sliderFragment = new SliderFragment();
      switch (position){
        case 0:
          sliderFragment.setImage(R.drawable.coffee0);
          break;
        case 1:
          sliderFragment.setImage(R.drawable.coffee1);
          break;
        case 2:
          sliderFragment.setImage(R.drawable.coffee2);
          break;
        case 3:
          sliderFragment.setImage(R.drawable.coffee3);
          break;
      }
      return sliderFragment;
    }

    @Override
    public int getCount() {
      return PAGE_COUNT;
    }
  }
}
