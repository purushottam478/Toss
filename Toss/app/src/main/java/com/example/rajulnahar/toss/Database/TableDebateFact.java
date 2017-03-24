package com.example.rajulnahar.toss.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rajulnahar.toss.Helper;
import com.example.rajulnahar.toss.Objects.DebateFact;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajul Nahar on 04-02-2017.
 */

public class TableDebateFact {

    public SQLiteDatabase sqLiteDatabase;

    public static  String Table_DebateFact = "DebateFact";
    public static  String Col_DebateFact_factId = "factId";
    public static  String Col_DebateFact_factGuid = "factGuid";
    public static  String Col_DebateFact_claimGuid = "claimGuid";
    public static  String Col_DebateFact_factHeading = "factHeading";
    public static  String Col_DebateFact_factBody = "factBody";
    public static  String Col_DebateFact_factViewCount = "factViewCount";
    public static  String Col_DebateFact_factImage = "factImage";
    public static String Col_DebateFact_displayMoodOfHouse = "displayMoodOfHouse";
    public static String Col_DebateFact_headVoteCount = "headVoteCount";
    public static String Col_DebateFact_tailVoteCount = "tailVoteCount";

    public static  String createTableDebateFact = "create table "+ Table_DebateFact +"(" +
            Col_DebateFact_factId+" integer primary key autoincrement," +
            Col_DebateFact_factGuid+" text, " +
            Col_DebateFact_claimGuid+" text, " +
            Col_DebateFact_factHeading+" text, " +
            Col_DebateFact_factBody+" text, " +
            Col_DebateFact_factViewCount+" text, " +
            Col_DebateFact_factImage+" blob, " +
            Col_DebateFact_displayMoodOfHouse+" text, " +
            Col_DebateFact_headVoteCount+" text, " +
            Col_DebateFact_tailVoteCount+" text" +
            ")";

    public static String deleteTableDebateFact = "drop table if exists "+ Table_DebateFact;

    public TableDebateFact(Context context){
        sqLiteDatabase = Tossdb.getInstance(context).getWritableDatabase();
    }

    public long addDebateFact(DebateFact debateFact){
        return sqLiteDatabase.insertOrThrow(Table_DebateFact,null,getContentValuesFromDebateFact(debateFact));
    }

    public void addAllDebateFact(List<DebateFact> debateFactList){
        for(int i = 0; i < debateFactList.size(); i++){
            long id = addDebateFact(debateFactList.get(i));
            Helper.log("Database DebateFact Insert Id: " + String.valueOf(id));
        }
    }

    public ContentValues getContentValuesFromDebateFact(DebateFact debateFact){
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Col_DebateFact_factId,debateFact.getFactId());
        contentValues.put(Col_DebateFact_factGuid,debateFact.getFactGuid());
        contentValues.put(Col_DebateFact_claimGuid,debateFact.getClaimGuid());
        contentValues.put(Col_DebateFact_factHeading,debateFact.getFactHeading());
        contentValues.put(Col_DebateFact_factBody,debateFact.getFactBody());
        contentValues.put(Col_DebateFact_factViewCount,debateFact.getFactViewCount());
        contentValues.put(Col_DebateFact_factImage, String.valueOf(debateFact.getFactImage()));
        contentValues.put(Col_DebateFact_displayMoodOfHouse,debateFact.isDisplayMoodOfHouse());
        contentValues.put(Col_DebateFact_headVoteCount,debateFact.getHeadVoteCount());
        contentValues.put(Col_DebateFact_tailVoteCount,debateFact.getTailsVoteCount());
        return contentValues;
    }

    public DebateFact setDebateFactFromCursor(Cursor cursor){
        DebateFact debateFact = new DebateFact();
        String x = cursor.getString(cursor.getColumnIndex(Col_DebateFact_displayMoodOfHouse));
        if (x.equals("1")){
            debateFact.setDisplayMoodOfHouse(true);
        }else {
            debateFact.setDisplayMoodOfHouse(false);
        }
        debateFact.setClaimGuid(cursor.getString(cursor.getColumnIndex(Col_DebateFact_claimGuid)));
        debateFact.setFactBody(cursor.getString(cursor.getColumnIndex(Col_DebateFact_factBody)));
        debateFact.setFactGuid(cursor.getString(cursor.getColumnIndex(Col_DebateFact_factGuid)));
        debateFact.setFactHeading(cursor.getString(cursor.getColumnIndex(Col_DebateFact_factHeading)));
        debateFact.setFactId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateFact_factId))));
        debateFact.setFactViewCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateFact_factViewCount))));
        debateFact.setFactImage(cursor.getBlob(cursor.getColumnIndex(Col_DebateFact_factImage)));
        debateFact.setHeadVoteCount(Integer.valueOf(cursor.getString(cursor.getColumnIndex(Col_DebateFact_headVoteCount))));
        debateFact.setTailsVoteCount(Integer.valueOf(cursor.getString(cursor.getColumnIndex(Col_DebateFact_tailVoteCount))));
        return debateFact;
    }

    public List<DebateFact> getAllDebateFacts(){
        List<DebateFact> debateFactList = new ArrayList<>();
        String select = "select * from "+Table_DebateFact;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                debateFactList.add(setDebateFactFromCursor(cursor));
            }while (cursor.moveToNext());
        }
        return debateFactList;
    }

    public void deleteDebateFact(){
        sqLiteDatabase.delete(Table_DebateFact,null,null);
    }


}

