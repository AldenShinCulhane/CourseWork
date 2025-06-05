from django.db import models

# Create your models here.
class SurveyQuestion(models.Model):
    respondent = models.CharField(max_length=50)
    counter = models.IntegerField(default=5)
