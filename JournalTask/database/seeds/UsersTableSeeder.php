<?php

use App\User;
use Illuminate\Database\Seeder;

class UsersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $admin = User::create([
            'name' => 'Samir',
            'email' => 'samir@admin.com',
            'email_verified_at' => now(),
            'password' => bcrypt('admin'),
            'is_admin' => 1,
            'remember_token' => Str::random(10),
        ]);
    }
}
