from flask_wtf import FlaskForm
from wtforms import StringField, IntegerField, SubmitField

class EdgeInputForm(FlaskForm):
    vert1 = StringField('V1')
    vert2 = StringField('V2')
    weight = IntegerField('Weight')
    submit = SubmitField('Add edge')