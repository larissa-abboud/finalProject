<?php
/**gets logged in user (loop threw each row , check if user_list = currently logged in user id , add to json array)
 * prints all data in  list format
 * 
 */
session_start();
$mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    include("getUserOfList.php");
    //connect to server;
    $current_user = getCurrentUser();
    $id_current_user = ObtainIdOfUser($current_user);
    //echo $current_user .$id_current_user;


    $response = [];
    $response['handy ']="";
    $count = 0;

    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT id ,handy_person , user_list from saved where user_list='$id_current_user'   ");
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);
    //$table_handy_person = "";
    $table_id = "2";
    if($exist > 0 ){
        $table_id = $row['user_list'];
        echo $table_id;
        while($row = mysqli_fetch_assoc($query)  ){
            $count++;
            
            $table_id = $row['user_list'];
            if($table_id == $id_current_user){
            $table_handy_person = $row['handy_person']?? null;
            //echo $table_handy_person;
             $response['handy'] = $table_handy_person;
             
        //
            
            

        }//echo $count;
       
    }
}$json_response = json_encode($response);
    echo $json_response;
?>