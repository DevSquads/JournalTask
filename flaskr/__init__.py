from flask import Flask
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

@app.route('/')
# @requires_auth('manage:articles')
# @requires_auth()
def hello():
    author = Author.query.first()
    return 'Hello ' + author.author_name


#  authors
#  ----------------------------------------------------------------

@app.route('/authors')
def get_authors():
   pass


@app.route('/authors', methods=['POST'])
def post_author():
   pass


@app.route('/authors/<int:id>')
def get_author():
   pass


@app.route('/authors/<int:id>', methods=['POST'])
def update_author():
   pass


@app.route('/authors/<int:id>', methods=['DELETE'])
def delete_author():
   pass

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
    pass


@app.errorhandler(404)
def not_found(error):
    pass


@app.errorhandler(422)
def Unprocessable(error):
    pass


@app.errorhandler(AuthError)
def auth_error(error):
    pass


if __name__ == '__main__':
    app.run()
