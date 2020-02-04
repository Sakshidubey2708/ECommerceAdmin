package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.adapter.SliderAdapter;
import com.bytes.tech.awizom.ecommerceadmin.chat.ChatActivity;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.AddUser;
import com.bytes.tech.awizom.ecommerceadmin.models.StockMain;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent intent= new Intent();
    ViewPager viewPager;
    TabLayout indicator;
    List<Integer> imglist;
    List<Integer> color;
    List<String> colorName;
    TextView offerTextViews;
    List<StockMain> stockMains;
    private TextView searchEdits;
    private String result="";
    private AddUser  addUser;
    private TextView userNameIDs,catagories;
    GridView gridView;
    private TextView notoifye;
    private ImageView notificationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(SharedPrefManager.getInstance(MainActivity.this).getUser().getFirmName());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        MenuItem target = menu.findItem(R.id.nav_login);
        MenuItem target2 = menu.findItem(R.id.nav_logout);
        userNameIDs = headerView.findViewById(R.id.userNameID);
        if(SharedPrefManager.getInstance(this).getUser().getUserId() != null){
             target.setVisible(false);
             target2.setVisible(true);
        }else {
             target.setVisible(true);
             target2.setVisible(false);
        }

//       FirebaseInstanceId.getInstance().getToken().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
        FirebaseMessaging.getInstance().subscribeToTopic("Product");
        initview();
    }

    private void initview() {

        viewPager = findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);
        searchEdits = findViewById(R.id.searchEdit);
        notoifye = findViewById(R.id.cart_badge);
        notificationIcon = findViewById(R.id.notification);
        offerTextViews =findViewById(R.id.offerTextView);
        gridView = (GridView) findViewById(R.id.gridview);
        catagories = findViewById(R.id.catagory);
        try{
            userNameIDs.setText(SharedPrefManager.getInstance(this).getUser().getUserName().toString());
            catagories.setText(SharedPrefManager.getInstance(this).getUser().getCategory());
            catagories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this,StockActivity.class);
                    startActivity(intent);
                }
            });
            getNotificationCount();
        }catch (Exception e){
            e.printStackTrace();
        }


        searchEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,NotificationListActivity.class);
                startActivity(intent);
            }
        });


        color = new ArrayList<>();
        imglist = new ArrayList<Integer>();
        imglist.add(R.drawable.ec);
        imglist.add(R.drawable.m1);
        imglist.add(R.drawable.stt);
        color.add(Color.RED);
        color.add(Color.GREEN);
        color.add(Color.BLUE);
        colorName = new ArrayList<>();
        colorName.add("");
        colorName.add("");
        colorName.add("");

        viewPager.setAdapter(new SliderAdapter(this, color, colorName, imglist));
        indicator.setupWithViewPager(viewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        final Handler handler = new Handler();
        final int[] colors = {Color.BLUE, Color.RED};
        final int[] i = new int[1];
        Runnable runnable = new Runnable () {
            @Override
            public void run() {
                i[0] = i[0] % colors.length;
                offerTextViews.setTextColor(colors[i[0]]);
                i[0]++;
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);

    }

    private void getNotificationCount() {
        try {

            result = new HelperApi.GETNotificationCount().execute(SharedPrefManager.getInstance(this).getUser().getSubsciberID()).get();
            if (result.isEmpty()) {

            } else {
                notoifye.setText(result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (this != null) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < color.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_viewClients) {
            startActivity(intent=new Intent(this,ViewUsers.class));
        }else if (id == R.id.nav_searchActivity) {
            startActivity(intent=new Intent(this,SearchActivity.class));
        }else if (id == R.id.nav_addClients) {
            startActivity(intent=new Intent(this,AddClient.class));
        }
        else if (id == R.id.nav_productDeatails) {
            startActivity(intent=new Intent(this,ProductListActivity.class));
        }else if (id == R.id.nav_cart) {
            startActivity(intent=new Intent(this,CartActivity.class));
        }else  if (id == R.id.nav_Chat) {
             startActivity(intent=new Intent(this,ChatActivity.class));
         }else  if (id == R.id.nav_ordrTrack) {
             startActivity(intent=new Intent(this,OrdertrackingActivity.class));

         }else  if (id == R.id.nav_builtyUpload) {
             startActivity(intent=new Intent(this,BuiltiUploadActivity.class));

         } else if (id == R.id.nav_userpermission) {

            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            alertbox.setIcon(R.drawable.ic_warning_black_24dp);
            alertbox.setTitle("Working process....");
            alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            alertbox.show();
         }else if (id == R.id.nav_notification) {

            startActivity(intent=new Intent(this,NotificationListActivity.class));
        }else if (id == R.id.nav_orderHistory) {

            startActivity(intent=new Intent(this,MyOrderActivity.class));
        }else if (id == R.id.nav_orderRunning) {

            startActivity(intent=new Intent(this,MyRunningOrderActivity.class));
        }else if (id == R.id.nav_login) {
             if(SharedPrefManager.getInstance(this).getUser().getUserId() == null){
                 startActivity(intent=new Intent(this,SignInActivity.class));
             }else {
                 AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                 alertbox.setIcon(R.drawable.ic_warning_black_24dp);
                 alertbox.setTitle("You have already logged In");
                 alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface arg0, int arg1) {

                     }
                 });

                 alertbox.show();
             }

        }else if (id == R.id.nav_logout) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
            alertbox.setIcon(R.drawable.ic_warning_black_24dp);
            alertbox.setTitle("Do You Want To Logout?");
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // finish used for destroyed activity


                    SharedPrefManager.getInstance(MainActivity.this).logout();
                    startActivity(intent=new Intent(MainActivity.this,SignInActivity.class));
                }
            });

            alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            alertbox.show();

            //startActivity(intent=new Intent(this,SignInActivity.class));
        }else if (id == R.id.nav_weblink) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
            alertbox.setIcon(R.drawable.ic_bookmark_black_24dp);
            alertbox.setTitle("Do You Want To Open?");
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // finish used for destroyed activity

                intent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);

                }
            });

            alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            alertbox.show();

            //startActivity(intent=new Intent(this,SignInActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
            alertbox.setIcon(R.drawable.ic_warning_black_24dp);
            alertbox.setTitle("Do You Want To Exit Programme?");
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // finish used for destroyed activity
                    finishAffinity();
                    System.exit(0);
                }
            });

            alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // Nothing will be happened when clicked on no button
                    // of Dialog
                }
            });
            alertbox.show();
        }

        return super.onKeyDown(keyCode, event);
    }
    private void GetSubscriberUsers(String userID) {
        try {

            result = new HelperApi.GetSubscriberUsers().execute(userID.toString()).get();
            if (result.isEmpty()) {
            } else {
                Gson gson = new Gson();
                AddUser jsonbody = gson.fromJson(result, AddUser.class);
                AddUser us = new AddUser();
                us.SubscriberID = jsonbody.SubscriberID;
                SharedPrefManager.getInstance(MainActivity.this).addsubscribeUser(us);
                Log.d("LOGSubscride Id",SharedPrefManager.getInstance(MainActivity.this).getaddsubscribeUser().SubscriberID);

            }
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
