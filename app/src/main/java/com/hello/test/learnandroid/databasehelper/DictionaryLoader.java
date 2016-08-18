package com.hello.test.learnandroid.databasehelper;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

public class DictionaryLoader extends CursorLoader{
  Context context;
  public DictionaryLoader(Context context){
    super(context);
    this.context = context;
  }

  @Override
  public Cursor loadInBackground() {
    DictionaryDatabase dictionaryDatabase = new DictionaryDatabase(context);
    return dictionaryDatabase.getWordList();
  }
}
