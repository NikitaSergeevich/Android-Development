package com.innopolis.navrelin.bluetoothchat;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by Иванов on 11.11.2015.
 */

public final class ChatContract {

    private ChatContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ChatEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" +
                ChatContentProvider.AUTHORITY + "/chats");

        public static final String TABLE_NAME = "chats";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_COLOUR = "colour";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_TIME = "time";
    }

    public static void addChat(Context context,
                               String user, String text, int colour, int time) {
        final ContentResolver resolver = context.getContentResolver();

        final ContentValues values = new ContentValues();
        values.put(ChatEntry.COLUMN_NAME_USER, user);
        values.put(ChatEntry.COLUMN_NAME_COLOUR, colour);
        values.put(ChatEntry.COLUMN_NAME_TEXT, text);
        values.put(ChatEntry.COLUMN_NAME_TIME, time);
        resolver.insert(ChatEntry.CONTENT_URI, values);
    }

    public static void UpdateChat(Context context, String user, String text, int time) {
        final ContentResolver resolver = context.getContentResolver();
        final ContentValues values = new ContentValues();
        values.put(ChatEntry.COLUMN_NAME_TEXT, text);
        values.put(ChatEntry.COLUMN_NAME_TIME, time);
        resolver.update(ChatEntry.CONTENT_URI, values, "user=" + user, null);
    }
}