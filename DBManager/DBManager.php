<?php
require __DIR__."/../Settings.php";

class DBManager
{
    public $conn;

    public function __construct(){
        $this->conn = $this->getConnection();
    }

    private function getConnection(){
        $conn = null;
        $settings = new Settings();
        $credentials = $settings->get_database_credentials();
        $conn = new PDO("mysql:host=" . $credentials->server . ";dbname=" . $credentials->db_name, $credentials->username, $credentials->password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return $conn;
    }
    public function Read($query, $array){
        $stmt = $this->conn->prepare($query);
        $stmt->execute($array);
        $results = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $results;
    }

    public function Create_Update_Delete($query, $array){
        $stmt = $this->conn->prepare($query);
        $stmt->execute($array);
    }
}

