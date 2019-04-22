package com.example.mzj.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class recycler_view_customer_report extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private customer_report_adapter adapter;
    Context context = this;
    private final String TAG = "recycler_view_customer_report";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_customer_report);
        this.setTitle("Customer report");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getinformation();

    }
    private void getinformation(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.3.2/Customer_report.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                List<customer_report_data> list = new ArrayList<customer_report_data>();
                list = Arrays.asList(mGson.fromJson(response, customer_report_data[].class));
                adapter = new customer_report_adapter(list, context );
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
}
