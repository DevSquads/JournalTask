<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Auth::routes();

Route::get('/', 'HomeController@index');
Route::get('/articles/notapproved', 'ApproveController@index')->name('articles.notApproved');
Route::post('/articles/notapproved', 'ApproveController@approval')->name('articles.approval');
Route::resource('/articles', 'ArticleController');