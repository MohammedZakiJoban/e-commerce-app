package com.example.mzj.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MZJ on 11/22/2017.
 */

public class inventoryAdapter extends RecyclerView.Adapter<inventoryAdapter.MyViewholder>  {


    private List<inventoryData> list = new ArrayList<>();
    private Context context;

    inventoryAdapter(List<inventoryData> list, Context ctx){
        this.list = list;
        context = ctx;
    }

    @Override
    public inventoryAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_layout,parent,false);
        return new inventoryAdapter.MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(inventoryAdapter.MyViewholder holder, int position) {

        holder.nametx.setText(list.get(position).getName());
        holder.stocktx.setText(Integer.toString(list.get(position).getStock()));




    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView nametx,stocktx;

        Context context;

        public MyViewholder(View itemView){
            super(itemView);


            nametx = (TextView)itemView.findViewById(R.id.tx_name);
            stocktx = (TextView)itemView.findViewById(R.id.tx_stock);

        }}


}

