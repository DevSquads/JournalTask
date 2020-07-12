@extends('layouts.dashboard.app')

@section('content')

    <div class="content-wrapper">

        <section class="content-header">

            <h1>@lang('site.articles')</h1>

            <ol class="breadcrumb">
                <li><a href="{{ route('dashboard.welcome') }}"><i class="fa fa-dashboard"></i> @lang('site.dashboard')</a></li>
                <li class="active">@lang('site.tickets')</li>
            </ol>
        </section>

        <section class="content">

            <div class="box box-primary">

                <div class="box-header with-border">

                    <h3 class="box-title" style="margin-bottom: 15px">@lang('site.articles') <small></small></h3>

                    <form action="{{ route('articles.index') }}" method="get">

                        <div class="row">

                            <div class="col-md-4">
                                <input type="text" name="search" class="form-control" placeholder="@lang('site.search')" value="{{ request()->search }}">
                            </div>



                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> @lang('site.search')</button>
                                @if (auth()->user()->hasPermission('create_articles'))
                                    <a href="{{ route('articles.create') }}" class="btn btn-primary"><i class="fa fa-plus"></i> @lang('site.add')</a>
                                 @else
                                    <a href="#" class="btn btn-primary disabled"><i class="fa fa-plus"></i> @lang('site.add')</a>
                                @endif
                            </div>

                        </div>
                    </form><!-- end of form -->

                </div><!-- end of box header -->

                <div class="box-body">

                    @if ($articles->count() > 0)

                        <table class="table table-hover">

                            <thead>
                            <tr>
                                <th>#</th>
                                <th>@lang('site.title')</th>

                                <th>@lang('site.status')</th>
                                <th>@lang('site.description')</th>
                                @if (auth()->user()->hasPermission('update_articles','delete_articles'))
                                <th>@lang('site.action')</th>
                                @endif
                            </tr>
                            </thead>

                            <tbody>
                            @foreach ($articles as $index=>$row)
                                <tr>
                                    <td>{{ $index + 1 }}</td>
                                    <td>{{ $row->title }}</td>
                                    <td>{{ $row->status }}</td>
                                    <td>{!! $row->description !!}</td>
{{--                                    @if (auth()->user()->hasPermission('read_articles'))--}}
{{--                                    <td>--}}
{{--                                        <a href="{{ route('articles.show',  $row->id) }}" class="btn btn-info btn-sm">@lang('site.show')</a></td>--}}
{{--                                     @endif--}}
                                    <td>
                                    @if (auth()->user()->hasPermission('read_articles'))
                                            <a href="{{ route('articles.show',  $row->id) }}" class="btn btn-info btn-sm">@lang('site.show')</a>
                                    @endif
                                        @if (auth()->user()->hasPermission('update_articles'))
                                            <a href="{{ route('articles.edit', $row->id) }}" class="btn btn-info btn-sm"><i class="fa fa-edit"></i> @lang('site.edit')</a>
                                         @else
                                            <a href="#" class="btn btn-info btn-sm disabled"><i class="fa fa-edit"></i> @lang('site.edit')</a>
                                        @endif
                                        @if (auth()->user()->hasPermission('delete_articles'))
                                            <form action="{{ route('articles.destroy', $row->id) }}" method="post" style="display: inline-block">
                                                {{ csrf_field() }}
                                                {{ method_field('delete') }}
                                                <button type="submit" class="btn btn-danger delete btn-sm"><i class="fa fa-trash"></i> @lang('site.delete')</button>
                                            </form><!-- end of form -->
                                         @else
                                            <button class="btn btn-danger btn-sm disabled"><i class="fa fa-trash"></i> @lang('site.delete')</button>
                                        @endif
                                    </td>
                                </tr>

                            @endforeach
                            </tbody>

                        </table><!-- end of table -->

                        {{ $articles->appends(request()->query())->links() }}

                    @else

                        <h2>@lang('site.no_data_found')</h2>

                    @endif

                </div><!-- end of box body -->


            </div><!-- end of box -->

        </section><!-- end of content -->

    </div><!-- end of content wrapper -->


@endsection
