package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.AppConfig;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDetailMain;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderMainModel;
import com.bytes.tech.awizom.ecommerceadmin.models.PricerequestModel;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductModel;
import com.bytes.tech.awizom.ecommerceadmin.models.StockMain;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class StockActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    private String result = "",productIDs="";
    private ProductModel stockMains;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressDialog progressDialog;
    private int k =0;
    private long orderMainIDs=0,orderMainID=0,orderDetailID=0,orderDetailIDs=0;

    private TextView productName,productDescription,product_idss;
    private Button request,show_price;
    PricerequestModel pricerequestModel;
    long PriceRequestId = 0;

    OrderMainModel orderMainModel;
    OrderDetailMain orderDetailMain;
    private LinearLayout requestlayout,getAmount;
    private TextView salesPrices,DiscountPrices,stockQuantitys;
    private TextView imgelinks,chatstarts;
  //  private TextView stockin,stockout;
    private ImageView images;
    private Button DoneBtn;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stockgrid);
            initview();
    }

    private void initview() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Product");
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
        productIDs = getIntent().getStringExtra("productID").toString();

        progressDialog = new ProgressDialog(this);
        productName =  findViewById(R.id.productNames);
        productDescription =  findViewById(R.id.productDescriptions);
        product_idss = findViewById(R.id.product_ids);
        request = findViewById(R.id.requestBtn);
        show_price = findViewById(R.id.btnShow);
        chatstarts = findViewById(R.id.chatstart);
        show_price.setOnClickListener(this);
        request.setOnClickListener(this);

//        stockin =  findViewById(R.id.stockIN);
//        stockout =  findViewById(R.id.stockOut);

        imgelinks = findViewById(R.id.imgelinkText);
        images = findViewById(R.id.image);
        DoneBtn = findViewById(R.id.BtnDone);
        requestlayout = findViewById(R.id.requestlayoutlayer);
        getAmount = findViewById(R.id.showAmounts);
        salesPrices = findViewById(R.id.salesPrice);
        DiscountPrices = findViewById(R.id.DiscountPrice);
        stockQuantitys = findViewById(R.id.stockQuantity);
        chatstarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  v.startAnimation(buttonClick);*/


              Intent intent=new Intent(StockActivity.this,ProductBaseActivity.class);
              intent.putExtra("ProductID",productIDs.toString());
              startActivity(intent);
            }
        });

        getProductList();
        getPrricerequestTable();
    }



    private void getProductList() {
        try {
            result = new HelperApi.GetSingleProductsForHomeBySubscriberIDThrough().execute(productIDs.toString()).get();
            if (result.isEmpty()) {
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<ProductModel>() {
                }.getType();
                stockMains = new Gson().fromJson(result, listType);
               // Toast.makeText(this,stockMains.toString(),Toast.LENGTH_LONG).show();
                productName.setText(stockMains.getProductName().toString());
                productDescription.setText(stockMains.getDescriptions().toString());
                product_idss.setText(String.valueOf(stockMains.getProductId()));
                imgelinks.setText(stockMains.getProImg1().toString());
//                stockin.setText(String.valueOf(stockMains.getStockInQuantity()));
//                stockout.setText(String.valueOf(stockMains.getStockOutQuantity()));
                String url = AppConfig.BASE_URL+"/" +imgelinks.getText().toString();
                if (url != null) {
                    Glide.with(images)
                            .load(url)
                            .centerCrop()
                            .placeholder(R.mipmap.product)
                            .error(R.mipmap.product)
                            .fallback(R.mipmap.product)
                            .into(images) ;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getPrricerequestTable() {
        try {
            result = new HelperApi.GETPriceRequests().execute(product_idss.getText().toString(),
                    SharedPrefManager.getInstance(StockActivity.this).getUser().getUserID()).get();
            if (result.isEmpty()) {
                result = new HelperApi.GETPriceRequests().execute(product_idss.getText().toString()).get();
            } else {
                Gson gsons = new Gson();
                Type listType1 = new TypeToken<PricerequestModel>() {
                }.getType();
                pricerequestModel = new Gson().fromJson(result, listType1);
                Log.d("Error", pricerequestModel.toString());

                salesPrices.setText(String.valueOf(pricerequestModel.getSalePrice()));
                salesPrices.setTextColor(Color.parseColor("#666666"));
                DiscountPrices.setText(String.valueOf(pricerequestModel.getSaleDiscount()));
                DiscountPrices.setTextColor(Color.parseColor("#666666"));
                stockQuantitys.setText(String.valueOf(0));
                stockQuantitys.setTextColor(Color.parseColor("#666666"));


                if(pricerequestModel.isRequest == false){
                    requestlayout.setVisibility(View.VISIBLE);
                    getAmount.setVisibility(View.GONE);
                }else {
                    requestlayout.setVisibility(View.GONE);
                    getAmount.setVisibility(View.VISIBLE);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(buttonClick);
        switch (v.getId()){
            case R.id.BtnDone:
                Intent i = new Intent(StockActivity.this,RetailerHomeActivity.class);
                startActivity(i);
                break;
            case R.id.requestBtn:

                    final Dialog dialogs = new Dialog(this);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dialogs.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.BOTTOM;
                    lp.windowAnimations = R.style.DialogAnimation;
                    dialogs.getWindow().setAttributes(lp);
                    dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogs.setCancelable(false);
                    dialogs.setContentView(R.layout.reuest_dailog);


                   final TextView productNames = dialogs.findViewById(R.id.productName);
                   final ImageView closeBtn = dialogs.findViewById(R.id.close);
                    final EditText quantitys = dialogs.findViewById(R.id.quantity);
                    Button request_send = dialogs.findViewById(R.id.requestSend);
                    Button request_cancel = dialogs.findViewById(R.id.requestcancel);
                    productNames.setText(productName.getText().toString());

                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.startAnimation(buttonClick);
                        dialogs.dismiss();
                    }
                });

                    request_send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.startAnimation(buttonClick);
                            try {
                                result = new HelperApi.PostSentPriceRequest().execute(
                                        product_idss.getText().toString(),
                                        SharedPrefManager.getInstance(StockActivity.this).getUser().getUserID().toString(),
                                        product_idss.getText().toString().trim(),
                                        "PriceRequest", SharedPrefManager.getInstance(StockActivity.this).getUser().getSubscriberId(),
                                        quantitys.getText().toString()).get();

                                if (result.isEmpty()) {

                                } else {
                                    dialogs.dismiss();
                                    Gson gson = new Gson();
                                    Type listType = new TypeToken<PricerequestModel>() {
                                    }.getType();
                                    pricerequestModel = new Gson().fromJson(result, listType);
                                      PriceRequestId = pricerequestModel.getPriceRequestId();
                                    stockQuantitys.setText(String.valueOf(pricerequestModel.getReqQty()).split("//.")[0]);
                                    //  Toast.makeText(this,String.valueOf(PriceRequestId),Toast.LENGTH_LONG).show();

                                    requestlayout.setVisibility(View.GONE);
                                    getAmount.setVisibility(View.VISIBLE);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    request_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.startAnimation(buttonClick);
                            dialogs.dismiss();
                        }
                    });
                    dialogs.show();



                break;
            case R.id.btnShow:
                try {
                    result = new HelperApi.GETPriceRequests().execute(product_idss.getText().toString(),
                            SharedPrefManager.getInstance(this).getUser().getUserID()).get();
                    if (result.isEmpty()) {
                        result = new HelperApi.GETPriceRequests().execute(product_idss.getText().toString(),
                                SharedPrefManager.getInstance(this).getUser().getUserID()).get();
                    } else {
                        Gson gsons = new Gson();
                        Type listType1 = new TypeToken<PricerequestModel>() {
                        }.getType();
                        pricerequestModel = new Gson().fromJson(result, listType1);
                        Log.d("Error", pricerequestModel.toString());


                        final Dialog dialog = new Dialog(this);
                        WindowManager.LayoutParams lp1 = new WindowManager.LayoutParams();
                        lp1.copyFrom(dialog.getWindow().getAttributes());
                        lp1.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp1.gravity = Gravity.BOTTOM;
                        lp1.windowAnimations = R.style.DialogAnimation;
                        dialog.getWindow().setAttributes(lp1);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.pricing_id_show);

                        final TextView discountPrices = dialog.findViewById(R.id.discountPrice);
                        final TextView salePrices = dialog.findViewById(R.id.salePrice);
                        final EditText quantity = dialog.findViewById(R.id.quantity);
                        Button ok = dialog.findViewById(R.id.okbtn);
                        final Button order = dialog.findViewById(R.id.orderPlace);
                        discountPrices.setText(String.valueOf(pricerequestModel.getSaleDiscount()));
                        salePrices.setText(String.valueOf(pricerequestModel.getSalePrice()));



                        if(salePrices.getText().toString().equals("0.0")){
                            order.setVisibility(View.GONE);
                            quantity.setVisibility(View.GONE);
                        }else {
                            order.setVisibility(View.VISIBLE);
                            quantity.setVisibility(View.VISIBLE);
                        }

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                v.startAnimation(buttonClick);
                                dialog.dismiss();
                            }
                        });

                        order.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                v.startAnimation(buttonClick);

                               // if(!stockin.getText().toString().equals("0")){
                                    try {
                                        k = ++k;
                                        String S= "ord"+k;

                                        result = new HelperApi.PostOrderMain().execute(
                                                String.valueOf(orderMainIDs),
                                                S.toString(),
                                                SharedPrefManager.getInstance(StockActivity.this).getUser().getUserID().toString(),
                                                "",order.getText().toString().trim(),"","","",
                                                SharedPrefManager.getInstance(StockActivity.this).getUser().getSubscriberId(),"1").get();

                                        if (result.isEmpty()) {

                                        } else {

                                            Gson gson = new Gson();
                                            Type listType = new TypeToken<OrderMainModel>() {
                                            }.getType();
                                            orderMainModel = new Gson().fromJson(result, listType);
                                            orderMainID =orderMainModel.getOrderId();

                                            if(orderMainID == 0){

                                            }else {
                                                Double  qty, ass_price,total;
                                                qty = Double.parseDouble(quantity.getText().toString());
                                                ass_price = Double.parseDouble(salePrices.getText().toString());
                                                total = qty * ass_price;

                                                salesPrices.setText(String.valueOf(ass_price));
                                                salesPrices.setTextColor(Color.parseColor("#0d0d0d"));
                                                DiscountPrices.setText(String.valueOf(pricerequestModel.getSaleDiscount()));
                                                DiscountPrices.setTextColor(Color.parseColor("#0d0d0d"));
                                                stockQuantitys.setText(String.valueOf(qty).split("//.")[0]);
                                                stockQuantitys.setTextColor(Color.parseColor("#0d0d0d"));

                                                //  Toast.makeText(StockActivity.this,"OrderMAin"+String.valueOf(orderMainID),Toast.LENGTH_LONG).show();
                                                result = new HelperApi.PostOrderDetailMain().execute(
                                                        String.valueOf(orderDetailID),
                                                        String.valueOf(orderMainID),
                                                        product_idss.getText().toString(),
                                                        salePrices.getText().toString(),quantity.getText().toString(),
                                                        total.toString(),  discountPrices.getText().toString(),
                                                        "", "",  "",  "", "").get();
                                                if(result.isEmpty()){

                                                }else {
                                                    Gson gsons = new Gson();
                                                    Type listType1 = new TypeToken<OrderDetailMain>() {
                                                    }.getType();
                                                    orderDetailMain = new Gson().fromJson(result, listType1);
                                                    result = new HelperApi.PostOrderMain().execute(
                                                            String.valueOf(orderMainID),
                                                            S.toString(),
                                                            SharedPrefManager.getInstance(StockActivity.this).getUser().getUserID().toString(),
                                                            "",order.getText().toString().trim(),
                                                            total.toString(),"","",SharedPrefManager.getInstance(StockActivity.this).getUser().getSubscriberId(),"1").get();

                                                    orderDetailIDs = orderDetailMain.getOrderId();
                                                    getPrricerequestTable();
                                                    if(!(orderMainID == 0)){
                                                        order.setVisibility(View.GONE);
                                                        quantity.setVisibility(View.GONE);
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
//                                }else {
//                                    AlertDialog.Builder alertbox = new AlertDialog.Builder(StockActivity.this);
//                                    alertbox.setIcon(R.drawable.ic_warning_black_24dp);
//                                    alertbox.setTitle("Sorry !! Stock not available.");
//                                    alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface arg0, int arg1) {
//                                            // finish used for destroyed activity
//
//                                        }
//                                    });
//
//
//                                    alertbox.show();
//                                }

                            }
                        });
                        dialog.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
