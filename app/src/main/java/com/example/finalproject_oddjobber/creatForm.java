package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class creatForm extends AppCompatActivity {
    Button send;
    EditText time_app , context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_form);
        time_app = (EditText) findViewById(R.id.time);
        context = (EditText) findViewById(R.id.details);
        send = (Button) findViewById(R.id.gohomepage);
        Intent x = getIntent();
        String name = x.getStringExtra("username");
        Toast.makeText(getApplicationContext(), " " , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), name + " is the handyperson" , Toast.LENGTH_LONG).show();

    }
    public void status(View view){
        Intent intent = new Intent(getApplicationContext(), thanksBook.class);
        startActivity(intent);
    }
    public void sendForm(View v){

        /**
         * post api :context , time_app
         * username obtained from saved user intent
         * goes to status
         * */
        status(v);
        /*
        *  */
    }

}