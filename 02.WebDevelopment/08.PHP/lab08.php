<?php
function getTimeBasedGreeting() {
    $hour = date("G");

    if ($hour >= 5 && $hour < 12) {
        return ["Good morning", "morningBackground.jpg"];
    } elseif ($hour >= 12 && $hour < 18) {
        return ["Good afternoon", "afternoonBackground.jpg"];
    } elseif ($hour >= 18 && $hour < 22) {
        return ["Good evening", "eveningBackground.jpg"];
    } else {
        return ["Good night", "nightBackground.jpg"];
    }
}

list($greeting, $bgImage) = getTimeBasedGreeting();
?>
<!DOCTYPE html>
<html>
<head>
    <title>My CPS530 Lab 8 using PHP</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            color: white;
            background: url('/~ashincul/<?php echo $bgImage; ?>') no-repeat center center fixed;
            background-size: cover;
        }
        body::before {
            content: '';
            position: absolute;
            background: rgba(0, 0, 0, 0.2);
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: -1;
        }
        .greeting {
            z-index: 1;
            position: relative;
            font-size: 2em;
            height: 200px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        form {
            z-index: 1;
            text-align: center;
            margin-top: 50px;
        }
        .info-box {
            background-color: rgba(0, 0, 0, 0.5);
            padding: 10px;
            border-radius: 5px;
        }
        .visit-count {
            position: fixed;
            bottom: 10px;
            right: 10px;
            z-index: 1;
        }
        .image-info {
            position: absolute;
            top: 180px;
            right: 10px;
            z-index: 1;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 5px;
            border-radius: 5px;
            color: white;
            opacity: 1;
        }
        .outlined-text {
            text-shadow:
                -1px -1px 0 #000,
                1px -1px 0 #000,
                -1px 1px 0 #000,
                1px 1px 0 #000;
        }
    </style>
</head>
<body>
    <div class="greeting">
        <?php echo $greeting; ?>
    </div>

    <form action="lab08b.php" method="post" target="_blank" class="outlined-text">
        <div>
            <label for="num1">Number 1 (3-12):</label>
            <input type="number" id="num1" name="num1" min="3" max="12" required>
            <label for="num2">Number 2 (3-12):</label>
            <input type="number" id="num2" name="num2" min="3" max="12" required>
            <input type="submit" value="Generate Table">
        </div>
    </form>

    <?php
    if(isset($_COOKIE['visitCount'])) {
        $visitCount = $_COOKIE['visitCount'] + 1;
    } else {
        $visitCount = 1;
    }
    setcookie('visitCount', $visitCount, time() + 86400 * 30);
    ?>

    <div class="visit-count info-box">
        Visit count: <?php echo $visitCount; ?>
    </div>

    <?php
    if (isset($_GET['image'])) {
        $imageName = $_GET['image'];
        $allowedImages = ['ghost.gif', 'jackfire.gif', 'mummy.gif'];
        if (in_array($imageName, $allowedImages)) {
            echo "<div style='position: absolute; top: 0; right: 0; opacity: 0.5;'>
                    <img src='/~ashincul/$imageName' alt='Image' style='max-width: 200px; max-height: 200px;'>
                    <p class='image-info'>Halloween image is $imageName</p>
                  </div>";
        }
    }
    ?>
</body>
</html>
