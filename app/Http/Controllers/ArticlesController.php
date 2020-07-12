<?php

namespace App\Http\Controllers;

use App\Models\Article;
use Illuminate\Http\Request;
use Illuminate\Pagination\LengthAwarePaginator;
use Illuminate\Pagination\Paginator;
use Illuminate\Support\Collection;
use Illuminate\Support\Facades\DB;
use Sqits\UserStamps\Tests\Models\User;

class ArticlesController extends Controller
{

    public function index(Request $request)
    {
        if(auth()->user()->hasRole('super_admin') ){
            $allArticles=[];
            $users= DB::table('users')
                ->orderBy('articles_counts', 'desc')
                ->get();
            foreach($users as $user) {
                $articles =Article::where('user_id' , '=' , $user->id)
                    ->get();
                 $user->articles =$articles;
                foreach ( $articles as $article) {
                    array_push($allArticles,['article'=>$article,'email'=>$user->email]);
                 }
            }

            $finalResults=$this->paginate($allArticles,15,0,
                ["path" => "http://task.devel/en/articles",
                 "pageName" => "page"]);
            return view('dashboard.articles.adminindex',compact('finalResults'));
        }
        if(auth()->user()->hasRole('user')) {
            $allArticles=[];
            $myarticles=Article::where('user_id','=',auth()->user()->id)->get();
            foreach($myarticles as $row) {
                array_push($allArticles,$row);
            }
            $otherArticles=Article::where('user_id','!=',auth()->user()->id)
                ->Where('status','=',1)->get();

            foreach ( $otherArticles as $article) {
                array_push($allArticles,$article);
            }
            $finalResults=$this->paginate($allArticles,15,0,
                ["path" => "http://task.devel/en/articles",
                    "pageName" => "page"]);
//           dd($finalResults);
            return view('dashboard.articles.index',compact('finalResults'));
        }
        $finalResults = Article::where(function ($q) use ($request) {
            return $q->when($request->search, function ($query) use ($request) {
                return $query->where('title', 'like', '%' . $request->search . '%');
            });
        })->latest()->paginate(10);
        return view('dashboard.articles.index',compact('finalResults'));
    }//end of index
    public function paginate($items, $perPage = 15, $page = null, $options = [])
    {
        $page = $page ?: (Paginator::resolveCurrentPage() ?: 1);

        $items = $items instanceof Collection ? $items : Collection::make($items);

        return new LengthAwarePaginator($items->forPage($page, $perPage), $items->count(), $perPage, $page, $options);
    }
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
    public function review($id,$res){
        $article=Article::where('id','=',$id)->first();
        $article->status=$res;
        $article->save();
        session()->flash('success', __('site.updated_successfully'));
        return redirect()->route('articles.index');
    }//end of Article











}//End Of Controller

