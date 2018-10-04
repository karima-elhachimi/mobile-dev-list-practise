package be.ap.karima.listapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    //singleton
    DataManager dm = DataManager.getInstance();

    @Test
    public void getList() {
        //Zien of er een lijst correct wordt doorgegeven met bestaande items.

        assertSame(dm.list, dm.getList());

    }

    @Test
    public void addItem() {
        //item dat toegevoegd wordt, wordt correct opgeslgen in de lijst
        String title = "This is a title";
        String description = "This is a description";

        MyItem item = new MyItem(title, description);

        dm.addItem(item);

        int indexOfAddedItem = dm.list.indexOf(item);

        assertEquals(title, dm.list.get(indexOfAddedItem).getmTitle());
        assertEquals(description, dm.list.get(indexOfAddedItem).getMyDescription());
    }

    @Test
    public void updateItem() {
        //testen of dat de item dat ik meegeef wordt geupdate


        String updatedDescription = "This is an updated description";

        MyItem firstItemOfList = dm.list.get(0);

        String originalDescription = firstItemOfList.getMyDescription();

        firstItemOfList.setMyDescription(updatedDescription);

        dm.updateItem(firstItemOfList);

        assertNotEquals(originalDescription, dm.list.get(0).getMyDescription());
        assertEquals(updatedDescription, dm.list.get(0).getMyDescription());


    }
}