<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Astrotomic\Translatable\Contracts\Translatable as TranslatableContract;
use Astrotomic\Translatable\Translatable;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\HasOne;

class Article extends Model implements TranslatableContract
{
    use Translatable;
    public $useTranslationFallback = true;
    public $translatedAttributes = ['title', 'description'];
    protected $fillable=['status','user_id'];
    protected $table='articles';
    public function user(){
        return $this->belongsTo(User::class,'article_id');
    }

}//end of model
