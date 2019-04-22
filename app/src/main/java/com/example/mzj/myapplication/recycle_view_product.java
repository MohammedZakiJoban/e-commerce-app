package com.example.mzj.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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

public class recycle_view_product extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String url = "http://10.0.3.2/get_product_table.php";
    private productAdapter adapter;
    Context  context = this;
    Dialog dialog;
    EditText tvname ;
    EditText tvprice;
    String name;
    String price;
    String stock;
    EditText tvstock ;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_product);
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
                List<productData> list = new ArrayList<productData>();
                list = Arrays.asList(mGson.fromJson(response, productData[].class));
                adapter = new productAdapter(list, context );
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
        dialog.setContentView(R.layout.dialog_add_product);
        dialog.setTitle("Add new product");

        context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvname = (EditText) dialog.findViewById(R.id.etname);
                tvprice = (EditText) dialog.findViewById(R.id.etprice);
                tvstock = (EditText) dialog.findViewById(R.id.etstock);
                name = tvname.getText().toString();
                price = tvprice.getText().toString();
                stock = tvstock.getText().toString();


                product_update update = new product_update(context);
                update.execute("add",name,price,stock);
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();





    }




    public void deletes(View view) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_product);
        dialog.setTitle("delete");

        tvname = (EditText) dialog.findViewById(R.id.etname);
        tvprice = (EditText) dialog.findViewById(R.id.etprice);
        tvstock = (EditText) dialog.findViewById(R.id.etstock);

        tvname.setHint("ID");
        tvprice.setVisibility(View.INVISIBLE);
        tvstock.setVisibility(View.INVISIBLE);


        context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);
        dialogButton.setText("delete");


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = tvname.getText().toString();


                if (id == null){
                    Toast.makeText(context,"Please enter ID to delete",Toast.LENGTH_LONG).show();
                }
                else {

                    product_update update = new product_update(context);
                    update.execute("delete",id);
                    dialog.dismiss();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        dialog.show();

    }
}
