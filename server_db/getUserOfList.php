<?php 
//helper methods
/** get the current user logged in
 *  get the  id of a user 
 */

$response = [];
function ObtainIdOfUser($user_obtained){
    //get method: return id 
    //session_start();
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
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
       
    }
   
    return $table_id;

 

 }
 function checkUserIn($table , $user){
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    
    //connect to server;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from $table where handy_person = '$user' ");
    $exist = mysqli_num_rows($query);
    $table_handy = "";
    $table_id = "";
    if($exist > 0 ){
        while($row = mysqli_fetch_assoc($query)){
            $table_handy = $row['handy_person'];
            $table_id = $row['id'];

        }
        if($table_handy == ""){
            return $table_handy;
        }else{
            return "already saved";
        }
       
    }
   
    

 }
 function getCurrentUser(){
     //get method : return username
    
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    //$user_obtained = "admin2";
    //$username = mysqli_real_escape_string($mysqli , $_POST['username']);
    $response = [];

    //connect to server;
    $id = 0 ;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from currently_loged_in    ");//SELECT * FROM TableName WHERE id=(SELECT max(id) FROM TableName);
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);

    $table_usernam = "";
    //$table[$key] ?? null;
    $table_id = 0;
    if($exist > 0  ){
        while($row = mysqli_fetch_assoc($query)and $id <=$table_id ){
            
            $table_usernam = $row['username'];
            $id = $table_id;
            $table_id = $row['id']?? null;

        }
       
    }
   
    return $table_usernam;

 }/*
 $current = getCurrentUser();
 $response['username'] = $current;
 $json_response = json_encode($response);
 echo $json_response;*/



/**
 * obtain username from databse 
 * serach threw the databe for user name
 * return id of username to savelist 
 */
?>