package com.example.mzj.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
import java.util.Arrays;
import java.util.List;

public class shopping_cart extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private shoping_cart_adapter adapter;
    private String string_url = "http://10.0.3.2/shoping_cart_table.php";
    Context context;
    TextView total;



String username;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        setTitle("Shopping cart");


        recyclerView = (RecyclerView)findViewById(R.id.recyclerv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getinformation();
        price();

        Bundle extras = getIntent().getExtras();
        username = extras.getString("user");
        Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();



    }



    private void getinformation(){



        StringRequest stringRequest = new StringRequest(Request.Method.POST,string_url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();


                        List<productData> list = Arrays.asList(gson.fromJson(response,productData[].class));
                        adapter = new shoping_cart_adapter(list,context);
                        recyclerView.setAdapter(adapter);






                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQue(stringRequest);


    }

    public void order(View view) {


        order or = new order(this);
        or.execute("order",username);

        Toast.makeText(getApplicationContext(),"order is placed",Toast.LENGTH_LONG).show();
        Intent in = new Intent(getApplicationContext(),recycle_view_shop.class);
        in.putExtra("user",username);
        startActivity(in);

    }

    public void  price(){

        order or = new order(this);
        or.execute("total");
    }


public class order extends AsyncTask<String, String, String>{


    Context context;
    order(Context ctx){
        context = ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {



    }

    @Override
    protected String doInBackground(String... params) {

        if (params[0].equals("order")){

            placeOrder(params[1]);

        }
        else if (params[0].equals("total")) {

            total = (TextView) findViewById(R.id.tx_total_price);
            total.setText(gettotal());
        }


        return null;

    }

    public String gettotal(){

        try {

            String login_url = "http://10.0.3.2/get_price.php";
            URL url = new URL(login_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);

            InputStream ips = con.getInputStream();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(ips, "iso-8859-1"));
            String result = "";
            String line;
            while ((line = bfr.readLine()) != null) {
                result += line;

            }
            bfr.close();
            ips.close();
            con.disconnect();
            return result;
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public String placeOrder (String username){

        try {
            String price = gettotal();
            String user= username;
            String login_url = "http://10.0.3.2/order.php";
            URL url = new URL(login_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            OutputStream ops = con.getOutputStream();
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String post_data = URLEncoder.encode("product_price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8")+"&"
                    +URLEncoder.encode("User_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8") ;
            bfw.write(post_data);
            bfw.flush();
            bfw.close();
            ops.close();
            InputStream ips = con.getInputStream();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(ips, "iso-8859-1"));
            String result = "";
            String line;
            while ((line = bfr.readLine()) != null) {
                result += line;

            }
            bfr.close();
            ips.close();
            con.disconnect();
            return result;
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }
}



}
