package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.adapter.CartAdapter;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.AmountTotalShow;
import com.bytes.tech.awizom.ecommerceadmin.models.CartModel;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDetailMain;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderMainModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartActivity extends AppCompatActivity implements View.OnClickListener , OnItemClick {

    RecyclerView recyclerView;
    private String result = "",orderMain_Id="",orderDetails_ID="";
    List<CartModel> productModelList;
   // SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout gridlayout,baglayout;
    private Button proceed;
    private ProgressDialog progressDialog;
    private AmountTotalShow amountTotalShow;
    private TextView subtotal_prices,total_amounts,shippingcharge_amounts,anyother_charge;
    ArrayList<String> mrps = new ArrayList<String>();
    private final int SPLASH_DISPLAY_DURATION = 1000;
    String AssuredString="",deliveryAddress="Raipur",productId="",quantities="",mrpPric="",dicounts="";
    OrderMainModel orderMainModel;
    long orderMainID=0,orderDetailID=0;

    OrderDetailMain orderDetailMain;
    private String[] order_main_ID;
    private String[] order_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocart_layout);
        initview();
    }

    private void initview() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Total Items");
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

        orderMain_Id = getIntent().getStringExtra("orderMainID").toString();
        orderDetails_ID = getIntent().getStringExtra("orderDetailID").toString();

        progressDialog = new ProgressDialog(this);
        try{
            gridlayout = findViewById(R.id.details);
            baglayout =  findViewById(R.id.goneBagLayout);
            proceed =findViewById(R.id.proceed);
            proceed.setOnClickListener(this);
            recyclerView = findViewById(R.id.recyclerViewCart);
           // mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutCart);

            subtotal_prices =findViewById(R.id.subtotal_price);
            total_amounts=findViewById(R.id.total_amount);
            shippingcharge_amounts =findViewById(R.id.shippingcharge_amount);
            anyother_charge=findViewById(R.id.payable_amount);


            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            if(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserId() == String.valueOf(0)){
                gridlayout.setVisibility(View.GONE);
                baglayout.setVisibility(View.VISIBLE);
                total_amounts.setVisibility(View.GONE);
                proceed.setEnabled(false);
            }else {
                getCArt();
                getAssuredTotalAmount(SharedPrefManager.getInstance(CartActivity.this).getUser().getUserId().toString());


            }
            getCArt();



        }catch (Exception e){
            e.printStackTrace();
            gridlayout.setVisibility(View.GONE);
            baglayout.setVisibility(View.VISIBLE);
            total_amounts.setVisibility(View.GONE);
            proceed.setEnabled(false);
        }
    }

    private void getCArt() {
//        postOrderMain();

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
         //   mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetCartList().execute(SharedPrefManager.getInstance(this).getUser().getUserId().toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
                gridlayout.setVisibility(View.GONE);
                baglayout.setVisibility(View.VISIBLE);
                total_amounts.setVisibility(View.GONE);
                proceed.setEnabled(false);
                progressDialog.dismiss();
            //    mSwipeRefreshLayout.setRefreshing(false);
            } else {
                //    mSwipeRefreshLayout.setRefreshing(false);
               /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CartModel>>() {
                    }.getType();
                    productModelList = new Gson().fromJson(result, listType);
                    order_main_ID = new String[productModelList.size()];
                    order_no = new String[productModelList.size()];
                   for (int i = 0; i < productModelList.size(); i++) {

                       order_no[i] = String.valueOf(productModelList.get(i).getOrderNo());

                       order_main_ID[i] = String.valueOf(productModelList.get(i).getOrderId());

                  }
                for (String s : order_main_ID) {
                    anyother_charge.setText(s);
                }
                for (String s : order_no) {
                    shippingcharge_amounts.setText(s);
                }

                    Log.d("Error", productModelList.toString());
                    CartAdapter cartAdapter= new CartAdapter(CartActivity.this, productModelList,this,orderMainID);
                    recyclerView.setAdapter(cartAdapter);
                    progressDialog.dismiss();


            }
        } catch (Exception e) {
           // mSwipeRefreshLayout.setRefreshing(false);
            e.printStackTrace();
            progressDialog.dismiss();
            gridlayout.setVisibility(View.GONE);
            baglayout.setVisibility(View.VISIBLE);
            total_amounts.setVisibility(View.GONE);
            proceed.setEnabled(false);
            progressDialog.dismiss();
        }

    }

    private void getAssuredTotalAmount(String s) {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();
            result = new HelperApi.GetCartAmountList().execute(s.toString()).get();
            progressDialog.dismiss();


        if (result.isEmpty()) {
            progressDialog.dismiss();
        } else {
            progressDialog.dismiss();
            Gson gson = new Gson();
            Type listType = new TypeToken<AmountTotalShow>() {
            }.getType();
            amountTotalShow = new Gson().fromJson(result, listType);
            if (amountTotalShow != null) {
               // subtotal_prices.setText("₹" + String.valueOf(amountTotalShow.getTotalAssuredPriceINR()) );

                Double  discountamount;
                discountamount = Double.parseDouble(String.valueOf(amountTotalShow.getMRPINR()));
                int i = Math.round(Float.parseFloat(discountamount.toString()));
                subtotal_prices.setText(String.valueOf(i) + ".00");
                total_amounts.setText( "Total Amount =" +String.valueOf(i) + ".00");
            }
        }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.proceed:
                    postOrderMain();
                break;
        }
    }

    private  void postOrderMain(){


        try {
            result = new HelperApi.PostOrderMain().execute(
                    anyother_charge.getText().toString(),
                    shippingcharge_amounts.getText().toString(),
                    SharedPrefManager.getInstance(this).getUser().getUserId().toString(),
                    "","",subtotal_prices.getText().toString().split("\\.")[0],
                    "","",SharedPrefManager.getInstance(this).getUser().getSubsciberID(),"1").get();


            if (result.isEmpty()) {
                result = new HelperApi.PostOrderMain().execute(
                        anyother_charge.getText().toString(),
                        shippingcharge_amounts.getText().toString(),
                        SharedPrefManager.getInstance(this).getUser().getUserId().toString(),
                        "","",subtotal_prices.getText().toString().split("\\.")[0],
                        "","",SharedPrefManager.getInstance(this).getUser().getSubsciberID(),"1").get();
            } else {

                    Gson gson = new Gson();
                    Type listType = new TypeToken<OrderMainModel>() {
                    }.getType();

                    orderMainModel = new Gson().fromJson(result, listType);
                    orderMainID =orderMainModel.getOrderId();



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void orderDetails(String orderMainID) {

        try {


            result = new HelperApi.PostOrderDetailMain().execute(
                    String.valueOf(orderDetailID),
                    orderMainID.toString(),
                    "",
                    "", "",
                    subtotal_prices.getText().toString().split("\\.")[0],
                    "",
                    "", "", "").get();
            if (result.isEmpty()) {
                result =  new HelperApi.PostOrderDetailMain().execute(
                        String.valueOf(orderDetailID),
                        orderMainID.toString(),
                        "",
                        "", "",
                        subtotal_prices.getText().toString().split("\\.")[0],
                        "",
                        "", "", "").get();
            } else {

                Gson gson = new Gson();
                Type listType = new TypeToken<OrderMainModel>() {
                }.getType();
                orderDetailMain = new Gson().fromJson(result, listType);
                orderDetailID = orderDetailMain.getOrderId();




            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(String value) {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("key_name5", value.toString());
//        editor.apply();

        subtotal_prices.setText(value.toString() + ".00");
        total_amounts.setText( "Total Amount =" +value.toString()+ ".00");
        Toast.makeText(getApplicationContext(),value.toString(),Toast.LENGTH_LONG).show();
    }


}
