package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;



public class MainActivity extends AppCompatActivity {
    EditText username,password ;
    Button login;
    String todo;
    String response;
    Button cont;
    boolean flag = false ; // will indicate the result of the api
    String url = "http://10.31.200.210/finalProject/server_db/loginverification.php";
    //post
    DownloadTask task;
    //get
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
                Log.i("error ", responses);
                Log.i("msg ", todo);
                response = responses;


            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.usernametexts);
        password = (EditText) findViewById(R.id.passwords);
        login = (Button) findViewById(R.id.log_in);
        cont = (Button) findViewById(R.id.button);

        task = new DownloadTask();

        cont.setAlpha(0);



    }




    public void display(View view){
        Intent intent = new Intent(getApplicationContext(), displayUser.class);
        startActivity(intent);
    }
    public void signup(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
    public void LogIn(View v) {
        //link of post api
        String llink = "http://10.31.200.210/finalProject/server_db/loginverification.php" + "?username=" + username.getText().toString() + "&" + "pass=" + password.getText().toString();

        task.execute(llink);
        cont.setAlpha(1);


    }




    public void contt(View v){

            // api reads from edit text

            if(response.equalsIgnoreCase("false")){
                username.setText("");
                password.setText("");
                display(v);
                cont.setAlpha(0);

            }else {
                cont.setAlpha(0);
                username.setText("");
                password.setText("");
                if(todo.equalsIgnoreCase("incorrect password or username")){
                    Toast.makeText(getApplicationContext(),"incorrect username or password" , Toast.LENGTH_LONG).show();

                }else if (todo.equalsIgnoreCase("missing values")) {
                    Toast.makeText(getApplicationContext(),"missing values" , Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getApplicationContext(),"Register to log in" , Toast.LENGTH_LONG).show();
                    signup(v);


                }

                 //op = wrong pass
                //op = wring username
                //op = doesn't exist
            }
            /**obtain from as : username  password
             * using api , check if user reg
             * yes = go to home page
             * no  = go to sign up with taost or stay with taost if feilds incorrect */




        }}

