@extends('layouts.dashboard.app')

@section('content')

    <div class="content-wrapper">
        <section class="content-header">
            <h1>@lang('site.articles')</h1>

            <ol class="breadcrumb">
                <li><a href="{{ route('dashboard.welcome') }}"><i class="fa fa-dashboard"></i> @lang('site.dashboard')</a></li>
                <li><a href="{{ route('articles.index') }}"> @lang('site.articles')</a></li>
                <li class="active">@lang('site.add')</li>
            </ol>
        </section>

        <section class="content">

            <div class="box box-primary">

                <div class="box-header">
                    <h3 class="box-title">@lang('site.add')</h3>
                </div><!-- end of box header -->
                <div class="box-body">

                    @include('partials._errors')

                    <form action="{{ route('articles.store') }}" method="post" enctype="multipart/form-data">
                        {{ csrf_field() }}
                        {{ method_field('post') }}

                        @foreach (config('translatable.locales') as $locale)
                            <div class="form-group">
                                @if(count(config('translatable.locales'))>1)
                                    <label>@lang('site.' . $locale . '.name')</label>
                                @else
                                    <label>@lang('site.title')</label>
                                @endif
                                <input type="text" name="{{ $locale }}[title]" class="form-control" value="{{ old($locale . '.title') }}">
                            </div>

                            <div class="form-group">
                                @if(count(config('translatable.locales'))>1)
                                    <label>@lang('site.' . $locale . '.description')</label>
                                @else
                                    <label>@lang('site.description')</label>
                                @endif
                                <textarea name="{{ $locale }}[description]" class="form-control ckeditor">{{ old($locale . '.description') }}</textarea>
                            </div>

                        @endforeach

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> @lang('site.add')</button>
                        </div>
                    </form><!-- end of form -->

                </div><!-- end of box body -->

            </div><!-- end of box -->

        </section><!-- end of content -->

    </div><!-- end of content wrapper -->

@endsection
