@extends('layouts.dashboard.app')

@section('content')
    <div class="content-wrapper">
        <section class="content-header">
            <h1>@lang('site.articles')</h1>
            <ol class="breadcrumb">
                <li><a href="{{ route('dashboard.welcome') }}"><i class="fa fa-dashboard"></i> @lang('site.dashboard')</a></li>
                <li class="active">@lang('site.articles')</li>
            </ol>
        </section>
        <section class="content">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>@lang('site.title')</th>
                            <th>@lang('site.status')</th>
                            <th>@lang('site.description')</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{{ $article->title }}</td>
                                <td>{{ $article->status }}</td>
                                <td>{!! $article->description !!}</td>
                            </tr>
                        </tbody>
                    </table><!-- end of table -->



                </div><!-- end of box body -->
            </div><!-- end of box -->
        </section><!-- end of content -->
    </div><!-- end of content wrapper -->


@endsection
