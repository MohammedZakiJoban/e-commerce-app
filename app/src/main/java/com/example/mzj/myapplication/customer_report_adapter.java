package com.example.mzj.myapplication;

import android.content.Context;
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

public class customer_report_adapter extends RecyclerView.Adapter<customer_report_adapter.MyViewholder> {


    private List<customer_report_data> list = new ArrayList<>();
    private Context context;


    customer_report_adapter(List<customer_report_data> list, Context ctx) {
        this.list = list;
        context = ctx;
    }


    @Override
    public customer_report_adapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_report_layout, parent, false);
        return new customer_report_adapter.MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(customer_report_adapter.MyViewholder holder, int position) {

        holder.nametx.setText(list.get(position).getName());
        holder.timestx.setText(list.get(position).getTimes());
        holder.pricetx.setText(list.get(position).getTotal());


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView nametx, timestx,pricetx;

        Context context;

        public MyViewholder(View itemView) {
            super(itemView);


            nametx = (TextView) itemView.findViewById(R.id.name);
            timestx = (TextView) itemView.findViewById(R.id.tx_number);
            pricetx = (TextView) itemView.findViewById(R.id.tx_total);

        }
    }

}