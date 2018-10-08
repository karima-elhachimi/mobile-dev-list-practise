package be.ap.karima.listapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static be.ap.karima.listapp.ListDatabaseContract.*;

public class ListOpenHelper extends SQLiteOpenHelper {

    //constants voor de database en versie aanmaken
    public static final String DB_NAME = "List.db";
    public static final int DB_VERSION = 1;

    public ListOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null , DB_VERSION); //factory wordt gebruikt om specifiek te customizen, voorlopig nog niet nodig en mag dus null zijn
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ItemsEntry.SQL_CREATE_ITEMS); //dit herhalen voor elke tabel in het contract

        //dit beter in een aparte worker class uitwerken, maar voorlopig hier

        addEntries(db, "This is the first title", "And this is the first description");
        addEntries(db, "This is the second title", "This is the second description, woooo");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEntries(SQLiteDatabase db, String title, String description) {

        ContentValues values = new ContentValues();
        values.put(ItemsEntry.ITEM_TITLE, title);
        values.put(ItemsEntry.ITEM_DESCRIPTION, description);

        db.insert(ItemsEntry.ITEMS, null, values);

    }


}
