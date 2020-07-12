<?php


namespace App\Http\Controllers;


use Illuminate\Database\Eloquent\Builder;

class StringLengthSort implements \Spatie\QueryBuilder\Sorts\Sort
{

    public function __invoke(Builder $query, bool $descending, string $property)
    {
        // TODO: Implement __invoke() method.
    }
}
