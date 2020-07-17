<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title> Articles </title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/articless.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		</head>
	<body>

		<!-- Header -->
		<header id="header" class="transparent-nav">
			<div class="container">

				<div class="navbar-header">
					<button class="navbar-toggle">
						<span></span>
					</button>
			
				</div>

				<!-- Navigation -->
				<nav id="nav">
					<ul class="main-menu nav navbar-nav navbar-right">
						<li><a href="articles.php">Articles</a></li>
						<li><a href="addArticle.php">Add article</a></li>
						<li><a href="login.php">Log Out</a></li>
					</ul>
				</nav>
				<!-- /Navigation -->

			</div>
		</header>
		<!-- /Header -->

		<!-- Home -->
		<div id="home" class="hero-area">
			<div class="bg-image bg-parallax overlay" style="background-image:url(img/robert-haverly-125127.jpg); height: 120%;"> </div>
			 
               <div class="articless">
			   <h3 style="color:yellow;margin-left:20px;margin-top:90px;"> Your Published Articles : </h3>
			   <?php 

					include_once 'database.php';
					session_start();
					$username = $_SESSION['user'];

					$sql=" SELECT * FROM articles WHERE author = '$username' AND approved = 1 ";
					$result = mysqli_query($conn, $sql);

					while($row = mysqli_fetch_assoc($result)) {
						$id = $row["id"];
						$name = $row["name"];
						$description = $row["description"];

					?>

                    <div class="article">
                        <form  method = "POST">
						<p> <?php echo $name; ?> </p>
                        <input type="submit" value="Go to Article" name="<?php echo $id;?>">
                       </form>
					</div>
					
				<?php } 

				?>
	

			</div>        
			
         

			<div class="articless">
			   <h3 style="color:yellow;margin-left:20px;margin-top:90px;"> Other Published Articles : </h3>
			   <?php 
					$sql_a = "SELECT * FROM users Where username != '$username' ORDER BY num_of_articles DESC";
					$result_a = mysqli_query($conn, $sql_a);

					while($row_a = mysqli_fetch_assoc($result_a))
					{
						$author_name = $row_a['username'];
						$sql=" SELECT * FROM articles WHERE author = '$author_name' AND approved = 1 ";
						$result = mysqli_query($conn, $sql);

						while($row = mysqli_fetch_assoc($result)) {
							$id = $row["id"];
							$name = $row["name"];
						?>

						<div class="article">
							<form  method = "POST">
							<p> <?php echo $name; ?> </p>
							<p> Author : <?php echo $author_name; ?> </p>
							<br>
							<input type="submit" value="Go to Article" name="<?php echo $id;?>">
						</form>
						</div>
					
				<?php 
				 } 
					}
				?>
	
	<?php
		//$admin = $_SESSION['admin'];
		
		$sql_b = "SELECT * FROM articles";
		$result_b = mysqli_query($conn, $sql_b);

		while($row_b = mysqli_fetch_assoc($result_b))
		{
			$id = $row_b['id'];
			 if(isset($_POST[$id]))
			 {
				  $_SESSION['clicked_article'] = $id;
				  header('location:article.php');
				//  exit();
			     // break;
			 }
		}
?>

            </div>        
            

		<div id='preloader'><div class='preloader'></div>
		<!-- /preloader -->
		</div>

		<!-- jQuery Plugins -->
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>

	</body>
</html>
