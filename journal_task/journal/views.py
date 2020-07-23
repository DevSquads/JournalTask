from django.http import HttpResponse
from django.contrib.auth import login, authenticate
from django.contrib.auth.forms import UserCreationForm
from django.shortcuts import render, redirect
from .forms import RegisterForm
from django.contrib.auth import get_user_model
from django.contrib.auth.decorators import login_required
from django.views import generic
from journal.models import Article
from django.utils.decorators import method_decorator

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
	username = request.POST['username']
	password = request.POST['password']
	user = authenticate(request, username=username, password=password)
	if user is not None:
		login(request, user)
		return redirect("/journal")
	else:
		# Return an 'invalid login' error message.
		...

@method_decorator(login_required, name='dispatch')
class ArticleList(generic.TemplateView):
	template_name = "article_list.html"
	def get_context_data(self, **kwargs):
		context = super().get_context_data(**kwargs)
		context['object_list'] = Article.objects.all()
		return context

@login_required
def create_article(request):
	response = "You're creating an article"
	return HttpResponse(response)
