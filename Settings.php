<?php
require __DIR__. "/Database/DBCredentials.php";


class Settings
{
    public  function get_backend_root(){
        return $_SERVER['DOCUMENT_ROOT'] . '/Dev_Task/';
    }

    public function get_database_credentials(){
        return new DBCredentials("localhost", "dev_test", "root", "");
    }
}

