package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.CatagoriesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private String result="";
    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    List<CatagoriesModel> categorylist;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        initView();
    }

    private void initView() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search");

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

        t1 = findViewById(R.id.T1);
        t2 = findViewById(R.id.T2);
        t3 = findViewById(R.id.T3);
        t4 = findViewById(R.id.T4);
        t5 = findViewById(R.id.T5);
        t6 = findViewById(R.id.T6);
        t7 = findViewById(R.id.T7);


        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        t6.setOnClickListener(this);
        t7.setOnClickListener(this);


        getCategoryList();
    }
    private void getCategoryList() {

        try {
            result = new HelperApi.GetAllGetMainCategoriesList().execute().get();
            if (result.isEmpty()) {
                result = new HelperApi.GetAllGetMainCategoriesList().execute().get();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<CatagoriesModel>>() {
                }.getType();
                categorylist = new Gson().fromJson(result, listType);

                if(result.isEmpty()){
                    t1.setText("Home"+ String.valueOf(categorylist.get(0).getMainCatId()));
                    t2.setText("Electronics"+"."+"\n"+String.valueOf(categorylist.get(1).getMainCatId()));
                    t3.setText("Education"+"."+"\n"+String.valueOf(categorylist.get(2).getMainCatId()));
                    t4.setText("Medical"+"."+"\n"+ String.valueOf(categorylist.get(3).getMainCatId()));
                    t5.setText("Machine"+"."+"\n"+String.valueOf(categorylist.get(4).getMainCatId()));
                    t6.setText("Electronic"+"."+"\n"+String.valueOf(categorylist.get(5).getMainCatId()));
                    t7.setText("Tickets"+"."+"\n"+String.valueOf(categorylist.get(6).getMainCatId()));

                }else {

                    for(int i=0;i<=categorylist.size();i++){
                        t1.setText(categorylist.get(0).getMainCatName()+"."+"\n"+ String.valueOf(categorylist.get(0).getMainCatId()));
                        t2.setText(categorylist.get(1).getMainCatName()+"."+"\n"+String.valueOf(categorylist.get(1).getMainCatId()));
                        t3.setText(categorylist.get(2).getMainCatName()+"."+"\n"+String.valueOf(categorylist.get(2).getMainCatId()));
                        t4.setText(categorylist.get(3).getMainCatName()+"."+"\n"+ String.valueOf(categorylist.get(3).getMainCatId()));
                        t5.setText(categorylist.get(4).getMainCatName()+"."+"\n"+String.valueOf(categorylist.get(4).getMainCatId()));
                        t6.setText(categorylist.get(5).getMainCatName()+"."+"\n"+String.valueOf(categorylist.get(5).getMainCatId()));
                        t7.setText(categorylist.get(6).getMainCatName()+"."+"\n"+String.valueOf(categorylist.get(6).getMainCatId()));

                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.T1:
                intent = new Intent(this, ViewSubCatagoryActivity.class);
                intent.putExtra("mainID",t1.getText().toString().split("\\n")[1]);
                startActivity(intent);
                break;
            case R.id.T2:
                intent = new Intent(this, ViewSubCatagoryActivity.class);
                intent.putExtra("mainID",t2.getText().toString().split("\\n")[1]);
                startActivity(intent);
                break;
            case R.id.T3:
                intent = new Intent(this, ViewSubCatagoryActivity.class);
                intent.putExtra("mainID",t3.getText().toString().split("\\n")[1]);
                startActivity(intent);
                break;
            case R.id.T4:
                intent = new Intent(this, ViewSubCatagoryActivity.class);
                intent.putExtra("mainID",t4.getText().toString().split("\\n")[1]);
                startActivity(intent);

                break;
            case R.id.T5:
                intent = new Intent(this, ViewSubCatagoryActivity.class);
                intent.putExtra("mainID",t5.getText().toString().split("\\n")[1]);
                startActivity(intent);
                break;
            case R.id.T6:
                intent = new Intent(this, ViewSubCatagoryActivity.class);
                intent.putExtra("mainID",t6.getText().toString().split("\\n")[1]);
                startActivity(intent);
                break;
            case R.id.T7:
                intent = new Intent(this, ViewSubCatagoryActivity.class);
                intent.putExtra("mainID",t7.getText().toString().split("\\n")[1]);
                startActivity(intent);
                break;
        }
    }
}
