<?php

class Script_Admin_Validation
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
        $this->validatade_admin($this->pramters['user_name']);

    }

    private function validatade_admin($name)
    {
        $this->validation($name);
        $query = "Select *FROM users  where name=:name ";
        $values = [
            ":user_name" => $name
        ];
        $Result = $this->dbmanger->Read($query, $values);
        if (sizeof($Result) < 1) {
            throw new InvalidArgumentException("wrong user name or passworf", 15);
        }
        if($Result['Role'] !== 'admin'){
            throw new InvalidArgumentException("wrong user Access", 15);

        }
    }

    private function validation($name)
    {
        if (empty($name)) {
            throw new InvalidArgumentException("missing input", 15);
        }
    }

}