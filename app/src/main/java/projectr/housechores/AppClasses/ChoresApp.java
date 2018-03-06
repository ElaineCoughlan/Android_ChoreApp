package projectr.housechores.AppClasses;


import android.app.Application;
import android.util.Log;

import projectr.housechores.ConstructorClass.Tasks;
import projectr.housechores.DatabaseClasses.DBManager;

public class ChoresApp extends Application {
    public int totalEntered = 0;
    public DBManager dbmanager;

    public int newTask(Tasks task) {
        dbmanager.add(task);
        totalEntered++;
        return totalEntered;
    }

    public void onCreate(){
        super.onCreate();
        Log.v("LogMessage","ChoresAppClass");
        dbmanager = new DBManager(this);
        Log.v("LogMessage","Database created");
    }



}
