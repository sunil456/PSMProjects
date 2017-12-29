package com.androidprojects.sunilsharma.leconpsm.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidprojects.sunilsharma.leconpsm.API.ApiConfig;
import com.androidprojects.sunilsharma.leconpsm.Helper.SharedPrefManager;
import com.androidprojects.sunilsharma.leconpsm.Model.ProspectUser;
import com.androidprojects.sunilsharma.leconpsm.Model.StateModel;
import com.androidprojects.sunilsharma.leconpsm.Model.User;
import com.androidprojects.sunilsharma.leconpsm.R;
import com.androidquery.AQuery;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class ADDProspectCustomerActivity extends AppCompatActivity
{

    private static final String INSERT_URL = "http://api.leconseals.in/api/prospect";
/*   private static final String BRANCH_URL = "http://api.leconseals.in/api/prospect";*/

    /** Declaring the Variables*/
    Spinner spinnerType;
    Spinner spinnerBranch;
    Spinner spinnerState;
    Spinner spinnerCity;
    Spinner spinnerArea;
    Spinner spinnerCategory;
    Spinner spinnerCustomerType;

    TextInputEditText edtContactPerson;
    TextInputEditText edtCompanyName;
    TextInputEditText edtDesignation;
    TextInputEditText edtPlant;
    TextInputEditText edtAddress;
    TextInputEditText edtLandmark;
    TextInputEditText edtTelephoneNumber;
    TextInputEditText edtAlternateNumber;
    TextInputEditText edtMobileNumber;
    TextInputEditText edtEmail;
    TextInputEditText edtWebsite;
    TextInputEditText edtHeadOffice;
    TextInputEditText edtDOB;
    TextInputEditText edtDOA;
    TextInputEditText edtDetailedDescription;
    TextInputEditText edtRemark;

    DatePickerDialog datePickerDialog;
    // Creating Progress dialog.
    ProgressDialog progressDialog;

    Button btnInsert;

    ArrayAdapter<CharSequence> TypeAdapter;
    ArrayAdapter<CharSequence> CustomerTypeAdapter;
    ArrayAdapter<CharSequence> CustomerCategoryAdapter;

    AQuery aQuery;
    Context context;
    List<String> stateList;
    List<String> cityList;
    List<String> areaList;
    List<String> branchList;
    List<String> categoryList;

    List<StateModel> stateModelList = new ArrayList<>();
    List<StateModel> cityModelList = new ArrayList<>();
    List<StateModel> areaModelList = new ArrayList<>();
    List<StateModel> branchModelList = new ArrayList<>();
    List<StateModel> categoryModelList = new ArrayList<>();
    String stateId = "", cityId = "";


    ProspectUser prospectUser;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprospect_customer);
        context = this;
        aQuery = new AQuery(context);

        /** Here m Initializing the AlertDialog Builder*/
        builder = new AlertDialog.Builder(this);

        prospectUser = new ProspectUser();
        progressDialog = new ProgressDialog(ADDProspectCustomerActivity.this);

        /** Here M Initializing All the Variable*/
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        spinnerBranch = (Spinner) findViewById(R.id.spinnerBranch);
        spinnerState = (Spinner) findViewById(R.id.spinnerState);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        spinnerArea = (Spinner) findViewById(R.id.spinnerArea);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spinnerCustomerType = (Spinner) findViewById(R.id.spinnerCustomerType);


        edtContactPerson = (TextInputEditText) findViewById(R.id.edtContactPerson);
        edtCompanyName = (TextInputEditText) findViewById(R.id.edtCompanyName);
        edtDesignation = (TextInputEditText) findViewById(R.id.edtDesignation);
        edtPlant = (TextInputEditText) findViewById(R.id.edtPlant);
        edtAddress = (TextInputEditText) findViewById(R.id.edtAddress);
        edtLandmark = (TextInputEditText) findViewById(R.id.edtLandmark);
        edtTelephoneNumber = (TextInputEditText) findViewById(R.id.edtTelephoneNumber);
        edtMobileNumber = (TextInputEditText) findViewById(R.id.edtMobileNumber);
        edtAlternateNumber = (TextInputEditText) findViewById(R.id.edtAlternateNumber);
        edtEmail = (TextInputEditText) findViewById(R.id.edtEmail);
        edtWebsite = (TextInputEditText) findViewById(R.id.edtWebsite);
        edtHeadOffice = (TextInputEditText) findViewById(R.id.edtHeadOffice);
        edtDOB = (TextInputEditText) findViewById(R.id.edtDOB);
        edtDOA = (TextInputEditText) findViewById(R.id.edtDOA);
        edtRemark = (TextInputEditText) findViewById(R.id.edtRemark);
        edtDetailedDescription = (TextInputEditText) findViewById(R.id.edtDetailedDescription);


        /** here  i have to give event to the edtDOB*/
        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current Year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                //Date Picker Dialog
                datePickerDialog = new DatePickerDialog(ADDProspectCustomerActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                            {
                                // set day of month , month and year value in the edit text
                                edtDOB.setText(dayOfMonth + "/"
                                        + (month + 1) + "/" + year);
                            }
                        } , mYear , mMonth , mDay);
                datePickerDialog.show();
            }
        });


        /** here  i have to give event to the edtDOA*/
        edtDOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current Year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                //Date Picker Dialog
                datePickerDialog = new DatePickerDialog(ADDProspectCustomerActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                            {
                                // set day of month , month and year value in the edit text
                                edtDOA.setText(dayOfMonth + "/"
                                        + (month + 1) + "/" + year);
                            }
                        } , mYear , mMonth , mDay);
                datePickerDialog.show();
            }
        });


        /** This is a Static Spinner*/
        TypeAdapter = ArrayAdapter.createFromResource(ADDProspectCustomerActivity.this ,
                R.array.Type,
                android.R.layout.simple_spinner_item);

        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(TypeAdapter);


        /** This is for Customer Type*/
        CustomerTypeAdapter = ArrayAdapter.createFromResource(ADDProspectCustomerActivity.this ,
                R.array.customer_Type , android.R.layout.simple_spinner_item);

        CustomerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCustomerType.setAdapter(CustomerTypeAdapter);

        /** This is for Customer Category*/
        /*CustomerCategoryAdapter = ArrayAdapter.createFromResource(ADDProspectCustomerActivity.this ,
                R.array.customer_category , android.R.layout.simple_spinner_item);

        CustomerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(CustomerCategoryAdapter);*/

        /** Getting the Current user*/
        final User user = SharedPrefManager.getInstance(this).getUser();
        Log.d("USER" , user.toString());

        new FetchState().execute();
        new FetchBranch().execute();
        new FetchCategory().execute();

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ADDProspectCustomerActivity.this,
                        parent.getItemAtPosition(position)+" Selected",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /** This one is for STATE*/
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {

                // setCitySpinner(stateList.get(position));
                stateId = stateModelList.get(position).getId();
                new FetchCity().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        /** This one is for CITY*/
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {

                // setCitySpinner(stateList.get(position));
                cityId = cityModelList.get(position).getId();
                new FetchArea().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        /** This one is for BRANCH*/
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /** This is for Customer Category*/
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnInsert = (Button) findViewById(R.id.btnInsert);


        /** giving event to btnInsert for inserting data*/
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                insertData();
            }
        });
    }



    /** This Code is for loading State City and AREA based on on State Id*/
    class FetchState extends AsyncTask<Void, Integer, String> {
        private OkHttpClient client = new OkHttpClient();
        String serresponse = "";
        ProgressDialog progress;

        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage(Html
                    .fromHtml("<b>Fetching state</b><br/>Please Wait..."));
            progress.setCanceledOnTouchOutside(false);
            progress.show();
        }

        protected String doInBackground(Void... arg0) {

            String urlToSend;
            urlToSend = "http://api.leconseals.in/api/masterlist/getstate";
            // urlToSend = "http://api.leconseals.in/api/masterlist/getallmaster";

            Log.d("urlToState", "" + urlToSend);
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(urlToSend)
                    .build();
            try {
                okhttp3.Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    serresponse = response.body().string();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return serresponse;
        }

        protected void onPostExecute(String result) {
            progress.dismiss();
            Log.d("StateResponse", serresponse);

            stateModelList = new ArrayList<>();
            stateModelList = new Gson().fromJson(serresponse,
                    new TypeToken<List<StateModel>>() {
                    }.getType());
            stateList = new ArrayList<>();
            for (int i = 0; i < stateModelList.size(); i++) {
                stateList.add(stateModelList.get(i).getName());
            }

            if (!stateList.isEmpty()) {
                setStateSpinner();
            }

        }
    }


    class FetchCity extends AsyncTask<Void, Integer, String> {
        private OkHttpClient client = new OkHttpClient();
        String serresponse = "";
        ProgressDialog progress;

        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage(Html
                    .fromHtml("<b>Fetching city</b><br/>Please Wait..."));
            progress.setCanceledOnTouchOutside(false);
            progress.show();
        }

        protected String doInBackground(Void... arg0) {

            String urlToSend;
            urlToSend = "http://api.leconseals.in/api/masterlist/getcity?stateid=" + stateId;
            // urlToSend = "http://api.leconseals.in/api/masterlist/getallmaster";

            Log.d("urlToCity", "" + urlToSend);
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(urlToSend)
                    .build();
            try {
                okhttp3.Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    serresponse = response.body().string();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return serresponse;
        }

        protected void onPostExecute(String result) {
            progress.dismiss();
            Log.d("CityResponse", serresponse);
            cityModelList = new ArrayList<>();

            cityModelList = new Gson().fromJson(serresponse,
                    new TypeToken<List<StateModel>>() {
                    }.getType());
            cityList = new ArrayList<>();
            for (int i = 0; i < cityModelList.size(); i++) {
                cityList.add(cityModelList.get(i).getName());
            }

            if (!cityList.isEmpty()) {
                setCitySpinner();
            }

        }
    }

    class FetchArea extends AsyncTask<Void, Integer, String> {
        private OkHttpClient client = new OkHttpClient();
        String serresponse = "";
        ProgressDialog progress;

        protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage(Html
                    .fromHtml("<b>Fetching area</b><br/>Please Wait..."));
            progress.setCanceledOnTouchOutside(false);
            progress.show();
        }

        protected String doInBackground(Void... arg0) {

            String urlToSend;
            urlToSend = "http://api.leconseals.in/api/masterlist/getarea?cityid=" + cityId;
            // urlToSend = "http://api.leconseals.in/api/masterlist/getallmaster";

            Log.d("urlToArea", "" + urlToSend);
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(urlToSend)
                    .build();
            try {
                okhttp3.Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    serresponse = response.body().string();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return serresponse;
        }

        protected void onPostExecute(String result) {
            progress.dismiss();
            Log.d("AreaResponse", serresponse);

            areaModelList = new ArrayList<>();
            areaModelList = new Gson().fromJson(serresponse,
                    new TypeToken<List<StateModel>>() {
                    }.getType());
            areaList = new ArrayList<>();
            for (int i = 0; i < areaModelList.size(); i++) {
                areaList.add(areaModelList.get(i).getName());
            }
            if (!areaList.isEmpty()) {
                setAreaSpinner();
            }

        }
    }

    class FetchBranch extends AsyncTask<Void , Integer , String>{

        private OkHttpClient client = new OkHttpClient();
        String serresponse = "";
        ProgressDialog progress;

        protected void onPreExecute()
        {
            progress = new ProgressDialog(context);
            progress.setMessage(Html
                    .fromHtml("<b>Fetching Branch</b><br/>Please Wait..."));
            progress.setCanceledOnTouchOutside(false);
            progress.show();
        }

        @Override
        protected String doInBackground(Void... arg0)
        {
            String urlToSend;
            urlToSend = "http://api.leconseals.in/api/masterlist/getBranch";
            // urlToSend = "http://api.leconseals.in/api/masterlist/getallmaster";

            Log.d("urlToBranch", "" + urlToSend);
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(urlToSend)
                    .build();
            try {
                okhttp3.Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    serresponse = response.body().string();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return serresponse;
        }

        protected void onPostExecute(String result) {
            progress.dismiss();
            Log.d("BranchResponse", serresponse);

            branchModelList = new ArrayList<>();
            branchModelList = new Gson().fromJson(serresponse,
                    new TypeToken<List<StateModel>>() {
                    }.getType());
            branchList = new ArrayList<>();
            for (int i = 0; i < branchModelList.size(); i++) {
                branchList.add(branchModelList.get(i).getName());
            }
            if (!branchList.isEmpty()) {
                setBranchSpinner();
            }

        }
    }

    class FetchCategory extends AsyncTask<Void , Integer , String>{

        private OkHttpClient client = new OkHttpClient();
        String serresponse = "";
        ProgressDialog progress;

        @Override
        protected void onPreExecute()
        {
            progress = new ProgressDialog(context);
            progress.setMessage(Html
                    .fromHtml("<b>Fetching Category</b><br/>Please Wait..."));
            progress.setCanceledOnTouchOutside(false);
            progress.show();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String urlToSend;
            urlToSend = "http://api.leconseals.in/api/masterlist/getCategory";

            Log.d("urlToCity" , "" + urlToSend);
            okhttp3.Request request = new okhttp3.Request.Builder().
                    url(urlToSend).
                    build();

            try
            {
                okhttp3.Response response = client.newCall(request).execute();

                if (response.isSuccessful())
                {
                    serresponse = response.body().string();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return serresponse;
        }

        @Override
        protected void onPostExecute(String s)
        {
            progress.dismiss();
            Log.d("CategoryResponse" , serresponse);
            categoryModelList = new ArrayList<>();
            categoryModelList = new Gson().fromJson(serresponse ,
                    new TypeToken<List<StateModel>>(){}.getType());

            categoryList = new ArrayList<>();
            for (int i = 0 ; i < categoryModelList.size() ; i++)
            {
                categoryList.add(categoryModelList.get(i).getName());
            }
            if (!categoryList.isEmpty())
            {
                setCategorySpinner();
            }


        }
    }

    public void setStateSpinner() {
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, stateList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerState.setAdapter(dataAdapter);
    }

    public void setCitySpinner() {
        // Creating adapter for spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, cityList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerCity.setAdapter(dataAdapter);
    }

    public void setAreaSpinner() {
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, areaList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerArea.setAdapter(dataAdapter);
    }

    public void setBranchSpinner(){
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(context ,
                        android.R.layout.simple_spinner_item , branchList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(dataAdapter);
    }

    public void setCategorySpinner(){
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(context ,
                        android.R.layout.simple_spinner_item , categoryList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(dataAdapter);
    }




    /** Sinner code Completed  Here*/

    private void insertData()
    {

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String DOBDate = dateFormat.format(new Date(edtDOB.getText().toString()).getTime());
        Log.d("DOBDate" , DOBDate.toString());
        String DOADate = dateFormat.format(new Date(edtDOA.getText().toString()).getTime());
        Log.d("DOBDate" , DOADate.toString());

        final String STYPE = spinnerType.getSelectedItem().toString();
        final String CState = spinnerState.getSelectedItem().toString();
        final String City = spinnerCity.getSelectedItem().toString();
        final String AREA = spinnerArea.getSelectedItem().toString();
        final String CUSTOMERTYPE = spinnerCustomerType.getSelectedItem().toString();
        final String Category = spinnerCategory.getSelectedItem().toString();
        final String CNAME = edtContactPerson.getText().toString().trim();
        final String Desig = edtDesignation.getText().toString().trim();
        final String LandMark = edtLandmark.getText().toString().trim();
        final String COMPANYNAME = edtCompanyName.getText().toString().trim();
        final String Add1 = edtAddress.getText().toString().trim();
        final String Add2 = edtAddress.getText().toString().trim();
        final String HeadOffice = edtHeadOffice.getText().toString().trim();
        final String Mobile = edtMobileNumber.getText().toString().trim();
        final String AlternateNo = edtAlternateNumber.getText().toString().trim();
        final String PhoneNo = edtTelephoneNumber.getText().toString().trim();
        final String Email = edtEmail.getText().toString().trim();
        final String Website = edtWebsite.getText().toString().trim();
        final String Remark = edtRemark.getText().toString().trim();
        final String Plant = edtPlant.getText().toString().trim();
        final String DOB = DOBDate.toString();
        final String DOA = DOADate.toString();

        // String UserName = new User().getEmp_Name();

        final User user = SharedPrefManager.getInstance(this).getUser();


        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,
                ApiConfig.URL_PROSPECT_CUSTOMER_ENTRY,
                new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                builder.setTitle("Server Response");
                builder.setMessage("Response :" + response.toString());
                Log.d("Response" , response);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    /**
                     * This method will be invoked when a button in the dialog is clicked.
                     *
                     * @param dialog the dialog that received the click
                     * @param which  the button that was clicked (ex.
                     *               {@link DialogInterface#BUTTON_POSITIVE}) or the position
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        /*edtContactPerson.setText("");
                        edtCompanyName.setText("");
                        edtAddress.setText("");
                        edtHeadOffice.setText("");
                        edtTelephoneNumber.setText("");
                        edtAlternateNumber.setText("");
                        edtMobileNumber.setText("");
                        edtEmail.setText("");
                        edtLandmark.setText("");
                        edtWebsite.setText("");
                        edtDOB.setText("");
                        edtDOA.setText("");
                        edtRemark.setText("");
                        edtPlant.setText("");
                        edtDesignation.setText("");
                        edtDetailedDescription.setText("");

                        startActivity(new Intent(ADDProspectCustomerActivity.this , ProspectCustomerEntry.class));*/

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();

                Toast.makeText(context,
                        "Error While Adding the Data To The Server",
                        Toast.LENGTH_SHORT).show();
                Log.i("MyError" , "" + error.getMessage());
            }
        }){
            @Override
            protected Map<String , String> getParams() throws AuthFailureError {

                Map<String , String> parameters = new HashMap<String,String>();

                parameters.put("$id" , "1");
                parameters.put("ID" ,user.getLoginID());
                parameters.put("STYPE" , STYPE);
                parameters.put("CState" , CState);
                parameters.put("City" , City);
                parameters.put("AREA" , AREA);
                parameters.put("Country" , "");
                parameters.put("CUSTOMERTYPE" , CUSTOMERTYPE);
                parameters.put("Category" , Category);
                parameters.put("CNAME" , CNAME);
                parameters.put("COMPANYNAME" , COMPANYNAME);
                parameters.put("Add1" , Add1);
                parameters.put("Add2" , Add2);
                parameters.put("HeadOffice" , HeadOffice);
                parameters.put("Mobile" , Mobile);
                parameters.put("AlternateNo" , AlternateNo);
                parameters.put("PhoneNo" , PhoneNo);
                parameters.put("Email" , Email);
                parameters.put("Website" , Website);
                parameters.put("Remark" , Remark);
                parameters.put("Plant" , Plant);
                parameters.put("Desig" , Desig);
                parameters.put("User" , user.getEmp_Name());
                parameters.put("CreateBy" , user.getLoginID());
                parameters.put("LandMark" , LandMark);
                parameters.put("DOB" , DOB);
                parameters.put("DOA" , DOA);


                return parameters;


            }
        };
        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(stringRequest);
    }

}
