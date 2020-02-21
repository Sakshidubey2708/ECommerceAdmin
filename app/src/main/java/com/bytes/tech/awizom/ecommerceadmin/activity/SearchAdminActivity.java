package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.adapter.SearchAdapter;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.SearchModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchAdminActivity extends AppCompatActivity implements View.OnClickListener {

    private String result = "";
    private AutoCompleteTextView byProduct, byBrand;
    List<SearchModel> categorylist;
    private long pid = 0,bid=0;
    Intent intent;
    private List<SearchModel> searchModels;
    private String[] brandName;
    private String[] productName;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_admin_activity_layout);
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


        byProduct=findViewById(R.id.searchByProduct);
        byBrand=findViewById(R.id.searchByBrand);

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutItems);
        recyclerView = findViewById(R.id.recyclerViewItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        t1=findViewById(R.id.T1);
        t2=findViewById(R.id.T2);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);

        byProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (byProduct.getText().length() == 1) {
                    String.valueOf(pid);
                } else {
                    String b="null";
                    getSerchByProduct();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        byBrand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (byBrand.getText().length() == 1) {
                    String.valueOf(pid);
                } else {
                    String b="null";
                    getSearchListByBrand();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSerachProduct();
                getSerachBrand();

            }
        });
        getSerachProduct();
        getSerachBrand();
    }

    private void getSerchByProduct() {
        try {
            result = new HelperApi.GetSearchByProduct().execute(byProduct.getText().toString()).get();

            if (result.isEmpty()) {

            } else {

//                Gson gson = new Gson();
//                Type listType = new TypeToken<List<SearchModel>>()  {
//                }.getType();
//                categorylist = new Gson().fromJson(result, listType);
//                if (categorylist != null) {
//                    for(int i=0;i<=categorylist.size();i++){
//                        pid = categorylist.get(i).getProductId();
//                    }
//
//                }

                mSwipeRefreshLayout.setRefreshing(false);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SearchModel>>() {
                }.getType();
                categorylist = new Gson().fromJson(result, listType);
                SearchAdapter adapter = new SearchAdapter(SearchAdminActivity.this, categorylist);
                recyclerView.setAdapter(adapter);


            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getSearchListByBrand() {
        try {
            result = new HelperApi.GetSearchByBrand().execute(byBrand.getText().toString()).get();

            if (result.isEmpty()) {

            } else {
                mSwipeRefreshLayout.setRefreshing(false);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SearchModel>>() {
                }.getType();
                categorylist = new Gson().fromJson(result, listType);
                SearchAdapter adapter = new SearchAdapter(SearchAdminActivity.this, categorylist);
                recyclerView.setAdapter(adapter);


//                Gson gson = new Gson();
//                Type listType = new TypeToken<SearchModel>() {
//                }.getType();
//                SearchModel dataOrderValue = new Gson().fromJson(result, listType);
//                if (dataOrderValue != null) {
//                    bid = dataOrderValue.getBrandId();
//                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getSerachProduct() {
        try {
            result = new HelperApi.GetSearch().execute().get();

            if (result.isEmpty()) {

            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SearchModel>>() {
                }.getType();
                searchModels = new Gson().fromJson(result, listType);
                productName = new String[searchModels.size()];
                for (int i = 0; i < searchModels.size(); i++) {
                    productName[i] = searchModels.get(i).getProductName();
                }
                adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.select_dialog_item, productName);
                byProduct.setThreshold(1);//will start working from first character
                byProduct.setAdapter(adapter1);//
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getSerachBrand() {
        try {
            result = new HelperApi.GetSearch().execute().get();

            if (result.isEmpty()) {

            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SearchModel>>() {
                }.getType();
                searchModels = new Gson().fromJson(result, listType);
                brandName = new String[searchModels.size()];
                for (int i = 0; i < searchModels.size(); i++) {
                    brandName[i] = searchModels.get(i).getBrandName();
                }
                adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.select_dialog_item, brandName);
                byBrand.setThreshold(1);//will start working from first character
                byBrand.setAdapter(adapter2);//
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getSearch() {

        try {
            result = new HelperApi.GetSearch().execute().get();
        if (result.isEmpty()) {
        } else {
               mSwipeRefreshLayout.setRefreshing(false);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SearchModel>>() {
                }.getType();
                categorylist = new Gson().fromJson(result, listType);
                SearchAdapter adapter = new SearchAdapter(SearchAdminActivity.this, categorylist);
                recyclerView.setAdapter(adapter);
        }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(buttonClick);
        switch (v.getId()){
            case R.id.T1:
                byBrand.setVisibility(View.GONE);
                byProduct.setVisibility(View.VISIBLE);
                break;
            case R.id.T2:
                byProduct.setVisibility(View.GONE);
                byBrand.setVisibility(View.VISIBLE);
                break;
        }
    }
}
