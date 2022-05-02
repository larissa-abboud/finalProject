<?php
include("db_connect.php");
include("getUserOfList.php");
$name = getCurrentUser();



$handyperson = $_GET["content"] ??"" ;
$user_l = ObtainIdOfUser($name); //return id of user logged in
//$userlist = $_GET["user_list"] ??"";
$response = [];
if(
    (!empty($handyperson )) 
    )
    //creat another 
 {
    $query = $mysqli->prepare("INSERT INTO post (content, user_id) VALUES (?, ?);");
    $query->bind_param("si", $handyperson, $user_l);
    $query->execute();
     $response['error'] = false;
     $response['message'] = "post saved in database";
     }else {
         
     $response['error'] = true;
     $response['message'] = "some err occurred";
     }
 
 
 
 $json_response = json_encode($response);
 //echo $user_l;
 echo $json_response;
?>