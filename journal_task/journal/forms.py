from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth import get_user_model
from journal.models import Article


class RegisterForm(UserCreationForm):
	email = forms.EmailField()

	class Meta:
		model = get_user_model()
		fields = ["username", "email", "password1", "password2"]

class ArticleForm(forms.ModelForm):
	def __init__(self, *args, **kwargs):
	   self.request = kwargs.pop('request', None)
	   return super(ArticleForm, self).__init__(*args, **kwargs)


	def save(self, *args, **kwargs):
		kwargs['commit']=False
		obj = super(ArticleForm, self).save(*args, **kwargs)
		if self.request:
			obj.author_id = self.request.user.id
			obj.save()
			return obj

	class Meta:
		model = Article
		fields = ['title','description']