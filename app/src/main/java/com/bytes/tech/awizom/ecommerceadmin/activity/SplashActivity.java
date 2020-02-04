package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;


public class SplashActivity extends AppCompatActivity {
    boolean connected = false;
    Intent intent;
    private final int SPLASH_DISPLAY_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        initview();
    }

    private void initview() {
       checkInternet();
    }


    private void checkInternet() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                try {
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            if(SharedPrefManager.getInstance(SplashActivity.this).getUser().getUserId() == null){
                                Intent mainIntent = new Intent(SplashActivity.this,SignInActivity.class);
                                SplashActivity.this.startActivity(mainIntent);
                                SplashActivity.this.finish();
                            }else {


                                Intent mainIntent = new Intent(SplashActivity.this,RetailerHomeActivity.class);
                                SplashActivity.this.startActivity(mainIntent);
                                SplashActivity.this.finish();
                            }

                        }
                    }, SPLASH_DISPLAY_DURATION);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                connected = true;

            } else {
                connected = false;
                Snackbar.make(getWindow().getDecorView().getRootView(), "No internet connection, retry", Snackbar.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
