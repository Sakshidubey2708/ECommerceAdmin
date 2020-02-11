package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderMainModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MyOrderTrackingActivity extends AppCompatActivity {


    private com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar verticalSeekBar;
    int trackinProgress = 0, secondaryProgress = 0;
    OrderMainModel orderMainModel;
    private String OrderId="";
    private TextView placeOrders,AcceptOrders,UnderProcessOrders,DispatchOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eccccxx);
        initView();
    }

    private void initView() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tracking");
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

        verticalSeekBar = findViewById(R.id.capacity_seek);

        placeOrders = findViewById(R.id.orderplaced);
//        AcceptOrders = findViewById(R.id.orderAccept);
//        UnderProcessOrders = findViewById(R.id.UnderProcess);
 //       DispatchOrders = findViewById(R.id.dispatch);


        OrderId = getIntent().getStringExtra("OId").toString();
        GetTracking();

        if (Build.VERSION.SDK_INT >= 11) {

            ObjectAnimator animation = ObjectAnimator.ofInt(verticalSeekBar, "progress", trackinProgress);
            verticalSeekBar.setSecondaryProgress(secondaryProgress);
            animation.setDuration(1050); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
            verticalSeekBar.setEnabled(false);

        } else {
            verticalSeekBar.setProgress(3);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void GetTracking() {

        try {
            String result = new HelperApi.GETMyOrderTracking().execute(OrderId.toString(),SharedPrefManager.getInstance(this).getUser().getUserID()).get();
            Gson gson = new Gson();
            Type listType = new TypeToken<OrderMainModel>() {
            }.getType();
            orderMainModel = new Gson().fromJson(result, listType);
            String vals="1";

            if (orderMainModel.getOrderDispatch().equals(vals.toString())) {
                trackinProgress = 4;
                secondaryProgress = trackinProgress + 1;
                placeOrders.setTextColor(R.color.green);

            } else if (orderMainModel.getUnderProccess().equals(vals.toString())) {
                trackinProgress = 3;
                secondaryProgress = trackinProgress + 1;

            } else if (orderMainModel.getOrderAccept().equals(vals.toString())) {
                trackinProgress = 2;
                secondaryProgress = trackinProgress + 1;
            } else if (orderMainModel.getPlaceOrder().equals(vals.toString())) {
                trackinProgress = 1;
                secondaryProgress = trackinProgress + 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
