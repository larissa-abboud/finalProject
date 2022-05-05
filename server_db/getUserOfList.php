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
 function checkUserIn($table , $user ,$id){
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    
    //connect to server;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from $table where handy_person = '$user' ");
    $exist = mysqli_num_rows($query);
    $table_handy = "";
    $table_id = "";
    if($exist > 0 ){
        while($row = mysqli_fetch_assoc($query) ){
            $table_handy = $row['handy_person'];
            $table_id = $row['user_list'];

        }
        if($table_handy == ""){
            return $table_handy;
        }else{
            if($table_id == $id){
            return "already saved";}
            else{
                return $table_handy;
            }
        }
       
    }
   
    

 }
 function checkUserInappointments($table , $user){
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    
    //connect to server;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from $table where for_user = '$user' ");
    $exist = mysqli_num_rows($query);
    $table_handy = "";
    $table_id = "";
    if($exist > 0 ){
        while($row = mysqli_fetch_assoc($query)){
            $table_handy = $row['for_user'];
            $table_id = $row['b_id'];

        }
        if($table_handy == ""){
            
            return $table_handy;
        }else{
            return "already booked an appointment";
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

 /**gets the username an pass
  * check in user db 
  if both then alredy reg
  if username then username taken
  if non then insert in db
  */
  $response = [];
function checkReg($user_obtained,$user_pass){
    //get method: return id 
    //session_start();
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    //connect to server;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT username, pass from user  ");
    $exist = mysqli_num_rows($query);
    $table_username = "";
    $table_pass= "";
    $flag;
    if($exist > 0 ){
        while($row = mysqli_fetch_assoc($query)){
            $table_username = $row['username'];
            $table_pass = $row['pass'];
            
            if($table_username == $user_obtained and $table_pass == $user_pass ){
                $flag = 1;
                return $flag;
                
            }else if($table_username == $user_obtained ){
                $flag = 2;
                return $flag;
            }else{
                
               $flag =3;
               
            }

        }
       
    }
   
    return $flag;
   //regUser.php?username=larissa123&pass=abb&full_name=larissabboud&bio=bio

 

 } //echo checkReg("admin4","admin4");

 function getBio($user){
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    $response = [];

    $id = 0 ;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from user where username = '$user' ");
    $exist = mysqli_num_rows($query);
    $table_username = "";
    $table_bio = "";
    if($exist > 0 ){
        while($row = mysqli_fetch_assoc($query)){
            $table_username = $row['username'];
            $table_bio = $row['bio'];

        }
       
    }
   
    return $table_bio;
    

 }

 
 //echo getBio("larissa123");
?>