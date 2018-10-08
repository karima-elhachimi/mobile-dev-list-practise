package be.ap.karima.listapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static be.ap.karima.listapp.ListDatabaseContract.*;

public class DataManager {

    private static DataManager singleInstance = null;

    MyItem[] values = new MyItem[] { new MyItem("test", "test2"), new MyItem("WindowsMobile",
            "Blackberry"), new MyItem("WebOS", "Ubuntu"), new MyItem( "Windows7", "Max OS X"), new MyItem(
            "Linux", "OS/2"), new MyItem("Ubuntu", "Windows7")};

    public static ArrayList<MyItem> list = new ArrayList<>();

    public static DataManager getInstance(){
        if(singleInstance == null) {
            singleInstance = new DataManager();
            singleInstance.populateList();
        }

        return singleInstance;
    }

    private  void populateList() {

        for(MyItem item : values) {
            this.list.add(item);
        }

    }


    public ArrayList<MyItem> getList(){
        return this.list;
    }

    public void addItem(MyItem item) {
        this.list.add(item);
    }

    public void updateItem(MyItem item) {

        int index = this.list.indexOf(item);
        this.list.set(index, item);
    }

    public static void loadFromDatabase(ListOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] myCols = new String[] {
                ItemsEntry.ITEM_TITLE,
                ItemsEntry.ITEM_DESCRIPTION
        };

        final Cursor myCursor = db.query(ItemsEntry.ITEMS, myCols, null, null, null, null, null );

        loadDataFromCursor(myCursor);
    }

    private static void loadDataFromCursor(Cursor cursor) {
        int titlePos = cursor.getColumnIndex(ItemsEntry.ITEM_TITLE);
        int descriptionPos = cursor.getColumnIndex(ItemsEntry.ITEM_DESCRIPTION);

        while(cursor.moveToNext()) {
            MyItem item = new MyItem(
                    cursor.getString(titlePos),
                    cursor.getString(descriptionPos)
            );

            list.add(item);

        }

        cursor.close();
    }
}
