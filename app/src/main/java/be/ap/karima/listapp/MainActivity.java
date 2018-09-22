package be.ap.karima.listapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button addButton;
    public EditText txtTitle;
    public EditText txtDescription;
    public DataManager myDataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.addButton = (Button) findViewById(R.id.button_main_add);
        this.txtTitle = (EditText) findViewById(R.id.text_main_title);
        this.txtTitle = (EditText) findViewById(R.id.text_main_description);


    }

    public void saveItem(View view) {
        String title = this.txtTitle.getText().toString();
        String descr = this.txtDescription.getText().toString();
        MyItem item = new MyItem(title, descr);
        myDataManager.addItem(item);
        Snackbar.make(view, "Item was added!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        clearText();
    }

    public void clearText() {
        this.txtTitle.setText("");
        this.txtDescription.setText("");
    }


}
