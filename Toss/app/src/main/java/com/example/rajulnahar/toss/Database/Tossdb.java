package com.example.rajulnahar.toss.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rajul Nahar on 24-01-2017.
 */

public class Tossdb extends SQLiteOpenHelper{

    private static String Database_name = "Toss.db";
    private  static SQLiteDatabase sqLiteDatabase;
    private static Tossdb tossdbInstance = null;

    private Tossdb(Context context) {
        super(context, Database_name, null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }
    public static Tossdb getInstance(Context context){
        if (tossdbInstance == null){
            tossdbInstance = new Tossdb(context);
        }
       // tossdbInstance.deletealltables();
        return tossdbInstance;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableDebateClaim.createTableDebateClaim);
        db.execSQL(TableDebateFact.createTableDebateFact);
        db.execSQL(TableUserDetails.createTableUserDetails);
        db.execSQL(TableUserFeaturedDebate.createTableUserFeaturedDebate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TableDebateClaim.deleteTableDebateClaim);
        db.execSQL(TableDebateFact.deleteTableDebateFact);
        db.execSQL(TableUserDetails.deleteTableUserDetails);
        db.execSQL(TableUserFeaturedDebate.deleteTableUserFeaturedDebate);
        onCreate(db);

    }
    public void deletealltables(){
        sqLiteDatabase.execSQL(TableDebateFact.deleteTableDebateFact);
        sqLiteDatabase.execSQL(TableDebateClaim.deleteTableDebateClaim);
        sqLiteDatabase.execSQL(TableUserDetails.deleteTableUserDetails);
        sqLiteDatabase.execSQL(TableUserFeaturedDebate.deleteTableUserFeaturedDebate);
        onCreate(sqLiteDatabase);
    }
}
