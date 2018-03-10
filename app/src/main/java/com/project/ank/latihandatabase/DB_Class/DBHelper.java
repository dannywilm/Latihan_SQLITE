package com.project.ank.latihandatabase.DB_Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by wilim on 12/16/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "MOVIE_STAR";
    private final static int DB_VERSION = 1;
    private final String TABLE_NAME = "ARTIST";
    private final String ARTIST_ID = "id";
    private final String ARTIST_NAME = "name";
    private final String ARTIST_GENDER = "sex";
    private final String ARTIST_DOB = "dob"; // Date Of Birth
    private SQLiteDatabase db;

    private final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            ARTIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ARTIST_NAME + " TEXT NOT NULL, " +
            ARTIST_GENDER + " TEXT NOT NULL, " +
            ARTIST_DOB + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(CREATE_QUERY);
        }
        catch (SQLiteException e)
        {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public long insertData(String name, String gender, String dob)
    {
        try
        {
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ARTIST_NAME, name);
            contentValues.put(ARTIST_GENDER, gender);
            contentValues.put(ARTIST_DOB, dob);

            return db.insert(TABLE_NAME, null, contentValues);
        }
        catch(SQLiteException e)
        {
            return -1;
        }
    }

    public Cursor getAllData()
    {
        db = this.getReadableDatabase();

        return db.query(TABLE_NAME,
                new String[] { ARTIST_ID, ARTIST_NAME, ARTIST_GENDER, ARTIST_DOB},
                null, null, null, null, null);
    }

    public Cursor searchName(String name)
    {
        db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME,
                new String[] {
                ARTIST_ID, ARTIST_NAME, ARTIST_GENDER, ARTIST_DOB},
                ARTIST_NAME + " LIKE '%" + name + "%'", null, null, null, null);

        if (c != null)
            c.moveToFirst();

        return c;
    }

    public Cursor searchKey (long key)
    {
        db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME,
                new String[]{ARTIST_ID, ARTIST_NAME, ARTIST_GENDER, ARTIST_DOB},
                ARTIST_ID + " = " + key, null, null, null, null);

        if (c != null)
            c.moveToFirst();

        return c;
    }

    public boolean deleteData(long key)
    {
        db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ARTIST_ID + " = " + key, null) > 0;
    }

    public boolean updateData(long key, String name, String sex, String dob)
    {
        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ARTIST_ID, key);
        cv.put(ARTIST_NAME, name);
        cv.put(ARTIST_GENDER, sex);
        cv.put(ARTIST_DOB, dob);

        return db.update(TABLE_NAME, cv, ARTIST_ID + " = " + key, null) > 0;
    }

}