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

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;



public class MainActivity extends AppCompatActivity {
    EditText username,password ;
    Button login;
    boolean flag = false ; // will indicate the result of the api
    String url = "http://192.168.1.104/finalProject/server_db/testapi.php";
    //post
    protected static class DownloadInfo extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Accept", "*/*");

                connection.setDoOutput(true);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                writer.write(params[1]);
                writer.close();

                connection.connect();

                // Response: 400
                Log.e("Response", connection.getResponseMessage() + "");

            } catch (Exception e) {
                Log.e(e.toString(), "Something with request");
            }

            return null;
        }
    }
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
                String created_at = json.getString("created_at");
                String response= json.getString("error");
                String todo= json.getString("message");
                Log.i("Created At", created_at);
                Log.i("Joke", response);


            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.usernametext);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.log_in);
        //String amount =  ""; //get the amount from the view


        DownloadTask task = new DownloadTask();
        task.execute(url);

    }
    public void clickHelloWorld (View view) {

        DownloadInfo downloadInfoOfWeather = new DownloadInfo();




        downloadInfoOfWeather.execute(url, "q=select wind from weather.forecast where woeid=2460286&format=json");

    }
    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), homepage.class);
        startActivity(intent);
    }
    public void signup(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
    public void LogIn(View v){
        DownloadTask task = new DownloadTask();
        task.execute(url);

        // api reads from edit text
        if(flag){
            username.setText("");
            password.setText("");
            signup(v);

        }else {
            username.setText("");
            password.setText("");
            home(v);
        }
        /**obtain from as : username  password
         * using api , check if user reg
         * yes = go to home page
         * no  = go to sign up with taost */




    }

}