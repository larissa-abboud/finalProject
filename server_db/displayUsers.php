<?php

/**get api that displays the users so and option to save or request */

session_start();
$mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
   
    //connect to server;
    
    $query = mysqli_query($mysqli , " SELECT username from user");
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);
    $response = [];
    $table_username ="";
    $count = 0;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    while ( $row = mysqli_fetch_assoc($query)){
        $table_username = $row['username'];
        

        $response[$count] = $table_username;
        $count++;

    }
    $json_response = json_encode($response);
    echo $json_response;
?>