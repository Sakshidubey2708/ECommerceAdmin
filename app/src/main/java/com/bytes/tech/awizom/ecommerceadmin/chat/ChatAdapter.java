package com.bytes.tech.awizom.ecommerceadmin.chat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import com.bytes.tech.awizom.ecommerceadmin.adapter.ProductListAdapter;
import com.bytes.tech.awizom.ecommerceadmin.configure.HelperApi;
import com.bytes.tech.awizom.ecommerceadmin.configure.SharedPrefManager;
import com.bytes.tech.awizom.ecommerceadmin.models.ChatModel;
import com.bytes.tech.awizom.ecommerceadmin.models.PricerequestModel;
import com.bytes.tech.awizom.ecommerceadmin.models.ProductModel;
import com.bytes.tech.awizom.ecommerceadmin.models.RatingModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatAdapter extends  RecyclerView.Adapter<ChatAdapter.OrderItemViewHolder> {

    private Context mCtx;
    private List<ChatModel> chatmodellist;
    private ChatModel propertyName;
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

    public ChatAdapter(final Context mCtx, List<ChatModel> chatmodellist) {
        this.mCtx = mCtx;
        this.chatmodellist = chatmodellist;

    }


    @NonNull
    @Override
    public ChatAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.chat_adapter, null);
        return new ChatAdapter.OrderItemViewHolder(view, mCtx, chatmodellist);
    }


    @Override
    public void onBindViewHolder(@NonNull final ChatAdapter.OrderItemViewHolder holder, int position) {
        ChatModel catagoriesModel = chatmodellist.get(position);

        try{
            if(catagoriesModel.getRoleMessageBy().equals("Admin"))
            {
                holder.adminmsz.setText(catagoriesModel.getChatContain());
                holder.clientcard.setVisibility(View.GONE);
            }
            else
            {
                holder.clientmsz.setText(catagoriesModel.getChatContain());
                holder.admincard.setVisibility(View.GONE);
            }


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
        return chatmodellist.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private Context mCtx;
        private TextView clientmsz,adminmsz;
        private List<ChatModel> chatmodellist;
        private ChatModel productModel;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        CardView admincard,clientcard;
        // RatingBar ratingbar;

        private Button book,getRequest;
        //private Button buttonRates;

        public OrderItemViewHolder(View view, final Context mCtx, List<ChatModel> OrderNewOnes) {

            super(view);
            this.mCtx = mCtx;
            this.chatmodellist = OrderNewOnes;

            clientmsz = view.findViewById(R.id.clientmsz);
            adminmsz =view.findViewById(R.id.adminmsz);

            admincard = view.findViewById(R.id.adm_card);
            clientcard =view.findViewById(R.id.clt_card);


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


