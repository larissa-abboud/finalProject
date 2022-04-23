<?php
// send data to the database and obtaining the results
include("db_connect.php");


$username = $_GET["username"] ??"" ;
$password = $_GET["pass"] ??"";
$response = [];


if(
   (!empty($username)) and 
   ( !empty($password)))
   // $paass = md5($pass);
{$query = $mysqli->prepare("INSERT INTO user (username, pass) VALUES (?, ?);");
$query->bind_param("ss", $username, $password);
$query->execute();
    $response['error'] = false;
    $response['message'] = "user registered";
    }else {
        
    $response['error'] = true;
    $response['message'] = "some err occurred";
    }



$json_response = json_encode($response);
echo $json_response;

?>