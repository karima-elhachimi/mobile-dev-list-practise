package be.ap.karima.listapp;

public class MyItem {
    private String mTitle;
    private String myDescription;

    public MyItem(String title, String description) {
        this.mTitle = title;
        this.myDescription = description;
    }

    @Override
    public String toString() {
        return this.mTitle;
    }
}
