<?php include ('dbconnect.php'); ?>

<?php
$sql = "CREATE TABLE Photographs (
    picture_number INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    date_taken DATE NOT NULL,
    picture_url VARCHAR(255) NOT NULL
);";

if (mysqli_query($connect, $sql)) {
    echo "Photographs table created successfully.<br>";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($connect);
}
?>
