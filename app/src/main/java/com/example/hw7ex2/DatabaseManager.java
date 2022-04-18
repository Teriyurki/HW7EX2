package com.example.hw7ex2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friendsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIENDS = "friends";
    private static final String ID = "id";
    private static final String FNAME = "fname";
    private static final String LNAME = "lname";
    private static final String EMAIL = "email";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //build sql create statement
        String sqlCreate = "create table " + TABLE_FRIENDS + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + FNAME;
        sqlCreate += " text, " + LNAME;
        sqlCreate += " text, " + EMAIL + ")";


        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_FRIENDS);
        // Re-create tables
        onCreate(db);
    }

    public void insert(Friends friends) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FRIENDS;
        sqlInsert += " values( null, '" + friends.getFname();
        sqlInsert += "', '" + friends.getLname();
        sqlInsert += "', '" + friends.getEmail() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Friends> selectAll() {
        String sqlQuery = "select * from " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Friends> friendArray = new ArrayList<Friends>();
        while (cursor.moveToNext()) {
            Friends currentFriend
                    = new Friends(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
            friendArray.add(currentFriend);
        }
        db.close();
        return friendArray;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIENDS;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int friendID, String fname, String lname, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_FRIENDS;
        sqlUpdate += " set " + FNAME + " = '" + fname + "', ";
        sqlUpdate +=   LNAME + " = '" + lname + "', ";
        sqlUpdate +=   EMAIL + " = '" + email + "'";
        sqlUpdate += " where " + ID + " = " + friendID;

        db.execSQL(sqlUpdate);
        db.close();
    }
}
    /*public Friends selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_FRIENDS;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        Friends currentFriend = null;
        if( cursor.moveToFirst( ) )
            currentFriend = new Friends(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return currentFriend;*/


