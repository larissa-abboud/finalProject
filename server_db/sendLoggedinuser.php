<?php
/**insert user logging in to facilitate the interaction of all functions that use fk of type int */
function login( $username){
    include("db_connect.php");


    $username = $_GET["username"] ??"" ;

    $response = [];


    if(
    (!empty($username)) )
    // $paass = md5($pass);
    {$query = $mysqli->prepare("INSERT INTO currently_loged_in (username) VALUES (?);");
    $query->bind_param("s", $username);
    $query->execute();
        
        }



    
}
?>
