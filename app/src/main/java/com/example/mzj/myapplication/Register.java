package com.example.mzj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class Register extends AppCompatActivity {
    EditText nameet,passwordet,ageet,addresset,emailet, phoneet,usernameet;
    String name,password,age,address,email,phone,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nameet = (EditText)findViewById(R.id.etname);
        passwordet = (EditText)findViewById(R.id.etpassword);
        ageet = (EditText)findViewById(R.id.etage);
        addresset = (EditText)findViewById(R.id.etaddress);
        emailet = (EditText)findViewById(R.id.etemail);
        phoneet = (EditText)findViewById(R.id.etphone);
        usernameet = (EditText)findViewById(R.id.etusername);



    }

    public void register(View view) {

        name = nameet.getText().toString();
        age = ageet.getText().toString();
        address = addresset.getText().toString();
        email = emailet.getText().toString();
        phone = phoneet.getText().toString();
        username = usernameet.getText().toString();
        password = passwordet.getText().toString();



        String type = "register";
        Login login = new Login(this);
        login.execute(type, username, password, name,age,address,email,phone);


    }
}
