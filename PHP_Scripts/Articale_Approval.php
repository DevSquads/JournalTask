<?php

require_once 'Admin_Validation.php';

class Script_Article_Apporoval{
    private $dbmanger;
    private $pramters;

    public function __construct($pramters ,DBManager $dbmanger ){
        $this->pramters = $pramters;
        $this->dbmanger = $dbmanger;
    }
    public function output(){
        $admin = new Admin($this->pramters , $this->dbmanger);
        $admin->output();
        $this->approval($this->pramters['author'] , $this->pramters['articale_name']);

    }

    private function approval($author , $articale_name ){
        $this->validation($author , $articale_name);
        $query ="UPDATE articles SET visable =1 where author=:author AND name=:articale_name";
        $values =[
            ":author"=>$author,
            ":articale_name" => $articale_name
        ];
        $result = $this->dbmanger->Create_Update_Delete($query, $values);
        if(sizeof($result) <1){
            throw new InvalidArgumentException("Record doesn't exixts", 15);
        }
    }

    private function  validation($name ,$author ){
        if (empty($name)|| empty($author)){
            throw new InvalidArgumentException("missing input", 15);
        }
    }

}