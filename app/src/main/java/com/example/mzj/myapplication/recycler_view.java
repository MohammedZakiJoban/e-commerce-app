package com.example.mzj.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class recycler_view extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String url = "http://10.0.3.2/get_username_table.php";
    private dataAdapter adapter;
    EditText tvusername ;
    EditText tvpassword ;
    String username;
    String password;
    String id;
    Context  context = this;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        this.setTitle("Admin table");

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
                List<data> list = new ArrayList<data>();
                list = Arrays.asList(mGson.fromJson(response, data[].class));
                adapter = new dataAdapter(list, context );
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
        dialog.setContentView(R.layout.dialog_add_admin);
        dialog.setTitle("Add new user");

         context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvusername = (EditText) dialog.findViewById(R.id.etusername);
                tvpassword = (EditText) dialog.findViewById(R.id.etpassword);
                username = tvusername.getText().toString();
                password = tvpassword.getText().toString();


                admin_update update = new admin_update(context);
                update.execute("add",username,password);
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();





    }




    public void deletes(View view) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_admin);
        dialog.setTitle("delete");
        tvusername = (EditText) dialog.findViewById(R.id.etusername);
        tvusername.setHint("ID");
        tvpassword = (EditText) dialog.findViewById(R.id.etpassword);
        tvpassword.setVisibility(View.INVISIBLE);


        context = this;

        Button dialogButton = (Button) dialog.findViewById(R.id.add);
        dialogButton.setText("delete");


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = tvusername.getText().toString();


                if (id == null){
                    Toast.makeText(context,"Please enter ID to delete",Toast.LENGTH_LONG).show();
                }
                else {

                    admin_update update = new admin_update(context);
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
 /*     TextView username;
        TextView password;
     Button but1;
        AlertDialog alert;
        LinearLayout layout;

        layout = new LinearLayout(this);
        username = new TextView(this);
        password = new TextView(this);
        but1 = new Button(this);
       // but1.setOnClickListener(this);
        layout.addView(but1);
        alert = new AlertDialog.Builder(this).create();
        setContentView(layout);
        username.setText("Test");





        addRecord a = new addRecord(this);
        a.execute();
*/