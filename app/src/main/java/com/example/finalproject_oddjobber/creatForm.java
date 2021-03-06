package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class creatForm extends AppCompatActivity {
    Button send, next;
    EditText time_app , context;
    String result ,todo;
    String url = "http://10.31.200.210/finalProject/server_db/BookApp.php";
    String name ,chosen;
    ImageView m ;
    //String responses;
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


                InputStream in = http.getInputStream();//problem??
                //Log.i("result",in.toString());
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
            //  Log.i("result",s);
            //api is executing
            super.onPostExecute(s);

            try{

                    JSONObject json2 = new JSONObject(s);
                    String che = json2.getString("id");
                Log.i("status",che);

                    String responses = json2.getString("error");
                    todo = json2.getString("message");
                    Log.i("error", responses);
                    Log.i("msg", todo);
                    chosen = responses;




            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_form);
        time_app = (EditText) findViewById(R.id.time);
        context = (EditText) findViewById(R.id.details);
        send = (Button) findViewById(R.id.gohomepage);
        next = (Button) findViewById(R.id.gocheck);
        m = (ImageView) findViewById(R.id.muki) ;

        Intent x = getIntent();
        name = x.getStringExtra("username");
        Toast.makeText(getApplicationContext(),  " Booking Appointment with" + name , Toast.LENGTH_LONG).show();
        next.setAlpha(0);
    }
    public void status(View view){
        Intent intent = new Intent(getApplicationContext(), thanksBook.class);
        startActivity(intent);
    }
    public void sendForm(View v){
        m.setX(-1000);
        m.animate().translationXBy(1000).rotation(3600).setDuration(2000);


        String link = url + "?time_needed="+time_app.getText().toString()+ "&"+ "details="+context.getText().toString()+"&"+"for_user="+name;
        DownloadTask task = new DownloadTask();

        task.execute(link);

        Log.i("name", name);
        Log.i("time", time_app.getText().toString());
        Log.i("d", context.getText().toString());
        next.setAlpha(1);


        /**
         * post api :context , time_app ,for user
         * username obtained from saved user intent
         * goes to status
         * */

    }
    public void check(View v){
        Log.i("status", chosen);
        if(todo.equalsIgnoreCase("Apointment booked")){
            status(v);
        }else{
            Toast.makeText(getApplicationContext(),todo  , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), saved_users.class);
            startActivity(intent);
        }
    }



}