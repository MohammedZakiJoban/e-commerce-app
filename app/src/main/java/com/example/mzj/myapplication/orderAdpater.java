package com.example.mzj.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MZJ on 11/22/2017.
 */

public class orderAdpater extends RecyclerView.Adapter<orderAdpater.MyViewholder> {

    static Dialog dialog;
    static EditText tvdate ;
    static EditText tvprice;
    static EditText tvusername ;

    private List<orderData> list = new ArrayList<>();
    private Context context;

    public orderAdpater(List<orderData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public orderAdpater.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout,parent,false);
        return new MyViewholder(view,context,list);
    }

    @Override
    public void onBindViewHolder(orderAdpater.MyViewholder holder, int position) {



        holder.idtx.setText(list.get(position).getId());
        holder.datetx.setText(list.get(position).getDate_time());
        holder.totaltx.setText(list.get(position).getTotal());
        holder.usernametx.setText(list.get(position).getUsername());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView idtx,datetx,totaltx,usernametx;
        ImageView url;
        List<orderData> datas = new ArrayList<orderData>();
        Context context;

        public MyViewholder(View itemView, Context ctx, List<orderData> datas){
            super(itemView);
            this.datas = datas;
            context = ctx;
            itemView.setOnClickListener(this);
            idtx = (TextView)itemView.findViewById(R.id.tx_id);
            datetx = (TextView)itemView.findViewById(R.id.tx_date);
            totaltx = (TextView)itemView.findViewById(R.id.tx_total);
            usernametx = (TextView)itemView.findViewById(R.id.tx_usernames);





        }
        @Override
        public void onClick(View v) {

            dialog = new Dialog(context);
            int postion = getAdapterPosition();
            orderData datas = this.datas.get(postion);


            dialog.setContentView(R.layout.dialog_orders);
            dialog.setTitle("update user");

            tvdate = (EditText) dialog.findViewById(R.id.etdate);
            final String id = String.valueOf(datas.getId());
            tvprice = (EditText) dialog.findViewById(R.id.etprice);
            tvusername = (EditText) dialog.findViewById(R.id.etusername);

            tvdate.setText(datas.getDate_time());
            tvprice.setText(datas.getTotal());
            tvusername.setText(datas.getUsername());

            Button dialogButton = (Button) dialog.findViewById(R.id.add);
            dialogButton.setText("UPDATE");

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String date = tvdate.getText().toString();
                    String price = tvprice.getText().toString();
                    String username = tvusername.getText().toString();


                    order_update update= new order_update(context);
                    update.execute("edit",date,price,username,id);

                    dialog.dismiss();
                    Intent in = new Intent(context,recycler_view_order.class);
                    context.startActivity(in);


                }
            });

            dialog.show();



        }
    }
}
