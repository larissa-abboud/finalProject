package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class saved_users extends AppCompatActivity {
    String username ;
    TextView user;
    Button show_saved,create_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_users);
        user = (TextView) findViewById(R.id.user);
        show_saved = (Button) findViewById(R.id.showsaved);
        create_app = (Button) findViewById(R.id.bookUser);
    }
    public void creat_form_to_book(View view){

        //with object username

        Intent obj = new Intent(getApplicationContext(), creatForm.class);
        obj.putExtra("username", username);
        startActivity(obj);

    }
    public void load_saved_users(View view){
        //


        /*get saved from get api, lists onto screen and stores usernames in spinner*/
        //create list
        //create spinner
        //cond =  cannot book an appointment twice for the same user , in php(check if added is already in list)

    }

}