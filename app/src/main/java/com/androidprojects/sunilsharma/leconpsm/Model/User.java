package com.androidprojects.sunilsharma.leconpsm.Model;

/**
 * Created by sunil sharma on 12/25/2017.
 */

public class User
{
    private String id;
    private String EmpType;
    private String LoginID;
    private String Emp_Name;
    private String Emp_Dept;
    private int LoginStatus;
    private String User_Message;


    public User() {
    }

    public User(String id, String empType, String loginID,
                String emp_Name, String emp_Dept,
                int loginStatus, String user_Message) {
        this.id = id;
        EmpType = empType;
        LoginID = loginID;
        Emp_Name = emp_Name;
        Emp_Dept = emp_Dept;
        LoginStatus = loginStatus;
        User_Message = user_Message;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpType() {
        return EmpType;
    }

    public void setEmpType(String empType) {
        EmpType = empType;
    }

    public String getLoginID() {
        return LoginID;
    }

    public void setLoginID(String loginID) {
        LoginID = loginID;
    }

    public String getEmp_Name() {
        return Emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        Emp_Name = emp_Name;
    }

    public String getEmp_Dept() {
        return Emp_Dept;
    }

    public void setEmp_Dept(String emp_Dept) {
        Emp_Dept = emp_Dept;
    }

    public int getLoginStatus() {
        return LoginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        LoginStatus = loginStatus;
    }

    public String getUser_Message() {
        return User_Message;
    }

    public void setUser_Message(String user_Message) {
        User_Message = user_Message;
    }
}