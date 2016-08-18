package com.hello.test.learnandroid.learndata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.hello.test.learnandroid.R;
import com.hello.test.learnandroid.databasehelper.DictionaryDatabase;

public class LearnUsingSQLIteActivity extends AppCompatActivity {
  EditText wordEditText;
  EditText definitionEditText;
  DictionaryDatabase dictionaryDatabase;
  ListView mListView;

  private void saveRecord(){
    dictionaryDatabase.saveRecord(wordEditText.getText().toString(),definitionEditText.getText().toString());
    wordEditText.setText("");
    definitionEditText.setText("");
    updateWordList();
  }

  private void updateWordList(){
    SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
      getApplicationContext(),
      android.R.layout.simple_list_item_1,
      dictionaryDatabase.getWordList(),
      new String[]{"word"},
      new int[]{android.R.id.text1},
      0
    );
    mListView.setAdapter(simpleCursorAdapter);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_using_sqlite);

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

    mListView =(ListView)findViewById(R.id.listView);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),dictionaryDatabase.getDefinition(id),Toast.LENGTH_SHORT).show();
      }
    });
    mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Record deleted = " + dictionaryDatabase.deleteRecord(id),Toast.LENGTH_SHORT).show();
        updateWordList();
        return true;
      }
    });

    updateWordList();
  }
}
