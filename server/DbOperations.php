<?php

class DbOperation{
    private $con;
    function_construct(){
        require_once dirname(__FILE__).'/DbConnect.php';
        $db=new DbConnect () ;
        $this-> con = $db->connect();
    }
    //create
    function CreateUser ($username , $password){
        //password = md5(password)
        $stmt  = $this->con->prepare("INSERT INTO `user` (`id`, `username`, `password`) VALUES (NULL, ?, ?);");
        $stmt->bind_param("ss", $username, $password);
        if($stmt->execute()){
            return true;
        }else{
            return false;
        }
        }
        //insert data in database

    }





?>
