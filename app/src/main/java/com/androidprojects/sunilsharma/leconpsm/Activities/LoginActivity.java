package com.androidprojects.sunilsharma.leconpsm.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidprojects.sunilsharma.leconpsm.API.ApiConfig;
import com.androidprojects.sunilsharma.leconpsm.Helper.SharedPrefManager;
import com.androidprojects.sunilsharma.leconpsm.Model.User;
import com.androidprojects.sunilsharma.leconpsm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextInputEditText edtloginid;
    private TextInputEditText edtloginpassword;
    private Button btnSIgnIn;

    List<User> userList;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        edtloginid = (TextInputEditText) findViewById(R.id.edtloginid);
        edtloginpassword = (TextInputEditText) findViewById(R.id.edtloginpassword);
        btnSIgnIn = (Button) findViewById(R.id.btnSIgnIn);

        /** Progress Dialog*/
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        userList = new ArrayList<>();



        /** Here M Checking if user is NOT LOG OUT Then This condition will be execute */
        if (SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this , Home.class));
            return;
        }

        /** Login Button Click En=vent*/
        btnSIgnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        /** Here m gi*/
    }

    private void userLogin()
    {
        final String loginid = edtloginid.getText().toString();
        final String LoginPassword = edtloginpassword.getText().toString();

        /** here m Validating the Fields*/
        if(TextUtils.isEmpty(loginid))
        {
            edtloginid.setError("Please Enter Your Name");
            edtloginid.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(LoginPassword))
        {
            edtloginpassword.setError("Please Enter Password");
            edtloginpassword.requestFocus();
            return;
        }

        progressDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiConfig.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonArray = new JSONArray(response);
                    /*Toast.makeText(LoginActivity.this,
                            response.toString(),
                            Toast.LENGTH_SHORT).show();
                    Log.d("User", response.toString());*/



                    for (int i = 0 ; i<jsonArray.length() ; i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        User user = new User();
                        user.setId(jsonObject.getString("$id"));
                        user.setEmpType(jsonObject.getString("EmpType"));
                        user.setLoginID(jsonObject.getString("LoginID"));
                        user.setEmp_Name(jsonObject.getString("Emp_Name"));
                        user.setEmp_Dept(jsonObject.getString("Emp_Dept"));
                        user.setLoginStatus(jsonObject.getInt("LoginStatus"));
                        user.setUser_Message(jsonObject.getString("User_Message"));

                        userList.add(user);
                        if(user.getLoginStatus() == 1)
                        {

                            startActivity(new Intent(LoginActivity.this , Home.class));
                        }

                        else if (user.getLoginStatus() == 0)
                            Toast.makeText(LoginActivity.this,
                                    "Sorry Sir you are NOT Registered",
                                    Toast.LENGTH_SHORT).show();

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(LoginActivity.this,
                        "Something Went Wrong", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("loginid", loginid);
                params.put("LoginPassword", LoginPassword);
                return params;
            }
        };
        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(stringRequest);
    }
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }


}
