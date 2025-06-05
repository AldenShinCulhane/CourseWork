<html>
<head>
  <title>My CPS530 Lab 10 Webpage using Classic ASP</title>
  <style>
  body { padding: 50px; }
  </style>
  <%
  ' Set body colour based on query string param "bgColor"
  bgColor = Request.QueryString("bgColor")
  response.write("<style> body { background-color: " & bgColor & ";} </style>")
  %>
</head>
<body>
  <h1>Alden Shin-Culhane: Lab 10</h1>
  <br>
  <%
  ' Get "lastVisit" cookie value, then check if it's set
  lastVisit = Request.Cookies("lastVisit")
  If Len(lastVisit) < 1 Then
    response.write("<p><h2>This is your first visit ... welcome!</h2></p>")
  Else
    response.write("<p><h2>Last visit: " & lastVisit & "</h2></p>")
  End If

  ' Update "lastVisit" cookie with current EST time (1 hour offset, as server is CST)
  Response.Cookies("lastVisit") = DateAdd("h", 1, Now())
  %>
</body>
</html>
