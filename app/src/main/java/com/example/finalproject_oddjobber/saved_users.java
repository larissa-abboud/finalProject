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
    String options;
    String url = "http://192.168.1.104/finalProject/server_db/showSaved.php";
    String result;
    String[] response;
    int size;
    boolean flag ;
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
                int x=0;
                //parse data

                JSONObject json = new JSONObject(s);
                String check = json.getString("empty");
                if(check.equalsIgnoreCase("empty")){
                    Log.i("list", check);
                    flag = true;}
                else{
                    //flag = true;
                    Log.i("list", check);
                size = json.length();
                response = new String[size-1];// -1 to not get null pointer in list view
                while( x < json.length()-1) {
                    //fix

                    x++;
                    String result = json.getString("handy_person" + x);
                    Log.i("list", result);
                    response[x - 1] = result;

                }







                }


                //response= json.getString("user");
               // Log.i("current user", response);
                    //Log.i("list", jsonobject.getString("handy_person"));





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




         //options =""+ size;

        //the_list = new ArrayList(size);
        ///Log.i("x",options );
       //the_list = new ArrayList<String>(size);
        //int i = 0;
        //while (i!= size-1){
        DownloadTask task = new DownloadTask();
        task.execute(url);
          // the_list.add(response[i]);
          //  i++;


       // }



        /*;
        the_list.add("Mobile Computing");
        the_list.add("Database Management Systems");
        the_list.add("Software Engineering");*/
        //("Mobile Computing", "Database Management Systems", "Software Engineering")
         //options = new String[]{};




        /*
        * name,username , bio*/
        //ArrayAdapter <String> languages = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        //languages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //dropdown.setAdapter(languages);





    }
    public void addList(View view){
        //String[] options = new String[]{size};
       // Log.i("list", response[0]);


        if(flag){
            Toast.makeText(getApplicationContext(), "not saved", Toast.LENGTH_LONG).show();
        }else{

            //Toast.makeText(getApplicationContext(),response[3] , Toast.LENGTH_LONG).show();
            the_list = new ArrayList<String>(Arrays.asList(response));
            Toast.makeText(getApplicationContext(),response[0] +the_list.get(0) , Toast.LENGTH_LONG).show();
            ;

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,the_list);
            my_list.setAdapter(adapter);



        //load list from database

       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //the_list.add("I'm adding from JAVA");
        my_list.setAdapter(adapter);
        dropdown.setAdapter(adapter);

    }}

    public void creat_form_to_book(View view){
        text_list = dropdown.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), text_list, Toast.LENGTH_LONG).show();

        //with object username
        username = text_list;

        Intent obj = new Intent(getApplicationContext(), creatForm.class);
        obj.putExtra("username", username);
        startActivity(obj);

    }

        //


        /*get saved from get api, lists onto screen and stores usernames in spinner*/
        //create list
        //create spinner
        //cond =  cannot book an appointment twice for the same user , in php(check if added is already in list)

    }

