package projectr.housechores.MainClasses;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import projectr.housechores.ConstructorClass.Tasks;
import projectr.housechores.R;

public class Home extends Super_MenuMethods {

    private TextView choresAdded;

    private EditText enterTask;
    private EditText enterDay;
    private EditText enterName;

    private Button addButton;
    String totalentered;

    String taskenterd;
    String dayentered;
    String nameentered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        choresAdded=(TextView)findViewById(R.id.completedTasks);
        enterTask=(EditText)findViewById(R.id.task);
        enterDay = (EditText)findViewById(R.id.daytocomplete);
        enterName = (EditText)findViewById(R.id.name);

        addButton = (Button)findViewById(R.id.addbutton);

        choresAdded.setText("");
        totalentered ="" +choreapp.totalEntered;
        choresAdded.setText(totalentered);

    }


    public void addButtonPressed(View view){
        taskenterd = enterTask.getText().toString().trim();
        dayentered = enterDay.getText().toString().trim();
        nameentered = enterName.getText().toString().trim();

        if(taskenterd.equals("")){
            Toast.makeText(this,"Please enter in task",Toast.LENGTH_LONG).show();
        }
        else if(!("").equals(dayentered)&&!("Monday").equalsIgnoreCase(dayentered)&&!("Tuesday").equalsIgnoreCase(dayentered)
                &&!("Wednesday").equalsIgnoreCase(dayentered)&&!("Thursday").equalsIgnoreCase(dayentered)&&!("Friday").equalsIgnoreCase(dayentered)
                &&!("Saturday").equalsIgnoreCase(dayentered)&&!("Sunday").equalsIgnoreCase(dayentered)){
            showWarning();}
        else{
            choreapp.newTask(new Tasks(taskenterd,dayentered,nameentered));
            totalentered ="" +choreapp.totalEntered;
            choresAdded.setText(totalentered);

            clearTextFields();
        }

    }

    public void clearTextFields() {
        enterDay.setText("");
        enterTask.setText("");
        enterName.setText("");

        Toast.makeText(this,"Task added",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void reset(MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete all tasks?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choreapp.dbmanager.reset();
                choreapp.totalEntered=0;
                choresAdded.setText(""+choreapp.totalEntered);

            }

        });

        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }

        });
        builder.show();


    }//https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
}