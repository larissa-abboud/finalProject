<?php
//get api,returns username to display
include("getUserOfList.php");
include("db_connect.php");
$response = [];

    $response['user'] =getCurrentUser();
    $json_response = json_encode($response);
echo $json_response;
?>