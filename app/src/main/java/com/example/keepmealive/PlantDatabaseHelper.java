package com.example.keepmealive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class PlantDatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME="PlantDatabase.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME = "owned_plants";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME ="plant_name";
    private static final String COLUMN_TYPE = "plant_type";
    private static final String COLUMN_ADD_DATE = "add_date";
    private static final String COLUMN_WATER_PERIOD = "water_period";

    public PlantDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase dbase)
    {
        String query =
                "CREATE TABLE "+ TABLE_NAME + " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_ADD_DATE + " INTEGER, " +
                        COLUMN_WATER_PERIOD + " INTEGER); "
                ;
        dbase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbase, int i, int i1)
    {
        dbase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(dbase);
    }

    void addPlant(String name, String type, int water)
    {
        SQLiteDatabase db  = this.getWritableDatabase();
        Date d = new Date(System.currentTimeMillis());
        long milis = d.getTime();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_ADD_DATE,milis);
        cv.put(COLUMN_WATER_PERIOD, water);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1)
        {
           Toast.makeText(context, "Unable to set", Toast.LENGTH_SHORT).show();
        }
        else
        {
           Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor crs =null;
        if( db != null)
        {
           crs = db.rawQuery(query,null);
        }

        return crs;
    }

    void updateTime(String row_id)
    {
        Date d = new Date(System.currentTimeMillis());
        long milis = d.getTime();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ADD_DATE, milis);

        long res = 1;
        Log.i("name",row_id);
        db.update(TABLE_NAME, cv, "plant_name=?", new String[]{row_id});
        if(res == -1)
        {
            Toast.makeText(context, "Couldn't update",Toast.LENGTH_SHORT).show();
        }


    }
}
