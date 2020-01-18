package com.bytes.tech.awizom.ecommerceadmin.activity;

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
import com.bytes.tech.awizom.ecommerceadmin.models.UserModels;

import java.util.List;

public class ViewUserAdapter extends  RecyclerView.Adapter<ViewUserAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<UserModels> userModelsList;


    public ViewUserAdapter(Context mCtx, List<UserModels> userModelsList) {
        this.mCtx = mCtx;
        this.userModelsList = userModelsList;
    }

    @NonNull
    @Override
    public ViewUserAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.viewuser_adapter, null);
        return new ViewUserAdapter.OrderItemViewHolder(view, mCtx, userModelsList);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewUserAdapter.OrderItemViewHolder holder, int position) {
        UserModels catagoriesModel = userModelsList.get(position);

        holder.catagory_names.setText("Name"+catagoriesModel.getUserName().toString());
        holder.catagoryIDs.setText("Business"+String.valueOf(catagoriesModel.getBusiness()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }


    @Override
    public int getItemCount() {
        return userModelsList.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView catagory_names,catagoryIDs;

        private List<UserModels> userModelsList;
        private UserModels catagoriesModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        public OrderItemViewHolder(View view, Context mCtx, List<UserModels> userModelsList) {
            super(view);
            this.mCtx = mCtx;
            this.userModelsList = userModelsList;

            catagory_names = view.findViewById(R.id.name);
            catagoryIDs=view.findViewById(R.id.work);
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
