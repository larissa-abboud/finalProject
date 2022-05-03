<?php
include("db_connect.php");

$id_current_user = 2;
$response = [];
   // $response['handy ']="";
   

    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT id ,handy_person , user_list from saved where user_list='$id_current_user'   ");
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);
    //$table_handy_person = "";
    $table_id = "0";
    if($exist > 0 ){
        $table_id = $row['user_list'];
       // echo $table_id;
        while($row = mysqli_fetch_assoc($query)  ){
            
            
            $table_id = $row['user_list'];
            if($table_id == $id_current_user){
               
            $table_handy_person = $row['handy_person']?? null;
            //echo $table_handy_person;
            //{"categories":[],"created_at":"2020-01-05 13:42:28.143137","icon_url":"https://assets.chucknorris.host/img/avatar/chuck-norris.png",
                //"id":"LsCVUvEHQ0GXHr5rWPNh-A","updated_at":"2020-01-05 13:42:28.143137",
                //"url":"https://api.chucknorris.io/jokes/LsCVUvEHQ0GXHr5rWPNh-A","value":"Jason didn't drown Chuck Norris pulled him under"}
            $response["categories"] = $table_handy_person;
            $response["icon_url"] = $table_handy_person;
            $response["id"] = $table_id;
            $response["updated_at"] = "2020-01-05 13:42:28.143137";
            $response["created_at"] = "2020-01-05 13:42:28.143137";
            $response["url"] = "http://192.168.1.104/finalProject/server_db/testapi.php";
            $response["value"] = $table_handy_person;
             
        //
            
            

        }//echo $count;
       
    }
}$json_response = json_encode($response);
    echo $json_response;

?>