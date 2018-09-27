package be.ap.karima.listapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class MyItem implements Parcelable {
    private String mTitle;
    private String myDescription;
    final static String ITEM = "be.ap.karima.listapp.MyItem";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyItem)) return false;
        MyItem myItem = (MyItem) o;
        return Objects.equals(getmTitle(), myItem.getmTitle());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getmTitle());
    }

    public MyItem(String title, String description) {
        this.mTitle = title;
        this.myDescription = description;
    }

    public MyItem(Parcel parcel) {
        mTitle = parcel.readString();
        myDescription = parcel.readString();
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getMyDescription() {
        return myDescription;
    }

    @Override
    public String toString() {
        return this.mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(mTitle);
        parcel.writeString(myDescription);
    }

    public final static Parcelable.Creator<MyItem> CREATOR =
            new Creator<MyItem>() {
                @Override
                public MyItem createFromParcel(Parcel parcel) {
                    return new MyItem(parcel);
                }

                @Override
                public MyItem[] newArray(int i) {
                    return new MyItem[i];
                }
            };
}
