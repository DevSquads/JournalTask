@extends('layouts.app')

@section('content')
    <div class="d-flex justify-content-between">
      <h2>List of Articles</h2>
    </div>
    <table class="table table-stripped table-hover mt-4">
      <thead>
        <tr>
          <th>Id</th>
          <th>Title</th>
          <th>decription</th>
          <th>Name of The Author</th>
        </tr>
      </thead>

      @foreach ($users as $user)
      @forelse ($user->articles as $article)
      <tbody>
        <tr>
          <td>{{ $loop->iteration }}</td>
          <td>{{$article->title}}</td>
          <td>{{Str::limit($article->description, 200)}}</td>
          <td>{{ $article->user->name }}</td>
        </tr>
      </tbody>
      @empty
          <h4 class="mt-4">No Articles yet.</h4>
      @endforelse
      @endforeach

    </table>
@endsection