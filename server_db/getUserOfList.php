<?php
function ObtainIdOfUser($user_obtained){
    session_start();
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    //$user_obtained = "admin2";
    //$username = mysqli_real_escape_string($mysqli , $_POST['username']);
    $response = [];

    //connect to server;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from user where username = '$user_obtained' ");
    $exist = mysqli_num_rows($query);
    $table_username = "";
    $table_id = "";
    if($exist > 0 ){
        while($row = mysqli_fetch_assoc($query)){
            $table_username = $row['username'];
            $table_id = $row['id'];

        }
       /* if($user_obtained = $table_username){
            $response['error'] = false;
        $response['message'] = "user found of id  = ".$table_id;
        }
        else{
            $response['error'] = true;
            $response['message'] = "user not found";
        }*/
    }
    //$response['error'] = false;
    //  $response['message'] = $user_obtained;
   // $json_response = json_encode($response);
    //echo $json_response;
   // echo $table_id;
    return $table_id;

 

 }



/**
 * obtain username from android studio
 * serach threw the databe for user name
 * return id of username to ssavelist 
 */
?>