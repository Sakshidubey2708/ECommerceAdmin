package com.bytes.tech.awizom.ecommerceadmin.configure;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytes.tech.awizom.ecommerceadmin.models.UserLogin;


public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedprefretrofit";
    private static final String KEY_USER_TOKEN = "accesstoken";
    private static final String KEY_USER_EMAIL = "username";
    private static final String KEY_USER_ID = "userid";

    private static final String SHARED_PREF_assured_amt = "dhh";


    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(UserLogin user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_TOKEN, user.UserName);
        editor.putString(KEY_USER_ID, user.UserID);
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_TOKEN, null) != null)
            return true;
        return false;
    }
    public UserLogin getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        UserLogin users=new UserLogin();
        users.UserName   =  sharedPreferences.getString(KEY_USER_EMAIL, null);
        users.UserID   =  sharedPreferences.getString(KEY_USER_ID, null);
        return  users;
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
