@extends('layouts.app')

@section('content')
  <h2>Create an Article</h2>
  <form action="{{ route('articles.store') }}" method="POST">
    @csrf

    <input type="hidden" value="{{ auth()->user()->id }}">
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" name="title" id="tile" placeholder="Enter Tilte" class="form-control" value="{{ old('title') }}">

      @error('title')
        <small class="text-danger">{{ $message }}</small>
      @enderror
    </div>

    <div class="form-group">
      <label for="description">Description</label>
      <textarea type="text" name="description" id="description" class="form-control"placeholder="Enter Description">{{ old('description') }}</textarea>
      @error('description')
        <small class="text-danger">{{ $message }}</small>
      @enderror
    </div>

    <div class="form-group">
      <button type="submit" class="btn btn-primary">Add Article</button>
    </div>

  </form>
@endsection