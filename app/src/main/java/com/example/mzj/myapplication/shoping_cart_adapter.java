package com.example.mzj.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MZJ on 11/18/2017.
 */

public class shoping_cart_adapter extends RecyclerView.Adapter<shoping_cart_adapter.MyViewholder>{


    public static boolean add= true;
    private List<productData> list = new ArrayList<>();
    private Context context;




    public shoping_cart_adapter(List<productData> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public shoping_cart_adapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_layout,parent,false);
        return new MyViewholder(view,context,list);
    }

    @Override
    public void onBindViewHolder(shoping_cart_adapter.MyViewholder holder, int position) {

        holder.nametx.setText(list.get(position).getName());
        holder.pricetx.setText("RM" + Integer.toString(list.get(position).getPrice()));
        Picasso.with(context).load(list.get(position).getPicture()).resize(150, 150).into(holder.url);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewholder extends RecyclerView.ViewHolder  {

        TextView  nametx, pricetx,total;
        ImageView url;
        List<productData> datas = new ArrayList<productData>();
        Context context;

        public MyViewholder(View itemView, Context ctx, List<productData> datas) {
            super(itemView);
            this.datas = datas;
            context = ctx;

            nametx = (TextView) itemView.findViewById(R.id.tx_product_name);
            pricetx = (TextView) itemView.findViewById(R.id.tx_price);
            url = (ImageView) itemView.findViewById(R.id.imageView);
            total = (TextView) itemView.findViewById(R.id.tx_total_price);



        }



    }
}
