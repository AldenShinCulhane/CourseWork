#!/usr/bin/python

# Read form parameter values
import cgi, cgitb
form = cgi.FieldStorage()
city = form.getvalue('city')
province = form.getvalue('province')
country = form.getvalue('country')
imageUrl = form.getvalue('imageUrl')

# If not set, give default value
if not city:
  city = ""
if not province:
  province = ""
if not country:
  country = ""

print("Content-type:text/html\n\n")
print("<html>")
print("<body>")
print("<center>")
print("<h1 style='font-size:80; color: LightSalmon; background-color: Cornsilk'>")
print("%s, %s<br>%s</h1>" % (city.upper(), province.upper(), country.upper()))
print("<img style='width: 80%%; border: 50px solid green;' src=%s>" % (imageUrl))
print("</center>")
print("</body>")
print("</html>")
