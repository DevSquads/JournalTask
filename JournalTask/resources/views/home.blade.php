@extends('layouts.app')

@section('content')
    <div class="d-flex justify-content-between">
      <h2>List of Articles</h2>
      <div>
        @auth
        <a href="{{ route('articles.index') }}" class="btn btn-primary btn-sm">View My Articles First</a>
        @endauth
      </div>
    </div>
    
    @if ($articles->count() > 0)
      <table class="table table-stripped table-hover mt-4">
        <thead>
          <tr>
            <th>Title</th>
            <th>decription</th>
            <th>Name of The Author</th>
          </tr>
        </thead>

        @foreach ($users as $user)
        @foreach ($user->articles as $article)
        <tbody>
          <tr>
            <td>{{$article->title}}</td>
            <td>{{Str::limit($article->description, 200)}}</td>
            <td>{{ $article->user->name }}</td>
          </tr>
        </tbody>
        @endforeach
        @endforeach

      </table>
    @else
      <h4 class="mt-4">No Articles yet.</h4>
    @endif
@endsection