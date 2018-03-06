package projectr.housechores.MainClasses;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import projectr.housechores.R;

/**
 * Created by ejcou on 31/07/2017.
 */

public class SearchName extends Super_MenuMethods {

    private EditText searchitem;
    public TextView showResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        searchitem = (EditText) findViewById(R.id.searchname);
        showResults = (TextView) findViewById(R.id.printResults);

    }

    public void searchNameButtonPressed(View view){
        String search = searchitem.getText().toString();
        choreapp.dbmanager.searchByName(search, showResults);

    }

}