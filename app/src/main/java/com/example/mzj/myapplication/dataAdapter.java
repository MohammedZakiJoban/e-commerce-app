package com.example.mzj.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MZJ on 10/5/2017.
 */

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewholder>{

   static Dialog dialog;
    static EditText tvusername ;
    static EditText tvpassword ;

    private List<data> list = new ArrayList<>();
    Context context;

    dataAdapter(List<data> list, Context ctx){
        this.list = list;
        context = ctx;
    }
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veiw,parent,false);

        return new MyViewholder(view,context,list);
    }



    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

        holder.idtx.setText(Integer.toString(list.get(position).getId()));

        holder.usernametx.setText(list.get(position).getUsername());
        holder.passwordtx.setText(list.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView idtx,usernametx,passwordtx;
        List<data> datas = new ArrayList<data>();
        Context context;

        public MyViewholder(View itemView, Context ctx, List<data> datas){
            super(itemView);
            this.datas = datas;
            context = ctx;
            itemView.setOnClickListener(this);
            idtx = (TextView)itemView.findViewById(R.id.tx_id);
            usernametx = (TextView)itemView.findViewById(R.id.tx_username);
            passwordtx = (TextView)itemView.findViewById(R.id.tx_password);




        }

        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
            data datas = this.datas.get(postion);

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_add_admin);
            dialog.setTitle("update user");

            tvusername = (EditText) dialog.findViewById(R.id.etusername);
            tvpassword = (EditText) dialog.findViewById(R.id.etpassword);

            tvusername.setText(datas.getUsername());
            tvpassword.setText(datas.getPassword());
            Button dialogButton = (Button) dialog.findViewById(R.id.add);
            dialogButton.setText("UPDATE");

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

               String username = tvusername.getText().toString();
               String password = tvpassword.getText().toString();


                admin_update update = new admin_update(context);
                update.execute("edit",username,password);


                    dialog.dismiss();
                    Intent in = new Intent(context,recycler_view.class);
                    context.startActivity(in);


                }
            });
            dialog.show();




        }
    }
}
