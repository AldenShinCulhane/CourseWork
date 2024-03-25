<?php include ('dbconnect.php'); ?>

<?php
$photos = [
    ['1', 'Art Gallery of Ontario', 'Ontario', '2023-04-06', '/~ashincul/pictures/AGO.jpg'],
    ['2', 'Niagara Falls', 'Ontario', '2018-09-20', '/~ashincul/pictures/NiagaraFalls.jpg'],
    ['3', 'CN Tower', 'Ontario', '2019-12-11', '/~ashincul/pictures/CNTower.jpg'],
    ['4', 'Hopewell Rocks', 'New Brunswick', '2017-01-30', '/~ashincul/pictures/HopewellRocks.jpg'],
    ['5', 'Parliament Hill', 'Ontario', '2016-06-04', '/~ashincul/pictures/ParliamentHill.jpg'],
    ['6', 'Canadian National Exhibition', 'Ontario', '2023-08-30', '/~ashincul/pictures/CNE.jpg'],
    ['7', 'Centre Island', 'Ontario', '2022-04-07', '/~ashincul/pictures/CentreIsland.jpg'],
    ['8', 'Grouse Mountain', 'British Columbia', '2017-05-17', '/~ashincul/pictures/GrouseMountain.jpg'],
    ['9', 'Peggys Cove Lighthouse', 'Nova Scotia', '2021-03-22', '/~ashincul/pictures/PeggysCoveLighthouse.jpg'],
    ['10', 'Banff National Park', 'Alberta', '2023-02-19', '/~ashincul/pictures/Banff.jpg']
];

foreach ($photos as $photo) {
    $sql = "INSERT INTO Photographs (picture_number, subject, location, date_taken, picture_url) VALUES ('$photo[0]', '$photo[1]', '$photo[2]', '$photo[3]', '$photo[4]')";
    mysqli_query($connect, $sql) or die(mysqli_error($connect));
}

echo "Table populated successfully.";
?>
