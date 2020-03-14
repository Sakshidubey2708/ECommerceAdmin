package com.bytes.tech.awizom.ecommerceadmin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductBaseChatModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class ProductChatAdapter extends
        RecyclerView.Adapter<ProductChatAdapter.MyViewHolder> {

    private List<ProductBaseChatModel> chatlist;
    private Context mCtx;


    public ProductChatAdapter(Context baseContext, List<ProductBaseChatModel> chatlist) {
        this.chatlist = chatlist;
        this.mCtx = baseContext;

    }

    @Override
    public void onBindViewHolder(ProductChatAdapter.MyViewHolder holder, int position) {

        ProductBaseChatModel c = chatlist.get(position);
        /* introducing most important line for get data from firestore set this holder as recyclable*/
        holder.setIsRecyclable(false);
        if (c.getMsgBy().equals("Customer")) {
            holder.my_card.setVisibility(View.VISIBLE);
            holder.mymsg.setText(c.getChatContain());
        } else {
            holder.retailmsg.setText(c.getChatContain());
            holder.rtl_card.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return chatlist.size();
    }

    @Override
    public ProductChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productbase_chat_adapter, parent, false);
        return new ProductChatAdapter.MyViewHolder(v);
    }

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mymsg, retailmsg;
        android.support.v7.widget.CardView my_card, rtl_card;

        public MyViewHolder(View view) {
            super(view);
            mymsg = view.findViewById(R.id.mymsg);
            retailmsg = view.findViewById(R.id.retailermsg);
            my_card = view.findViewById(R.id.my_card);
            rtl_card = view.findViewById(R.id.rtl_card);
        }
    }
}