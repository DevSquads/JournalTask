from django.http import HttpResponse
from django.contrib.auth import login, authenticate
from django.contrib.auth.forms import UserCreationForm
from django.shortcuts import render, redirect
from .forms import RegisterForm,ArticleForm
from django.contrib.auth import get_user_model
from django.contrib.auth.decorators import login_required
from django.views import generic
from journal.models import Article
from django.utils.decorators import method_decorator
from django.db.models import Count,Q
from django.db.models import Case, When

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

@method_decorator(login_required(login_url='/login/'), name='dispatch')
class AdminArticleList(generic.TemplateView):
	template_name = "article_list.html"
	def get_context_data(self, **kwargs):
		context = super().get_context_data(**kwargs)

		context['object_list'] = Article.objects.annotate(count_approved=Count('author', filter=Q(approved=True))).order_by('count_approved')
		return context

@method_decorator(login_required(login_url='/login/'), name='dispatch')
class AuthorArticleList(generic.TemplateView):
	template_name = "article_list.html"
	def get_context_data(self, **kwargs):
		context = super().get_context_data(**kwargs)

		context['object_list'] = Article.objects.order_by(
	Case(When(author=self.request.user.id, then=0), default=1))
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
