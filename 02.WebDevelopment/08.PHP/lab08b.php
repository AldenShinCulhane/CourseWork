<!DOCTYPE html>
<html>
<head>
    <title>Problem 2 Multiplication Table</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #2f2f2f;
            color: black;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        table {
            margin: 0 auto;
            border-collapse: collapse;
        }
        td, th {
            border: 1px solid #ddd;
            padding: 8px;
            background-color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $num1 = $_POST["num1"];
        $num2 = $_POST["num2"];

        if ($num1 < 3 || $num1 > 12 || $num2 < 3 || $num2 > 12) {
            echo "Numbers must be between 3 and 12. <a href='lab08.php'>Go back</a>";
            exit;
        }

        echo "<table border='1'>";
        for ($i = 1; $i <= $num1; $i++) {
            echo "<tr>";
            for ($j = 1; $j <= $num2; $j++) {
                echo "<td>" . ($i * $j) . "</td>";
            }
            echo "</tr>";
        }
        echo "</table>";
    }
    ?>
</body>
</html>
