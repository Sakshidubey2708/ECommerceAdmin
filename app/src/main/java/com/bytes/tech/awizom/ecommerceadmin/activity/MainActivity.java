package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.adapter.ProductListAdapter;
import com.bytes.tech.awizom.ecommerceadmin.adapter.SliderAdapter;
import com.bytes.tech.awizom.ecommerceadmin.chat.ChatActivity;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.PricerequestModel;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductModel;
import com.bytes.tech.awizom.ecommerceadmin.models.RatingModel;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    private String result = "";
    List<ProductModel> productModel;
    List<RatingModel> ratingModels;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressDialog progressDialog;
    List<String> ratingId = new ArrayList<String>();//a
    List<String> statusRequst = new ArrayList<String>();//a
    private String TAG="hjsgdf";
    private Intent intent= new Intent();
    List<PricerequestModel> pricerequestModels;

    ViewPager viewPager;
    TabLayout indicator;
    List<Integer> imglist;
    List<Integer> color;
    List<String> colorName;
    TextView offerTextViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
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
        navigationView.setNavigationItemSelectedListener(this);
        if(SharedPrefManager.getInstance(this).getUser().getUserID() != null){
                Menu menu = navigationView.getMenu();
                MenuItem target = menu.findItem(R.id.nav_login);
                target.setVisible(false);
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
        progressDialog = new ProgressDialog(this);
        viewPager = findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);

        recyclerView = findViewById(R.id.recyclerViewItems);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        offerTextViews =findViewById(R.id.offerTextView);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                getProductList();
            }
        });
        getProductList();

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




    private void getProductList() {
        try {

            progressDialog.setMessage("loading...");
            progressDialog.show();
            mSwipeRefreshLayout.setRefreshing(true);
            result = new HelperApi.GetAllProductList().execute().get();
            if (result.isEmpty()) {
                mSwipeRefreshLayout.setRefreshing(false);
                progressDialog.dismiss();
            } else {

                    progressDialog.dismiss();
                    /*   Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();*/
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<ProductModel>>() {
                    }.getType();
                    productModel = new Gson().fromJson(result, listType);
                    Log.d("Error", productModel.toString());
                    ProductListAdapter productListAdapter= new ProductListAdapter(MainActivity.this, productModel);
                    recyclerView.setAdapter(productListAdapter);
                    mSwipeRefreshLayout.setRefreshing(false);

            }

        } catch (Exception e) {
            mSwipeRefreshLayout.setRefreshing(false);
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

        if (id == R.id.nav_productDeatails) {
            startActivity(intent=new Intent(this,ProductListActivity.class));
        }else  if (id == R.id.nav_Chat) {
             startActivity(intent=new Intent(this,ChatActivity.class));
         }else  if (id == R.id.nav_ordrTrack) {
             startActivity(intent=new Intent(this,OrdertrackingActivity.class));

         }else  if (id == R.id.nav_builtyUpload) {
             startActivity(intent=new Intent(this,BuiltiUploadActivity.class));

         } else if (id == R.id.nav_userpermission) {

             Intent intent = new Intent(this, UserPermissionActivity.class);
             startActivity(intent);
         }else if (id == R.id.nav_login) {
             if(SharedPrefManager.getInstance(this).getUser().getUserID() == null){
                 startActivity(intent=new Intent(this,SignInActivity.class));
             }else {
                 AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                 alertbox.setIcon(R.drawable.ic_warning_black_24dp);
                 alertbox.setTitle("You have already logged In");
                 alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface arg0, int arg1) {
//                         finishAffinity();
//                         System.exit(0);


                     }
                 });

                 alertbox.show();
             }

        }else if (id == R.id.nav_logout) {
             SharedPrefManager.getInstance(this).logout();
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
}
