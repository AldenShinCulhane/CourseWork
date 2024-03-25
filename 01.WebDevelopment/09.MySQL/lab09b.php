<?php include ('dbconnect.php'); ?>

<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
    <title>My CPS530 Photographs Database Display Webpage for Lab 9 using PHP and MySQL</title>
    <style>
        body {
            font-family: 'Lato', sans-serif;
            background-color: white;
            text-align: center;
        }
        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4C8BAF;
            color: white;
        }
        tr:nth-child(even) {background-color: white;}
        tr:hover {background-color: #ddd;}
        img {
            width: 150px;
            height: auto;
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <?php
    $sql = "SELECT * FROM Photographs ORDER BY date_taken DESC";
    $result = mysqli_query($connect, $sql) or die(mysqli_error($connect));

    echo "<table>";
    echo "<tr><th>Picture Number</th><th>Subject</th><th>Location</th><th>Date Taken</th><th>Picture</th></tr>";

    while ($row = mysqli_fetch_assoc($result)) {
        echo "<tr>";
        echo "<td>" . $row['picture_number'] . "</td>";
        echo "<td>" . $row['subject'] . "</td>";
        echo "<td>" . $row['location'] . "</td>";
        echo "<td>" . $row['date_taken'] . "</td>";
        echo "<td><img src='" . $row['picture_url'] . "'></td>";
        echo "</tr>";
    }

    echo "</table>";
    ?>
</body>
</html>
