package com.example.mzj.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by MZJ on 11/15/2017.
 */

public class admin_update  extends AsyncTask<String,String ,String> {
        Context context;

    admin_update (Context ctx){
        context = ctx;
        }




@Override
protected void onPostExecute(String result) {


        Toast.makeText(context,result, Toast.LENGTH_LONG).show();

        }

@Override
protected String doInBackground(String... params) {

        String urls;

        if (params[0].equals("add")){
        try {
        urls = "http://10.0.3.2/admin_add.php";
        String username =  params[1];
        String password =  params[2];



        URL url = new URL(urls);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStream ops = con.getOutputStream();
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
        String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
        bfw.write(post_data);
        bfw.flush();
        bfw.close();
        ops.close();
        InputStream ips = con.getInputStream();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));
        String result = "";
        String line;
        while ((line = bfr.readLine())!= null){
        result += line;

        }
        bfr.close();
        ips.close();
        con.disconnect();
        return result;
        } catch (MalformedURLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        else if (params[0].equals("edit")){
        try {

        urls = "http://10.0.3.2/admin_update.php";
        String username =  params[1];
        String password =  params[2];

        URL url = new URL(urls);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStream ops = con.getOutputStream();
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
        String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
        bfw.write(post_data);
        bfw.flush();
        bfw.close();
        ops.close();
        InputStream ips = con.getInputStream();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));
        String result = "";
        String line;
        while ((line = bfr.readLine())!= null){
        result += line;

        }
        bfr.close();
        ips.close();
        con.disconnect();
        return result;
        } catch (MalformedURLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }


        }

        else if (params[0].equals("delete")){
            try {

                urls = "http://10.0.3.2/admin_delete.php";
                String username =  params[1];




                URL url = new URL(urls);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                OutputStream ops = con.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");

                bfw.write(post_data);
                bfw.flush();
                bfw.close();
                ops.close();
                InputStream ips = con.getInputStream();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(ips,"iso-8859-1"));
                String result = "";
                String line;
                while ((line = bfr.readLine())!= null){
                    result += line;

                }
                bfr.close();
                ips.close();
                con.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else{

        }

    return null;
        }

        }

