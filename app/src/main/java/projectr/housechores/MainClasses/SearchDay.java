package projectr.housechores.MainClasses;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import projectr.housechores.DatabaseClasses.DBManager;
import projectr.housechores.R;


public class SearchDay extends Super_MenuMethods {

    private EditText searchitem;
    public TextView showResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchitem = (EditText) findViewById(R.id.searchDay);
        showResults = (TextView) findViewById(R.id.results);

    }

    public void searchButtonPressed(View view){
        String search = searchitem.getText().toString();
        choreapp.dbmanager.searchByDay(search, showResults);

    }

}
