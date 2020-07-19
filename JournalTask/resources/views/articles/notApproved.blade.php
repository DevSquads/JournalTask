@extends('layouts.app')

@section('content')
    <div class="d-flex justify-content-between">
      <h2>List of Not Approved Articles</h2>
      <div>
        <a href="{{ route('articles.index') }}" class="btn btn-primary btn-sm">Articles</a>
      </div>
    </div>
    @if ($articles->count() > 0)
      <table class="table table-stripped table-hover mt-4">
        <thead>
          <tr>
            <th>Id</th>
            <th>Title</th>
            <th>decription</th>
            <th>Name of The Author</th>
            <th>Approve</th>
            <th>Action</th>
          </tr>
        </thead>

      @foreach ($articles as $index=>$article)
        <tbody>
          <tr>
            <td>{{ $index + 1}}</td>
            <td>{{$article->title}}</td>
            <td>{{Str::limit(strip_tags($article->description), 200)}}</td>
            <td>{{ $article->user->name }}</td>
            <td>
              <form action="{{ route('articles.approval') }}" method="POST">
                @csrf
                <input type="checkbox" name="status" @if ($article->status == 1)
                      checked="checked"
                  @endif>
                  <input type="hidden" name="articleId" value="{{ $article->id }}">
                  <input type="submit" value="Submit" class="btn btn-primary btn-sm">
              </form>
            </td>
            <td>
              <form action="{{ route('articles.destroy', $article->id) }}" method="POST">
                @csrf
                @method("DELETE")

                <button type="submit" class="btn btn-danger delete btn-sm"><i class="fa fa-trash"></i> Delete</button>
              </form><!-- end of form -->
            </td>
          </tr>
        </tbody>
      @endforeach
      </table>
    @else
        <h4 class="mt-4">No Articles Yet.</h4>
    @endif
@endsection