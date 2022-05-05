<?php 
//
/**  functions that are used in the apis.
 */

$response = [];
function ObtainIdOfUser($user_obtained){
    //takes username, return id 
    //session_start();
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    //connect to server;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from user  ");
    $exist = mysqli_num_rows($query);
    $table_username = "";
    $table_id = "";
    if($exist > 0 ){
      
        while($row = mysqli_fetch_assoc($query)){
            $table_username = $row['username'];
            $table_id = $row['id'];

            if($table_username == $user_obtained){
            return $table_id;
        }
            

        }
       
    }
    
   
    

 

 }
 function checkUserIn($table , $user ,$id){
     //check if handyperson is in saved list of currently logged in user
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
 function checkUserInappointments($table , $user,$time ){
     // check if apointment requested with a user is occupied
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    
    //connect to server;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT for_user ,time_needed  from $table where for_user = '$user' ");
    $exist = mysqli_num_rows($query);
    
    $table_handy = "";
    $table_time = "";
    $table_details = "";
    
    
    if($exist > 0 ){
        while($row = mysqli_fetch_assoc($query)){
            $table_handy = $row['for_user'];
            $table_time = $row['time_needed'];
           
           

        }
        if($table_handy != ""){
            if($table_time == $time ){

            //and same time and 
            
            
            return "already booked an appointment";}
        }else{
            return $table_handy;
        }
       
    }
   
    

 }
 function getCurrentUser(){
     // return username of the current user who recently logged in
    
    $mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    
    $response = [];

    //connect to server;
    $id = 0 ;
    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT * from currently_loged_in    ");//SELECT * FROM TableName WHERE id=(SELECT max(id) FROM TableName);
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);

    $table_usernam = "";
    
    $table_id = 0;
    if($exist > 0  ){
        while($row = mysqli_fetch_assoc($query)and $id <=$table_id ){
            
            $table_usernam = $row['username'];
            $id = $table_id;
            $table_id = $row['id']?? null;

        }
       
    }
   
    return $table_usernam;

 }





 /**gets the username an pass
  * check in user db 
  if both then already reg
  if username then username taken
  if non then insert in db
  */
  $response = [];
function checkReg($user_obtained,$user_pass){
    // return dlag if in or not reg with conditions
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
  

 

 } 

 function getBio($user){
     //obtain bio of user
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

 
 
?>