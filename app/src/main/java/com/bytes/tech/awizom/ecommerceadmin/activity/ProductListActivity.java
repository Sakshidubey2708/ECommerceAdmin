package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.AppConfig;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDetailMain;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderMainModel;
import com.bytes.tech.awizom.ecommerceadmin.models.PricerequestModel;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ProductListActivity extends AppCompatActivity  {

    private String result = "",productID="";
    ProductModel productModel;
    private ProgressDialog progressDialog;

    private TextView productName,productDescription,product_idss;

    PricerequestModel pricerequestModel;
    long PriceRequestId = 0;

    OrderMainModel orderMainModel;
    OrderDetailMain orderDetailMain;
    private LinearLayout requestlayout,getAmount;
    private TextView salesPrices,DiscountPrices,stockQuantitys;
    private TextView imgelinks;
    private ImageView images;
    private Button DoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producet_adpater);
        try {
            initview();
        }catch (Exception e){
            e.printStackTrace();
        }



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
        productID = getIntent().getStringExtra("PID").toString();
        progressDialog = new ProgressDialog(this);

        progressDialog = new ProgressDialog(this);
        productName =  findViewById(R.id.productNames);
        productDescription =  findViewById(R.id.productDescriptions);
        product_idss = findViewById(R.id.product_ids);




        imgelinks = findViewById(R.id.imgelinkText);
        images = findViewById(R.id.image);
        DoneBtn = findViewById(R.id.BtnDone);
        requestlayout = findViewById(R.id.requestlayoutlayer);
        getAmount = findViewById(R.id.showAmounts);

        salesPrices = findViewById(R.id.salesPrice);
        DiscountPrices = findViewById(R.id.DiscountPrice);
        stockQuantitys = findViewById(R.id.stockQuantity);
        getProductList();
    }

    private void getProductList() {
        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();

            result = new HelperApi.GetSingleProductList().execute(productID.toString()).get();
            if (result.isEmpty()) {

                progressDialog.dismiss();
            } else {
                if (result.isEmpty()) {
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                    Gson gson = new Gson();
                    Type listType = new TypeToken<ProductModel>() {
                    }.getType();
                    productModel = new Gson().fromJson(result, listType);
                    Log.d("Error", productModel.toString());

                    imgelinks.setText(productModel.getProImg1().toString());
                    productName.setText(productModel.getProductName().toString());
                    productDescription.setText(productModel.getDescriptions().toString());
                    product_idss.setText(String.valueOf(productModel.getProductId()));


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
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
