package com.example.mzj.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;

/**
 * Created by MZJ on 11/20/2017.
 */

public class salesAdapter extends RecyclerView.Adapter<salesAdapter.MyViewholder> {



    private List<salesData> list = new ArrayList<>();
    private Context context;


    salesAdapter(List<salesData> list, Context ctx){
        this.list = list;
        context = ctx;
    }


    @Override
    public salesAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_report_layout,parent,false);
        return new salesAdapter.MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(salesAdapter.MyViewholder holder, int position) {

        holder.datetx.setText(list.get(position).getDate());

        holder.salestx.setText("RM" + list.get(position).getSales());







    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView datetx,salestx;
        List<salesData> datas = new ArrayList<salesData>();
        Context context;

        public MyViewholder(View itemView){
            super(itemView);


            datetx = (TextView)itemView.findViewById(R.id.tx_date);
            salestx = (TextView)itemView.findViewById(R.id.tx_sales);

        }}


}
//, Context ctx, List<salesData> datas