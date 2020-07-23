from . import views
from django.contrib import admin
from django.urls import path,include
from django.conf.urls import url

urlpatterns = [
    path('', views.ArticleList.as_view(), name='index'),    
    url(r'^register/$', views.register, name='register'),
    url(r'^create_article/$', views.create_article, name='create_article'),
]