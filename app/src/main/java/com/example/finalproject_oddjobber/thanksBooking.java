package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class thanksBooking extends AppCompatActivity {
    Button next;
    TextView result;
    String responseo;
    String todoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_booking);
        next = (Button) findViewById(R.id.button2);
        result = (TextView) findViewById(R.id.result);


        Intent x = getIntent();
        String nameo = x.getStringExtra("username");
        todoo = x.getStringExtra("todo");
        responseo = x.getStringExtra("response");
        Toast.makeText(getApplicationContext(), nameo, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), responseo , Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), todoo , Toast.LENGTH_LONG).show();
        result.setText(todoo);


    }

    public void goLogin(View v) {
        if(responseo.equalsIgnoreCase("false")) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    else

    {
        if(todoo.equalsIgnoreCase("already reg")){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }else{
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);}


    }
}





}