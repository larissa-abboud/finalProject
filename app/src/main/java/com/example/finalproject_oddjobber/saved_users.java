package com.example.finalproject_oddjobber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class saved_users extends AppCompatActivity {
    String username ;
    TextView user;
    Button show_saved,create_app;
    ListView my_list;
    ArrayList<String> the_list;
    ArrayAdapter<String> adapter;
    Spinner dropdown;
    String text_list;
    String[] options;
    String url = "http://192.168.1.104/finalProject/server_db/showSaved.php";
    String result;
    String response[];
    public  class DownloadTask extends AsyncTask<String, Void, String> {
        //run in parallel with the app
        protected String doInBackground(String... urls){
            //preexecute
            //url sent here
            result = "";

            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);//getlogin.php
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
                    //not optimal
                    //get all input into string
                    //Log.i("result",result);


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
                //parse data
                JSONArray json = new JSONArray(s);
                for(int i=0; i < json.length(); i++) {
                    JSONObject jsonobject = json.getJSONObject(i);
                    //String id       = jsonobject.getString("id");
                    String handy_person    = jsonobject.getString("handy_person");
                   // Log.i("list", id);
                    Log.i("list", handy_person);

                }



                //response = json.getString("handy_person");

                //options = response;
                //display.setText(response);
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
        setContentView(R.layout.activity_saved_users);
        user = (TextView) findViewById(R.id.user);
        show_saved = (Button) findViewById(R.id.showsaved);
        create_app = (Button) findViewById(R.id.bookUser);
        my_list = (ListView) findViewById(R.id.myList);
        dropdown =  findViewById(R.id.users);
        DownloadTask task = new DownloadTask();
        task.execute(url);

        /*the_list = new ArrayList<String>();
        the_list.add("Mobile Computing");
        the_list.add("Database Management Systems");
        the_list.add("Software Engineering");*/
        //("Mobile Computing", "Database Management Systems", "Software Engineering")
         options = new String[]{"HANDYPERSON1","HANDYPERSON2"};

        the_list = new ArrayList<String>(Arrays.asList(options)); //will store
        /*
        * name,username , bio*/
        //ArrayAdapter <String> languages = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        //languages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //dropdown.setAdapter(languages);





    }
    public void addList(View view){
        //load list from database
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

    }

    public void creat_form_to_book(View view){
        text_list = dropdown.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), text_list, Toast.LENGTH_LONG).show();

        //with object username
        username = text_list;

        Intent obj = new Intent(getApplicationContext(), creatForm.class);
        obj.putExtra("username", username);
        startActivity(obj);

    }
    public void load_saved_users(View view){
        //


        /*get saved from get api, lists onto screen and stores usernames in spinner*/
        //create list
        //create spinner
        //cond =  cannot book an appointment twice for the same user , in php(check if added is already in list)

    }

}