<?php

namespace App\Models;

use App\Models\Permission;
use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laratrust\Traits\LaratrustUserTrait;

class User extends Authenticatable
{
    use LaratrustUserTrait;
    use Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name', 'email', 'password','image'
    ];
    protected $appends=['image_path'];

    protected $hidden = [
        'password', 'remember_token',
    ];

    /**
     * The attributes that should be cast to native types.
     *
     * @var array
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];

    public function getNameAttribute($value)
    {
        return ucfirst($value);

    }//end of get  name


    public function getImagePathAttribute(){
        return asset('uploads/user_images/' . $this->image);

    }//end of get image path
    public function permissions(){
        return $this->belongsToMany(Permission::class, 'permission_user');

    }//end of products
    public function photo(){
        return $this->morphOne('App\Models\Photo', 'photoable');
    }
    public function tickets(){
        return $this->hasMany(Article::class);
    }
}//End Of User
