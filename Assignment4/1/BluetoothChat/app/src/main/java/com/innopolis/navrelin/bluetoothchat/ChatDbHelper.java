package com.innopolis.navrelin.bluetoothchat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.innopolis.navrelin.bluetoothchat.ChatContract.ChatEntry;

public class ChatDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "bluetooth.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ChatContract.ChatEntry.TABLE_NAME + " (" +
                    ChatEntry._ID + " INTEGER PRIMARY KEY," +
                    ChatEntry.COLUMN_NAME_USER + " TEXT," +
                    ChatEntry.COLUMN_NAME_TEXT + " TEXT," +
                    ChatEntry.COLUMN_NAME_COLOUR + " INTEGER," +
                    ChatEntry.COLUMN_NAME_TIME + " INTEGER" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ChatEntry.TABLE_NAME;

    public ChatDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
