<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;

class Articles extends Seeder
{

    public function run()
    {
        $faker = Faker::create();
        $ids = [10,20,30,40,50,60,70,80,90,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120];
        //looping for each user to create some of articles to each one
        foreach ($ids as $id){
            for($j = 0; $j < 50; $j++) {
                \App\Models\Article::create([
                    'status'=>'0',
                    'user_id'=>$id,
                    'ar' => ['title' => 'عنوان المقال' . $faker->name, 'description' => 'وصف' . $faker->name],
                    'en' => ['title' => 'Article Title' . $faker->name, 'description' => 'Description' . $faker->name],


                ]);

            }







        }//end of ForLoop
    }//end of run
}//end of class
