package com.hello.test.learnandroid.learndata;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hello.test.learnandroid.R;
import com.hello.test.learnandroid.databasehelper.DictionaryAdapter;
import com.hello.test.learnandroid.databasehelper.DictionaryDatabase;
import com.hello.test.learnandroid.databasehelper.DictionaryLoader;

public class LearnUsingLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
  EditText wordEditText;
  EditText definitionEditText;
  DictionaryDatabase dictionaryDatabase;
  ListView mListView;
  DictionaryAdapter dictionaryAdapter;

  private void saveRecord(){
    dictionaryDatabase.saveRecord(wordEditText.getText().toString(),definitionEditText.getText().toString());
    wordEditText.setText("");
    definitionEditText.setText("");
    getSupportLoaderManager().restartLoader(0,null,LearnUsingLoaderActivity.this);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_using_loader);

    dictionaryDatabase = new DictionaryDatabase(this);
    wordEditText = (EditText)findViewById(R.id.word);
    definitionEditText = (EditText)findViewById(R.id.definition);

    Button buttonAddUpdate = (Button)findViewById(R.id.button_add_update);
    buttonAddUpdate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        saveRecord();
      }
    });

    mListView = (ListView)findViewById(R.id.listView);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),dictionaryDatabase.getDefinition(id),Toast.LENGTH_SHORT).show();
      }
    });
    mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Record deleted = " + dictionaryDatabase.deleteRecord(id), Toast.LENGTH_SHORT).show();
        getSupportLoaderManager().restartLoader(0,null,LearnUsingLoaderActivity.this);
        return true;
      }
    });
    getSupportLoaderManager().initLoader(0,null,this);
    dictionaryAdapter = new DictionaryAdapter(this,dictionaryDatabase.getWordList(),0);
    mListView.setAdapter(dictionaryAdapter);
  }

  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    return new DictionaryLoader(this);
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    dictionaryAdapter.swapCursor(data);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    dictionaryAdapter.swapCursor(null);
  }
}
