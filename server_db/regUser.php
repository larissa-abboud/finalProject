<?php
//post api to sign up
// send data(user name and password to register user) to the database and obtaining the results
include("db_connect.php");
include("getUserOfList.php");


$username = $_GET["username"] ??"" ;
$password = $_GET["pass"] ??"";
$name = $_GET["full_name"] ??"" ;
$bio = $_GET["bio"] ??"";
$response = [];
$flag = checkReg($username,$password);




if(
   (!empty($username)) and 
   ( !empty($password))and ( !empty($name)))
   // $paass = md5($pass);
{
    if($flag == 1){
        $response['error'] = true;
    $response['message'] = "already reg";

    }
    else if($flag == 2){
        $response['error'] = true;
    $response['message'] = "username taken";

    }
    else{
        $query = $mysqli->prepare("INSERT INTO user (username, pass,full_name , bio) VALUES (?, ?,?,?);");
$query->bind_param("ssss", $username, $password,$name , $bio);
$query->execute();
    $response['error'] = false;
    $response['message'] = "user registered";
    }
    
    
    
    }else {
        
        
    $response['error'] = true;
    $response['message'] = "missing fields";
    
    }



$json_response = json_encode($response);
echo $json_response;
/**
 * connect to android studio
 */

?>