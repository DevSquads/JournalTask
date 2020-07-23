from django.shortcuts import render
from django.http import HttpResponse
from django.contrib.auth import login, authenticate
from django.contrib.auth.forms import UserCreationForm
from django.shortcuts import render, redirect
from .forms import RegisterForm
from django.contrib.auth import get_user_model

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

def index(request):
	return HttpResponse("You're looking at articles.")

def create_article(request):
	response = "You're creating an article"
	return HttpResponse(response)
