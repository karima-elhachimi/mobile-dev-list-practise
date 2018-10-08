package be.ap.karima.listapp;

import android.provider.BaseColumns;

public final class ListDatabaseContract {


    //item tabel, elke tabel krijgt zijn eigen entry static class
    public static final class ItemsEntry implements BaseColumns {


        //tabel naam
        public static final String ITEMS = "Items";

        //titel kolom
        public static final String ITEM_TITLE = "Title";

        //description kolom
        public static final String ITEM_DESCRIPTION = "Description";

        //query samenstellen om tabel te maken
        public static final String SQL_CREATE_ITEMS = "CREATE TABLE " + ITEMS + " ( "
                + _ID + " INTEGER PRIMARY KEY, "
                + ITEM_TITLE + " NOT NULL, "
                + ITEM_DESCRIPTION+" )";

    }
}
