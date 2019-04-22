package com.example.mzj.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
 * Created by MZJ on 9/20/2017.
 */

public class Login extends AsyncTask<String,String ,String> {


    Context context;

    Login (Context ctx){
        context = ctx;
    }
    private String username;
    public String getUsername (){

        return username;
    }
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(String result) {


        if (result.contains("admin access")){

            context.startActivity(new Intent(context,admin.class));
        }
        else if (result.contains("welcome")) {




            Intent in = new Intent(context,recycle_view_shop.class);
            in.putExtra("user",getUsername());
            context.startActivity(in);

            //context.startActivity(new Intent(context,recycle_view_shop.class));

        }

        else if (result.contains("insert success!!!")) {

            Toast.makeText(context,"Data entered", Toast.LENGTH_LONG).show();


        }
        else if (result.contains("username already exist")) {

            Toast.makeText(context,"user name already exist", Toast.LENGTH_LONG).show();

        }
        else if (result.contains("login not success")) {

            Toast.makeText(context,"Wrong username and password", Toast.LENGTH_LONG).show();

        }

        else {

            Toast.makeText(context,"Wrong username and password", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected String doInBackground(String... params) {
        String type =  params[0];
        String login_url = "http://10.0.3.2/login.php";
        String register_url = "http://10.0.3.2/register.php";


        if (type.equals("login")){
            try {
                username =  params[1];
                String pass =  params[2];

                URL url = new URL(login_url);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                OutputStream ops = con.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
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
        else if (type.equals("register")) {
            //type, username, password, name,age,address,email,phone
            try {
                String username =  params[1];
                String pass =  params[2];
                String name =  params[3];
                String age =  params[4];
                String address =  params[5];
                String email =  params[6];
                String phone =  params[7];

                URL url = new URL(register_url);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                OutputStream ops = con.getOutputStream();
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"
                        +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("phone_number","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                        +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
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
     return null;


    }

}
