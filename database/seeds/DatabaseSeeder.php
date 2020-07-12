<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{

    public function run()
    {
//        $this->call(LaratrustSeeder::class);
//        $this->call(UsersTableSeeder::class);
        $this->call(Articles::class);

    }//end of run
}//end of class
