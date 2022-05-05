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

public class handyperson extends AppCompatActivity {
    TextView user_chosen;
    Button show_handy,save_user , home;
    ListView my_list;
    ArrayList<String> the_list , the_list2;
    ArrayAdapter<String> adapter, adapter2;
    Spinner dropdown;
    boolean flag;
    int size ;
    String text_list , result ,response[] ,todo,options[];
    String url = "http://10.31.200.210/finalProject/server_db/displayUsers.php";
    String url_post = "http://10.31.200.210/finalProject/server_db/saveList.php";
    DownloadTask task ;
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

            super.onPostExecute(s);

            try{
                if( flag == true){
                    JSONObject json2 = new JSONObject(s);

                    String responses = json2.getString("error");
                    todo = json2.getString("message");
                    Log.i("error ", responses);
                    Log.i("msg", todo);
                    //chosen = responses;

                }
                else{
                int x=0;


                JSONObject json = new JSONObject(s);




                    size = json.length();
                    options = new String[(size)/2];
                    response = new String[(size)/2];// -1 to not get null pointer in list view
                    while( x < (json.length())/2) {


                        x++;
                        String result = json.getString("user" + x);
                        String result_bio = json.getString("" + x);
                        //Log.i("list", result + result_bio);
                        options[x-1] = result+result_bio;
                        Log.i(" user in list :", result);
                        response[x -1] = result;
                        //Log.i("res", response[x-1]);










                }}


            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyperson);
        user_chosen = (TextView) findViewById(R.id.user);
        show_handy = (Button) findViewById(R.id.showusers);
        save_user = (Button) findViewById(R.id.saveuser);
        my_list = (ListView) findViewById(R.id.myList);
        dropdown =  findViewById(R.id.users_saved);
        home = (Button) findViewById(R.id.gohomepage);
        flag = false;
        DownloadTask task = new DownloadTask();
        task.execute(url);


    }
    /*
    * show user , get api , list users and adds to spinner array list
    * spinner of user
    * save user (chosen_user) post api,goes to homepage*/
    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), homepage.class);
        startActivity(intent);
    }

    public void show_handyperson_list(View v){
        //list all user from get api
        the_list = new ArrayList<String>(Arrays.asList(options));


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,the_list);
        my_list.setAdapter(adapter);
        the_list2 = new ArrayList<String>(Arrays.asList(response));
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,the_list2);



        //load list from database

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        my_list.setAdapter(adapter);
        dropdown.setAdapter(adapter2);

    }
    public void save(View v){
        text_list = dropdown.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), text_list, Toast.LENGTH_LONG).show();
        DownloadTask task2 = new DownloadTask();

        flag = true;
        task2.execute(url_post +"?handy_person=" + text_list);

        //post api, choose from spinner
        home(v);
        //cond =  cannot save user twice, in php(check if added is already in list)
    }
    public void back(View v){
        home(v);
    }

}