<?php

class DBCredentials {
    public $server;
    public $db_name;
    public $username;
    public $password;

    public function __construct($server, $db_name, $username, $password) {
        $this->server = $server;
        $this->db_name = $db_name;
        $this->username = $username;
        $this->password = $password;
    }
}