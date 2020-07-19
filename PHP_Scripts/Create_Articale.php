<?php
class Script_Create_Article{
private $dbmanger;
private $pramters;

public function __construct($pramters ,DBManager $dbmanger ){
    $this->pramters = $pramters;
    $this->dbmanger = $dbmanger;
}
public function output(){
        $this->create_aricale($this->pramters['name'] , $this->pramters['author'] , $this->pramters['description']);
}

private function create_aricale($name ,$author ,$description ){
    $this->validation($name ,$author ,$description);
$query ="INSERT INTO articles (name ,Description , author, visable ) VALUES (:name , :description ,:author , :visable)";
$values =[
    ":name"=>$name,
    ":description"=>$description,
    ":author"=>$author,
    ":visable"=>0
];
    $this->dbmanger->Create_Update_Delete($query, $values);
}

private function  validation($name ,$author ,$description){
    if (empty($name)|| empty($author)|| empty($description)){
        throw new InvalidArgumentException("missing input", 15);
    }
}
}