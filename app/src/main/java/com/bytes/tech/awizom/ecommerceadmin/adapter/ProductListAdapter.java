package com.bytes.tech.awizom.ecommerceadmin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.activity.SignInActivity;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.PricerequestModel;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductModel;
import com.bytes.tech.awizom.ecommerceadmin.models.RatingModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ProductListAdapter extends  RecyclerView.Adapter<ProductListAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<ProductModel> productModelList;
    private ProductModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;
    long PriceRequestId=0,ratingID=0;
    PricerequestModel pricerequestModel;
    RatingModel ratingModel;
    List<String> rating;
    List<String> statusRequst;
    String ratings="";

    public ProductListAdapter(Context mCtx, List<ProductModel> OrderNewOnes) {
        this.mCtx = mCtx;
        this.productModelList = OrderNewOnes;

    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.producet_adpater, null);
        return new OrderItemViewHolder(view, mCtx, productModelList);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderItemViewHolder holder, int position) {
        ProductModel catagoriesModel = productModelList.get(position);

        try{
            holder.titleNames.setText(catagoriesModel.getTypeWeight().toString());
            holder.productNames.setText(catagoriesModel.getProductName().toString());
            holder.mrpPrice.setText("MRP"+"₹"+String.valueOf(catagoriesModel.getMRPINR()));
            holder.discounts.setText("Dis:" +String.valueOf(catagoriesModel.getTotalDiscountsPer())+"%");
            holder.productId.setText(String.valueOf(catagoriesModel.getProductId()));
            holder.brandId.setText(String.valueOf(catagoriesModel.getBrandId()));
            holder.categoryId.setText(String.valueOf(catagoriesModel.getMainCatId()));
            holder.mainId.setText(String.valueOf(catagoriesModel.getMainCatId()));





            holder.getRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        result = new HelperApi.GETPriceRequest().execute(String.valueOf(PriceRequestId)).get();
                        if (result.isEmpty()) {
                            result = new HelperApi.GETPriceRequest().execute(holder.productId.getText().toString()).get();
                        } else {
                            Gson gsons = new Gson();
                            Type listType1 = new TypeToken<PricerequestModel>() {
                            }.getType();
                            pricerequestModel = new Gson().fromJson(result, listType1);
                            Log.d("Error", pricerequestModel.toString());

                            final Dialog dialog = new Dialog(mCtx);
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

                            TextView discountPrices = dialog.findViewById(R.id.discountPrice);
                            TextView salePrices = dialog.findViewById(R.id.salePrice);
                            Button ok = dialog.findViewById(R.id.okbtn);
                            discountPrices.setText(String.valueOf(pricerequestModel.getSalePrice()));
                            salePrices.setText(String.valueOf(pricerequestModel.getSaleDiscount()));

                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });




                            dialog.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            holder.book.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    v.startAnimation(holder.buttonClick);
                    try {
                        if( SharedPrefManager.getInstance(mCtx).getUser().getUserID() == null){
                            final Dialog dialog = new Dialog(mCtx);
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
                                    Intent i = new Intent(mCtx, SignInActivity.class);
                                    mCtx.startActivity(i);
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
                                result = new HelperApi.PostSentPriceRequest().execute(
                                        String.valueOf(PriceRequestId),
                                        SharedPrefManager.getInstance(mCtx).getUser().getUserID().toString(),
                                        holder.productId.getText().toString().trim(),
                                        "PriceRequest").get();
                                if (result.isEmpty()) {
                                    result = new HelperApi.PostSentPriceRequest().execute(
                                            String.valueOf(PriceRequestId),
                                            SharedPrefManager.getInstance(mCtx).getUser().getUserID().toString(),
                                            holder.productId.getText().toString().trim(),
                                            "PriceRequest").get();
                                } else {
                                    Gson gson = new Gson();
                                    Type listType = new TypeToken<PricerequestModel>() {
                                    }.getType();
                                    pricerequestModel = new Gson().fromJson(result, listType);
                                    PriceRequestId =pricerequestModel.getPriceRequestId();

                                    holder.book.setVisibility(View.GONE);
                                    holder.getRequest.setVisibility(View.VISIBLE);




                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }catch (Exception e){

                    }


                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView titleNames,productNames, mrpPrice,discounts,productId,brandId,categoryId,mainId;
        private List<ProductModel> productModelList;
        private ProductModel productModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
       // RatingBar ratingbar;

        private Button book,getRequest;
        //private Button buttonRates;

        public OrderItemViewHolder(View view, final Context mCtx, List<ProductModel> OrderNewOnes) {

            super(view);
            this.mCtx = mCtx;
            this.productModelList = OrderNewOnes;

            titleNames = view.findViewById(R.id.TitleNAme);
            productNames =view.findViewById(R.id.productNAme);
            mrpPrice =view.findViewById(R.id.MRP);
            discounts = view.findViewById(R.id.discount);
            productId =view.findViewById(R.id.ProductId);
            brandId = view.findViewById(R.id.BrandId);
            categoryId = view.findViewById(R.id.CategoryId);
            mainId = view.findViewById(R.id.mainID);
            book = view.findViewById(R.id.sentRequest);
            getRequest = view.findViewById(R.id.getRequest);


            final Handler handler = new Handler();
            final int[] colors = {Color.BLUE, Color.RED};
            final int[] i = new int[1];
            Runnable runnable = new Runnable () {
                @Override
                public void run() {
                    i[0] = i[0] % colors.length;
                    discounts.setTextColor(colors[i[0]]);
                    i[0]++;
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(runnable, 1000);

        }

        @Override
        public void onClick(final View v) {
            v.startAnimation(buttonClick);
        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }

}


