<?php

session_start();
$mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    include("getUserOfList.php");
    //connect to server;
    $current_user = getCurrentUser();
    $id_current_user = ObtainIdOfUser($current_user);
   //echo $current_user;



    $response = [];
   
    $count = 0;

    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT id, content , user_id from post    ");
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);
    //$table_handy_person = "";
    $table_id = "0";
    if($exist > 0 ){
        $table_id = $row['user_id'];
        
       // echo $table_id;
        while($row = mysqli_fetch_assoc($query)  ){
            
            
            $table_id = $row['user_id'];
            if($table_id == $id_current_user){
                $count++;
            $table_content = $row['content']?? null;
            echo $table_id;
            
             $response[$count] = $table_content;
             
        //
            
            

        }else{
            $response["message"] = "no content found";
        }
       
    }
}$json_response = json_encode($response);
    echo $json_response;
?>