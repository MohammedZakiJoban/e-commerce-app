package com.example.mzj.myapplication;

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

public class recycler_view_order extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private orderAdpater adapter;
    Context context = this;
    private final String TAG = "recycler_view_order";
    Dialog dialog;
    EditText tvdate ;
    EditText tvprice;
    String date;
    String price;
    String username;
    EditText tvusername ;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_order);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        getinformation();
    }

    private void getinformation(){
    RequestQueue queue = Volley.newRequestQueue(this);
    String url ="http://10.0.3.2/get_order_table.php";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(TAG, "Response " + response);
            GsonBuilder builder = new GsonBuilder();
            Gson mGson = builder.create();
            List<orderData> list = new ArrayList<orderData>();
            list = Arrays.asList(mGson.fromJson(response, orderData[].class));
            adapter = new orderAdpater(list, context );
            recyclerView.setAdapter(adapter);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, "Error " + error.getMessage());
        }
    });
        queue.add(stringRequest);
}

    public void add(View view) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_orders);
        dialog.setTitle("Add new order");

        context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdate = (EditText) dialog.findViewById(R.id.etdate);
                tvprice = (EditText) dialog.findViewById(R.id.etprice);
                tvusername = (EditText) dialog.findViewById(R.id.etusername);
                date = tvdate.getText().toString();
                price = tvprice.getText().toString();
                username = tvusername.getText().toString();


                order_update update = new order_update(context);
                update.execute("add",date,price,username);
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();





    }




    public void deletes(View view) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_orders);
        dialog.setTitle("delete");

        tvdate = (EditText) dialog.findViewById(R.id.etdate);
        tvprice = (EditText) dialog.findViewById(R.id.etprice);
        tvusername = (EditText) dialog.findViewById(R.id.etusername);

        tvdate.setHint("ID");
        tvprice.setVisibility(View.INVISIBLE);
        tvusername.setVisibility(View.INVISIBLE);


        context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);
        dialogButton.setText("delete");


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = tvdate.getText().toString();


                if (id == null){
                    Toast.makeText(context,"Please enter ID to delete",Toast.LENGTH_LONG).show();
                }
                else {

                    order_update update = new order_update(context);
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
