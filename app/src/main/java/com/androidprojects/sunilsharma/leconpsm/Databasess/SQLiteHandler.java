package com.androidprojects.sunilsharma.leconpsm.Databasess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by sunil sharma on 12/24/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper
{
    private static final String TAG = SQLiteHandler.class.getSimpleName();


    /** All Static variables*/

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "LECON";

    //Login table name
    private static final String TABLE_USER = "user";


    //Login Table Columns name
    private static final String KEY_ID = "$id";
    private static final String KEY_EMPTYPE = "EmpType";
    private static final String KEY_LOGINID = "LoginID";
    private static final String KEY_EMP_NAME = "Emp_Name";
    private static final String KEY_EMP_DEPT = "Emp_Dept";
    private static final int KEY_LOGINSTATUS = 1;
    private static final String KEY_USER_MESSAGE = "User_Message";


    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     */
    public SQLiteHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " TEXT,"
                + KEY_EMPTYPE + " TEXT,"
                + KEY_LOGINID + " TEXT,"
                + KEY_EMP_NAME + " TEXT,"
                + KEY_EMP_DEPT + " TEXT,"
                + KEY_LOGINSTATUS + " INTEGER,"
                + KEY_USER_MESSAGE + " TEXT"
                + ");";

        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG , "Database tables created");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        //Created tables again
        onCreate(db);
    }


    /** Here m storing user details in database*/
    public void addUser(String $id , String EmpType , String LoginID ,
                        String Emp_Name , String Emp_Dept , int LoginStatus , String User_Message)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID , $id);
        values.put(KEY_EMPTYPE , EmpType);
        values.put(KEY_LOGINID , LoginID);
        values.put(KEY_EMP_NAME , Emp_Name);
        values.put(KEY_EMP_DEPT , Emp_Dept);
        values.put(String.valueOf(KEY_LOGINSTATUS), LoginStatus);
        values.put(KEY_USER_MESSAGE , User_Message);

        //Inserting row
        long id = db.insert(TABLE_USER , null , values);
        //Closing Database Connection
        db.close();

        Log.d(TAG , "New User Inserted into sqlite: " + id);
    }

    /** Here Getting user data from database*/
    public HashMap<String , String> getUserDetails()
    {
        HashMap<String , String> user = new HashMap<String,String>();

        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery , null);

        //Move to First Row
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
        {
            user.put("$id" , cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("EmpType" , cursor.getString(cursor.getColumnIndex(KEY_EMPTYPE)));
            user.put("LoginID" , cursor.getString(cursor.getColumnIndex(KEY_LOGINID)));
            user.put("Emp_Name" , cursor.getString(cursor.getColumnIndex(KEY_EMP_NAME)));
            user.put("Emp_Dept" , cursor.getString(cursor.getColumnIndex(KEY_EMP_DEPT)));
            user.put("LoginStatus" , String.valueOf(cursor.getInt(cursor.getColumnIndex(String.valueOf(KEY_LOGINSTATUS)))));
            user.put("User_Message" , cursor.getString(cursor.getColumnIndex(KEY_USER_MESSAGE)));
        }
        cursor.close();
        db.close();
        //return user
        Log.d(TAG , "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
}
