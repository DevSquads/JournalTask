<?php
class Script_Sign_In{
    private $dbmanger;
    private $pramters;

    public function __construct($pramters ,DBManager $dbmanger ){
        $this->pramters = $pramters;
        $this->dbmanger = $dbmanger;
    }
    public function output(){
        $this->sign_in($this->pramters['name'] , $this->pramters['password']);

    }

    private function sign_in($name , $password ){
        $this->validation($name , $password);
        $query ="Select *FROM articles  where name=:name AND password=:password";
        $values =[
            ":name"=>$name,
            ":password" => $password
        ];
         $Result =$this->dbmanger->Read($query, $values);
         if(sizeof($Result) <1){
             throw new InvalidArgumentException("wrong user name or password", 15);
         }
    }

    private function  validation($name , $password ){
        if (empty($name)|| empty($password)){
            throw new InvalidArgumentException("missing input", 15);
        }
    }

}