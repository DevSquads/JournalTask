<?php
require_once 'Admin_Validation.php';

class Script_Delete_Articale{
    private $dbmanger;
    private $pramters;

    public function __construct($pramters ,DBManager $dbmanger ){
        $this->pramters = $pramters;
        $this->dbmanger = $dbmanger;
    }
    public function output(){
        $admin = new Admin($this->pramters , $this->dbmanger);
        $admin->output();
        return $this->Delete($this->pramters['name'] , $this->pramters['author']);
    }

    private function Delete($name , $author ){
        $this->validation($name , $author);
        $query ="DELETE FROM  articles WHERE name=:name AND author=:author";
        $values =[
            ':name'=>$name,
            ':author'=>$author
        ];
        $result = $this->dbmanger->Create_Update_Delete($query, $values);
        if(sizeof($result) <1){
            throw new InvalidArgumentException("Record doesn't exixts", 15);
        }
        return $result;
    }

    private function  validation($name , $author ){
        if (empty($name)|| empty($author)){
            throw new InvalidArgumentException("missing input", 15);
        }
    }



}