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
    DownloadTask task ;
    Button n ;

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
        n = (Button) findViewById(R.id.button6);
        task = new DownloadTask();
        n.setAlpha(0);
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), homepage.class);
        startActivity(intent);
    }
    public void signuphelp(View v){
        Intent obj = new Intent(getApplicationContext(), thanksBooking.class);
        obj.putExtra("username", username.getText().toString());
        obj.putExtra("todo", todo);
        obj.putExtra("response", response);
        startActivity(obj);
       // Intent intent = new Intent(getApplicationContext(), thanksBooking.class);
        //startActivity(intent);
    }
    public void signup(View v){
        String llink = "http://192.168.1.104/finalProject/server_db/regUser.php" + "?username=" + username.getText().toString() + "&" + "pass=" + password.getText().toString()+ "&" + "full_name=" + name.getText().toString()+ "&" + "bio=" + bio.getText().toString();

        task.execute(llink);
        //signuphelp(v);
        n.setAlpha(1);


        //intent to thanks book
        //intent  todo , response,


        //post api to register user
        //go back to logged in
        //Login(v);



    }
}