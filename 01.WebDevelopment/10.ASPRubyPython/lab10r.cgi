#!/usr/bin/ruby
print "Content-type: text/html\n\n"

require 'cgi'
cgi = CGI.new
puts "<html>"
puts "<body>"
puts "<center>"
puts "<h1 style='font-size:80; color: LightSalmon; background-color: Cornsilk'>"
puts cgi['city'].capitalize + ", " + cgi['province'].capitalize + "<br>"
puts cgi['country'].capitalize + "</h1>"
puts "<img style='width: 100%;' src=" + cgi['imageUrl'] + ">"
puts "</center>"
puts "</body>"
puts "</html>"

