<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Article extends Model
{
    protected $guarded = [];

    // Queries
    public function scopeActive($query)
    {
        return $query->whereStatus(1);
    }

    public function scopeInActive($query)
    {
        return $query->whereStatus(0);
    }

    public function scopeOtherArticles($query)
    {
        return $query->where('user_id', '<>', auth()->id());
    }

    // Relations
    public function user()
    {
        return $this->belongsTo(User::class);
    }

}
