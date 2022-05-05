<?php
/**get api
 * gets logged in user 
 * prints all data in  list format
 * 
 */
session_start();
$mysqli = mysqli_connect("localhost", "root" , "", "oddjobberdb") or die(mysqli_error());
    include("getUserOfList.php");
    //connect to server;
    $current_user = getCurrentUser();
    $id_current_user = ObtainIdOfUser($current_user);
    
    $response = [];
   
    $count = 1;

    mysqli_select_db($mysqli ,"oddjobberdb") or due("cannot connect to database");
    $query = mysqli_query($mysqli , "SELECT id ,handy_person , user_list from saved where user_list='$id_current_user'   ");
    $exist = mysqli_num_rows($query);
    $row = mysqli_fetch_assoc($query);
    
    $table_id = "0";
    if($exist > 0 ){
        $table_id = $row['user_list'];
       
       $response ['empty'] = "not";
       $response['handy_person'.$count] = $row['handy_person'];
       $response[$count] = " : ".getBio( $row['handy_person']) ;
       
        while($row = mysqli_fetch_assoc($query)  ){
            
            
            
            
            $table_id = $row['user_list'];
            
                $count++;
            $table_handy_person = $row['handy_person']?? null;
           
           $response[$count] = " : ".getBio($table_handy_person) ?? "none";
             $response['handy_person'.$count] = $table_handy_person ;
             
        
            
            

        //echo $count;
       
    }
    
}else {
        $response ['empty'] = "empty";
    }
    $json_response = json_encode($response);
    echo $json_response;
?>