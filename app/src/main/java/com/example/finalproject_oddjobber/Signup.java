package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup extends AppCompatActivity {
    EditText username,password,bio ,name;
    Button signup;
    String todo , response ;

    public  class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls){
            String result = "";

            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while( data != -1){
                    char current = (char) data;
                    result = result + current;
                    data = reader.read();
                    //Log.i("msg", result);


                }

            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s);

                String responses = json.getString("error");
                todo = json.getString("message");
                Log.i("status", responses);
                Log.i("msg", todo);
                response = responses;


            }catch(Exception e){
                e.printStackTrace();
            }
            //Log.i("response", response);
        }

    }
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
        //Login(v);



    }
}