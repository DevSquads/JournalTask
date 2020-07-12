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
//        if(auth()->user()->hasRole('super_admin') ){
//            $articles=Article::get()->sortBy(function ($article){
//                return $article->
//            })
//        }
//        if(auth()->user()->hasRole('user')) {
//            dd('USer');
//        }
//
        $articles=Article::latest()->paginate(5);
        $articles = Article::where(function ($q) use ($request) {
            return $q->when($request->search, function ($query) use ($request) {
                return $query->where('title', 'like', '%' . $request->search . '%')
                    ;
            });
        })->latest()->paginate(5);
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
        session()->flash('success', __('site.deleted_successfully'));
        return redirect()->route('dashboard.articles.index');

    }//end of destroy














}//End Of Controller

