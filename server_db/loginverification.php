<?php

/**
 *  api
 * recive input from user check if in database and updated table currently logged in
 */

session_start();
$mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    include("getUserOfList.php");
    include("sendLoggedinuser.php");
    //connect to server;
    $current_user = getCurrentUser();
    $id_current_user = ObtainIdOfUser($current_user);
    $query = mysqli_query($mysqli , " SELECT username,pass from user");
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);
    $username = $_GET["username"] ??"" ;
    $password = $_GET["pass"] ??"";

    $response = [];
    $table_username ="";
    $table_pass = "";
     $flag = true;

    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    if(
        (!empty($username)) and 
        ( !empty($password))){
    if( $exist > 0){
        while($row = mysqli_fetch_assoc($query) and $flag){
            $table_username = $row['username'];
            $table_pass = $row['pass'];
            

            if ($username == $table_username and $password== $table_pass){
                login($username);
                $response['error'] = false;
                $response['message'] = "currently logged in user is : ".$username;
                $flag = false;
                

            }else{

                /**add options{
                 *              wrong password
                 *              or wrong username
                 *              not registered} */
                 if($username != $table_username || $password != $table_pass ){
                    
                    $response['error'] = true;
                    $response['message'] = "incorrect password or username"; 
                    
                 }
                 else{
                    $response['error'] = true;
                    $response['message'] = "please sign up";
                 }
            }
    }
}}else{$response['error'] = true;
    $response['message'] = "missing values";}
    $json_response = json_encode($response);
echo $json_response;
?>