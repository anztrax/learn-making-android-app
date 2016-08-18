package com.hello.test.learnandroid;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class SearchResultActivity extends Activity {
  TextView mTextViewSearchResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_result);
    mTextViewSearchResult = (TextView)findViewById(R.id.textViewSearchResult);

    if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
      handleSearch(getIntent().getStringExtra(SearchManager.QUERY));
    }
  }

  private void handleSearch(String searchQuery){
    mTextViewSearchResult.setText(searchQuery);
  }

}
