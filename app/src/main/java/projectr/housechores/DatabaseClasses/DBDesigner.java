package projectr.housechores.DatabaseClasses;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBDesigner extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "chorelist.db";
    private static final int DATABASE_VERSION =7;
    private static final String DATABASE_CREATE_TABLE_CHORES = "create table chorelist"+
            "( _id integer primary key autoincrement not null,"+
            "task text not null,"+
            "day text not null,"+
            "name text not null);";

    public DBDesigner(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_CHORES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(DBDesigner.class.getName(), "Upgrading databse from " + i + "to version "+ i1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS chorelist");
        onCreate(sqLiteDatabase);

    }
}
