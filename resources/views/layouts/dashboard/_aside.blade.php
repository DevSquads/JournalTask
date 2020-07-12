<aside class="main-sidebar">

    <section class="sidebar">

        <div class="user-panel">
            <div class="pull-left image">
                <img src="{{ asset(auth()->user()->image_path) }}" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>{{auth()->user()->name}}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <ul class="sidebar-menu" data-widget="tree">
        <li><a href="{{ route('dashboard.welcome') }}"><i class="fa fa-th"></i><span>@lang('site.dashboard')</span></a></li>
        @if (auth()->user()->hasPermission('read_roles'))
        <li><a href="{{ route('dashboard.roles.index') }}"><i class="fa fa-th"></i><span>@lang('site.roles')</span></a></li>
         @endif
        @if (auth()->user()->hasPermission('read_users'))
            <li><a href="{{ route('dashboard.users.index') }}"><i class="fa fa-th"></i><span>@lang('site.users')</span></a></li>
        @endif
        @if (auth()->user()->hasPermission('read_articles'))
            <li><a href="{{ route('articles.index') }}"><i class="fa fa-th"></i><span>@lang('site.articles')</span></a></li>
        @endif
        </ul>

    </section>

</aside>

