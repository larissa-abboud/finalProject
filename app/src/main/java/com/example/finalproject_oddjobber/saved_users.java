package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class saved_users extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_users);
    }
    public void creat_form_to_book(View view){
        //with object username
        Intent intent = new Intent(getApplicationContext(), creatForm.class);
        startActivity(intent);
    }
    public void load_saved_users(View view){
        /*get saved from get api, lists onto screen and stores usernames in spinner*/
        //create list
        //create spinner
        
    }

}