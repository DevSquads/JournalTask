from flask import Flask, jsonify, request, abort
from flask_migrate import Migrate

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


# ---------------------------------------------------------------------------#
# Controllers.
# ---------------------------------------------------------------------------#
# resources    | GET     | POST     | PATCH     | DELETE
# -------------|---------|----------|-----------|----------------------------#
# authors      | get all | post new |  XXX      |  XXX
#              |         | get self |           |
# -------------|---------|----------|-----------|----------------------------#
# authors/id   | get one |   XXX    | edit one  | delete one
# -------------|-------------------------------------------------------------#
# articles     |
# -------------|-------------------------------------------------------------#
# articles/id  |
# ---------------------------------------------------------------------------#

@app.route('/')
# @requires_auth('manage:articles')
# @requires_auth()
def hello():
   author = Author.query.first()
   return jsonify({
      'message': 'Hello ' + author.name
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
#  ----------------------------------------------------------------

@app.route('/articles')
def get_articles():
   pass


@app.route('/articles/<int:id>')
def get_article():
   pass


@app.route('/articles/<int:id>', methods=['PATCH'])
def update_article():
   pass


@app.route('/articles', methods=['POST'])
def post_article():
   pass


@app.route('/articles/<int:id>', methods=['POST'])
def approve_article():
   pass


@app.route('/articles/<int:id>', methods=['DELETE'])
def delete_article():
   pass

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
