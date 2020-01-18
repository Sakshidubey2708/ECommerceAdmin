package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.adapter.SubCatagoryAdapter;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.CatagoriesModel;
import com.bytes.tech.awizom.ecommerceadmin.models.UserModels;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ViewUsers extends AppCompatActivity {

    RecyclerView recyclerView;
    private String result = "";
    List<UserModels> userModels;
    ViewUserAdapter subCatagoryAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String ID="";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_list_layout);

        initview();
    }

    private void initview() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Users");
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




        progressDialog = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewUsers.this));
        getuser();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                getuser();
            }
        });

    }

    private void getuser() {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GETUsers().execute().get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
                mSwipeRefreshLayout.setRefreshing(false);
            } else {


                    progressDialog.dismiss();
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<UserModels>>() {
                    }.getType();
                    userModels = new Gson().fromJson(result, listType);
                    Log.d("Error", userModels.toString());
                    ViewUserAdapter viewUserAdapter= new ViewUserAdapter(ViewUsers.this, userModels);
                    recyclerView.setAdapter(viewUserAdapter);
                    mSwipeRefreshLayout.setRefreshing(false);




            }
        } catch (Exception e) {
            mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();

        }
    }

}
