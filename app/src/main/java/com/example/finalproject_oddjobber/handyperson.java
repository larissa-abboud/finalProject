package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class handyperson extends AppCompatActivity {
    TextView user_chosen;
    Button show_handy,save_user;
    ListView my_list;
    ArrayList<String> the_list;
    ArrayAdapter<String> adapter;
    Spinner dropdown;
    String text_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyperson);
        user_chosen = (TextView) findViewById(R.id.user);
        show_handy = (Button) findViewById(R.id.showusers);
        save_user = (Button) findViewById(R.id.saveuser);
        my_list = (ListView) findViewById(R.id.myList);
        dropdown =  findViewById(R.id.users_saved);

        String[] options = new String[]{"HANDYPERSON1","HANDYPERSON2"};

        the_list = new ArrayList<String>(Arrays.asList("HANDYPERSON1","HANDYPERSON2"));
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
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, the_list);
        my_list.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"choose from the list" , Toast.LENGTH_LONG).show();
                //the_list.get(i)
            }
        });
        //the_list.add("I'm adding from JAVA");
        my_list.setAdapter(adapter);
        dropdown.setAdapter(adapter);
        //get api,list
    }
    public void save(View v){
        text_list = dropdown.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), text_list, Toast.LENGTH_LONG).show();
        //post api, choose from spinner
        home(v);
        //cond =  cannot save user twice, in php(check if added is already in list)
    }
}