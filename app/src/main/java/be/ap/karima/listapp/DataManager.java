package be.ap.karima.listapp;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class DataManager {

    private static DataManager singleInstance = null;

    MyItem[] values = new MyItem[] { new MyItem("test", "test2"), new MyItem("WindowsMobile",
            "Blackberry"), new MyItem("WebOS", "Ubuntu"), new MyItem( "Windows7", "Max OS X"), new MyItem(
            "Linux", "OS/2"), new MyItem("Ubuntu", "Windows7")};

    ArrayList<MyItem> list = new ArrayList<>();

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

        this.list.set(this.list.indexOf(item), item);
    }
}
