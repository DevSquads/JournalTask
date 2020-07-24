from django.test import TestCase
from django.urls import reverse
from django.contrib.auth import get_user_model
from journal.models import Article
from . import views

def create_test_admin_user():
	credentials = {
			'username': 'testuser',
			'email' : 'testuser@email.com',
			'password': 'qwerty12345*',
			'is_superuser': True}
	user = get_user_model().objects.create_superuser(**credentials)
	return credentials

def create_test_author_user():
	credentials = {
			'username': 'testuser',
			'email' : 'testuser@email.com',
			'password': 'qwerty12345*'}
	get_user_model().objects.create_user(**credentials)
	return credentials

def create_articles():
	author1 = {
			'username': 'Author1',
			'email' : 'author1@email.com',
			'password': 'qwerty12345*'}
	author1 = get_user_model().objects.create_user(**author1)
	author2 = {
			'username': 'Author2',
			'email' : 'author2@email.com',
			'password': 'qwerty12345*'}
	author2 = get_user_model().objects.create_user(**author2)
	article1 = {
	'title': "Test Article 1",
	'description': "This is the first article.",
	'author' : author1,
	'approved': True
	}
	Article.objects.create(title = article1['title'], description = article1['description'], author = article1['author'], approved = article1['approved'])
	article2 = {
	'title': "Test Article 2",
	'description': "This is the second article.",
	'author' : author1,
	'approved': True
	}
	Article.objects.create(title = article2['title'], description = article2['description'], author = article2['author'], approved = article2['approved'])
	article3 = {
	'title': "Test Article 3",
	'description': "This is the third article.",
	'author' : author2,
	'approved': True
	}
	Article.objects.create(title = article3['title'], description = article3['description'], author = article3['author'], approved = article3['approved'])
	article4 = {
	'title': "Test Article 4",
	'description': "This is the fourth article.",
	'author' : author2,
	'approved': False
	}
	Article.objects.create(title = article4['title'], description = article4['description'], author = article4['author'], approved = article4['approved'])

class SignUpPageTests(TestCase):
	def setUp(self) -> None:
		self.username = 'testuser'
		self.email = 'testuser@email.com'
		self.password = 'qwerty12345*'

	def test_signup_page_url(self):
		response = self.client.get("/journal/register/")
		self.assertEqual(response.status_code, 200)
		self.assertTemplateUsed(response, template_name='journal/register.html')

	def test_signup_form(self):
		response = self.client.post(reverse('register'), data={
			'username': self.username,
			'email': self.email,
			'password1': self.password,
			'password2': self.password
		}, follow = True)
		self.assertEqual(response.status_code, 200)
		user = get_user_model().objects.filter(email = self.email)
		self.assertEqual(user.exists(), True)

class LogInTest(TestCase):
	def setUp(self):
		self.credentials = {
			'username': 'testuser',
			'password': 'qwerty12345*'}
		get_user_model().objects.create_user(**self.credentials)
	def test_login(self):
		response = self.client.post('/login/', self.credentials, follow=True)
		self.assertTrue(response.context['user'].is_active)

class AdminTests(TestCase):
	def setUp(self) -> None:
		self.credentials = create_test_admin_user()
		self.client.login(username=self.credentials['username'], password=self.credentials['password'])
		create_articles()
		current_user = get_user_model().objects.get(username = self.credentials['username'])
		self.article = {
	'title': "Test Article 5",
	'description': "This is the fifth article.",
	'author' : current_user,
	'approved': False}

	def test_article_list_view(self):
		response = self.client.get("/journal/", follow=True)
		self.assertEqual(response.status_code, 200)
		self.assertTemplateUsed(response, template_name='article_list.html')

	def test_article_sorting(self):
		article_list = views.get_articles_list_admin_view()
		self.assertEqual(article_list[0].author.username, "Author1")

	def test_delete_article(self):
		response = self.client.get("/journal/delete_article/1", follow=True)
		self.assertEqual(response.status_code, 200)
		article = Article.objects.filter(id = 1)
		self.assertEqual(article.exists(), False)

	def test_approve_article(self):
		response = self.client.get("/journal/approve_article/4", follow=True)
		self.assertEqual(response.status_code, 200)
		article = Article.objects.get(id = 4)
		self.assertEqual(article.approved, True)

	def test_create_article(self):
		response = self.client.get("/journal/create_article", follow=True)
		self.assertTemplateUsed(response, template_name='journal/create_article.html')
		self.assertEqual(response.status_code, 200)

		response = self.client.post(reverse('create_article'), data=self.article, follow = True)
		self.assertEqual(response.status_code, 200)
		article = Article.objects.filter(title = self.article['title'])
		self.assertEqual(article.exists(), True)

class AuthorTests(TestCase):
	def setUp(self) -> None:
		self.credentials = create_test_author_user()
		self.client.login(username=self.credentials['username'], password=self.credentials['password'])
		create_articles()
		current_user = get_user_model().objects.get(username = self.credentials['username'])
		self.article = {
	'title': "Test Article 5",
	'description': "This is the fifth article.",
	'author' : current_user,
	'approved': False}

	def test_article_list_view(self):
		response = self.client.get("/journal/", follow=True)
		self.assertEqual(response.status_code, 200)
		self.assertTemplateUsed(response, template_name='article_list.html')

	def test_article_sorting(self):
		Article.objects.create(title = self.article['title'], description = self.article['description'], author = self.article['author'], approved = self.article['approved'])
		article_list = views.get_articles_list_author_view(self.client.session['_auth_user_id'])
		self.assertEqual(article_list[0].author.username, self.credentials['username'])

	def test_delete_article(self):
		response = self.client.get("/journal/delete_article/2", follow=True)
		self.assertEqual(response.status_code, 404)
		article = Article.objects.filter(id = 1)
		self.assertEqual(article.exists(), True)

	def test_approve_article(self):
		response = self.client.get("/journal/approve_article/4", follow=True)
		self.assertEqual(response.status_code, 404)
		article = Article.objects.get(id = 4)
		self.assertEqual(article.approved, False)

	def test_create_article(self):
		response = self.client.get("/journal/create_article", follow=True)
		self.assertTemplateUsed(response, template_name='journal/create_article.html')
		self.assertEqual(response.status_code, 200)

		response = self.client.post(reverse('create_article'), data=self.article, follow = True)
		self.assertEqual(response.status_code, 200)
		article = Article.objects.filter(title = self.article['title'])
		self.assertEqual(article.exists(), True)