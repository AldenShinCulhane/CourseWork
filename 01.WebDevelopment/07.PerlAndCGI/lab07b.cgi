#!/usr/bin/perl
use strict;
use warnings;
use CGI;

my $cgi = CGI->new;

print "Content-type: text/html\n\n";
print <<"END_HTML";
<html>
<head>
    <title>Registration Result</title>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Lato', sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        p {
            margin: 10px 0;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class='container'>

END_HTML

my $first_name = $cgi->param('first_name');
my $last_name = $cgi->param('last_name');
my $street = $cgi->param('street');
my $city = $cgi->param('city');
my $postal_code = $cgi->param('postal_code');
my $province = $cgi->param('province');
my $phone = $cgi->param('phone');
my $email = $cgi->param('email');

my $phone_valid = $phone =~ /^\d{10}$/;
my $postal_valid = $postal_code =~ /^[A-Z]\d[A-Z] \d[A-Z]\d$/;
my $email_valid = $email =~ /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

print "<div class='container'>";
if ($phone_valid && $postal_valid && $email_valid) {
    print "<p>Registration Successful!</p>";
    print "<p>Name: $first_name $last_name</p>";
    print "<p>Address: $street, $city, $province, $postal_code</p>";
    print "<p>Phone: $phone</p>";
    print "<p>Email: $email</p>";
} else {
    print "<p class='error'>Registration Failed due to incorrect data:</p>";
    print "<p class='error'>Phone Number Error</p>" unless $phone_valid;
    print "<p class='error'>Postal Code Error</p>" unless $postal_valid;
    print "<p class='error'>Email Address Error</p>" unless $email_valid;
}
print "</div></body></html>";
