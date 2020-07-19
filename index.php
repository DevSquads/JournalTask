<?php
require __DIR__ . '/DBManager/DBManager.php';
require 'Settings.php';

try {
        if ($_SERVER['REQUEST_METHOD'] !== 'POST')
            throw new InvalidArgumentException("not a post request", 15);
        $data = get_data_from_request();
        $db_manager = new DBManager();
        $settings = new Settings();
    $response = process_data_request($data, $db_manager ,$settings);
    exit($response);
    } catch (\Exception $e) {
    $ERROR =["errorCode" => $e->getCode(), "errorMessage" => $e->getMessage()];
    exit( $ERROR);
    }

function get_data_from_request() {
    $data = $_POST['data'];
    if (empty($data))
        throw new RuntimeException("Data was not specified", 20);
    if(empty($data['target']))
        throw new RuntimeException("Target was not specified", 20);
    return $data;
}


function process_data_request( $request, DBManager $db_manager ,Settings $settings){
    $file_path =  $settings->get_backend_root.'PHP_Scripts/'.$request->script;
    if (file_exists($file_path)){
        require_once $file_path;
    }else{
        throw new RuntimeException("Target Wrong", 20);
    }
    $target_name = explode('.' , $request->script );
    $class_name = 'Script_' . $target_name[0];
    $script_output = new $class_name($request->parameters , $db_manager);
    return $script_output->output();
}

