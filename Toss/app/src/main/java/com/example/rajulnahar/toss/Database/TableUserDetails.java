package com.example.rajulnahar.toss.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rajulnahar.toss.Helper;
import com.example.rajulnahar.toss.Objects.DebateFact;
import com.example.rajulnahar.toss.Objects.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajul Nahar on 07-02-2017.
 */

public class TableUserDetails {
    public SQLiteDatabase sqLiteDatabase;

    public static String Table_UserDetails = "TableUserDetails";
    public static String Col_UserDetails_userId = "userId";
    public static String Col_UserDetails_userGuid = "userGuid";
    public static String Col_UserDetails_emailId = "emailId";
    public static String Col_UserDetails_loginType = "loginType";
    public static String Col_UserDetails_isUserActive = "isUserActive";

    public static String createTableUserDetails = "create table "+Table_UserDetails+" ("+
            Col_UserDetails_userId+" integer primary key autoincrement, "+
            Col_UserDetails_userGuid+" text, "+
            Col_UserDetails_emailId+ " text, "+
            Col_UserDetails_loginType+" text, "+
            Col_UserDetails_isUserActive+ " text "+
            ")";

    public static String deleteTableUserDetails = "drop table if exists "+ Table_UserDetails;

    public TableUserDetails(Context context){
        sqLiteDatabase = Tossdb.getInstance(context).getWritableDatabase();
    }

    public long addUserDetails(UserDetails userDetails){
        return sqLiteDatabase.insertOrThrow(Table_UserDetails,null,getContentValuesFromUserDetails(userDetails));
    }

    public void addAllUserDetails(List<UserDetails> userDetailsList){
        for(int i = 0; i < userDetailsList.size(); i++){
            long id = addUserDetails(userDetailsList.get(i));
            Helper.log("Database User Detail Insert Id: " + String.valueOf(id));
        }
    }

    public ContentValues getContentValuesFromUserDetails(UserDetails userDetails){
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Col_DebateFact_factId,debateFact.getFactId());
        contentValues.put(Col_UserDetails_userGuid,userDetails.getUserGuid());
        contentValues.put(Col_UserDetails_emailId,userDetails.getEmailId());
        contentValues.put(Col_UserDetails_loginType,userDetails.getLoginType());
        contentValues.put(Col_UserDetails_isUserActive,userDetails.isUserActive());
        return contentValues;
    }

    public UserDetails setUserDetailsFromCursor(Cursor cursor){
        UserDetails userDetails = new UserDetails();
        String x = cursor.getString(cursor.getColumnIndex(Col_UserDetails_isUserActive));
        if (x.equals("1")){
            userDetails.setUserActive(true);
        }else {
            userDetails.setUserActive(false);
        }
        userDetails.setEmailId(cursor.getString(cursor.getColumnIndex(Col_UserDetails_emailId)));
        userDetails.setLoginType(cursor.getString(cursor.getColumnIndex(Col_UserDetails_loginType)));
        userDetails.setUserGuid(cursor.getString(cursor.getColumnIndex(Col_UserDetails_userGuid)));
        userDetails.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Col_UserDetails_userId))));
        return userDetails;
    }

    public List<UserDetails> getAllUserDetails(){
        List<UserDetails> userDetailsList = new ArrayList<>();
        String select = "select * from "+Table_UserDetails;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                userDetailsList.add(setUserDetailsFromCursor(cursor));
            }while (cursor.moveToNext());
        }
        return userDetailsList;
    }

    public void deleteUserDetails(){
        sqLiteDatabase.delete(Table_UserDetails,null,null);
    }

}
