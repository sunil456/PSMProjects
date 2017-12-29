package com.androidprojects.sunilsharma.leconpsm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidprojects.sunilsharma.leconpsm.R;

public class DetailsActicity extends AppCompatActivity {

    TextView txtCompanyName;
    TextView txtType;
    TextView txtAddress;
    TextView txtArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_acticity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        //INITIATE
        txtCompanyName = (TextView) findViewById(R.id.txtCompanyName);
        txtType = (TextView) findViewById(R.id.txtType);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtArea = (TextView) findViewById(R.id.txtArea);

        /** Receive*/
        Intent intent = getIntent();
        String CompanyName = intent.getExtras().getString("COMPANYNAME");
        String Type = intent.getExtras().getString("Type");
        String Area = intent.getExtras().getString("AREA");
        String Address = intent.getExtras().getString("Add1");
        String choice = intent.getExtras().getString("CHOICE");

        /**BIND*/

        txtCompanyName.setText(CompanyName);
        txtType.setText(Type);
        txtArea.setText(Area);
        txtAddress.setText(Address);

        Toast.makeText(this, "RECEIVED" + choice, Toast.LENGTH_SHORT).show();
    }

}
