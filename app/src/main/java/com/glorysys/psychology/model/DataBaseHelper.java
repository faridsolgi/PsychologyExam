package com.glorysys.psychology.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME="QUIZ";
    private static int DATABASE_VERSION=3;
    private static String TABLE_POINT="TABLE_POINT";
    private static final String TAG = "DataBaseHelper";
    private static String COLUMN_ID="ID";
    private static String COLUMN_POINT="POINT";
    private static String COLUMN_TITLE="TITLE";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuiz="create table "+TABLE_POINT+" ("+COLUMN_ID+" TEXT, "+COLUMN_POINT+" INTEGER)";

        db.execSQL(createTableQuiz);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_POINT);
        onCreate(db);

    }

    public void WriteOnDataBase(String id,int point){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        if(Exists(id)){
            db.rawQuery("delete from "+ TABLE_POINT,null);
            contentValues.put(COLUMN_ID,id);
            contentValues.put(COLUMN_POINT,point);

            long result =db.update(TABLE_POINT,contentValues,COLUMN_ID+"=?",new String[]{id});
            if (result== -1){
                Log.i(TAG, "WriteOnDataBase: "+"ERRRRRRor");
            }else{
                Log.i(TAG, "WriteOnDataBase: "+"ok");
            }
        }else{
            contentValues.put(COLUMN_ID,id);
            contentValues.put(COLUMN_POINT,point);

            long result =db.insert(TABLE_POINT,null,contentValues);
            if (result== -1){
                Log.i(TAG, "WriteOnDataBase: "+"ERRRRRRor");
            }else{
                Log.i(TAG, "WriteOnDataBase: "+"ok");
            }

        }

    }


    public Cursor ReadOnDataBase(){
        String query="Select * from "+ TABLE_POINT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean Exists(String i) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select " + COLUMN_ID + " from " + TABLE_POINT + " where " + COLUMN_ID + "=?", new String[]{i});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
