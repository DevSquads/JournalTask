import django_tables2 as tables
from journal.models import Article

class ArticleListTable(tables.Table):
	template_code = '''<a href="/journal/approve_article/{{ record.id }}">Approve</a> / <a href="/journal/delete_article/{{ record.id }}" 
	onclick="return confirm('Are you sure you want to delete this?')">Delete</a>'''
	change = tables.TemplateColumn(template_code)
	class Meta:
		model = Article