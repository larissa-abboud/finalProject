<?php
class DbConnect{
    //private  $response ;
    private $con;

    function construct() {
    }
    
    function connect(){
        $response = array();
        include_once dirname(__FILE__).'/constants.php';
        $this->con = new mysqli(DB_HOST , DB_USER , DB_PASSWORD , DB_NAME);
        if(mysqli_connect_errno()){
           // $response['message'] ="failed to connect to database";
            echo "failed to connect to database".mysqli_connect_err();
        }
       // $response['message'] = "connection established";
        //echo json_encode($response);

        return $this->con;
    }
    
    }
    ?>