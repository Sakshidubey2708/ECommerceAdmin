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
import com.bytes.tech.awizom.ecommerceadmin.models.SearchModel;

import java.util.List;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<SearchModel> searchModelList;
    private SearchModel propertyName;
    TextView calltext;
    private String result = "";
    private boolean isTailor = true;
    long pid = 0;
    private Intent intent;
    public SearchAdapter(Context mCtx, List<SearchModel> OrderNewOnes) {
        this.mCtx = mCtx;
        this.searchModelList = OrderNewOnes;
    }

    @NonNull
    @Override
    public SearchAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.sub_catagory_adapter, null);
        return new SearchAdapter.OrderItemViewHolder(view, mCtx, searchModelList);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchAdapter.OrderItemViewHolder holder, int position) {
        SearchModel searchModel = searchModelList.get(position);

        holder.catagory_names.setText(searchModel.getBrandName().toString());
        holder.catagoryIDs.setText(String.valueOf(searchModel.getProductName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                intent = new Intent(mCtx, ProductListActivity.class);
//                intent.putExtra("subID",holder.catagoryIDs.getText().toString());
//                mCtx.startActivity(intent);



            }
        });

    }


    @Override
    public int getItemCount() {
        return searchModelList.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView catagory_names,catagoryIDs;

        private List<SearchModel> searchModelList;
        private SearchModel searchModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        public OrderItemViewHolder(View view, Context mCtx, List<SearchModel> OrderNewOnes) {
            super(view);
            this.mCtx = mCtx;
            this.searchModelList = OrderNewOnes;

            catagory_names = view.findViewById(R.id.catagory_name);
            catagoryIDs=view.findViewById(R.id.catagoryID);

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

