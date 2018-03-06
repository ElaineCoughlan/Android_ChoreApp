package projectr.housechores.DatabaseClasses;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projectr.housechores.ConstructorClass.Tasks;
import projectr.housechores.MainClasses.Super_MenuMethods;

public class DBManager {
    private SQLiteDatabase database;
    private DBDesigner dbDesignerobject;


    public DBManager(Context context) {
        dbDesignerobject = new DBDesigner(context);
    }

    public void open() throws SQLException {
        database = dbDesignerobject.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(Tasks task) {
        ContentValues values = new ContentValues();
        values.put("task", task.task);
        values.put("day", task.day);
        values.put("name",task.name);

        database.insert("chorelist", null, values);
    }

    public List<Tasks> getAll() {
        List<Tasks> chorelist = new ArrayList<Tasks>();
        Cursor cursor = database.rawQuery("SELECT * FROM chorelist", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Tasks task = toTask(cursor);
            chorelist.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return chorelist;
    }

    private Tasks toTask(Cursor cursor) {
        Tasks object = new Tasks();
        object._id = cursor.getInt(0);
        object.task = cursor.getString(1);
        object.day = cursor.getString(2);
        object.name = cursor.getString(3);

        return object;
    }

    public void setTotalChoresEntered(Super_MenuMethods smmObject) {
        Cursor cursor = database.rawQuery("SELECT COUNT(task) FROM chorelist", null);
        cursor.moveToFirst();

        if (!cursor.isAfterLast())
            smmObject.choreapp.totalEntered = cursor.getInt(0);

    }
//Search DAY
    public void searchByDay(String searchitem, TextView showResults) {

        Cursor cursor = database.rawQuery("SELECT * FROM chorelist WHERE LOWER(day) = '" + searchitem.toLowerCase().trim() + "' ", null);
        StringBuffer results = new StringBuffer();

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                results.append("Task: " + cursor.getString(1)+ ", ");
                results.append("Assigned to: " + cursor.getString(3)).append("\n");
                showResults.setText(results.toString());

            }
            cursor.close();
        } else {
            showResults.setText("No items Found");
        }//https://www.codeproject.com/Articles/783073/A-Simple-Android-SQLite-Example
    }
//Search NAME
public void searchByName(String searchitem, TextView showResults) {

    Cursor cursor = database.rawQuery("SELECT * FROM chorelist WHERE LOWER(name) = '" + searchitem.toLowerCase().trim() + "' ", null);
    StringBuffer results = new StringBuffer();

    if (cursor.getCount() != 0) {
        while (cursor.moveToNext()) {
            results.append("Task: " + cursor.getString(1)+ ", ");
            results.append("Day: " + cursor.getString(2)).append("\n");
            showResults.setText(results.toString());

        }
        cursor.close();
    } else {
        showResults.setText("No items Found");
    }//^^
}

    public void deleteOneItem(int id) {

        database.delete("chorelist", "_id" + "=" + id, null);

    }


    public void reset() {

        database.delete("chorelist", null, null);

    }

    public void geteditText(int id, String editTask, String editDay, String editName) {
            database.execSQL("UPDATE chorelist SET task = '"+editTask+"', day = '"+editDay+"', name = '"+editName+"' WHERE _id = "+ id);







       /** Cursor cursor = database.rawQuery("SELECT * FROM chorelist WHERE _id = '" + id + "' ", null);
        StringBuffer textid = new StringBuffer();
        String temp;
        if (cursor.getCount() != 0) {

            while (cursor.moveToNext()) {
                textid.append("Task: ").append(cursor.getString(1)).append("\n");
                temp = textid.toString();

                Log.v("WORK", "" + temp +" "+m_Text);

            }
            cursor.close();
        } else {

            Log.v("WORK", "Nothing found");

        }**/
    }
}
