from flask import Flask
#url_for finds the exact route for us in flask
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)

app.config['SECRET_KEY'] = 'bee2b6b98e91cbe9c98f9268a8eea985'
app.config['SQLALCHEMY_DATABASE_URI']= 'sqlite:///site.db'
db = SQLAlchemy(app)

from flaskblog import routes