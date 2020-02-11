package com.bytes.tech.awizom.ecommerceadmin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.activity.CartActivity;
import com.bytes.tech.awizom.ecommerceadmin.activity.RetailerHomeActivity;
import com.bytes.tech.awizom.ecommerceadmin.activity.SignInActivity;
import com.bytes.tech.awizom.ecommerceadmin.activity.StockActivity;
import com.bytes.tech.awizom.ecommerceadmin.configure.AppConfig;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDetailMain;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderMainModel;
import com.bytes.tech.awizom.ecommerceadmin.models.PricerequestModel;
import com.bytes.tech.awizom.ecommerceadmin.models.StockMain;
import com.bytes.tech.awizom.ecommerceadmin.models.StockProduct;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RetailerAdapter extends BaseAdapter {

    //  private final String[] catalogNameList;
    private List<StockMain> stockMains;
    private Context mContext;
    private String skipdata = "", result = "";

    PricerequestModel pricerequestModel;
    long PriceRequestId = 0, ratingID = 0,orderMainID=0,orderDetailID=0,orderDetailIDs=0,orderMainIDs=0;
    OrderMainModel orderMainModel;
    OrderDetailMain orderDetailMain;
    int k =0;

    public RetailerAdapter(RetailerHomeActivity newCustomerHome, List<StockMain> stockMains) {
        this.mContext = newCustomerHome;
        this.stockMains = stockMains;
        this.skipdata = skipdata;
    }

    @Override
    public int getCount() {
        return stockMains.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, final View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.retailr_adapter, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.catalogName);
            TextView descriptions = (TextView) gridViewAndroid.findViewById(R.id.description);
            final TextView productid = (TextView) gridViewAndroid.findViewById(R.id.product_ids);
            final TextView stockid = (TextView) gridViewAndroid.findViewById(R.id.stock_ids);
            TextView imglinkurl = gridViewAndroid.findViewById(R.id.imgLink);
            final CardView homeCleancardViewOne = gridViewAndroid.findViewById(R.id.homeCleancardViewOne);
            final LinearLayout frames = gridViewAndroid.findViewById(R.id.frame);
            Button request = gridViewAndroid.findViewById(R.id.btn);
            Button sent = gridViewAndroid.findViewById(R.id.btnSent);
            ImageView imgs = gridViewAndroid.findViewById(R.id.img);
            final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


            //  final ProgressBar progressBar = gridViewAndroid.findViewById(R.id.homeprogress);
            try {
                textViewAndroid.setText(stockMains.get(i).getProductName());
                descriptions.setText(stockMains.get(i).getHighlightsDesign());
                productid.setText(String.valueOf(stockMains.get(i).getProductId()));
                stockid.setText(String.valueOf(stockMains.get(i).getStockMainId()));
                imglinkurl.setText(stockMains.get(i).getProImg1());
                homeCleancardViewOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, StockActivity.class);
                        intent.putExtra("StockID",stockid.getText().toString());
                        mContext.startActivity(intent);
                    }
                });

                frames.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, StockActivity.class);
                        intent.putExtra("StockID",stockid.getText().toString());
                        mContext.startActivity(intent);
                    }
                });

                String url = AppConfig.BASE_URL+"/" +imglinkurl.getText().toString();
                if (url != null) {
                    Glide.with(imgs)
                            .load(url)
                            .centerCrop()
                            .placeholder(R.mipmap.product)
                            .error(R.mipmap.product)
                            .fallback(R.mipmap.product)
                            .into(imgs) ;
                }



//                if (stockMains.get(i).getPricingProductId() == 0) {
//                    request.setVisibility(View.VISIBLE);
//                    request.setText("Req");
//                    request.setTextColor(Color.parseColor("#FD210B"));
//                    sent.setVisibility(View.GONE);
//                } else {
//                    sent.setVisibility(View.VISIBLE);
//                    sent.setText("Sent");
//                    sent.setTextColor(Color.parseColor("#3B970A"));
//                    request.setVisibility(View.GONE);
//                }
                request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.startAnimation(buttonClick);
                        try {
                            if (SharedPrefManager.getInstance(mContext).getUser().getUserID() == null) {
                                final Dialog dialog = new Dialog(mContext);
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
                                        Intent i = new Intent(mContext, SignInActivity.class);
                                        mContext.startActivity(i);
                                    }
                                });
                                closebtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();

                            } else {
                                try {

                                    result = new HelperApi.PostSentPriceRequest().execute(
                                            productid.getText().toString(),
                                            SharedPrefManager.getInstance(mContext).getUser().getUserID().toString(),
                                            productid.getText().toString().trim(),
                                            "PriceRequest",SharedPrefManager.getInstance(mContext).getUser().getSubscriberId()).get();
                                    if (result.isEmpty()) {

                                    } else {
                                        Gson gson = new Gson();
                                        Type listType = new TypeToken<PricerequestModel>() {
                                        }.getType();
                                        pricerequestModel = new Gson().fromJson(result, listType);
                                        PriceRequestId = pricerequestModel.getPriceRequestId();


                                        Intent i = new Intent(mContext,StockActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        mContext.startActivity(i);

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                });
                sent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            result = new HelperApi.GETPriceRequests().execute(productid.getText().toString()).get();
                            if (result.isEmpty()) {
                                result = new HelperApi.GETPriceRequests().execute(productid.getText().toString()).get();
                            } else {
                                Gson gsons = new Gson();
                                Type listType1 = new TypeToken<PricerequestModel>() {
                                }.getType();
                                pricerequestModel = new Gson().fromJson(result, listType1);
                                Log.d("Error", pricerequestModel.toString());


                                final Dialog dialog = new Dialog(mContext);
                                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                lp.copyFrom(dialog.getWindow().getAttributes());
                                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                                lp.gravity = Gravity.BOTTOM;
                                lp.windowAnimations = R.style.DialogAnimation;
                                dialog.getWindow().setAttributes(lp);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setCancelable(false);
                                dialog.setContentView(R.layout.pricing_id_show);

                                final TextView discountPrices = dialog.findViewById(R.id.discountPrice);
                                final TextView salePrices = dialog.findViewById(R.id.salePrice);
                                final EditText quantity = dialog.findViewById(R.id.quantity);
                                Button ok = dialog.findViewById(R.id.okbtn);
                                final Button order = dialog.findViewById(R.id.orderPlace);
                                discountPrices.setText(String.valueOf(pricerequestModel.getSaleDiscount()));
                                salePrices.setText(String.valueOf(pricerequestModel.getSalePrice()));

                                if(salePrices.getText().toString().equals("0.0")){
                                    order.setVisibility(View.GONE);
                                    quantity.setVisibility(View.GONE);
                                }else {
                                    order.setVisibility(View.VISIBLE);
                                    quantity.setVisibility(View.VISIBLE);
                                }

                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });

                                order.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            k = ++k;
                                            String S= "ord"+k;

                                            result = new HelperApi.PostOrderMain().execute(
                                                    String.valueOf(orderMainIDs),
                                                    S.toString(),
                                                    SharedPrefManager.getInstance(mContext).getUser().getUserID().toString(),
                                                    "",order.getText().toString().trim(),"","","",
                                                    SharedPrefManager.getInstance(mContext).getUser().getSubscriberId(),"1").get();

                                            if (result.isEmpty()) {

                                            } else {

                                                Gson gson = new Gson();
                                                Type listType = new TypeToken<OrderMainModel>() {
                                                }.getType();

                                                orderMainModel = new Gson().fromJson(result, listType);
                                                orderMainID =orderMainModel.getOrderId();

                                                if(orderMainID == 0){

                                                }else {

                                                    Double  qty, ass_price,total;

                                                        qty = Double.parseDouble(quantity.getText().toString());
                                                        ass_price = Double.parseDouble(salePrices.getText().toString());
                                                        total = qty * ass_price;


                                                    Toast.makeText(mContext,"OrderMAin"+String.valueOf(orderMainID),Toast.LENGTH_LONG).show();
                                                    result = new HelperApi.PostOrderDetailMain().execute(
                                                            String.valueOf(orderDetailID),
                                                            String.valueOf(orderMainID),
                                                            productid.getText().toString(),
                                                            salePrices.getText().toString(),quantity.getText().toString(),
                                                            total.toString(),  discountPrices.getText().toString(),
                                                            "", "",  "",  "", "").get();
                                                    if(result.isEmpty()){

                                                    }else {
                                                        Gson gsons = new Gson();
                                                        Type listType1 = new TypeToken<OrderDetailMain>() {
                                                        }.getType();
                                                        orderDetailMain = new Gson().fromJson(result, listType1);
                                                        result = new HelperApi.PostOrderMain().execute(
                                                                String.valueOf(orderMainID),
                                                                S.toString(),
                                                                SharedPrefManager.getInstance(mContext).getUser().getUserID().toString(),
                                                                "",order.getText().toString().trim(),
                                                                total.toString(),"","",SharedPrefManager.getInstance(mContext).getUser().getSubscriberId(),"1").get();

                                                        orderDetailIDs = orderDetailMain.getOrderId();
                                                        if(!(orderMainID == 0)){
                                                            order.setVisibility(View.GONE);
                                                            quantity.setVisibility(View.GONE);
                                                        }


//   orderMainID=0;
//  Toast.makeText(mContext,"OrderDetailsMain"+String.valueOf(orderDetailID),Toast.LENGTH_LONG).show();
//                                                        try {
//
//                                                            String userID = SharedPrefManager.getInstance(mContext).getUser().getUserID();
//                                                            String h= "ord"+k;
//                                                            result =   new HelperApi.PostCarts().execute(productid.getText().toString(),
//                                                                    SharedPrefManager.getInstance(mContext).getUser().getUserID(),
//                                                                    String.valueOf(orderMainID),String.valueOf(orderDetailID),h.toString()).get();
//                                                            try {
//                                                                if (result.isEmpty()) {
//                                                                    Log.d("Result Empty", "Error");
//
//                                                                } else {
//
//                                                                    Toast.makeText(mContext, "Cart Added", Toast.LENGTH_SHORT).show();
//
//                                                                    Intent intent = new Intent(mContext,CartActivity.class);
//                                                                    intent.putExtra("orderMainID" , String.valueOf(orderMainID));
//                                                                    intent.putExtra("orderDetailID" , String.valueOf(orderMainID));
//                                                                    mContext.startActivity(intent);
//
//                                                                }
//
//                                                            } catch (Exception e) {
//                                                                Log.d("Result Empty", "Error");
//                                                                e.printStackTrace();
//                                                            }
//
//                                                        } catch (Exception e) {
//                                                            Log.d("Result Empty", "Error");
//                                                            e.printStackTrace();
//                                                        }

                                                    }
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }

}

