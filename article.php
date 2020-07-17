<?php

include_once 'database.php';

session_start();
$id = $_SESSION['clicked_article'];

$s = "select * from articles where id = '$id'";
$result = mysqli_query($conn , $s);
$row = mysqli_fetch_assoc($result);

$name = $row["name"];
$desc = $row["description"];
$author = $row['author'];

$names = explode(".", $desc);
//print_r($names);

?>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title> article </title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/article.css"/>

		</head>
	<body>

		<!-- Header -->
		<header id="header" class="transparent-nav">
			<div class="container">

				<div class="navbar-header">
					<!-- Mobile toggle -->
					<button class="navbar-toggle">
						<span></span>
					</button>
					<!-- /Mobile toggle -->
				</div>

				<!-- Navigation -->
				<nav id="nav">
					<ul class="main-menu nav navbar-nav navbar-right">
					 <li><a href="articles.php"> Back to articles</a></li>
						<li><a href="addArticle.php">Add Article</a></li>
						<li><a href="login.php">Log Out</a></li>
					</ul>
				</nav>
				<!-- /Navigation -->

			</div>
		</header>
		<!-- /Header -->

		<!-- Home -->
		

			<!-- Backgound Image -->
			<div class="bg-image bg-parallax overlay" style="background-image:url(img/robert-haverly-125127.jpg)"></div>
			<!-- /Backgound Image -->

			<div class="article">
				<p> <?php echo $name; ?> </p>
				<h4> written by :  <?php echo $author ?> </h4>

				<br> <br>
			   <?php  
			   for ($index = 0; $index < count($names); $index++){ ?>

				<h4>
					<?php echo $names[$index]; ?>
				</h4>

			   <?php } ?>
			</div>

		
 

		<!-- Footer -->
	
		<div id='preloader'><div class='preloader'></div></div>
		<!-- /preloader -->


		<!-- jQuery Plugins -->
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>

	</body>
</html>
