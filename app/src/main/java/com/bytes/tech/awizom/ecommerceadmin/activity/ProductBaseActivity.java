package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.adapter.ProductChatAdapter;
import com.bytes.tech.awizom.ecommerceadmin.configure.AppConfig;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductBaseChatModel;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductBaseActivity extends AppCompatActivity {

    android.support.v7.widget.RecyclerView recyclerView;
    EditText chatbox;
    de.hdodenhof.circleimageview.CircleImageView send;
    String result = "", results = "";
    String myids;
    String subsid;
    String product_id;
    List<ProductBaseChatModel> productBaseChatModelList;
    ProductChatAdapter productChatAdapter;
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_base);
        InitView();
    }

    @SuppressLint("ResourceAsColor")
    private void InitView() {
        myids = SharedPrefManager.getInstance(ProductBaseActivity.this).getUser().getUserID();
        subsid = SharedPrefManager.getInstance(ProductBaseActivity.this).getUser().getSubscriberId();
        product_id = getIntent().getStringExtra("ProductID");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Product Base Chat");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView = findViewById(R.id.reyclerview_message_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        send = findViewById(R.id.send);
        chatbox = findViewById(R.id.chatbox);
        getchats();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chatbox.getText().toString().equals("")) {
                    try {
                        results = new HelperApi.AddProductChat().execute(chatbox.getText().toString(),
                                myids.toString(), subsid.toString(), product_id.toString(), "Customer").get();
                        if (results.isEmpty()) {
                            results = new HelperApi.AddProductChat().execute(chatbox.getText().toString(),
                                    myids.toString(), subsid.toString(), product_id.toString(), "Customer").get();
                        } else {
                            if (results.equals("1")) {
                                getchats();
                                chatbox.setText("");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void getchats() {
        try {
            result = new HelperApi.GetProductBaseChat().execute(product_id.toString(), myids.toString(), subsid.toString()).get();
            if (result.isEmpty()) {
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductBaseChatModel>>() {
                }.getType();
                productBaseChatModelList = new Gson().fromJson(result, listType);
                productChatAdapter = new
                        ProductChatAdapter(ProductBaseActivity.this, productBaseChatModelList);
                recyclerView.setAdapter(productChatAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
