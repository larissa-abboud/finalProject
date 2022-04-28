<?php
// save favorit user in databse  to show in saved section

include("db_connect.php");
include("getUserOfList.php");
$name = getCurrentUser();
//echo $name;
/**create table that stores username of curently loggen in user.
 * when we want to perform any action , get latest entry , retrun as is
 * so the it is a post api that recived the userand adds it to the databse
 */

$handyperson = $_GET["handy_person"] ??"" ;
$user_l = ObtainIdOfUser($name); //return id of user logged in
//$userlist = $_GET["user_list"] ??"";
$response = [];
if(
    (!empty($handyperson )) 
    )
    //creat another 
 {
    $query = $mysqli->prepare("INSERT INTO saved (handy_person, user_list) VALUES (?, ?);");
    $query->bind_param("si", $handyperson, $user_l);
    $query->execute();
     $response['error'] = false;
     $response['message'] = "user saved in database";
     }else {
         
     $response['error'] = true;
     $response['message'] = "some err occurred";
     }
 
 
 
 $json_response = json_encode($response);
 //echo $user_l;
 echo $json_response;

?>