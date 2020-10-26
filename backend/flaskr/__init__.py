from flask import Flask, jsonify, request, abort
from flask_migrate import Migrate
from flask_cors import CORS

from .auth import requires_auth, AuthError
from .db import db, Author, Article


#----------------------------------------------------------------------------#
# App Config.
#----------------------------------------------------------------------------#

 
database_name = "test"
postgres_user = "postgres"
password = "root"

database_uri = "postgresql://{}:{}@{}/{}".format(
    postgres_user,
    password,
    'localhost:5432',
    database_name
    )


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = database_uri
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db.init_app(app)
migrate = Migrate(app, db)
CORS(app)


# ---------------------------------------------------------------------------#
# Controllers.
# ---------------------------------------------------------------------------#
# resources    | GET     | POST     | PATCH     | DELETE
# -------------|---------|----------|-----------|----------------------------#
# authors      | get all | post new |  XXX      |  XXX
#              |         | get self |           |
# -------------|---------|----------|-----------|----------------------------#
# authors/id   | get one |   XXX    | edit one  | delete one
# -------------|---------|----------|-----------|----------------------------#
# articles     | get all | post new |  XXX      |  XXX
#              | get app.|          |           |
# -------------|---------|----------|-----------|----------------------------#
# articles/id  | get one | approve  | edit one  | delete one
#              | get app.|          |           |
# ---------------------------------------------------------------------------#

@app.route('/')
def hello():
   return jsonify({
      'message': 'Hello world'
   })


#  authors
# ---------------------------------------------------------------------------#
# resources    | GET     | POST     | PATCH     | DELETE
# -------------|---------|----------|-----------|----------------------------#
# authors      | get all | post new |  XXX      |  XXX
#              |         | get self |           |
# -------------|---------|----------|-----------|----------------------------#
# authors/id   | get one |   XXX    | edit one  | delete one
# ---------------------------------------------------------------------------#

@app.route('/authors', methods=['GET'])
@requires_auth()
def get_authors():
   '''
   - A private endpoint to handle GET requests for all available authors.
   - return an ordered list of authors accroding to approved articles
   - It requires no permission
   '''
   _authors = Author.query.order_by(Author.approved_articles.desc()).all()
   authors = [author.format() for author in _authors]
   return jsonify({
      'response': authors,
      'success': True
   })


@app.route('/authors', methods=['POST'])
@requires_auth()
def post_author():
   '''
   This private endpoint should:
      - create a new author then return his data
      - if the author is already exists just return his data
   It requires no permission
   The header must contain the user mail and name 
   returns:
      - 422 if the mail is missing
      - 400 if the name is missing
      - 500 if there is an temporary error with the database 
      - 200 and the personal data of the author
   '''
   body = request.get_json()

   mail = body.get('mail')   
   if not mail:
      abort(422)

   name = body.get('name')
   if not name:
      abort(400)

   else:
      _author = Author.query.filter_by(mail=mail).first()

   if (_author is None):
      author = Author(mail, name, 0)
   
      try:
         author.insert()
         return jsonify({
         'response': author.format(),
         'success': True
         })
      except Exception:
         author.rollback()
         abort(500)

   else:
      return jsonify({
         'response': _author.format(),
         'success': True
      })


@app.route('/authors/<int:id>', methods=['GET'])
@requires_auth()
def get_author(id):
   '''
   - A private endpoint to handle GET requests for specific author data.
   - return details about a specific author.
   - It requires no permission
   '''
   author = Author.query.get(id)

   if author is None:
      abort(404)

   else:
      return jsonify({
         'response': author.format(),
         'success': True
      })


@app.route('/authors/<int:id>', methods=['PATCH'])
@requires_auth('manage:articles')
def update_author(id):
   '''
   - A private endpoint to handle PATCH requests to edit author's name.
   - return details about a specific author.
   - It requires admin permissions
   '''
   body = request.get_json()

   name = body.get('name')  

   if not name:
      abort(400)

   author = Author.query.get(id)

   if author is None:
      abort(404)

   else:
      author.name = name

      try:
         author.update()
      except Exception:
         author.rollback()
         abort(500)

      return jsonify({
         'response': author.format(),
         'success': True
      })


@app.route('/authors/<int:id>', methods=['DELETE'])
@requires_auth('manage:articles')
def delete_author(id):
   '''
   - A private endpoint to handle DELETE requests to delete an author from the database.
   - It requires admin permissions
   '''
   author = Author.query.get(id)

   if author is None:
      abort(404)
   
   else:
      author.delete()
      return jsonify({
         'success': True
      })

#  articles
# -------------|---------|----------|-----------|----------------------------#
# articles     | get all | post new |  XXX      |  XXX
#              | get app.|          |           |
# -------------|---------|----------|-----------|----------------------------#
# articles/id  | get one | approve  | edit one  | delete one
#              | get app.|          |           |
# ---------------------------------------------------------------------------#

@app.route('/admin/articles', methods=['GET'])
@requires_auth('manage:articles')
def get_all_articles():
   '''
   - A private endpoint to handle GET requests for all articles.
   - return a list of all articles ordered by authors' no of approved
   - It requires admin permission
   '''

   _articles = Article.query.all()

   articles = [article.format() for article in _articles]
   sorted_articles = sorted(articles, key=lambda i:i['no_of_approved'], reverse=True)
   return jsonify({
      'response': sorted_articles,
      'success': True
   })


@app.route('/admin/articles/<int:id>')
@requires_auth('manage:articles')
def get_article(id):
   '''
   - A private endpoint to handle GET requests for specific article data.
   - return details about a specific article.
   - It requires admin permission
   '''
   article = Article.query.get(id)

   if article is None:
      abort(404)

   else:
      return jsonify({
         'response': article.format(),
         'success': True
      })


@app.route('/articles', methods=['GET'])
def get_approved_articles():
   '''
   - A public endpoint to handle GET requests for approved articles.
   - return a list of approved articles ordered by authors' no of approved
   '''

   _articles = Article.query.filter_by(approved=True).all()
   articles = [article.format() for article in _articles]
   sorted_articles = sorted(articles, key=lambda i:i['no_of_approved'], reverse=True)
   return jsonify({
      'response': sorted_articles,
      'success': True
   })


@app.route('/articles/<int:id>')
def get_approved_article(id):
   '''
   - A public endpoint to handle GET requests for approved article data.
   - return details about a specific article.
   '''
   article = Article.query.get(id)

   if article is None:
      abort(404)

   if article.approved:

         return jsonify({
         'response': article.format(),
         'success': True
         })
      
   else:
      abort(404)


@app.route('/articles/<int:id>', methods=['PATCH'])
@requires_auth()
def update_article(id):
   '''
   - A private endpoint to handle PATCH requests to edit an article.
   - return the details of the articles after edit.
   - It requires no permissions
   '''
   article = Article.query.get(id)

   if article is None:
      abort(404)

   body = request.get_json()
   mail = body.get('mail')

   author = Author.query.filter_by(mail=mail).first()

   if author is None:
      abort(422)
   
   if author.mail != article.author.mail:
      abort(400)


   title = body.get('title')
   description = body.get('description')

   if (not title) and (not description):
      abort(400)

   else:
      if title:
         article.title = title
      if description:
         article.description = description
      if article.approved:
         article.approved = False
         article.author.approved_articles -= 1

      try:
         article.update()
      except Exception:
         author.rollback()
         abort(500)

      return jsonify({
         'response': article.format(),
         'success': True
      })


@app.route('/articles', methods=['POST'])
@requires_auth()
def post_article():
   '''
   This private endpoint should create a new article
      - It requires no permission
      - The header must contain the user id 
   returns:
      - 422 if the mail is missing
      - 400 if the body is missing the title or the description
      - 500 if there is an temporary error with the database 
      - 200 and the article in the response
   '''
   body = request.get_json()

   mail = body.get('mail')   
   if not mail:
      abort(422)

   title = body.get('title')
   description = body.get('description')

   if (not title) or (not description):
      abort(400)

   else:
      author = Author.query.filter_by(mail=mail).first()

   if (author is None):
      abort(400)

   else:
      article = Article(title, description, False, author.id)
   
      try:
         article.insert()
         return jsonify({
         'response': article.format(),
         'success': True
         })
      except Exception:
         article.rollback()
         abort(500)


@app.route('/articles/<int:id>', methods=['POST'])
@requires_auth('manage:articles')
def approve_article(id):
   '''
   - A private endpoint to handle POST requests to approve or unapprove articles.
   - return the details of the approved articles.
   - It requires admin permissions
   '''
   article = Article.query.get(id)

   if article is None:
      abort(404)

   else:
      article.approved = not article.approved
      
      if article.approved:
         article.author.approved_articles += 1

      else:
         article.author.approved_articles -= 1

      try:
         article.update()
      except Exception:
         article.rollback()
         abort(500)

      return jsonify({
         'response': article.format(),
         'success': True
      })


@app.route('/articles/<int:id>', methods=['DELETE'])
@requires_auth('manage:articles')
def delete_article(id):
   '''
   - A private endpoint to handle DELETE requests to delete an article from the database.
   - It requires admin permissions
   '''
   article = Article.query.get(id)

   if article is None:
      abort(404)
   
   else:
      if article.approved:
         article.author.approved_articles -= 1

      article.delete()
      return jsonify({
         'success': True
      })

#  errors
#  ----------------------------------------------------------------

@app.errorhandler(400)
def bad_request(error):
   return jsonify({
      'success': False,
      'error': 400,
      'message': 'bad request'
   }), 400


@app.errorhandler(404)
def not_found(error):
   return jsonify({
      'success': False,
      'error': 404,
      'message': 'resource not found'
   }), 404


@app.errorhandler(422)
def unprocessable(error):
   return jsonify({
      'success': False,
      'error': 422,
      'message': 'unprocessable'
   }), 422


@app.errorhandler(AuthError)
def auth_error(error):
   return jsonify({
        'success': False,
        'error': error.error['code'],
        'message': error.error['description']
    }), error.status_code


@app.errorhandler(500)
def server_error(error):
   return jsonify({
      'success': False,
      'error': 500,
      'message': 'server error, please try again'
   }), 500


if __name__ == '__main__':
    app.run()
