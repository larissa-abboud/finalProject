<?php

class DbOperations{
    private $con;
    function __construct(){
        require_once dirname(__FILE__).'/DbConnect.php';
        $db=new DbConnect () ;
        $this->con = $db->connect();
    }
    //create
    function CreateUser ($username , $pass){
       
        //password = md5(password)
        $stmt  = $this->con->prepare("INSERT INTO user ( username,pass) VALUES (? , ?)");
        $stmt->bind_param("ss", $username, $pass);
        if($stmt->execute()){
            return true;
        }else{
            return false;
        }
        }
        //insert data in database

    }





?>
