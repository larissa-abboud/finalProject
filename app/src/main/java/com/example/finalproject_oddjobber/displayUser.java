package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class displayUser extends AppCompatActivity {
    Button home;
    TextView display;
    String url = "http://10.31.200.210/finalProject/server_db/getLogin.php";//10.31.200.210
    String result;
    String response;

    public  class DownloadTask extends AsyncTask<String, Void, String> {
        //run in parallel with the app
        protected String doInBackground(String... urls){
            //preexecute
            //url sent here
            result = "";

            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection(); //establishes the connection
               // http.setDoOutput(true);


                InputStream in = http.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read(); //cursor reads output api

                while( data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read(); //move cursor one more character



                }

            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }


        protected void onPostExecute(String s){

            //api is executing
            super.onPostExecute(s);

            try{
                //parse data

                JSONObject json = new JSONObject(s);

                response= json.getString("user");
                Log.i("Current user : ", response);
                display.setText(response);
                //update values
                //post,send values to api using the url
                //format:
                //?attribute=value&


            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);
        display = (TextView)  findViewById(R.id.userDsiplay);
        home = (Button) findViewById(R.id.gohomepage);

        DownloadTask task = new DownloadTask();
        task.execute(url); //linking to the api
        displaytheuser();


    }
    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), homepage.class);
        startActivity(intent);
    }
    public void displaytheuser(){
        display.setText("user");
        display.setText(response);


        //obtain user from api
        //sets display to user

    }


}