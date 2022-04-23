<?php
//while creating the user add details section 

require_once '../server/DbOperations.php';
$response = array();

if($_SERVER["REQUEST_METHOD"]=="POST"){
    $username = $_GET["username"] ??"" ;
    $pass = $_GET["pass"] ??"";
    if(
        isset($_POST['username']) and isset($_POST['pass'])
        )
    {
        //update data
        $db = new DbOperations();
        
        if($db->CreateUser(
            $_POST['username'],
            $_POST['pass']
        )){
        $response['error'] = false;
        $response['message'] = "user registered";
        }else{
        $response['error'] = true;
        $response['message'] = "some err occurred";
        }



    }else{
        //missing values
        //echo echo json_encode($username);
        $response['error'] = true;
        $response['message'] = "req fields are missing";
    }


}else{
    $response['error'] = true;
   $response['message'] = "Invalid Request";

}

echo json_encode($response);


?>
