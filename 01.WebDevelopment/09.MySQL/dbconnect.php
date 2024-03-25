<?php
$host = "localhost";
$database = "ashincul";
$user = "ashincul";
$password = "znfyVMcA";

$connect = mysqli_connect($host, $user, $password, $database)
or die(mysqli_error());
echo "<div>connected to mysql database <b>$database</b></div>";
?>
