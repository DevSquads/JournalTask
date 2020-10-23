from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate

from .auth import requires_auth, AuthError

database_uri = 'postgresql://postgres:root@localhost:5432/test'

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = database_uri
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)
migrate = Migrate(app, db)


class Author(db.Model):
    __tablename__ = 'authors'
    id = db.Column(db.Integer, primary_key=True)
    mail = db.Column(db.String(50), nullable=False, unique=True)
    author_name = db.Column(db.String(50), nullable=False)
    approved_articles = db.Column(db.Integer, nullable=False)
    articles = db.relationship(
        'Article',
        backref='author',
        lazy=True
        )

    def __init__(self, mail, name, approved_articles):
        self.mail = mail
        self.author_name = name
        self.approved_articles = approved_articles

    def insert(self):
        db.session.add(self)
        db.session.commit()

    def update(self):
        db.session.commit()

    def delete(self):
        db.session.delete(self)
        db.session.commit()

    def format(self):
        return {
          'id': self.id,
          'mail': self.mail,
          'name': self.author_name,
          'approved_articles': self.approved_articles
        }


class Article(db.Model):
    __tablename__ = 'articles'
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(200), nullable=False)
    description = db.Column(db.Text, nullable=False)
    approved = db.Column(db.Boolean, nullable=False)

    # CASCADE option for the foreign key
    # added in the migration files
    author_id = db.Column(
        db.Integer,
        db.ForeignKey('authors.id'),
        nullable=False)

    def __init__(self, title, description, approved, author_id):
        self.title = title
        self.description = description
        self.approved = approved
        self.author_id = author_id

    def insert(self):
        db.session.add(self)
        db.session.commit()

    def update(self):
        db.session.commit()

    def delete(self):
        db.session.delete(self)
        db.session.commit()

    def format(self):
        return {
          'id': self.id,
          'title': self.title,
          'description': self.description,
          'approved': self.approved,
          'author_id': self.author_id
        }


@app.route('/')
# @requires_auth('manage:articles')
@requires_auth()
def hello(payload):
    author = Author.query.first()
    return 'Hello ' + author.author_name


if __name__ == '__main__':
    app.run()
