<?php

namespace App\Policies;

use App\Article;
use App\Author;
use Illuminate\Auth\Access\HandlesAuthorization;

class ArticlePolicy
{
    use HandlesAuthorization;

    /**
     * Determine whether the user can update the model.
     *
     * @param  \App\Author  $author
     * @param  \App\Article  $article
     * @return mixed
     */
    public function update(Author $author, Article $article)
    {
        return $author->isAdmin() || $article->isWrittenBy($author);
    }
}