package com.bytes.tech.awizom.ecommerceadmin.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;


public class MyfirebaseReceiver extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());

        FirebaseMessaging.getInstance().subscribeToTopic("Product");
    }




    @Override
    public void onNewIntent(Intent intent) {




    }


}