<?php
// save favorit user in databse  to show in saved section
//post api : send to databse the handy person as post and username from logged in


include("db_connect.php");
include("getUserOfList.php");
$name = getCurrentUser();

/**created table that stores username of curently loggen in user.
 * when we want to perform any action , get latest entry , retrun as is
 * so  it is an api that recives the user and adds it to the databse
 * and another that obtains the id since the input is a varchar to match with the fk and add it in the database
 */

$handyperson = $_GET["handy_person"] ??"" ;

$user_l = ObtainIdOfUser($name); 
$response = [];
if(
    (!empty($handyperson )) 
    )
    
 {  
    $check = checkUserIn("saved", $handyperson , $user_l );
    if($check == "already saved"){
        $response['error'] = true;
     $response['message'] = "user already saved";

    }
    else{
    $query = $mysqli->prepare("INSERT INTO saved (handy_person, user_list) VALUES (?, ?);");
    $query->bind_param("si", $handyperson, $user_l);
    $query->execute();
     $response['error'] = false;
     $response['message'] = "user saved in database";
     }}else {
         
     $response['error'] = true;
     $response['message'] = "some err occurred";
     }
 
 
 
 $json_response = json_encode($response);
 
 echo $json_response;

?>