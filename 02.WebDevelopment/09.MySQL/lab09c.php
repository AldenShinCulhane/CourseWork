<?php include('dbconnect.php'); ?>

<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
    <title>My CPS530 Photographs Database Queried for Ontario Photos Webpage</title>
    <style>
        body {
            font-family: 'Lato', sans-serif;
            background-color: white;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .photo-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }
        .photo-box {
            margin: 10px;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 10px;
            background-color: white;
            text-align: center;
            width: 220px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .photo-box img {
            width: 200px;
            height: 150px;
            border-radius: 8px;
            object-fit: cover;
        }
        .photo-box p {
            margin-top: 10px;
            color: #333;
        }
        .no-photo {
            color: red;
            margin: 20px;
        }
    </style>
</head>
<body>
    <div class="photo-container">
        <?php
        $sql = "SELECT * FROM Photographs WHERE location = 'Ontario'";
        $result = mysqli_query($connect, $sql) or die(mysqli_error($connect));

        if (mysqli_num_rows($result) > 0) {
            while ($row = mysqli_fetch_assoc($result)) {
                echo "<div class='photo-box'>";
                echo "<img src='" . $row['picture_url'] . "' alt='" . htmlspecialchars($row['subject']) . "'>";
                echo "<p>" . htmlspecialchars($row['subject']) . " - " . htmlspecialchars($row['location']) . "</p>";
                echo "</div>";
            }
        } else {
            echo "<p class='no-photo'>No Ontario photos found.</p>";
        }
        ?>
    </div>
</body>
</html>
