<?php

namespace App\Http\Controllers;

use App\Author;
use App\Article;
use Illuminate\View\View;
use Illuminate\Http\RedirectResponse;
use App\Http\Requests\ArticleFormRequest;

class ArticleController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth')->except(['index']);
        $this->middleware('can:update,article')->only(['edit','update']);
        $this->middleware('can:delete,article')->only(['delete']);
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index(): View
    {
        $authors = Author::select('id','name')
                    ->with('articles:id,title,description,author_id')
                    ->withCount('articles')
                    ->orderBy('articles_count', 'desc')
                    ->having('articles_count', '>', 0)
                    ->get();
        
        return view('articles.index', compact('authors'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create(Article $article): View
    {
        return view('articles.form', compact('article'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \App\Http\Requests\ArticleFormRequest  $request
     * @return \Illuminate\Http\RedirectResponse
     */
    public function store(ArticleFormRequest $request): RedirectResponse
    {
        $validatedData = $request->validated();
        
        $request->user()->articles()->create($validatedData);

        return redirect()->route('articles.index');
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Article  $article
     * @return \Illuminate\View\View
     */
    public function edit(Article $article): View
    {
        return view('articles.form', compact('article'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\ArticleFormRequest  $request
     * @param  \App\Article  $article
     * @return \Illuminate\Http\RedirectResponse
     */
    public function update(ArticleFormRequest $request, Article $article): RedirectResponse
    {
        $validatedData = $request->validated();

        $article->fill($validatedData);

        $request->user()->articles()->save($article);

        return redirect()->route('articles.index');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Article  $article
     * @return \Illuminate\Http\RedirectResponse
     */
    public function destroy(Article $article): RedirectResponse
    {
        $article->delete();

        return redirect()->route('articles.index');
    }
}
