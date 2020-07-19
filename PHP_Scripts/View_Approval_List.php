<?php
require_once 'Admin_Validation.php';
class Script_View_Apporoval_List{
    private $dbmanger;
    private $pramters;

    public function __construct($pramters ,DBManager $dbmanger ){
        $this->pramters = $pramters;
        $this->dbmanger = $dbmanger;
    }
    public function output(){
        $admin = new Admin($this->pramters , $this->dbmanger);
        $admin->output();
       return $this->view();
    }

    private function view($author , $articale_name ){
        $this->validation($author , $articale_name);
        $query ="Select * FROM  articles WHERE visable=:visable";
        $values =[
           ':visable'=>0
        ];
        $result = $this->dbmanger->Read($query, $values);
        if(sizeof($result) <1){
           return " there is no waiting List";
        }
        return $result;
    }

    private function  validation($name ,$author ){
        if (empty($name)|| empty($author)|| empty($description)){
            throw new InvalidArgumentException("missing input", 15);
        }
    }



}