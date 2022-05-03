package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class handyperson extends AppCompatActivity {
    TextView user_chosen;
    Button show_handy,save_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyperson);
        user_chosen = (TextView) findViewById(R.id.user);
        show_handy = (Button) findViewById(R.id.showusers);
        save_user = (Button) findViewById(R.id.saveuser);
    }
    /*
    * show handyperson , get api , lsit users and adds to spinner array list
    * spinner of user
    * save user (chosen_user) post api,goes to homepage*/
    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), homepage.class);
        startActivity(intent);
    }

    public void show_handyperson_list(View v){
        //get api,list
    }
    public void save(View v){
        //post api, choose from spinner
        home(v);
        //cond =  cannot save user twice, in php(check if added is already in list)
    }
}