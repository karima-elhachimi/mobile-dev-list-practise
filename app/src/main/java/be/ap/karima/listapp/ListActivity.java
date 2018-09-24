package be.ap.karima.listapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ListActivity extends AppCompatActivity {

    private MyItem item;
    private DataManager myDataManager;
    private Spinner mListSpinner;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        myDataManager = DataManager.getInstance();
        mListSpinner = (Spinner) findViewById(R.id.spinner_list);
        mListView = (ListView) findViewById(R.id.listview_list_all);
        displayListItems();
        itemOnClickHandler();
    }


    private void displayListItems() {
        ArrayAdapter<MyItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myDataManager.getList() );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mListSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myDataManager.getList());
        mListView.setAdapter(adapter);
    }

    private void itemOnClickHandler() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                MyItem item = (MyItem) mListView.getItemAtPosition(i);
                goToIteMActivity(view, item );
            }
        });
    }

    private void goToIteMActivity(View view, MyItem item) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra(MyItem.ITEM, item);
    }


}
