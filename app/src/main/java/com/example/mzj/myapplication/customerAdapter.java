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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MZJ on 11/6/2017.
 */

public class customerAdapter extends RecyclerView.Adapter<customerAdapter.MyViewholder> {

    static EditText nameet,usernameet,passwordet,emailet,ageet,phoneet,addresset ;
    static Dialog dialog;
    private Context context;
    private List<customerData> list = new ArrayList<>();

    customerAdapter(List<customerData> list, Context ctx){
        this.list = list;
        context = ctx;
    }
    @Override
    public customerAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item,parent,false);
        return new customerAdapter.MyViewholder(view,context,list);
    }



    @Override
    public void onBindViewHolder(customerAdapter.MyViewholder holder, int position) {

        holder.idtx.setText(Integer.toString(list.get(position).getId()));
        holder.nametx.setText(list.get(position).getName());
        holder.usernametx.setText(list.get(position).getUsername());
        holder.passwordtx.setText(list.get(position).getPassword());
        holder.agetx.setText(list.get(position).getAge());
        holder.emailtx.setText(list.get(position).getEmail());
        holder.phonetx.setText(list.get(position).getPhone_number());
        holder.addresstx.setText(list.get(position).getAddress());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView idtx,nametx,usernametx,passwordtx,agetx,emailtx,addresstx,phonetx;
        Context context;
        List<customerData> datas = new ArrayList<customerData>();
        public MyViewholder(View itemView, Context ctx, List<customerData> datas){
            super(itemView);
            this.datas = datas;
            context = ctx;
            itemView.setOnClickListener(this);
            idtx = (TextView)itemView.findViewById(R.id.tx_id);
            nametx = (TextView)itemView.findViewById(R.id.tx_name);
            usernametx = (TextView)itemView.findViewById(R.id.tx_username);
            passwordtx = (TextView)itemView.findViewById(R.id.tx_password);
            agetx = (TextView)itemView.findViewById(R.id.tx_age);
            emailtx = (TextView)itemView.findViewById(R.id.tx_email);
            phonetx = (TextView)itemView.findViewById(R.id.tx_phone);
            addresstx = (TextView)itemView.findViewById(R.id.tx_address);


        }
        @Override
        public void onClick(View v) {
            dialog = new Dialog(context);
            int postion = getAdapterPosition();
            customerData datas = this.datas.get(postion);


            dialog.setContentView(R.layout.dialog_add_customer);
            dialog.setTitle("update user");

            nameet = (EditText) dialog.findViewById(R.id.et_name);
            usernameet=(EditText) dialog.findViewById(R.id.et_username);
            passwordet =(EditText) dialog.findViewById(R.id.et_password);
            emailet =(EditText) dialog.findViewById(R.id.et_email);
            ageet =(EditText) dialog.findViewById(R.id.et_age);
            phoneet =(EditText) dialog.findViewById(R.id.et_phone);
            addresset =(EditText) dialog.findViewById(R.id.et_address);

            nameet.setText(datas.getName());
            usernameet.setText(datas.getUsername());
            passwordet.setText(datas.getPassword());
            emailet.setText(datas.getEmail());
            ageet.setText(datas.getAge());
            phoneet.setText( datas.getPhone_number());
            addresset.setText(datas.getAddress());
           final String id = String.valueOf(datas.getId());

            Button dialogButton = (Button) dialog.findViewById(R.id.add);
            dialogButton.setText("UPDATE");

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name,username,password,email,age,phone,address;

                    name = nameet.getText().toString();
                    username = usernameet.getText().toString();
                    password = passwordet.getText().toString();
                    email = emailet.getText().toString();
                    age = ageet.getText().toString();
                    phone = phoneet.getText().toString();
                    address = addresset.getText().toString();

                    customer_update update= new customer_update(context);
                    update.execute("edit",name,username,password,email,age ,phone ,address,id);

                    dialog.dismiss();
                    Intent in = new Intent(context,recycle_view_customer.class);
                    context.startActivity(in);


                }
            });

            dialog.show();




        }
    }
}
