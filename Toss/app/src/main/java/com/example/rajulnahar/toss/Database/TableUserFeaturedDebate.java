package com.example.rajulnahar.toss.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rajulnahar.toss.Helper;
import com.example.rajulnahar.toss.Objects.UserDetails;
import com.example.rajulnahar.toss.Objects.UserFeaturedDebate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajul Nahar on 07-02-2017.
 */

public class TableUserFeaturedDebate {

    public SQLiteDatabase sqLiteDatabase;

    public static String Table_UserFeaturedDebate = "TableUserFeaturedDebate";
    public static String Col_UserFeaturedDebate_userId = "userId";
    public static String Col_UserFeaturedDebate_debateFactId = "debateFactId";
    public static String Col_UserFeaturedDebate_debateVote = "debateVote";
    public static String Col_UserFeaturedDebate_isHeadViewed = "isHeadViewed";
    public static String Col_UserFeaturedDebate_isTailViewed = "isTailViewed";
    public static String Col_UserFeaturedDebate_isDebateFavourite = "isDebateFavourite";

    public static String createTableUserFeaturedDebate = "create table "+Table_UserFeaturedDebate+" ("+
            Col_UserFeaturedDebate_userId+" integer, "+
            Col_UserFeaturedDebate_debateFactId+" integer, "+
            Col_UserFeaturedDebate_debateVote+ " text, "+
            Col_UserFeaturedDebate_isHeadViewed+" text, "+
            Col_UserFeaturedDebate_isTailViewed+ " text, "+
            Col_UserFeaturedDebate_isDebateFavourite+ " text "+
            ")";

    public static String deleteTableUserFeaturedDebate = "drop table if exists "+ Table_UserFeaturedDebate;

    public TableUserFeaturedDebate(Context context){
        sqLiteDatabase = Tossdb.getInstance(context).getWritableDatabase();
    }

    public long addUserFeaturedDebate(UserFeaturedDebate userFeaturedDebate){
        return sqLiteDatabase.insertOrThrow(Table_UserFeaturedDebate,null,getContentValuesFromUserFeaturedDebate(userFeaturedDebate));
    }

    public void addAllUserFeaturedDebate(List<UserFeaturedDebate> userFeaturedDebateList){
        for(int i = 0; i < userFeaturedDebateList.size(); i++){
            long id = addUserFeaturedDebate(userFeaturedDebateList.get(i));
            Helper.log("Database User Featured Debate Insert Id: " + String.valueOf(id));
        }
    }

    public ContentValues getContentValuesFromUserFeaturedDebate(UserFeaturedDebate userFeaturedDebate){
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Col_DebateFact_factId,debateFact.getFactId());
        contentValues.put(Col_UserFeaturedDebate_userId,userFeaturedDebate.getUserId());
        contentValues.put(Col_UserFeaturedDebate_debateFactId,userFeaturedDebate.getDebateFactsId());
        contentValues.put(Col_UserFeaturedDebate_debateVote,userFeaturedDebate.getDebateVote());
        contentValues.put(Col_UserFeaturedDebate_isHeadViewed,userFeaturedDebate.isHeadViewed());
        contentValues.put(Col_UserFeaturedDebate_isTailViewed,userFeaturedDebate.isTailViewed());
        contentValues.put(Col_UserFeaturedDebate_isDebateFavourite,userFeaturedDebate.isDebateFavourite());
        return contentValues;
    }

    public UserFeaturedDebate setUserFeaturedDebateFromCursor(Cursor cursor){
        UserFeaturedDebate userFeaturedDebate = new UserFeaturedDebate();
        String x = cursor.getString(cursor.getColumnIndex(Col_UserFeaturedDebate_isDebateFavourite));
        if (x.equals("1")){
            userFeaturedDebate.setDebateFavourite(true);
        }else {
            userFeaturedDebate.setDebateFavourite(false);
        }

        x = cursor.getString(cursor.getColumnIndex(Col_UserFeaturedDebate_isHeadViewed));
        if (x.equals("1")){
            userFeaturedDebate.setHeadViewed(true);
        }else {
            userFeaturedDebate.setHeadViewed(false);
        }

        x = cursor.getString(cursor.getColumnIndex(Col_UserFeaturedDebate_isTailViewed));
        if (x.equals("1")){
            userFeaturedDebate.setTailViewed(true);
        }else {
            userFeaturedDebate.setTailViewed(false);
        }
        userFeaturedDebate.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_UserFeaturedDebate_userId))));
        userFeaturedDebate.setDebateFactsId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_UserFeaturedDebate_debateFactId))));
        userFeaturedDebate.setDebateVote(cursor.getString(cursor.getColumnIndex(Col_UserFeaturedDebate_debateVote)));
        return userFeaturedDebate;
    }

    public List<UserFeaturedDebate> getAllUserFeaturedDebate(){
        List<UserFeaturedDebate> userFeaturedDebateList = new ArrayList<>();
        String select = "select * from "+Table_UserFeaturedDebate;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                userFeaturedDebateList.add(setUserFeaturedDebateFromCursor(cursor));
            }while (cursor.moveToNext());
        }
        return userFeaturedDebateList;
    }

    public void deleteUserFeaturedDebate(){
        sqLiteDatabase.delete(Table_UserFeaturedDebate,null,null);
    }

}
