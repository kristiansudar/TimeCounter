package de.sudario.timecounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sudario10 on 23.04.2016.1
 */
public class WorkdayDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WORKTIME.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
    "CREATE TABLE " + WorkDay.NewWorkDay.TABLE_NAME+"("+ WorkDay.NewWorkDay.START_DATE+" TEXT, "+
            WorkDay.NewWorkDay.END_DATE + " TEXT);";

    public WorkdayDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created / opened...ks");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Database created...ks");
    }

    public void addworkingTime(String startdate, String enddate, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(WorkDay.NewWorkDay.START_DATE, startdate);
        contentValues.put(WorkDay.NewWorkDay.END_DATE, enddate);
        db.insert(WorkDay.NewWorkDay.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted...ks");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
