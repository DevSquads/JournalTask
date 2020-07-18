<?php

namespace App\Http\Controllers;

use App\Article;
use Illuminate\Http\Request;

class ApproveController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth');
    }

    public function index()
    {
        if (auth()->user()->is_admin === 1) {
            
        $articles = Article::where('status', 0)->get();

        return view ('articles.notApproved', compact('articles'));
        }
    }

    public function approval(Request $request)
    {
        if (auth()->user()->is_admin === 1) {
            $article = Article::find($request->articleId);
            $approveVal = $request->input('status');
            if($approveVal == 'on'){
                $approveVal = 1;
            }else{
                $approveVal = 0;
            }

            $article->status = $approveVal;
            $article->save();

            return back();
        }
    }

}
