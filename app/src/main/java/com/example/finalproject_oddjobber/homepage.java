package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        logout = (Button) findViewById(R.id.goLogin);
    }
    public void Login(View view){
        Intent intent = new Intent(getApplicationContext(), mainHome.class);
        startActivity(intent);
    }
    public void savedUsers(View v){
        Intent intent = new Intent(getApplicationContext(), saved_users.class);
        startActivity(intent);

    }
    public void users(View view){
        Intent intent = new Intent(getApplicationContext(), handyperson.class);
        startActivity(intent);
    }
}