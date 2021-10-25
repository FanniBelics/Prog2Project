package com.example.keepmealive;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class PlantDatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME="PlantDatabase.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME = "owned_plants";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME ="plant_name";
    private static final String COLUMN_TYPE = "plant_type";
    private static final String COLUMN_STATE = "plant_status";
    private static final String COLUMN_ADD_DATE = "add_date";
    private static final String COLUMN_WATER_PERIOD = "water_period";

    public PlantDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbase)
    {
        String query =
                "CREATE TABLE "+ TABLE_NAME + " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME + "TEXT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_STATE + " BOOLEAN, " +
                        COLUMN_ADD_DATE + " DATE, " +
                        COLUMN_WATER_PERIOD + " INTEGER); ";
        dbase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbase, int i, int i1)
    {
        dbase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(dbase);
    }

    void addPlant(String name, String type)
    {
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_TYPE, type);
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
}
