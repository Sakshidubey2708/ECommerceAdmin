package com.bytes.tech.awizom.ecommerceadmin.chat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.activity.MainActivity;
import com.bytes.tech.awizom.ecommerceadmin.activity.SignInActivity;
import com.bytes.tech.awizom.ecommerceadmin.activity.SplashActivity;
import com.bytes.tech.awizom.ecommerceadmin.adapter.ProductListAdapter;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.ChatModel;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductModel;
import com.bytes.tech.awizom.ecommerceadmin.models.UploadBuilty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatActivity extends AppCompatActivity {

    LinearLayout coordinatorLayout;
    Snackbar snackbar;
    Integer listsize;
    ImageView nointernet;
    ImageView sendmsg;
    String result="";
    List<ChatModel> chatModels;
    RecyclerView recyclerView;
    EditText typeMessage;
    private final int SPLASH_DISPLAY_DURATION = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_layout);

        sendmsg = (ImageView) findViewById(R.id.send);
        nointernet=findViewById(R.id.no_internet);
        coordinatorLayout = (LinearLayout) findViewById(R.id.coordinator);
        snackbar = Snackbar.make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ChatActivity.this,
                                MainActivity.class);

                        startActivity(intent);
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        checkInternet();
//        Timer timer = new Timer ();
//        TimerTask hourlyTask = new TimerTask () {
//            @Override
//            public void run () {
//                recyclerView.getRecycledViewPool().clear();
//                initView();
//            }
//        };
//
//// schedule the task to run starting now and then every hour...
//        timer.schedule (hourlyTask, 0l, 10000);
    }
    private void checkInternet() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            initView();

        } else {
            // Toast.makeText(getApplicationContext(), "Internet is off", Toast.LENGTH_SHORT).show();
            nointernet.setVisibility(View.VISIBLE);
            snackbar.show();
        /*    connected = false;
            snackbar.show();*/
        }
    }

    private void initView() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Chat");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        typeMessage = findViewById(R.id.chatcontain);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, true);
        //  recyclerLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(recyclerLayoutManager);


        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( SharedPrefManager.getInstance(ChatActivity.this).getUser().getUserID() == null){
                    final Dialog dialog = new Dialog(ChatActivity.this);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dialog.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.BOTTOM;
                    lp.windowAnimations = R.style.DialogAnimation;
                    dialog.getWindow().setAttributes(lp);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.bottom_slide_dailog);


                    ImageView closebtn = dialog.findViewById(R.id.close);
                    TextView loginview = dialog.findViewById(R.id.loginClickevent);

                    loginview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(ChatActivity.this, SignInActivity.class);
                            startActivity(i);
                        }
                    });
                    closebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });


                    closebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }else {

                    try {
                        result = new HelperApi.PostChating().execute(
                                SharedPrefManager.getInstance(ChatActivity.this).getUser().getUserID(),
                                typeMessage.getText().toString()).get();
                        if (result.isEmpty()) {
                            result = new HelperApi.PostChating().execute(
                                    SharedPrefManager.getInstance(ChatActivity.this).getUser().getUserID(),
                                    typeMessage.getText().toString()).get();
                        } else {
                            typeMessage.setText("");
                            recyclerView.getRecycledViewPool().clear();
                            getChat();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getChat();
    }
    public void getChat() {
        try {
            result = new HelperApi.GetChat().execute(SharedPrefManager.getInstance(this).getUser().getUserID()).get();
            if (result.isEmpty()) {
            } else {
                /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ChatModel>>() {
                }.getType();
                chatModels = new Gson().fromJson(result, listType);
                Log.d("Error", chatModels.toString());
                ChatAdapter chatAdapter= new ChatAdapter(ChatActivity.this, chatModels);
                recyclerView.setAdapter(chatAdapter);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
