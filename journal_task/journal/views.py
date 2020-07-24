from django.http import HttpResponse
from django.contrib.auth import login, authenticate
from django.contrib.auth.forms import UserCreationForm
from django.shortcuts import render, redirect
from .forms import RegisterForm,ArticleForm
from django.contrib.auth import get_user_model
from django.contrib.auth.decorators import login_required
from django.views import generic
from journal.models import Article,User
from django.utils.decorators import method_decorator
from django.db.models import Count,Q,Value,Case
from django.db.models import Case, When
from .tables import ArticleListTable
from django.db import models
from django.contrib.auth.decorators import user_passes_test

def register(response):
	if response.method == "POST":
		form = RegisterForm(response.POST)
		if form.is_valid():
			form.save()
		return redirect("/journal")
	else:
		form = RegisterForm()
		return render(response, "journal/register.html", {"form":form})

def login(request):
	if request.method == "POST":
		if request.POST.get('submit') == 'sign_in':
			username = request.POST['username']
			password = request.POST['password']
			user = authenticate(request, username=username, password=password)
			if user is not None:
				login(request, user)
				return redirect("/journal")
			else:
				return redirect("/journal/register")

def get_articles_list_admin_view():
	authors = User.objects.annotate(count_approved=Count('article', filter=Q(article__approved=True))).order_by('-count_approved').values_list('id', flat=True)
	authors_dict = {index: x for index, x in enumerate(authors, start=1)}
	whens = [When(author=v, then=k) for k, v in authors_dict.items()]
	articles_list = Article.objects.all().annotate(rank=Case(
			*whens,default=0,output_field=models.DecimalField())).order_by('rank')
	return articles_list

@method_decorator(login_required(login_url='/login/'), name='dispatch')
class AdminArticleList(generic.TemplateView):
	template_name = "article_list.html"
	def get_context_data(self, **kwargs):
		context_dict = {}
		articles_list = get_articles_list_admin_view()
		table = ArticleListTable(articles_list)
		context_dict['object_list'] = table
		return context_dict

def get_articles_list_author_view(author_id):
	articles_list = Article.objects.order_by(
	Case(When(author=author_id, then=0), default=1))
	return articles_list

@method_decorator(login_required(login_url='/login/'), name='dispatch')
class AuthorArticleList(generic.TemplateView):
	template_name = "article_list.html"
	def get_context_data(self, **kwargs):
		context = super().get_context_data(**kwargs)
		articles_list = get_articles_list_author_view(self.request.user.id)
		context['object_list'] = articles_list
		return context

@method_decorator(login_required(login_url='/login/'), name='dispatch')
class ArticleList(generic.View):
	def dispatch(self, request, *args, **kwargs):
		if request.user.is_superuser:
			return AdminArticleList.as_view()(request, *args, **kwargs)
		else:
			return AuthorArticleList.as_view()(request, *args, **kwargs)

@login_required
def create_article(request):
	if request.method == "POST":
		form = ArticleForm(request.POST, request = request)
		if form.is_valid():
			form.save()
			return redirect("/journal")
	else: 
		form = ArticleForm()
		return render(request, 'journal/create_article.html', {'form': form}) 


@user_passes_test(lambda u: u.is_superuser)
def delete_article(request,article_id):
	Article.objects.filter(id=article_id).delete()
	return redirect("/journal")

@user_passes_test(lambda u: u.is_superuser)
def approve_article(request,article_id):
	article = Article.objects.get(id = article_id)
	article.approved = True
	article.save()
	return redirect("/journal")
