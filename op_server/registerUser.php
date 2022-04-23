<?php
//while creating the user add details section 

require_once '../server/DbOperations.php';
$response = array();

if($_SERVER['REQUEST_METHOD']=='POST'){
    if(
        isset($_POST['username'])and 
        isset($_POST['password']))
    {
        //update data
        $db = new DbOperations();

        if($db->CreateUser(
            $_POST['username'],
            $_POST['password']
            )){
        $response['error'] = false;
        $response['message'] = "user registered";
        }else{
        $response['error'] = true;
        $response['message'] = "some err occurred";
        }



    }else{
        //missing values
        $response['error'] = true;
        $response['message'] = "req fields are missing";
    }


}else{
    $response['error'] = true;
   $response['message'] = "Invalid Request";

}

echo json_encode($response);


?>
