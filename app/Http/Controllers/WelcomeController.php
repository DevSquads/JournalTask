<?php

namespace App\Http\Controllers;


use App\Models\Article;
use App\Models\User;



use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\DB;

class WelcomeController extends Controller
{
    public function index()
    {
        $users_count=User::all()->count();
        $articles_count=Article::all()->count();
        $rejected_count=Article::where('status','=','-1')->get()->count();
        $pending_count=Article::where('status','=','0')->get()->count();
        $accepted_count=Article::where('status','=','1')->get()->count();

        return view('dashboard.welcome',compact(
            'users_count','articles_count','accepted_count','rejected_count','pending_count'
        ));
    }//end of index

}//end of controller
