<?php

namespace App\Http\Controllers;

use App\Article;
use App\User;

class HomeController extends Controller
{
    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        $users = User::with(['articles' => function ($q) {
            return $q->active();
        }])->withCount(['articles' => function ($q) {
            return $q->active();
        }])->orderByDesc('articles_count')->get();

        $articles = Article::active()->get();

        return view('home', compact('users', 'articles'));
    }
}
