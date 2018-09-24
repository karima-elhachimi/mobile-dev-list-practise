package be.ap.karima.listapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemActivity extends AppCompatActivity {

    public Button addButton;
    public EditText txtTitle;
    public EditText txtDescription;
    public DataManager myDataManager = DataManager.getInstance();
    private Intent intent;

    private MyItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        this.addButton = (Button) findViewById(R.id.button_main_add);
        this.txtTitle = (EditText) findViewById(R.id.text_main_title);
        this.txtDescription = (EditText) findViewById(R.id.text_main_description);
        //intent altijd in onCreate initialiseren, anders geeft het errors
        intent = getIntent();
        displayItem();
        addBtnClickHandler();
    }

    private void displayItem() {

        item = (MyItem) intent.getParcelableExtra(MyItem.ITEM);
        txtTitle.setText(item.getmTitle());
        txtDescription.setText(item.getMyDescription());
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
        String title = this.txtTitle.getText().toString();
        String descr = this.txtDescription.getText().toString();
        MyItem item = new MyItem(title, descr);
        myDataManager.addItem(item);
        showMessage(view, "Item was added!");
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
