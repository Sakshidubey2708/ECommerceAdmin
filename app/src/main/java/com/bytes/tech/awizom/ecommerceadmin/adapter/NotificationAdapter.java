package com.bytes.tech.awizom.ecommerceadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.activity.MyOrderActivity;
import com.bytes.tech.awizom.ecommerceadmin.activity.ProductListActivity;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.models.NotificationListModel;
import java.util.List;

public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<NotificationListModel> userModelsList;
    private Intent i;


    public NotificationAdapter(Context mCtx, List<NotificationListModel> userModelsList) {
        this.mCtx = mCtx;
        this.userModelsList = userModelsList;
    }

    @NonNull
    @Override
    public NotificationAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.notification_adapter, null);
        return new NotificationAdapter.OrderItemViewHolder(view, mCtx, userModelsList);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.OrderItemViewHolder holder, int position) {
        NotificationListModel catagoriesModel = userModelsList.get(position);

        holder.noti_id.setText(String.valueOf(catagoriesModel.getNotificationId()));
        holder.catagory_names.setText(catagoriesModel.getNotificationTitle().toString());
        holder.catagoryIDs.setText(String.valueOf(catagoriesModel.getNotificationDescription()));

        holder.order_id.setText(String.valueOf(catagoriesModel.getOrderId()));
        holder.product_id.setText(String.valueOf(catagoriesModel.getProductId()));
        holder.typenotif.setText(catagoriesModel.getNotificationType().toString());



        if(catagoriesModel.isRead)
        {
            holder.linear.setBackgroundColor(Color.parseColor("#F9F9F9"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(holder.buttonClick);
                try {
                 String   result = new HelperApi.PostNotification().execute(holder.noti_id.getText().toString()).get();
                    if (result.isEmpty()) {
                    } else {
                        if(!(holder.typenotif.getText().toString().equals("Purchase"))){


                            mCtx.startActivity(  i= new Intent(mCtx, MyOrderActivity.class));
                        } else if (!(holder.product_id.toString() == "0")) {

                            mCtx.startActivity(  i= new Intent(mCtx, ProductListActivity.class));
                        }else {

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return userModelsList.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView catagory_names,catagoryIDs,noti_id,product_id,order_id,typenotif;
        private List<NotificationListModel> userModelsList;
        private NotificationListModel catagoriesModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        LinearLayout linear;


        public OrderItemViewHolder(View view, Context mCtx, List<NotificationListModel> userModelsList) {
            super(view);
            this.mCtx = mCtx;
            this.userModelsList = userModelsList;
            noti_id=view.findViewById(R.id.noti_id);
            catagory_names = view.findViewById(R.id.name);
            catagoryIDs=view.findViewById(R.id.work);

            order_id = view.findViewById(R.id.orderID);
            product_id=view.findViewById(R.id.productID);

            typenotif = view.findViewById(R.id.notifcationType);
            linear=view.findViewById(R.id.linear);
            itemView.setOnClickListener(this);
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

