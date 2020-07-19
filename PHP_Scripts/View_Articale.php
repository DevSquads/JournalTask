<?php

class Script_View_Articale
{
    private $dbmanger;
    private $pramters;

    public function __construct($pramters, DBManager $dbmanger)
    {
        $this->pramters = $pramters;
        $this->dbmanger = $dbmanger;
    }
    public function output()
    {
        $this->view_aricale($this->pramters['author']);
    }


    private function view_aricale($name)
    {
        $query = "Select * From articles where visable =:visable";
        $values = [
            ":visable" => "true"
        ];

        $articales = $this->dbmanger->Read($query, $values);
        $arranged_articales = $this->arrange_max_articales($articales);
        $result = $this->author_articales($arranged_articales ,$name);
        return $result;
    }

private function arrange_max_articales($arrticales){
    $arranged_articale = [];
    $max = 0;
    foreach ($arrticales as $articale){
        $author_name = $articale['name'];



        //assosiative array that key is th author name and value is array of his articales
        //this line could add key if itsn't exists or if exists it add articale to values without any dublicate
        $arranged_articale[$author_name] = $articale;
        $current_size = sizeof($arranged_articale[$author_name]);



        //make max authors articales in firs
        if ($current_size> $max) {
            $max = $current_size;
            $values = $arranged_articale[$author_name];
            unset($arranged_articale[$author_name]);
            $arranged_articale =array_merge(array($author_name=>$values),$arranged_articale);
        }
    }
    return $arranged_articale;
}

private function author_articales($arranged_articale ,$name){
        if (array_key_exists($name , $arranged_articale)){
            $values = $arranged_articale[$name];
            unset($arranged_articale[$name]);
            $arranged_articale =array_merge(array($name=>$values),$arranged_articale);
        }
    return $arranged_articale;
}
}