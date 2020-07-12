<?php

namespace App\Http\Controllers;

use App\Models\Article;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Sqits\UserStamps\Tests\Models\User;

class ArticlesController extends Controller
{

    public function index(Request $request)
    {


//
//        if(auth()->user()->hasRole('super_admin') ){
//            DB::table('posts')
//                ->select('user_id', DB::raw('MAX(created_at) as last_post_created_at'))
//                ->where('is_published', true)
//                ->groupBy('user_id');
//            $articles = DB::table('articles')->select('user_id',
//                DB::raw('COUNT()'));
//            return view('dashboard.articles.index',compact('articles'));
//        }
//        if(auth()->user()->hasRole('user')) {
////            $userArticles=collect(Article::where('user_id','=',auth()->user()->id)->get());
////            $articles = Article::where('status','=','1')->get();
////            $userArticles=$userArticles->merge($articles)->paginate(5);
//            $articles=Article::orderByRaw('user_id','=',auth()->user()->id)->get();
//            return view('dashboard.articles.index',compact('articles'));
//        }
//
        $articles=Article::latest()->paginate(5);
        $articles = Article::where(function ($q) use ($request) {
            return $q->when($request->search, function ($query) use ($request) {
                return $query->where('title', 'like', '%' . $request->search . '%')
                    ;
            });
        })->latest()->paginate(10);
        return view('dashboard.articles.index',compact('articles'));
    }//end of index

    public function create(){
        return view('dashboard.articles.create');
    }//end of create

    public function show(Article $article){
        return view('dashboard.articles.show',compact('article'));
    }//end of create

    public function store(Request $request){
        $rules = [ ];
        foreach (config('translatable.locales') as $locale) {
             $rules += [ $locale .'.title' => 'required|unique:article_translations,title'];
             $rules += [  $locale.'.description' => 'required'];
        }//end of  for each
        $request->validate($rules);
//        dd($rules);
        $request_data = $request->all();
        $request_data['status']=0;
        $request_data['user_id']=auth()->user()->id;

        Article::create($request_data);
        auth()->user()->articles_counts=auth()->user()->articles_counts+1;
        auth()->user()->save();
        session()->flash('success', __('site.added_successfully'));
        return redirect()->route('articles.index');

    }//end of store

    public function edit(Article $article){
        return view('dashboard.articles.edit', compact('article'));

    }//end of edit

    public function update(Request $request, Article $article)
    {
        $rules = [ ];
//        foreach (config('translatable.locales') as $locale) {
//            $rules += [ $locale .'.title' => 'required|unique:article_translations,title'];
//            $rules += [  $locale.'.description' => 'required'];
//        }//end of  for each
        $request->validate($rules);
        $request_data = $request->all();
        $article->update($request_data);
        session()->flash('success', __('site.updated_successfully'));
        return redirect()->route('articles.index');
    }//end of update

    public function destroy(Article $article){
        $article->delete();
        auth()->user()->articles_counts=auth()->user()->articles_counts-1;
        auth()->user()->save();
        session()->flash('success', __('site.deleted_successfully'));
        return redirect()->route('articles.index');

    }//end of destroy

    public function review(Article $article,$res){
        $article->status=$res;
        $article->save();
        session()->flash('success', __('site.updated_successfully'));
        return redirect()->route('articles.index');
    }//end of Article











}//End Of Controller

