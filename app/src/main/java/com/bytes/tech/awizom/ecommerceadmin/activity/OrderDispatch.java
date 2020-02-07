package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDispatchDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class OrderDispatch extends AppCompatActivity implements View.OnClickListener {

    private String result="",oid="";
    private OrderDispatchDetail orderDispatchDetails;

    private TextView FirmName,FirstName,retailerContact,retailerEmail,retailerAddress;
    private TextView cityName,stateName,cityCode,pinCode;
    private TextView dateOfRegistration,dateOFDispatch;
    private TextView contactPersonName,contactNo,ContactEmailID,contactBusiness,ContactAddress,ContactPincode;
    private TextView trancationDate,driverName,driver_Contact,dates,vehicle_no,vehicle_address,dispatchIds;

    private  TextView oidTextView,gstinTextView;
    private Button downloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_dispatch_view);
        try {

            initview();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void initview() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Detail");
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

        oid = getIntent().getStringExtra("OId").toString();


        oidTextView = findViewById(R.id.orderNo);
        gstinTextView = findViewById(R.id.gstinNo);

        FirmName = findViewById(R.id.FirmNAme);
        FirstName = findViewById(R.id.FirmownerName);
        retailerContact = findViewById(R.id.FirmownerContact);
        retailerEmail = findViewById(R.id.FirmEmail);
        retailerAddress = findViewById(R.id.FirmAddress);

        cityName = findViewById(R.id.CityName);
        stateName = findViewById(R.id.StateNAme);
        cityCode = findViewById(R.id.CityCode);
        pinCode = findViewById(R.id.PinCode);

        dateOfRegistration = findViewById(R.id.Registrationdate);
        dateOFDispatch = findViewById(R.id.Dispatch_Date);
        contactPersonName = findViewById(R.id.ContactPersonName);
        contactNo = findViewById(R.id.ContactNo);
        ContactEmailID = findViewById(R.id.ContactEmail);
        contactBusiness = findViewById(R.id.business);
        ContactAddress = findViewById(R.id.ContactPersonAdress);
        ContactPincode = findViewById(R.id.CPPinCode);


        trancationDate = findViewById(R.id.Date);
        driverName = findViewById(R.id.DriverName);
        dispatchIds = findViewById(R.id.dispatchId);
        driver_Contact = findViewById(R.id.driverContact);
        dates = findViewById(R.id.date);
        vehicle_no = findViewById(R.id.Vehicle_no);
        vehicle_address = findViewById(R.id.Vehicle_address);

        downloadBtn = findViewById(R.id.download);
        downloadBtn.setOnClickListener(this);


        method();
    }

    private void method() {

        try {

            result = new HelperApi.GetMyOrderDispatchDetails().execute(oid.toString()).get();
            if (result.isEmpty()) {

            } else {

                Gson gson = new Gson();
                Type listType = new TypeToken<OrderDispatchDetail>() {
                }.getType();
                orderDispatchDetails = new Gson().fromJson(result, listType);
                Log.d("Error", orderDispatchDetails.toString());


                    try{


                        FirmName.setText(orderDispatchDetails.getFirmName().toString());
                        FirstName.setText(orderDispatchDetails.getRetailFName().toString()+ " "
                                +orderDispatchDetails.getRetailLName().toString());

                        retailerContact.setText(orderDispatchDetails.getRetailCon().toString());
                        retailerEmail.setText(orderDispatchDetails.getEmail().toString());
                        retailerAddress.setText(orderDispatchDetails.getRetailAddress().toString());


                        cityName.setText(orderDispatchDetails.getCityName().toString());
                        stateName.setText(orderDispatchDetails.getStateName().toString());
                        cityCode.setText(orderDispatchDetails.getCityName().toString());
                        pinCode.setText(String.valueOf(orderDispatchDetails.getPincode()));
                        dateOfRegistration.setText(orderDispatchDetails.getDateOfRegistration().toString());
                        dateOFDispatch.setText(orderDispatchDetails.getDispatchDate().toString());



                        ContactEmailID.setText(orderDispatchDetails.getEmailId().toString());
                        contactNo.setText(String.valueOf(orderDispatchDetails.getContactNo()));
                        contactBusiness.setText(orderDispatchDetails.getBusiness().toString());
                        ContactAddress.setText(orderDispatchDetails.getAddress().toString());
                        ContactPincode.setText(String.valueOf(orderDispatchDetails.getPincode()));


                        trancationDate.setText(orderDispatchDetails.getDate().toString());
                        driverName.setText(orderDispatchDetails.getDriverName().toString());
                        driver_Contact.setText(String.valueOf(orderDispatchDetails.getDriverContact()));
                        dates.setText(orderDispatchDetails.getDate().toString());
                        vehicle_no.setText(orderDispatchDetails.getVehicleNo().toString());
                        vehicle_address.setText(orderDispatchDetails.getVehicleAddress());
                        contactPersonName.setText(orderDispatchDetails.getContactPerson().toString());
                        dispatchIds.setText(String.valueOf(orderDispatchDetails.getDispatchID()));
                        oidTextView.setText(String.valueOf(orderDispatchDetails.getOrderID()));
                        gstinTextView.setText(orderDispatchDetails.getGstinNo());
                    }catch (Exception w){
                        w.printStackTrace();
                    }




            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.10.27:25402//Admin/Admin/DispatchOrderReport?DispatchID=" + dispatchIds.getText().toString().trim()));
                startActivity(browserIntent);
                break;
        }
    }
}
