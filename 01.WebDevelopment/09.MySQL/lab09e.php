<?php include('dbconnect.php'); ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
    <title>My CPS530 Photographs Database Randomly Displaying a Photo</title>
    <style>
        body {
            font-family: 'Lato', sans-serif;
            background-color: white;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .photo-container {
            padding: 20px;
        }
        .photo-box {
            margin: auto;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 10px;
            background-color: white;
            text-align: center;
            max-width: 640px;
        }
        .photo-box img {
            width: 100%;
            height: auto;
            border-radius: 8px;
            object-fit: cover;
        }
        .photo-box p {
            margin-top: 10px;
            color: #333;
        }
        .total-count {
            margin-top: 20px;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="photo-container">
        <?php
        $sql = "SELECT * FROM Photographs ORDER BY RAND() LIMIT 1";
        $result = mysqli_query($connect, $sql) or die(mysqli_error($connect));
        $row = mysqli_fetch_assoc($result);

        echo "<div class='photo-box'>";
        echo "<img src='" . htmlspecialchars($row['picture_url']) . "' alt='" . htmlspecialchars($row['subject']) . "'>";
        echo "<p>" . htmlspecialchars($row['subject']) . " - " . htmlspecialchars($row['location']) . "</p>";
        echo "</div>";

        $countSql = "SELECT COUNT(*) as total FROM Photographs";
        $countResult = mysqli_query($connect, $countSql);
        $countRow = mysqli_fetch_assoc($countResult);
        echo "<p class='total-count'>Total images in the database: " . $countRow['total'] . "</p>";
        ?>
    </div>
</body>
</html>
