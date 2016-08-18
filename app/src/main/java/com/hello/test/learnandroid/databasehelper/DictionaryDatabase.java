package com.hello.test.learnandroid.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DictionaryDatabase extends SQLiteOpenHelper{
  private static final String DATABASE_NAME="dictionary.db";
  private static final String TABLE_DICTIONARY="dictionary";

  private static final String FIELD_WORD = "word";
  private static final String FIELD_DESCRIPTION="definition";
  private static final int DATABASE_VERSION = 1;

  public DictionaryDatabase(Context context){
    super(context,DATABASE_NAME,null,DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(String.format("CREATE TABLE %s(_id integer PRIMARY KEY, %s TEXT, %s TEXT);",TABLE_DICTIONARY,FIELD_WORD,FIELD_DESCRIPTION));
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Handle database upgrade as needed
  }

  //handle write databse
  public void saveRecord(String word,String definition){
    long id = findIdByWord(word);
    if(id > 0){
      updateRecord(id,word,definition);
    }else{
      addRecord(word,definition);
    }
  }

  public long addRecord(String word,String definition){
    SQLiteDatabase db = getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(FIELD_WORD,word);
    values.put(FIELD_DESCRIPTION,definition);
    return db.insert(TABLE_DICTIONARY,null,values);
  }

  public int updateRecord(Long id,String word,String definition){
    SQLiteDatabase db = getReadableDatabase();

    ContentValues values = new ContentValues();
    values.put("_id",id);
    values.put(FIELD_WORD,word);
    values.put(FIELD_DESCRIPTION,definition);
    return db.update(TABLE_DICTIONARY,values,"_id = ? ",new String[]{String.valueOf(id)});
  }

  public long deleteRecord(long id){
    SQLiteDatabase db = getWritableDatabase();
    return db.delete(TABLE_DICTIONARY,"_id = ? ",new String[]{String.valueOf(id)});
  }

  //handle read database
  public long findIdByWord(String word){
    long returnVal = -1;
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery(
      String.format("SELECT _id FROM %s WHERE %s = ? ",TABLE_DICTIONARY,FIELD_WORD),
      new String[]{word}
    );
    Log.i("findWordId","getCount()=" + cursor.getCount());
    if(cursor.getCount() == 1){
      cursor.moveToFirst();
      returnVal = cursor.getInt(0);
    }
    return returnVal;
  }

  public String getDefinition(long id){
    String returnVal = "";
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery(
      String.format("SELECT %s FROM %s WHERE _id = ?",FIELD_DESCRIPTION,TABLE_DICTIONARY),
      new String[]{String.valueOf(id)}
    );
    if(cursor.getCount() == 1){
      cursor.moveToFirst();
      returnVal = cursor.getString(0);
    }
    return returnVal;
  }

  public Cursor getWordList(){
    SQLiteDatabase db = getReadableDatabase();
    String query = String.format("SELECT _id, %s FROM %s ORDER BY %s ASC",FIELD_WORD,TABLE_DICTIONARY,FIELD_WORD);
    return db.rawQuery(query, null);
  }
}
