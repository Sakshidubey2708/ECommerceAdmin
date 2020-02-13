package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.bytes.tech.awizom.ecommerceadmin.R;

import java.util.List;

public class EnquiryActivity extends AppCompatActivity {

    private TextView by_email, by_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_enquiry);

        ////
        ActivityCompat.requestPermissions(EnquiryActivity.this,
                new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        initialize();

    }


    private void initialize() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Enquiry");

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

        by_email = findViewById(R.id.emailEnquiry);
        by_mobile = findViewById(R.id.mobileEnquiry);

        by_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
                sendNotification();
            }
        });

        by_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeCall(v.getContext(),by_mobile.getText().toString().trim());
                cancelNotification();
            }
        });
    }

    @SuppressLint("LongLogTag")
    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {by_email.getText().toString().trim()};
        String[] CC = {"sharadsatyam1@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:sharadsatyam1@gmail.com"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "For Enquiry");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EnquiryActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public void makeCall(Context context, String number) {
        if (!TextUtils.isEmpty(number)) {
            Intent intents = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            PackageManager manager = context.getPackageManager();
            List<ResolveInfo> infos = manager.queryIntentActivities(intents, 0);
            if (infos.size() > 0) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                startActivity(intents);
            } else {
                Toast.makeText(context, "Please install a dialer app", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Please add phone number to make a call", Toast.LENGTH_LONG).show();
        }
    }


    public void sendNotification() {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.journaldev.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_active_black_24dp));
        builder.setContentTitle("Sp Client Notification");
        builder.setContentText("Email send On");
        builder.setSubText("This is example of Notification.");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }

    public void cancelNotification() {

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
        nMgr.cancel(1);


    }
}
