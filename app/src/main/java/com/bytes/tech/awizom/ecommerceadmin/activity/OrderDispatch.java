package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ActivityOptions;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDispatchDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class OrderDispatch extends AppCompatActivity {

    private String result="",oid="";
    private List<OrderDispatchDetail> orderDispatchDetails;

    private TextView FirmName,FirstName,retailerContact,retailerEmail,retailerAddress;
    private TextView cityName,stateName,cityCode,pinCode;
    private TextView dateOfRegistration,dateOFDispatch;
    private TextView contactPersonName,contactNo,ContactEmailID,contactBusiness,ContactAddress,ContactPincode;
    private TextView trancationDate,driverName,driver_Contact,dates,vehicle_no,vehicle_address;

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
        driver_Contact = findViewById(R.id.driverContact);
        dates = findViewById(R.id.date);
        vehicle_no = findViewById(R.id.Vehicle_no);
        vehicle_address = findViewById(R.id.Vehicle_address);


        method();
    }

    private void method() {

        try {

            result = new HelperApi.GetMyOrderDispatchDetails().execute(oid.toString()).get();
            if (result.isEmpty()) {

            } else {

                Gson gson = new Gson();
                Type listType = new TypeToken<List<OrderDispatchDetail>>() {
                }.getType();
                orderDispatchDetails = new Gson().fromJson(result, listType);
                Log.d("Error", orderDispatchDetails.toString());

                for(int i=0; i<=orderDispatchDetails.size(); i++){

                    FirmName.setText(orderDispatchDetails.get(i).getFirmName().toString());
                    FirstName.setText(orderDispatchDetails.get(i).getRetailFName().toString()+ " "
                    +orderDispatchDetails.get(i).getRetailLName().toString());

                    retailerContact.setText(orderDispatchDetails.get(i).getRetailCon().toString());
                    retailerEmail.setText(orderDispatchDetails.get(i).getEmail().toString());
                    retailerAddress.setText(orderDispatchDetails.get(i).getRetailAddress().toString());


                    cityName.setText(orderDispatchDetails.get(i).getCityName().toString());
                    stateName.setText(orderDispatchDetails.get(i).getStateName().toString());
                    cityCode.setText(orderDispatchDetails.get(i).getCityName().toString());
                    pinCode.setText(String.valueOf(orderDispatchDetails.get(i).getPincode()));
                    dateOfRegistration.setText(orderDispatchDetails.get(i).getDateOfRegistration().toString());
                    dateOFDispatch.setText(orderDispatchDetails.get(i).getDispatchDate().toString());


                    contactPersonName.setText(orderDispatchDetails.get(i).getCotactPerson().toString());
                    ContactEmailID.setText(orderDispatchDetails.get(i).getEmailId().toString());
                    contactNo.setText(String.valueOf(orderDispatchDetails.get(i).getContactNo()));
                    contactBusiness.setText(orderDispatchDetails.get(i).getBusiness().toString());
                    ContactAddress.setText(orderDispatchDetails.get(i).getAddress().toString());
                    ContactPincode.setText(String.valueOf(orderDispatchDetails.get(i).getPincode()));


                    trancationDate.setText(orderDispatchDetails.get(i).getDate().toString());
                    driverName.setText(orderDispatchDetails.get(i).getDriverName().toString());
                    driver_Contact.setText(String.valueOf(orderDispatchDetails.get(i).getDriverContact()));
                    dates.setText(orderDispatchDetails.get(i).getDate().toString());
                    vehicle_no.setText(orderDispatchDetails.get(i).getVehicleNo().toString());
                    vehicle_address.setText(orderDispatchDetails.get(i).getVehicleAddress());

                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
