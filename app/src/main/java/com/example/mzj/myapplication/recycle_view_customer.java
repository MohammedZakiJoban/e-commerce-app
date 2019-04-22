package com.example.mzj.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class recycle_view_customer extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String url = "http://10.0.3.2/get_customer_table.php";
    private customerAdapter adapter;
    Context context = this;
    Dialog dialog;
    EditText nameet,usernameet,passwordet,emailet,ageet,phoneet,addresset ;

    String name,username,password,email,age,phone,address;


    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_customer);
        this.setTitle("Customer table");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        getinformation();
    }


    private void getinformation(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                List<customerData> list = new ArrayList<customerData>();
                list = Arrays.asList(mGson.fromJson(response, customerData[].class));
                adapter = new customerAdapter(list, context );
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    public void add(View view) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_customer);
        dialog.setTitle("Add new product");

        context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameet = (EditText) dialog.findViewById(R.id.et_name);
                usernameet=(EditText) dialog.findViewById(R.id.et_username);
                passwordet =(EditText) dialog.findViewById(R.id.et_password);
                emailet =(EditText) dialog.findViewById(R.id.et_email);
                ageet =(EditText) dialog.findViewById(R.id.et_age);
                phoneet =(EditText) dialog.findViewById(R.id.et_phone);
                addresset =(EditText) dialog.findViewById(R.id.et_address);


                        name = nameet.getText().toString();
                        username = usernameet.getText().toString();
                        password = passwordet.getText().toString();
                        email = emailet.getText().toString();
                        age = ageet.getText().toString();
                        phone = phoneet.getText().toString();
                        address = addresset.getText().toString();


                customer_update update = new customer_update(context);
                update.execute("add",name,username,password,email,age ,phone ,address);
                dialog.dismiss();
                Intent in = new Intent(context,recycle_view_customer.class);
                context.startActivity(in);
            }
        });
        dialog.show();

    }




    public void deletes(View view) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_customer);
        dialog.setTitle("delete");

        nameet = (EditText) dialog.findViewById(R.id.et_name);
        usernameet=(EditText) dialog.findViewById(R.id.et_username);
        passwordet =(EditText) dialog.findViewById(R.id.et_password);
        emailet =(EditText) dialog.findViewById(R.id.et_email);
        ageet =(EditText) dialog.findViewById(R.id.et_age);
        phoneet =(EditText) dialog.findViewById(R.id.et_phone);
        addresset =(EditText) dialog.findViewById(R.id.et_address);

        nameet.setHint("ID");
        usernameet.setVisibility(View.INVISIBLE);
        passwordet.setVisibility(View.INVISIBLE);
        emailet.setVisibility(View.INVISIBLE);
        ageet.setVisibility(View.INVISIBLE);
        phoneet.setVisibility(View.INVISIBLE);
        addresset.setVisibility(View.INVISIBLE);




        context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);
        dialogButton.setText("delete");


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = nameet.getText().toString();


                if (id == null){
                    Toast.makeText(context,"Please enter ID to delete",Toast.LENGTH_LONG).show();
                }
                else {

                    customer_update update = new customer_update(context);
                    update.execute("delete",id);
                    dialog.dismiss();
                    Intent in = new Intent(context,recycle_view_customer.class);
                    context.startActivity(in);
                }
            }
        });
        dialog.show();

    }

}
