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
        // $articles = Article::whereStatus(1)
        //     ->groupBy('id')
        //     ->orderByRaw("COUNT('user_id')")
        //     ->get();

        // SELECT COUNT(user_id) as user_articles FROM articles GROUP BY user_id ORDER BY user_articles DESC

        // $articles = Article::all();

        // $articles = Article::whereIn('user_id',(Article::selectRaw("COUNT('user_id')")->groupBy('user_id')->orderByDesc('user_id')))->toSQL();

        // (Article::selectRaw("COUNT('user_id')")->groupBy('user_id')->orderByDesc('user_id')
            // ->pluck("COUNT('user_id')"))

        // $articles = Article::selectRaw("COUNT('user_id'), user_id")->groupBy('user_id')->orderByDesc('user_id')
            // ->pluck('user_id');

        // $articles = Article::whereStatus(1)
        //     ->whereIn('user_id', Article::selectRaw("COUNT('user_id'), user_id")->groupBy('user_id')->orderByDesc('user_id')->pluck('user_id'))
        //     ->get();

        // $orderByUserIds = Article::selectRaw("COUNT('user_id'), user_id")->groupBy('user_id')->orderByDesc('user_id')->pluck('user_id');

        // $articles = $orderByUserIds->map(function ($orderByUserId) {
        //     return Article::where('user_id', $orderByUserId)->get();
        // });

        $users = User::with(['articles' => function ($q) {
            return $q->where('status', 1);
        }])->withCount(['articles' => function ($q) {
            return $q->where('status', 1);
        }])->orderByDesc('articles_count')->get();

        // dd($users);

        return view('home', compact('users'));
    }
}
