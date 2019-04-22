package com.example.mzj.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MZJ on 11/18/2017.
 */

public class shopAdapter extends  RecyclerView.Adapter<shopAdapter.MyViewholder> {






    static String name;


    private static List<productData> list = new ArrayList<>();


    private Context context;



    shopAdapter(List<productData> list, Context ctx){
        this.list = list;
        context = ctx;
    }



    @Override
    public shopAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_layout,parent,false);
        return new shopAdapter.MyViewholder(view,context,list);
    }

    @Override
    public void onBindViewHolder(shopAdapter.MyViewholder holder, int position) {


        holder.nametx.setText(list.get(position).getName());
        holder.pricetx.setText("RM"+Integer.toString(list.get(position).getPrice()));
        Picasso.with(context).load(list.get(position).getPicture()).resize(150,150).into(holder.url);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView  nametx, pricetx;
        ImageView url;
        List<productData> datas = new ArrayList<productData>();
        Context context;


        public MyViewholder(View itemView, Context ctx, List<productData> datas) {
            super(itemView);
            this.datas = datas;
            context = ctx;
            itemView.setOnClickListener(this);
            nametx = (TextView) itemView.findViewById(R.id.tx_product_name);
            pricetx = (TextView) itemView.findViewById(R.id.tx_price);
            url = (ImageView) itemView.findViewById(R.id.imageView);



        }


        @Override
        public void onClick(View v) {

            int postion = getAdapterPosition();
            final productData datas = this.datas.get(postion);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Add to cart");
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    String name = datas.getName();
                    String price = String.valueOf(datas.getPrice());
                    String picture = datas.getPicture();

                    shopping_cart_update update= new shopping_cart_update(context);
                    update.execute("add",name,price,picture);
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });


            AlertDialog alertDialog = builder.create();
            alertDialog.show();





        }



    }



}

