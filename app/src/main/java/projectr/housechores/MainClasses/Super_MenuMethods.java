package projectr.housechores.MainClasses;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import projectr.housechores.AppClasses.ChoresApp;
import projectr.housechores.R;

public class Super_MenuMethods extends AppCompatActivity{

    public ChoresApp choreapp;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        choreapp = (ChoresApp) getApplication();
        choreapp.dbmanager.open();
        choreapp.dbmanager.setTotalChoresEntered(this);
    }

    protected void onDestroy(){
        super.onDestroy();
        choreapp.dbmanager.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem list = menu.findItem(R.id.action_list);
        MenuItem home = menu.findItem(R.id.action_home);
        MenuItem reset = menu.findItem(R.id.action_reset);
        MenuItem searchDay = menu.findItem(R.id.action_search);
        MenuItem searchName = menu.findItem(R.id.action_nameSearch);

        //If database is empty

        if(choreapp.dbmanager.getAll().isEmpty()){
            list.setEnabled(false);
            reset.setEnabled(false);
            searchDay.setEnabled(false);
            searchName.setEnabled(false);

        }else{
            list.setEnabled(true);
            reset.setEnabled(true);
            searchDay.setEnabled(true);
            searchName.setEnabled(true);

        }

        if (this instanceof Home){
            reset.setVisible(true);
        }else {
            reset.setVisible(false);
        }

        return true;
    }

@Override

    public void onBackPressed(){
    startActivity(new Intent(this, Home.class));//https://stackoverflow.com/questions/29841747/app-crashes-when-i-hit-the-back-button
    }


//menumethods
    public void list(MenuItem item){
        startActivity(new Intent(this, TaskList.class));
    }

    public void reset(MenuItem item){

    }
    public void searchday(MenuItem item){
        startActivity(new Intent(this, SearchDay.class));
    }
    public void searchname(MenuItem item){
        startActivity(new Intent(this, SearchName.class));
    }
    public void homescreen(MenuItem item){
        startActivity(new Intent(this, Home.class));

    }



//
    public void showWarning() {
        Toast.makeText(this,"Please enter valid day of the week",Toast.LENGTH_LONG).show();
    }



}
