#!/usr/bin/perl
use strict;
use warnings;
print "Content-type: text/html\n\n";

print <<"END_HTML";
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Lato', sans-serif;
            text-align: center;
            color: #0056b3;
        }
        .centered {
            margin-top: 20%;
        }
    </style>
</head>
<body>
    <div class="centered">
        <h1>This is my first Perl program</h1>
    </div>
</body>
</html>
END_HTML
