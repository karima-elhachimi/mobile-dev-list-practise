package be.ap.karima.listapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemActivity extends AppCompatActivity {

    public Button addButton;
    public EditText txtTitle;
    public EditText txtDescription;
    public FloatingActionButton fabList;
    public DataManager myDataManager = DataManager.getInstance();
    private Intent intent;

    private MyItem newItem;
    private MyItem existingItem;
    private Boolean isNewItem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);



        this.addButton = (Button) findViewById(R.id.button_main_add);
        this.txtTitle = (EditText) findViewById(R.id.text_main_title);
        this.txtDescription = (EditText) findViewById(R.id.text_main_description);
        this.fabList = (FloatingActionButton) findViewById(R.id.fab_list);
        //intent altijd in onCreate initialiseren, anders geeft het errors
        intent = getIntent();
        createNewItemOrDisplayExisting();
        addBtnClickHandler();
        fabListClickHandler();
    }

    //om settings mogelijk te maken:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
//            case R.id.action_add:
//                addSomething();
//                return true;
            case R.id.action_settings:
                startEmailing();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startEmailing() {
        String subject = "My Item: "+this.txtTitle.getText().toString();
        String body_text = "This is what I wrote about: "+this.txtTitle.getText().toString()+". \n"+this.txtDescription.getText().toString()+". Cool right?";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body_text);
        startActivity(intent);

    }

    private void startSettings() {
        //doet nog niets
        //todo_done: voeg extra opties toe om implicit intents te oefenen, kijk bij startEmailing()
    }
    //einde settings mogelijk maken


    @Override
    protected void onPause() {
        super.onPause();

        if(this.isNewItem) {
            this.newItem = new MyItem(this.txtTitle.getText().toString(), this.txtDescription.getText().toString());
            myDataManager.addItem(this.newItem);
            //showMessage(view, "Item was added!");
        } else {
            this.existingItem = new MyItem(this.txtTitle.getText().toString(), this.txtDescription.getText().toString());
            myDataManager.updateItem(this.existingItem);
            //showMessage(view, "Item was updated!");
        }

        //todo_done: maak het mogelijk om aanpassingen of een nieuwe item op te slaan voordat

    }

    private void fabListClickHandler() {
        fabList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ItemActivity.this, ListActivity.class));
            }
        });
    }

    private void displayItem(MyItem item) {

        txtTitle.setText(item.getmTitle());
        txtDescription.setText(item.getMyDescription());
    }

    private void createNewItemOrDisplayExisting() {
        this.isNewItem = ((intent.getParcelableExtra(MyItem.ITEM)) == null);
        if(!isNewItem) {
            this.existingItem = (MyItem) intent.getParcelableExtra(MyItem.ITEM);
            displayItem(this.existingItem);
        } else {
            this.newItem = new MyItem(this.txtTitle.getText().toString(), this.txtDescription.getText().toString());
            displayItem(this.newItem);
        }
    }

    private void addBtnClickHandler() {
        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem(view);
                //goToListActivity();
            }
        });
    }

    public void saveItem(View view) {
        if(this.isNewItem) {
            this.newItem = new MyItem(this.txtTitle.getText().toString(), this.txtDescription.getText().toString());
            myDataManager.addItem(this.newItem);
            showMessage(view, "Item was added!");
        } else {
            this.existingItem = new MyItem(this.txtTitle.getText().toString(), this.txtDescription.getText().toString());
            myDataManager.updateItem(this.existingItem);
            showMessage(view, "Item was updated!");
        }

        clearText();
    }

    public void clearText() {
        this.txtTitle.setText("");
        this.txtDescription.setText("");
    }

    public void showMessage(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void goToListActivity(){
        Intent intent = new Intent(this, ListActivity.class);
        this.startActivity(intent);
    }




}
