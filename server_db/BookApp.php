<?php

include("db_connect.php");


$time = $_GET["time_needed"] ??"" ;
$details = $_GET["details"] ??"";
$username = $_GET["for_user"] ??"";
$response = [];
include("getUserOfList.php");
$user_id = ObtainIdOfUser($username);


if(
   (!empty($time)) and 
   ( !empty($details))and(!empty($username)))
   // $paass = md5($pass);
{ 
    $check  = checkUserInappointments("book_app" , $user_id ,$time);
    if($check == "already booked an appointment"){
        $response['id'] = $user_id;
        $response['error'] = true;
    $response['message'] = "Already booked an appointment"." , Choose another user";
    }
    else{
    $query = $mysqli->prepare("INSERT INTO book_app (time_needed, details, for_user) VALUES (?, ?,?);");
$query->bind_param("ssi", $time, $details, $user_id);
$query->execute();
$response['id'] = $check;

    $response['error'] = false;
    $response['message'] = "Apointment booked".$check;
    }}else {
        
    $response['error'] = true;
    $response['message'] = "some err occurred";
    
    }



$json_response = json_encode($response);
echo $json_response;
?>
