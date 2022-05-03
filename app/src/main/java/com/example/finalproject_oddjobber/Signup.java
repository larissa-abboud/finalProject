package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {
    EditText username,password,bio ,name;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.usernametexts);
        password = (EditText) findViewById(R.id.passwords);
        bio = (EditText) findViewById(R.id.biodetails);
        name = (EditText) findViewById(R.id.fullname);
        signup = (Button) findViewById(R.id.signup);
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), homepage.class);
        startActivity(intent);
    }
    public void Login(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void signup(View v){
        //post api to register user
        //go back to logged in
        Login(v);



    }
}