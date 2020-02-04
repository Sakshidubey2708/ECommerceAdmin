package com.bytes.tech.awizom.ecommerceadmin.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.activity.OnItemClick;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.CartAssured;
import com.bytes.tech.awizom.ecommerceadmin.models.CartModel;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderDetailMain;
import com.bytes.tech.awizom.ecommerceadmin.models.OrderMainModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<CartModel> cardmodellist;
    private CartModel propertyName;

    private String result = "";
    private boolean isTailor = true;
    long pid = 0;
    private static int _counter = 1;
    private static int _counters = 1;
    private String _stringVal,stringCounters="";
    Double total = null;
    private long CartAssuredId=0;
    private CartAssured cartAssureds;
    private ProgressDialog progressDialog;
    private OnItemClick mCallback;
    long orderDetailID=0;
    OrderDetailMain orderDetailMain;
    long orderMainID=0;
    public CartAdapter(Context mCtx, List<CartModel> OrderNewOnes, OnItemClick listener, long orderMainID) {
        this.mCtx = mCtx;
        this.cardmodellist = OrderNewOnes;
        this.mCallback = listener;
        this.orderMainID = orderMainID;
    }

    @NonNull
    @Override
    public CartAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.addtocart_adapter_layout, null);
        return new CartAdapter.OrderItemViewHolder(view, mCtx, cardmodellist);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.OrderItemViewHolder holder, int position) {
        final CartModel catagoriesModel = cardmodellist.get(position);
        try{
            holder.productIdd.setText(String.valueOf(catagoriesModel.getProductId()));
            holder.cartIds.setText(String.valueOf(catagoriesModel.getCartId()));
            CartAssuredId =  Integer.parseInt(holder.cartIds.getText().toString());
            holder.product_name.setText(catagoriesModel.getProductName());
            holder.assuredprice.setText("₹"+String.valueOf(catagoriesModel.getMRPINR()));
            holder.mrpPrice.setText("₹"+String.valueOf(catagoriesModel.getMRPINR()));
            holder.descripyion.setText(catagoriesModel.getDescriptions());
            holder.brandId.setText("₹"+String.valueOf(catagoriesModel.getBrandId()));
            holder.branNam.setText(catagoriesModel.getBrandName().toString());

            holder.donebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        result = new HelperApi.PostOrderDetailMain().execute(
                                String.valueOf(orderDetailID),
                                String.valueOf(orderMainID),
                                String.valueOf(catagoriesModel.getProductId()),
                                String.valueOf(catagoriesModel.getMRPINR()),
                                holder.quantity.getText().toString(),
                                holder.totals.getText().toString(),
                                "0" ,
                                "0", "0", "0","0","0").get();
                        if (result.isEmpty()) {
                            result = new HelperApi.PostOrderDetailMain().execute(
                                    String.valueOf(orderDetailID),
                                    String.valueOf(orderMainID),
                                    String.valueOf(catagoriesModel.getProductId()),
                                    String.valueOf(catagoriesModel.getMRPINR()),
                                    holder.quantity.getText().toString(),
                                    holder.totals.getText().toString(),
                                    "0" ,
                                    "0", "0", "0","0","0").get();
                        } else {

                            Gson gson = new Gson();
                            Type listType = new TypeToken<OrderMainModel>() {
                            }.getType();
                            orderDetailMain = new Gson().fromJson(result, listType);
                            orderDetailID = orderDetailMain.getOrderId();




                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            holder.Qtys.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    dialog.setContentView(R.layout.bottom_qty_dailog);

                    final TextView quantity = dialog.findViewById(R.id.quantities);

                    Button plusBtn = dialog.findViewById(R.id.plus);
                    Button doneBtn = dialog.findViewById(R.id.done);
                    final Button minusbtn = dialog.findViewById(R.id.minus);
                    ImageView closebtn = dialog.findViewById(R.id.close);


                    closebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    doneBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();

                        if(!holder.totals.getText().toString().isEmpty()){
                            try {

                                result = new HelperApi.PostCartAmount().execute(
                                        String.valueOf(CartAssuredId),
                                        holder.cartIds.getText().toString(),
                                        holder.productIdd.getText().toString(),
                                        SharedPrefManager.getInstance(mCtx).getUser().getUserId().toString(),
                                        holder.quantity.getText().toString(),
                                        holder.totals.getText().toString().split("₹")[1],
                                        "img", holder.product_name.getText().toString(),
                                        holder.descripyion.getText().toString(),
                                        holder.totaldiscount.getText().toString(),
                                        holder.mrpPrice.getText().toString(),
                                        holder.brandId.getText().toString(),
                                        holder.branNam.getText().toString(),
                                        holder.discount.getText().toString()).get();
                                if (result.isEmpty()) {
                                    result = new HelperApi.PostCartAmount().execute(
                                            String.valueOf(CartAssuredId),
                                            holder.cartIds.getText().toString(),
                                            holder.productIdd.getText().toString(),
                                            SharedPrefManager.getInstance(mCtx).getUser().getUserId().toString(),
                                            holder.quantity.getText().toString(),
                                            holder.totals.getText().toString().split("₹")[1],
                                            "img", holder.product_name.getText().toString(),
                                            holder.descripyion.getText().toString(),
                                            holder.totaldiscount.getText().toString(),
                                            holder.mrpPrice.getText().toString(),
                                            holder.brandId.getText().toString(),
                                            holder.branNam.getText().toString(),
                                            holder.discount.getText().toString()).get();
                                } else {
                                    if (result.isEmpty()) {
                                        Toast.makeText(mCtx,"Empty Result",Toast.LENGTH_LONG).show();
                                    } else {
                                        Gson gson = new Gson();
                                        Type listType = new TypeToken<List<CartAssured>>() {
                                        }.getType();
                                        cartAssureds = new Gson().fromJson(result, listType);
                                        CartAssuredId =cartAssureds.getCartAssuredId();
                                        Toast.makeText(mCtx,String.valueOf(CartAssuredId),Toast.LENGTH_LONG).show();
                                        mCallback.onClick( holder.totals.getText().toString());
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {

                        }
                        }
                    });

                    plusBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("src", "Increasing value...");
                            _counters++;
                            _stringVal = Integer.toString(_counters);
                            quantity.setText(_stringVal);
                            stringCounters = quantity.getText().toString();
                            Double  qty, ass_price,mm;
                            if(_counter >=1) {
                                qty = Double.parseDouble(stringCounters.toString());
                                ass_price = Double.parseDouble(holder.assuredprice.getText().toString().split("₹")[1]);
                                total = qty * ass_price;
                                holder.totals.setText("₹" + total.toString());

                            }

                        }
                    });
                    minusbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("src", "Decreasing value...");
                            _counters--;
                            _stringVal = Integer.toString(_counters);
                            quantity.setText(_stringVal);
                            stringCounters = quantity.getText().toString();

                            Double  qty, ass_price,mm;
                            if(1 >= _counter) {
                                qty = Double.parseDouble(stringCounters.toString());
                                ass_price = Double.parseDouble(holder.assuredprice.getText().toString().split("₹")[1]);
                                total = qty * ass_price;
                                holder.totals.setText("₹"+total.toString());

                            }
                        }
                    });
                    closebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });

            //close dialog;

            holder.minusbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Decreasing value...");
                    _counter--;
                    _stringVal = Integer.toString(_counter);
                    holder.quantity.setText(_stringVal);
                    Double  qty, ass_price,mm;
                    if(1 >= _counter) {

                        qty = Double.parseDouble(holder.quantity.getText().toString());
                        ass_price = Double.parseDouble(holder.assuredprice.getText().toString().split("₹")[1]);
                        total = qty * ass_price;
                        holder.totals.setText("₹"+total.toString());

                        mCallback.onClick( holder.totals.toString());
                    }
                }
            });




            holder.plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Increasing value...");
                    _counter++;
                    _stringVal = Integer.toString(_counter);
                    holder.quantity.setText(_stringVal);
                    Double  qty, ass_price;
                    if(_counter >=1){
                        qty = Double.parseDouble(holder.quantity.getText().toString());
                        ass_price = Double.parseDouble(holder.assuredprice.getText().toString().split("₹")[1]);
                        total = qty*ass_price;
                        holder.totals.setText("₹"+total.toString());
                        mCallback.onClick( holder.totals.toString());

                    }


                  //  stringMM = holder.assuredprice.getText().toString().split("₹")[1];
                }
            });
            holder.deletBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        result = new HelperApi.DeleteCartPost().execute(  holder.cartIds.getText().toString()).get();
                        if (result.isEmpty()) {
                            result = new HelperApi.DeleteCartPost().execute(  holder.cartIds.getText().toString()).get();                        } else {
                            if (result.isEmpty()) {

                            } else {
                                   Toast.makeText(mCtx,"Deleted",Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
        return cardmodellist.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private ImageView product_img;
        private TextView product_name,assuredprice,mrpPrice,discount,productIdd,quantity,totals,imglinks,deletBTN,cartIds,Qtys;
        private List<CartModel> cardmodellist;
        private Context mCtx;
        private Button minusbtn,plusBtn;
        TextView descripyion,brandId,branNam, totaldiscount,donebtn;

        public OrderItemViewHolder(View view, Context mCtx, List<CartModel> OrderNewOnes) {
            super(view);
            this.mCtx = mCtx;
            this.cardmodellist = OrderNewOnes;
            progressDialog = new ProgressDialog(mCtx);

            deletBTN = view.findViewById(R.id.deleteButton);
            product_img =view.findViewById(R.id.productImage);
            product_name = view.findViewById(R.id.productname);
            cartIds = view.findViewById(R.id.cartId);
            quantity = view.findViewById(R.id.quantities);

            assuredprice =view.findViewById(R.id.assuredRate);
            mrpPrice =view.findViewById(R.id.mrpPrice);

            totals = view.findViewById(R.id.total);
            Qtys = view.findViewById(R.id.Qty);

            donebtn = view.findViewById(R.id.done);

            productIdd =view.findViewById(R.id.productId);
            imglinks = view.findViewById(R.id.imaglink);
            minusbtn =view.findViewById(R.id.minus);
            plusBtn = view.findViewById(R.id.add);

            descripyion =view.findViewById(R.id.description);
            brandId =view.findViewById(R.id.brandID);
            branNam = view.findViewById(R.id.brandName);


        }



        @Override
        public void onClick(final View v) {
//            v.startAnimation(buttonClick);
        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }


}


