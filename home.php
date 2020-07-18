<?php 
  include_once 'database.php';
   session_start();
   $admin = $_SESSION['admin'];
?>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title> Home </title>

	
		<link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link type="text/css" rel="stylesheet" href="css/style.css"/>

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
						<li><a href="articles.php">articles</a></li>
						<?php if($admin == 1) { ?>
							<li><a href="approveArticles.php">Pending Requests</a></li>
						<?php }else { ?>
						<li><a href="addArticle.php">Add Articles</a></li>
						<?php } ?>

						<li><a href="login.php">Log Out</a></li>
					</ul>
				</nav>
				<!-- /Navigation -->

			</div>
		</header>
		<!-- /Header -->

		<!-- Home -->
		<div id="home" class="hero-area">

			<!-- Backgound Image -->
			<div class="bg-image bg-parallax overlay" style="background-image:url(img/robert-haverly-125127.jpg)"></div>
			<!-- /Backgound Image -->

			<div class="home-wrapper">
				<div class="container">
					<div class="row">
						<div class="col-md-8">
							<h1 class="white-text" style="color:yellow;text-transform:uppercase;"> WELCOME <?php
							if (isset($_SESSION['user'])) { echo $_SESSION['user'];} ?> </h1>
							<h1 class="white-text">Go to articles if you want to see our latest articles</h1>
							<p class="lead white-text"> or if you want to create an article, navigate to Add Articles!</p>
<!--							<a class="main-button icon-button" href="#">Get Started!</a>-->
						</div>
					</div>
				</div>
			</div>

		</div>
	

	
		<div id='preloader'><div class='preloader'></div></div>
		<!-- /preloader -->


		<!-- jQuery Plugins -->
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>

	</body>
</html>
