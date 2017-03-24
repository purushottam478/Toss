package com.example.rajulnahar.toss.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rajulnahar.toss.Helper;
import com.example.rajulnahar.toss.Objects.DebateClaim;
import com.example.rajulnahar.toss.Objects.DebateFact;

import java.util.ArrayList;
import java.util.List;

import static com.example.rajulnahar.toss.Database.TableDebateFact.Col_DebateFact_claimGuid;
import static com.example.rajulnahar.toss.Database.TableDebateFact.Table_DebateFact;

/**
 * Created by Rajul Nahar on 04-02-2017.
 */

public class TableDebateClaim {
    public SQLiteDatabase sqLiteDatabase;
    
    public static String Table_DebateClaim = "debateClaim";
    public static String Col_DebateClaims_Id = "debateclaimid";
    public static String Col_DebateClaims_DebateClaimsGuid = "debateclaimsguid";
    public static String Col_DebateClaims_DebateFactsID = "debatefactsid";
    public static String Col_DebateClaims_HeadsText = "headtext";
    public static String Col_DebateClaims_HeadsViewCount = "headsviewcount";
    public static String Col_DebateClaims_headsAuthorId = "headsAuthorID";
    public static String Col_DebateClaims_TailsText = "tailstext";
    public static String Col_DebateClaims_TalsViewCount = "tailsviewcount";
    public static String Col_DebateClaims_TailsAuthorID = "tailsauthorid";

    public static  String createTableDebateClaim = "create table "+ Table_DebateClaim +"(" +
            Col_DebateClaims_Id+" integer primary key autoincrement," +
            Col_DebateClaims_DebateClaimsGuid+" text, " +
            Col_DebateClaims_DebateFactsID+" text, " +
            Col_DebateClaims_HeadsText+" text, " +
            Col_DebateClaims_HeadsViewCount+" text, " +
            Col_DebateClaims_headsAuthorId+" text, " +
            Col_DebateClaims_TailsText+" text, " +
            Col_DebateClaims_TalsViewCount+" text, " +
            Col_DebateClaims_TailsAuthorID+" text" +
            ")";


    public static String deleteTableDebateClaim = "drop table if exists "+ Table_DebateClaim;

    public TableDebateClaim(Context context){
        sqLiteDatabase = Tossdb.getInstance(context).getWritableDatabase();
    }

    public long addDebateClaim(DebateClaim debateClaim){
        return sqLiteDatabase.insertOrThrow(Table_DebateClaim,null,getContentValuesFromDebateClaim(debateClaim));
    }

    public void addAllDebateClaim(List<DebateClaim> debateClaimList){
        for(int i = 0; i < debateClaimList.size(); i++){
            long id = addDebateClaim(debateClaimList.get(i));
            Helper.log("Database DebateClaim insert id: " + String.valueOf(id));
        }
    }

    public ContentValues getContentValuesFromDebateClaim(DebateClaim debateClaim){
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Col_DebateFact_factId,debateFact.getFactId());
        contentValues.put(Col_DebateClaims_DebateClaimsGuid,debateClaim.getDebateClaimsGuid());
        contentValues.put(Col_DebateClaims_DebateFactsID,debateClaim.getDebateFactsId());
        contentValues.put(Col_DebateClaims_HeadsText,debateClaim.getHeadsText());
        contentValues.put(Col_DebateClaims_HeadsViewCount,debateClaim.getHeadsViewCount());
        contentValues.put(Col_DebateClaims_headsAuthorId,debateClaim.getHeadsAuthorId());
        contentValues.put(Col_DebateClaims_TailsText,debateClaim.getTailsText());
        contentValues.put(Col_DebateClaims_TalsViewCount,debateClaim.getTailsViewCount());
        contentValues.put(Col_DebateClaims_TailsAuthorID,debateClaim.getTailsAuthorId());
        return contentValues;
    }

    public DebateClaim setDebateClaimFromCursor(Cursor cursor){
        DebateClaim debateClaim = new DebateClaim();
        debateClaim.setDebateClaimsId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_Id))));
        debateClaim.setDebateClaimsGuid(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_DebateClaimsGuid)));
        debateClaim.setDebateFactsId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_DebateFactsID))));
        debateClaim.setHeadsAuthorId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_headsAuthorId))));
        debateClaim.setHeadsViewCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_HeadsViewCount))));
        debateClaim.setHeadsText(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_HeadsText)));
        debateClaim.setTailsAuthorId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_TailsAuthorID))));
        debateClaim.setTailsText(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_TailsText)));
        debateClaim.setTailsViewCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_DebateClaims_TalsViewCount))));
        return debateClaim;
    }

    public List<DebateClaim> getAllDebateClaims(){
        List<DebateClaim> debateClaimList = new ArrayList<>();
        String select = "select * from "+Table_DebateClaim;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                debateClaimList.add(setDebateClaimFromCursor(cursor));
            }while (cursor.moveToNext());
        }
        return debateClaimList;
    }

    public void deleteDebateClaims(){
        sqLiteDatabase.delete(Table_DebateClaim,null,null);
    }




}
