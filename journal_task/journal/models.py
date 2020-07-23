from django.db import models
from django import forms
from django.contrib.auth.models import AbstractUser

class User(AbstractUser):
    class Meta:
        db_table = 'auth_user'

    email = models.EmailField()


class Article(models.Model):
    title = models.CharField(max_length=200)
    description = models.TextField()
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    approved = models.BooleanField(default=False)
    
    def __str__(self):
    	return str(self.title) + " by " + str(self.author.username)