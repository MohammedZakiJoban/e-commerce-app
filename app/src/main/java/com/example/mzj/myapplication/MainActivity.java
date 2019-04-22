package com.example.mzj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText usernameet ;
    EditText passwordet ;
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Login page");

         usernameet = (EditText) findViewById(R.id.etuser);
       passwordet = (EditText) findViewById(R.id.etpass);
    }

    public void Loginbtt(View view) {

        username= usernameet.getText().toString();
        String password= passwordet.getText().toString();
        String type = "login";

         Login login = new Login(this);
        login.execute(type,username,password);




    }

    public void register1(View view) {

        startActivity(new Intent(this,Register.class));
    }
}
