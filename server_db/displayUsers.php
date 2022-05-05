<?php

/**get api that displays the users  */

session_start();
$mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
include("getUserOfList.php");
   
    //connect to server;
    
    $query = mysqli_query($mysqli , " SELECT username from user");
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);
    $response = [];
    $table_username ="";
    $count = 1;
    

    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    while ( $row = mysqli_fetch_assoc($query)){
        $table_username = $row['username'];
        $bio = getBio($table_username);
        
        $response['user'.$count] = $table_username;
        $response[$count] = " : ".$bio ;
        
        $count++;

    }
    $json_response = json_encode($response);
    echo $json_response;
?>