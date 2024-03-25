<?php include('dbconnect.php'); ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
    <title>My CPS530 Photographs Database Queried by the User</title>
    <style>
        body {
            font-family: 'Lato', sans-serif;
            background-color: white;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .form-container {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            margin-top: 20px;
        }
        .checkbox-group {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            text-align: left;
            margin: 10px;
            padding: 5px;
            border: 1px solid #ddd;
            min-height: 150px;
        }
        .checkbox-group label {
            display: block;
            cursor: pointer;
        }
        .checkbox-group input[type='checkbox'] {
            margin-right: 5px;
        }
        .submit-button {
            display: flex;
            align-items: flex-end;
            justify-content: center;
            padding: 5px;
            margin: 10px;
        }
        input[type='submit'] {
            padding: 10px 15px;
            font-size: 1em;
            cursor: pointer;
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
    <div class="form-container">
        <form action='lab09d.php' method='post'>
            <div class="checkbox-group">
                <strong>Locations</strong>
                <?php
                $locations = mysqli_query($connect, "SELECT DISTINCT location FROM Photographs");
                while ($loc = mysqli_fetch_assoc($locations)) {
                    echo "<label><input type='checkbox' name='location[]' value='" . htmlspecialchars($loc['location']) . "'>" . htmlspecialchars($loc['location']) . "</label>";
                }
                ?>
            </div>

            <div class="checkbox-group">
                <strong>Dates</strong>
                <?php
                $dates = mysqli_query($connect, "SELECT DISTINCT YEAR(date_taken) as year FROM Photographs ORDER BY year DESC");
                while ($date = mysqli_fetch_assoc($dates)) {
                    echo "<label><input type='checkbox' name='date[]' value='" . htmlspecialchars($date['year']) . "'>" . htmlspecialchars($date['year']) . "</label>";
                }
                ?>
            </div>

            <div class="submit-button">
                <input type='submit' value='Search'>
            </div>
        </form>
    </div>

    <div class="photo-container">
        <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $selectedLocations = $_POST['location'] ?? [];
            $selectedDates = $_POST['date'] ?? [];

            if ($selectedLocations && $selectedDates) {
                $locationQueryPart = implode(",", array_map(function($loc) use ($connect) {
                    return "'" . mysqli_real_escape_string($connect, $loc) . "'";
                }, $selectedLocations));
                $dateQueryPart = implode(",", array_map(function($date) use ($connect) {
                    return "'" . mysqli_real_escape_string($connect, $date) . "'";
                }, $selectedDates));

                $query = "SELECT * FROM Photographs WHERE location IN ($locationQueryPart) AND YEAR(date_taken) IN ($dateQueryPart)";
                $result = mysqli_query($connect, $query);

                if (mysqli_num_rows($result) > 0) {
                    while ($row = mysqli_fetch_assoc($result)) {
                        echo "<div class='photo-box'>";
                        echo "<img src='" . htmlspecialchars($row['picture_url']) . "' alt='" . htmlspecialchars($row['subject']) . "'>";
                        echo "<p>" . htmlspecialchars($row['subject']) . " - " . htmlspecialchars($row['location']) . " - " . date("Y", strtotime($row['date_taken'])) . "</p>";
                        echo "</div>";
                    }
                } else {
                    echo "<p class='no-photo'>No photos found for the selected criteria.</p>";
                }
            } else {
                echo "<p class='no-photo'>Please select at least one location and date.</p>";
            }
        }
        ?>
    </div>
</body>
</html>
