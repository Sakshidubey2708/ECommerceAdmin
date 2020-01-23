package com.bytes.tech.awizom.ecommerceadmin.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bytes.tech.awizom.ecommerceadmin.R;
import com.bytes.tech.awizom.ecommerceadmin.models.StockMain;
import com.bytes.tech.awizom.ecommerceadmin.models.StockProduct;


import java.util.List;

public class StockAdapter extends BaseAdapter {

    //  private final String[] catalogNameList;

    private List<StockMain> stockMains;
    private Context mContext;
    private String skipdata="";
    private CardView cardView;

    public StockAdapter(StockActivity newCustomerHome, List<StockMain> stockMains) {

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
            gridViewAndroid = inflater.inflate(R.layout.stock_adapter, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.catalogName);
            TextView descriptions = (TextView) gridViewAndroid.findViewById(R.id.description);
            final TextView catagoryIDss = (TextView) gridViewAndroid.findViewById(R.id.catagoryIDs);
            final TextView imglinkurl = gridViewAndroid.findViewById(R.id.imgLink);
            cardView = gridViewAndroid.findViewById(R.id.homeCleancardViewOne);
            //  final ProgressBar progressBar = gridViewAndroid.findViewById(R.id.homeprogress);
            try {
                textViewAndroid.setText(stockMains.get(i).getProductName());
                descriptions.setText(stockMains.get(i).getHighlightsDesign());
                catagoryIDss.setText(String.valueOf(stockMains.get(i).getProductId()));
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ViewSubCatagoryActivity.class);
                        intent.putExtra("mainID",catagoryIDss.getText().toString());
                        mContext.startActivity(intent);
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
