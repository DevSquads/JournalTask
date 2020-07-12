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
                            <th>@lang('site.name')</th>
                            <th>@lang('site.value')</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>@lang('site.title')</td>
                                <td>{{ $article->title }}</td>
                            </tr>
                            <tr>
                                <td>@lang('site.status')</td>
                                @if( $article->status===1)
                                    <td> @lang('site.accepted') </td>
                                @endif
                                @if( $article->status==0)
                                    <td>   @lang('site.pending')</td>
                                @endif
                                @if( $article->status===-1)
                                    <td>    @lang('site.rejected')</td>
                                @endif

                            </tr>
                            <tr>
                                <td>@lang('site.description')</td>
                                <td>{!! $article->description !!}</td>
                            </tr>

                        </tbody>
                    </table><!-- end of table -->



                </div><!-- end of box body -->
            </div><!-- end of box -->
        </section><!-- end of content -->
    </div><!-- end of content wrapper -->


@endsection
