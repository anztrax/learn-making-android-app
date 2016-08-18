package com.hello.test.learnandroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

public class LearnFragmentActivity extends FragmentActivity implements SimpleFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_fragment);
    }

    //must implement fragment interaction listener
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
