#----------------------------------------------------------------------------#
# Models.
#----------------------------------------------------------------------------#

from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


'''
>>>>>> authers <<<<<<
 - id:                INTEGER       PRIMARY KEY 
 - mail:              VARCHAR(50)   NOT NULL UNIQUE
 - name:              VARCHAR(50)   NOT NULL
 - approved_articles: INTEGER       NOT NULL
'''


class Author(db.Model):
    __tablename__ = 'authors'
    id = db.Column(db.Integer, primary_key=True)
    mail = db.Column(db.String(50), nullable=False, unique=True)
    name = db.Column(db.String(50), nullable=False)
    approved_articles = db.Column(db.Integer, nullable=False)
    articles = db.relationship(
        'Article',
        backref='author',
        lazy=True
    )

    def __init__(self, mail, name, approved_articles):
        self.mail = mail
        self.name = name
        self.approved_articles = approved_articles

    def insert(self):
        db.session.add(self)
        db.session.commit()

    def update(self):
        db.session.commit()

    def delete(self):
        db.session.delete(self)
        db.session.commit()

    def rollback(self):
        db.session.rollback()

    def format(self):
        return {
            'id': self.id,
            'mail': self.mail,
            'name': self.name,
            'approved_articles': self.approved_articles
        }


'''
>>>>>> authers <<<<<<
 - id:          INTEGER         PRIMARY KEY 
 - title:       VARCHAR(200)    NOT NULL UNIQUE
 - author_name: TEXT            NOT NULL
 - description: INTEGER         NOT NULL
 - author_id:   INTEGER         FOREIGNKEY
'''


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

    def rollback(self):
        db.session.rollback()

    def format(self):
        return {
            'id': self.id,
            'title': self.title,
            'description': self.description,
            'approved': self.approved,
            'author_id': self.author_id,
            'author_name': self.author.name,
            'no_of_approved': self.author.approved_articles
        }
