package com.androidprojects.sunilsharma.leconpsm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidprojects.sunilsharma.leconpsm.API.ApiConfig;
import com.androidprojects.sunilsharma.leconpsm.Adapter.ProspectCustomerEntryAdapter;
import com.androidprojects.sunilsharma.leconpsm.Model.ProspectUser;
import com.androidprojects.sunilsharma.leconpsm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProspectCustomerEntry extends AppCompatActivity
{
    //A List to store all Prospect Customer entry
    private List<ProspectUser> prospectUserList;

    //Recycler View
    private RecyclerView recyclerViewID;

    private ProspectCustomerEntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospect_customer_entry);

        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** Here we getting recycler view from xml*/
        recyclerViewID = findViewById(R.id.recyclerViewID);
        recyclerViewID.setHasFixedSize(true);
        recyclerViewID.setLayoutManager(new LinearLayoutManager(this));

        /** I Done this because m getting the Error Like
         *  E/RecyclerView: No adapter attached; skipping layout
         *  After Adding this Code Error gone*/
        recyclerViewID.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });

        /** Now we have to initialize prospectUserList*/
        prospectUserList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addProspectCustomer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent addCustomerIntent = new Intent(ProspectCustomerEntry.this , ADDProspectCustomerActivity.class);
                startActivity(addCustomerIntent);
            }
        });


        /**
         *  Now This Method will fetch and parse json
         *  to Display it in recycler view
         **/
        loadProspectCustomer();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        adapter.getSelectedContextMenuItem(item);
        return super.onContextItemSelected(item);
    }

    /** When Context MENU ITEM Selected*/


    private void loadProspectCustomer()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiConfig.URL_PROSPECT_CUSTOMER_ENTRY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonArray = new JSONArray(response);

                    //Traversing through all the object
                    for (int i = 0 ; i < jsonArray.length() ; i++)
                    {
                        /** Now Here we getting prospect Object from JSON ARRAY*/
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        ProspectUser prospectUser = new ProspectUser();

                        prospectUser.set$id(jsonObject.getString("$id"));
                        prospectUser.setCNAME(jsonObject.getString("CNAME"));
                        prospectUser.setCOMPANYNAME(jsonObject.getString("COMPANYNAME"));
                        prospectUser.setAdd1(jsonObject.getString("Add1"));
                        prospectUser.setMobile(jsonObject.getString("Mobile"));
                        prospectUser.setEmail(jsonObject.getString("Email"));
                        prospectUser.setAREA(jsonObject.getInt("AREA"));
                        prospectUser.setLandMark(jsonObject.getString("LandMark"));
                        prospectUser.setType(jsonObject.getString("Type"));

                        prospectUserList.add(prospectUser);

                        /** Now we have to Add the prospect customer to prospect User List*/

                    }

                    adapter = new ProspectCustomerEntryAdapter(ProspectCustomerEntry.this ,
                            prospectUserList);
                    recyclerViewID.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(ProspectCustomerEntry.this,
                        "ERROR" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer_list , menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView)
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
