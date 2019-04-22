package com.example.mzj.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MZJ on 11/6/2017.
 */

public class productAdapter extends RecyclerView.Adapter<productAdapter.MyViewholder>
{
    static Dialog dialog;

    private List<productData> list = new ArrayList<>();
    private Context context;

    static EditText tvname ;
    static EditText tvprice;
    static EditText tvstock ;

    productAdapter(List<productData> list, Context ctx){
        this.list = list;
        context = ctx;
    }
    @Override
    public productAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new MyViewholder(view,context,list);
    }



    @Override
    public void onBindViewHolder(productAdapter.MyViewholder holder, int position) {


        holder.idtx.setText(Integer.toString(list.get(position).getId()));
        holder.nametx.setText(list.get(position).getName());
        holder.pricetx.setText(Integer.toString(list.get(position).getPrice()));
        holder.stocktx.setText(Integer.toString(list.get(position).getStock()));

        Picasso.with(context).load(list.get(position).getPicture()).resize(75,50).into(holder.url);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView idtx,nametx,pricetx,stocktx;
       ImageView url;
        List<productData> datas = new ArrayList<productData>();
        Context context;

        public MyViewholder(View itemView, Context ctx, List<productData> datas){
            super(itemView);
            this.datas = datas;
            context = ctx;
            itemView.setOnClickListener(this);
            idtx = (TextView)itemView.findViewById(R.id.tx_id);
            nametx = (TextView)itemView.findViewById(R.id.tx_name);
            pricetx = (TextView)itemView.findViewById(R.id.tx_price);
            stocktx = (TextView)itemView.findViewById(R.id.tx_stock);
            url = (ImageView)itemView.findViewById(R.id.imageView2);




        }
        @Override
        public void onClick(View v) {
            dialog = new Dialog(context);
            int postion = getAdapterPosition();
            productData datas = this.datas.get(postion);


            dialog.setContentView(R.layout.dialog_add_product);
            dialog.setTitle("update user");

            tvname = (EditText) dialog.findViewById(R.id.etname);
            final String id = String.valueOf(datas.getId());
            tvprice = (EditText) dialog.findViewById(R.id.etprice);
            tvstock = (EditText) dialog.findViewById(R.id.etstock);

            tvname.setText(datas.getName());
            tvprice.setText(String.valueOf(datas.getPrice()));
            tvstock.setText(String.valueOf(datas.getStock()));

            Button dialogButton = (Button) dialog.findViewById(R.id.add);
            dialogButton.setText("UPDATE");

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = tvname.getText().toString();
                    String price = tvprice.getText().toString();
                    String stock = tvstock.getText().toString();


                    product_update update= new product_update(context);
                    update.execute("edit",name,price,stock,id);

                    dialog.dismiss();
                    Intent in = new Intent(context,recycle_view_product.class);
                    context.startActivity(in);


                }
            });

            dialog.show();




        }
    }

}
