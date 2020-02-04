package com.bytes.tech.awizom.ecommerceadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.activity.MyOrderTrackingActivity;
import com.bytes.tech.awizom.ecommerceadmin.models.MyOrderModel;
import com.bytes.tech.awizom.ecommerceadmin.models.NotificationListModel;

import java.util.List;

public class MyOrderAdapter extends  RecyclerView.Adapter<MyOrderAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<MyOrderModel> userModelsList;
    private Intent i;


    public MyOrderAdapter(Context mCtx, List<MyOrderModel> userModelsList) {
        this.mCtx = mCtx;
        this.userModelsList = userModelsList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_order_adapter, null);
        return new MyOrderAdapter.OrderItemViewHolder(view, mCtx, userModelsList);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderAdapter.OrderItemViewHolder holder, int position) {
        MyOrderModel catagoriesModel = userModelsList.get(position);

        holder.catagory_names.setText(catagoriesModel.getProductName().toString());
        holder.catagoryIDs.setText(String.valueOf(catagoriesModel.getStatus()));
        holder.amounts.setText(String.valueOf(catagoriesModel.getTotalAmount()));
        holder.orderIds.setText(String.valueOf(catagoriesModel.getOrderId()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(mCtx, MyOrderTrackingActivity.class);
                i.putExtra("OId",holder.orderIds.getText().toString());
                mCtx.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return userModelsList.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView catagory_names,catagoryIDs,amounts,orderIds;

        private List<MyOrderModel> userModelsList;
        private MyOrderModel catagoriesModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        public OrderItemViewHolder(View view, Context mCtx, List<MyOrderModel> userModelsList) {
            super(view);
            this.mCtx = mCtx;
            this.userModelsList = userModelsList;

            catagory_names = view.findViewById(R.id.name);
            catagoryIDs=view.findViewById(R.id.work);
            amounts=view.findViewById(R.id.amount);
            orderIds=view.findViewById(R.id.orderId);
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


