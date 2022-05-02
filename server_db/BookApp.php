<?php

include("db_connect.php");


$time = $_GET["time_needed"] ??"" ;
$details = $_GET["details"] ??"";
$username = $_GET["from_user"] ??"";
$response = [];
include("getUserOfList.php");
$user_id =  ObtainIdOfUser($username);

if(
   (!empty($time)) and 
   ( !empty($details))and(!empty($username)))
   // $paass = md5($pass);
{$query = $mysqli->prepare("INSERT INTO book_app (time_needed, details, from_user) VALUES (?, ?,?);");
$query->bind_param("ssi", $time, $details, $user_id);
$query->execute();
    $response['error'] = false;
    $response['message'] = "Apointment booked";$response['from'] = $username;
    }else {
        
    $response['error'] = true;
    $response['message'] = "some err occurred";
    
    }



$json_response = json_encode($response);
echo $json_response;
?>
