<?php
// send data to the database and obtaining the results
include("db_connect.php");


$username = $_GET["username"] ??"" ;
$password = $_GET["password"] ??"";

$query = $mysqli->prepare("INSERT INTO user (username, password) VALUES (?, ?);");
$query->bind_param("ss", $username, $password);
$query->execute();

$response = [];
$response["status"] = "obtained";
//echo $rate * $amount_rec;
$json_response = json_encode($response);
echo $json_response;

?>