from flask import Flask, escape, request, render_template, url_for
#url_for finds the exact route for us in flask
from forms import RegistrationForm, LoginForm

app = Flask(__name__)

app.config['SECRET_KEY'] = 'bee2b6b98e91cbe9c98f9268a8eea985'


posts = [
    {
        'author': 'Rana',
        'title':'tennis',
        'content': 'Blog post 1',
        'Date_Posted': 'April 21 , 2019'

    },
    {
        'author': 'Radwa',
        'title':'Books',
        'content': 'Blog post 2',
        'Date_Posted': 'April 21 , 2019'

    }
]


@app.route('/')
@app.route('/home')
def home():
    return render_template('home.html', posts=posts)

@app.route('/about')
def about():
    return render_template('about.html', title=about)

@app.route('/register')
def register():
    form= RegistrationForm()
    return render_template('register.html', title='Register', form=form)

@app.route('/login')
def register():
    form= LoginForm()
    return render_template('login.html', title='Login', form=form)


if __name__ == '__main__':
    app.run(debug=True)


