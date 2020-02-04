package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDetailMain;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderMainModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {
    private String OrderId = "", result = "";
    private EditText name, mobileno, pincode, address, city, state;
    private Button saveBtn, cancelBtn;
    OrderDetailMain orderDetailMain;
    long orderDetailID = 0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_activity_layout);

        initview();
    }

    private void initview() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Address");
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

        OrderId = getIntent().getStringExtra("ID").toString();

        name = findViewById(R.id.NAme);
        mobileno = findViewById(R.id.mobileNo);
        pincode = findViewById(R.id.pinCode);
        address = findViewById(R.id.addreSs);
        city = findViewById(R.id.cityy);
        state = findViewById(R.id.statee);

        saveBtn = findViewById(R.id.cancel);
        cancelBtn = findViewById(R.id.submit);

        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        intent = new Intent();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                final String n = name.getText().toString();
                final String d = mobileno.getText().toString();
                final String l = pincode.getText().toString();
                final String k = address.getText().toString();
                final String c = city.getText().toString();
                final String s = state.getText().toString();

                if (n.length() == 0) {
                    name.requestFocus();
                    name.setError("FIELD CANNOT BE EMPTY");
                } else if (!n.matches("[a-zA-Z ]+")) {
                    name.requestFocus();
                    name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else if (d.length() == 0) {
                    mobileno.requestFocus();
                    mobileno.setError("FIELD CANNOT BE EMPTY");
                }  else if (l.length() == 0) {
                    pincode.requestFocus();
                    pincode.setError("FIELD CANNOT BE EMPTY");
                }  else if (k.length() == 0) {
                    address.requestFocus();
                    address.setError("FIELD CANNOT BE EMPTY");
                }else if (c.length() == 0) {
                    city.requestFocus();
                    city.setError("FIELD CANNOT BE EMPTY");
                }else if (s.length() == 0) {
                    state.requestFocus();
                    state.setError("FIELD CANNOT BE EMPTY");
                }
                else {
//                    intent = new Intent(this, CameraExample.class);
//                    startActivity(intent);
                }


                break;
            case R.id.cancel:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void orderDetails(String orderMainID) {

        try {

            String Addresss = address.getText().toString().trim() + city.getText().toString().trim() + state.getText().toString().trim();
            result = new HelperApi.PostOrderDetailMain().execute(
                    String.valueOf(orderDetailID),
                    orderMainID.toString(),
                    SharedPrefManager.getInstance(this).getUser().getUserId().toString(),
                    "", "",
                    "",
                    "",
                    "", "", "").get();
            if (result.isEmpty()) {
                result = new HelperApi.PostOrderDetailMain().execute(
                        String.valueOf(orderDetailID),
                        orderMainID.toString(),
                        SharedPrefManager.getInstance(this).getUser().getUserId().toString(),
                        "", "",
                        "",
                        "",
                        "", "", "").get();
            } else {

                Gson gson = new Gson();
                Type listType = new TypeToken<OrderMainModel>() {
                }.getType();
                orderDetailMain = new Gson().fromJson(result, listType);
                orderDetailID = orderDetailMain.getOrderId();

//                Intent intent = new Intent(this, CameraExample.class);
//                startActivity(intent);
//                Toast.makeText(this, String.valueOf(orderMainID), Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
