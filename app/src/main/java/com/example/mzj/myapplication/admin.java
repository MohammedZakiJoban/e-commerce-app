package com.example.mzj.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.setTitle("Admin");
    }



    public void getfile(View view) {

        Intent in = new Intent(getApplicationContext(), recycler_view.class);

        startActivity(in);

    }

    public void getCustoemr(View view) {

        Intent in = new Intent(getApplicationContext(), recycle_view_customer.class);

        startActivity(in);
    }

    public void getProduct(View view) {

        Intent in = new Intent(getApplicationContext(), recycle_view_product.class);

        startActivity(in);
    }

    public void sales(View view) {

        Intent in = new Intent(getApplicationContext(), recycle_view_sales_report.class);

        startActivity(in);
    }


    public void order(View view) {
        Intent in = new Intent(getApplicationContext(), recycler_view_order.class);

        startActivity(in);
    }

    public void custoemrReport(View view) {

        Intent in = new Intent(getApplicationContext(), recycler_view_customer_report.class);

        startActivity(in);
    }

    public void inventory(View view) {


        Intent in = new Intent(getApplicationContext(), recycler_view_inventory.class);

        startActivity(in);
    }
}
