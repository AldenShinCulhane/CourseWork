from flask import render_template
from app import app

@app.route('/')
@app.route('/index')

def index():
    user = {'username': 'Samuel'}
    return render_template('index.html', title='Home', user=user)
