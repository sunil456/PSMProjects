package com.androidprojects.sunilsharma.leconpsm.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.androidprojects.sunilsharma.leconpsm.Activities.LoginActivity;
import com.androidprojects.sunilsharma.leconpsm.Model.User;

/**
 * Created by sunil sharma on 12/25/2017.
 */

public class SharedPrefManager
{
    private static final String SHARED_PREF_NAME = "lecon";
    private static final String KEY_ID = "$id";
    private static final String KEY_EmpType = "EmpType";
    private static final String KEY_LoginID = "LoginID";
    private static final String KEY_Emp_Name = "Emp_Name";
    private static final String KEY_Emp_Dept = "Emp_Dept";
    private static final String KEY_LoginStatus = "LoginStatus";
    private static final String KEY_User_Message = "User_Message";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    public SharedPrefManager(Context context)
    {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    /**
     *  Method to let the user login
     * this method will store the user data in shared preferences
     * */
    public void userLogin(User user)
    {
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("KEY_ID" , user.getId());
        editor.putString("KEY_EmpType" , user.getEmpType());
        editor.putString("KEY_LoginID" , user.getLoginID());
        editor.putString("KEY_Emp_Name" , user.getEmp_Name());
        editor.putString("KEY_Emp_Dept" , user.getEmp_Dept());
        editor.putInt("KEY_LoginStatus" , user.getLoginStatus());
        editor.putString("KEY_User_Message" , user.getUser_Message());

        editor.apply();
    }

    /** This method will checker whether user is already logged in or not*/
    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME , Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_LoginID , null) != null;
    }

    //This Method will give the logged in user
    public User getUser()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME , Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getString(KEY_ID , null),
                sharedPreferences.getString(KEY_EmpType , null),
                sharedPreferences.getString(KEY_LoginID , null),
                sharedPreferences.getString(KEY_Emp_Name , null),
                sharedPreferences.getString(KEY_Emp_Dept , null),
                sharedPreferences.getInt(String.valueOf(KEY_LoginStatus), -1),
                sharedPreferences.getString(KEY_User_Message , null)
        );

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
