package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.configure.AccountControlerHelper;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.UserLogin;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class login extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    String TAG = "Check", result = "";
    EditText emails, passWord, contact;

    ImageView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        loginButton = findViewById(R.id.loginbtn);
        loginButton.setOnClickListener(this);
        emails = findViewById(R.id.email);
        passWord = findViewById(R.id.pasword);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbtn:
                String e = emails.getText().toString();
                String p = passWord.getText().toString();
                if (e.isEmpty()) {
                    emails.requestFocus();
                    emails.setError("Required");
                } else if (p.isEmpty()) {
                    passWord.requestFocus();
                    passWord.setError("Required");
                } else {
                    loginEvent();
                }

                break;
        }
    }

    private void loginEvent() {

        try {
            progressDialog.setMessage("loading...");
            progressDialog.show();

            result = new AccountControlerHelper.PostLogin().execute(emails.getText().toString(), passWord.getText().toString()).get();


            if (result.isEmpty()) {
                progressDialog.dismiss();
                result = new AccountControlerHelper.PostLogin().execute(emails.getText().toString(), passWord.getText().toString()).get();
            } else {

                try {
                    progressDialog.dismiss();
                    Gson gson = new Gson();
                    UserLogin jsonbody = gson.fromJson(result, UserLogin.class);
                    boolean corrects = jsonbody.getCorrect();
                    if (corrects) {
                        UserLogin us = new UserLogin();

                        us.UserName = jsonbody.UserName;
                        us.UserID = jsonbody.UserID;
                        us.SubscriberId = jsonbody.SubscriberId;
                        us.FirmName = jsonbody.FirmName;
                        us.Category = jsonbody.Category;
                        us.Business = jsonbody.Business;
                        us.logedInUserName = jsonbody.logedInUserName;
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(us);

                        Snackbar.make(getWindow().getDecorView().getRootView(), "Login Successfull", Snackbar.LENGTH_LONG).show();
                        /*    Toast.makeText(login.this, result.toString(), Toast.LENGTH_SHORT).show();*/

                        Intent i = new Intent(this, RetailerHomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    } else {
                        Snackbar.make(getWindow().getDecorView().getRootView(), "Username or Password are not coorect", Snackbar.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                }

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

