package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.UploadBuilty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AddClient extends AppCompatActivity implements View.OnClickListener {

    private EditText  e1,e2,e3,e4,e5;
    private Button b1;
    private String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_add);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Client Add");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in, R.anim.slide_out);
                onBackPressed();
            }
        });
        toolbar.setSubtitleTextAppearance(getApplicationContext(), R.style.styleA);
        toolbar.setTitleTextAppearance(getApplicationContext(), R.style.styleA);
        toolbar.setTitleTextColor(Color.WHITE);


        initview();
    }

    private void initview() {
        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.contact);
        e3 = findViewById(R.id.address);
        e4 = findViewById(R.id.email);
        e5 = findViewById(R.id.profile);
        b1 = findViewById(R.id.submit);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                postClient();
                break;
        }
    }

    private void postClient() {
        String FirstName = e1.getText().toString().trim();
        String ContactNo = e1.getText().toString().trim();
        String Address = e1.getText().toString().trim();
        String EmailId = e1.getText().toString().trim();
        String Business = e1.getText().toString().trim();
        try {
            result = new HelperApi.PostClient().execute(
                    FirstName.toString(),
                    ContactNo.toString(),
                    Address.toString(),
                    EmailId.toString(),
                    Business.toString()).get();
            if (result.isEmpty()) {
                result = new HelperApi.PostClient().execute(
                        FirstName.toString(),
                        ContactNo.toString(),
                        Address.toString(),
                        EmailId.toString(),
                        Business.toString()).get();
            } else {

                AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                alertbox.setIcon(R.drawable.ic_warning_black_24dp);
                alertbox.setTitle("Successfully Added");
                alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {


                    }
                });

                alertbox.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
