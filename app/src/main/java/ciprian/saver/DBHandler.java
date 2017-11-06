package ciprian.saver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian Anton on 06-11-2017.
 */
public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "goal";

    // Contacts table name
    private static final String TABLE_DETAIL = "GoalDetails";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_GOAL_NAME = "goal_name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_SUM = "sum";




    public DBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_GOAL_DETAIL_TABLE = "CREATE TABLE " + TABLE_DETAIL + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_GOAL_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_SUM + " INTEGER " + ");";

        db.execSQL(CREATE_GOAL_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Student Information
    void addNewGoal(Goal newGoal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_GOAL_NAME, newGoal.get_goal_name());
        values.put(KEY_DESCRIPTION, newGoal.get_description());
        values.put(KEY_SUM, newGoal.get_sum());



        // Inserting Row
        db.insert(TABLE_DETAIL, null, values);
        db.close(); // Closing database connection
    }


    public boolean updateGoalInfo(int idNo, int updSum) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("Update " + TABLE_DETAIL + " set " +  KEY_SUM + " = " + KEY_SUM +" - " + updSum + " where id = " + idNo );

        return true;
    }


    public boolean deleteGoal(int delID){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_DETAIL, KEY_ID + "=" + delID, null) > 0;


    }



    // Getting All Students
    public List<Goal> getAllGoalsList() {


        List<Goal> goalList = new ArrayList<Goal>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Goal goal = new Goal();
                goal.set_id(Integer.parseInt(cursor.getString(0)));
                goal.set_goal_name(cursor.getString(1));
                goal.set_description(cursor.getString(2));
                goal.set_sum(Integer.parseInt(cursor.getString(3)));

                // Adding contact to list
                goalList.add(goal);

            } while (cursor.moveToNext());
        }

        // return contact list
        return goalList;
    }


}
