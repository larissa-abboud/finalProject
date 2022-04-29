<?php
/**insert user loging in to facilitate the interaction of all dunctions that use fk of type int */
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
        $response['error'] = false;
        $response['message'] = "currently logged in user is ".$username;
        }else {
            
        $response['error'] = true;
        $response['message'] = "some err occurred";
        }



    $json_response = json_encode($response);
    echo $json_response;
}
?>
