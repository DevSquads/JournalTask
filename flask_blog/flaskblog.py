from flask import Flask, escape, request, render_template, url_for

#url_for finds the exact route for us in flask

app = Flask(__name__)

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


if __name__ == '__main__':
    app.run(debug=True)


