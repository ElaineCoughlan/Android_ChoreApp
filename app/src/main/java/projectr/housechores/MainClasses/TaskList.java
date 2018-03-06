package projectr.housechores.MainClasses;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import projectr.housechores.ConstructorClass.Tasks;
import projectr.housechores.R;

public class TaskList extends Super_MenuMethods implements AdapterView.OnItemClickListener {

    ListView listview;
    CustomAdapter myAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listview = (ListView) findViewById(R.id.choreslist);
        myAdapter = new CustomAdapter(this, choreapp.dbmanager.getAll());
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this,""+myAdapter.choreslist.get(i),Toast.LENGTH_LONG).show();
    }






class CustomAdapter extends ArrayAdapter<Tasks> {
    private Context context;
    public List<Tasks> choreslist;
    private String editTask = "";
    private String editDay = "";
    private String editName = "";


    public CustomAdapter(Context context, List<Tasks> choreslist) {
        super(context, R.layout.row_list, choreslist);
        this.context = context;
        this.choreslist = choreslist;
    }

    public View getView(final int postion, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row_list, parent, false);
        final Tasks tasks = choreslist.get(postion);

        TextView task = view.findViewById(R.id.row_task);
        TextView day =  view.findViewById(R.id.row_day);
        TextView name = view.findViewById(R.id.row_name);
        /////////Delete button****************************

        Button delete = view.findViewById(R.id.button3);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TaskList.this);
                builder.setTitle("Delete task?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choreapp.dbmanager.deleteOneItem(choreslist.get(postion).getId());
                        startActivity(getIntent());

                    }

                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.show();
            }
        }); //https://stackoverflow.com/questions/45304307/cant-get-the-id-from-listview-right/45304344#45304344

        ///////Edit button***********************

        Button edit = (Button) view.findViewById(R.id.button2);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(TaskList.this);
                builder.setTitle("Edit Task/Day");

                final EditText day = new EditText(TaskList.this);
                day.setInputType(InputType.TYPE_CLASS_TEXT);
                day.setHint("Day"); //https://stackoverflow.com/questions/8922587/how-to-give-the-hint-of-edittextbox-of-dialog-which-is-created-by-code-in-androi

                final EditText task = new EditText(TaskList.this);
                task.setInputType(InputType.TYPE_CLASS_TEXT);
                task.setHint("Task");

                final EditText name = new EditText(TaskList.this);
                name.setInputType(InputType.TYPE_CLASS_TEXT);
                name.setHint("Name");

                LinearLayout alertLayout = new LinearLayout(TaskList.this);
                alertLayout.setOrientation(LinearLayout.VERTICAL);
                alertLayout.addView(task);
                alertLayout.addView(day);
                alertLayout.addView(name);
                builder.setView(alertLayout); //https://stackoverflow.com/questions/16169787/how-to-add-two-edit-text-fields-or-views-in-an-alertdialog-box

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTask = task.getText().toString();
                        editDay = day.getText().toString();
                        editName = name.getText().toString();

                        if (editName.equals("")) {
                            editName = choreslist.get(postion).getName();
                        }
                        if (editTask.equals("")) {
                            editTask = choreslist.get(postion).getTask();
                        }

                        if ((editDay).equals("") || !(editDay).equalsIgnoreCase("Monday") && !(editDay).equalsIgnoreCase("Tuesday")
                                && !("Wednesday").equalsIgnoreCase(editDay) && !("Thursday").equalsIgnoreCase(editDay) && !("Friday").equalsIgnoreCase(editDay)
                                && !("Saturday").equalsIgnoreCase(editDay) && !("Sunday").equalsIgnoreCase(editDay)) {

                            editDay = choreslist.get(postion).getDay();

                        }


                        choreapp.dbmanager.geteditText(choreslist.get(postion).getId(), editTask, editDay, editName);
                        startActivity(getIntent());


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }//https://stackoverflow.com/questions/10903754/input-text-dialog-android
        });//https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android

        task.setText(tasks.task);
        day.setText(tasks.day);
        name.setText(tasks.name);
        return view;
    }


 }
}

