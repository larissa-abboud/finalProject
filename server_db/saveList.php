<?php
// save favorit user in databse  to show in saved section

include("db_connect.php");

$handyperson = $_GET["handy_person"] ??"" ;
$userlist = $_GET["user_list"] ??"";
$response = [];
if(
    (!empty($handyperson )) and 
    ( !empty($userlist )))
    // $paass = md5($pass);
 {
    $query = $mysqli->prepare("INSERT INTO saved (handy_person, user_list) VALUES (?, ?);");
    $query->bind_param("si", $handyperson, $userlist);
    $query->execute();
     $response['error'] = false;
     $response['message'] = "user saved in database";
     }else {
         
     $response['error'] = true;
     $response['message'] = "some err occurred";
     }
 
 
 
 $json_response = json_encode($response);
 echo $json_response;

?>