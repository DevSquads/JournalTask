from . import views
from django.contrib import admin
from django.urls import path,include

urlpatterns = [
    path('', views.ArticleList.as_view(), name='index'),
    path("register/", views.register, name="register"),
]