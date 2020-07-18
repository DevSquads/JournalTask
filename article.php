<?php

include_once 'database.php';

session_start();
$id = $_SESSION['clicked_article'];
$admin = $_SESSION['admin'];

$s = "select * from articles where id = '$id'";
$result = mysqli_query($conn , $s);
$row = mysqli_fetch_assoc($result);

$name = $row["name"];
$desc = $row["description"];
$author = $row['author'];
$approved = $row['approved'];

$names = explode(".", $desc);


if($admin == 1 && isset($_POST['Deleted_article']) && $_POST['Deleted_article'] != "")
{
	//1. Reduce the number of published articles for that author by one.
	if ($approved == 1)
	{
		$s2 = "select * from users where username = '$author'";
		$result2 = mysqli_query($conn , $s2);
		$row2 = mysqli_fetch_assoc($result2);

		$num = $row2["num_of_articles"];
		$num -= 1;

		$s3="UPDATE users SET num_of_articles = '$num' WHERE username = '$author'";
		$result3 = mysqli_query($conn , $s3);
	}

	//2. Delete the articles data from the articles table.
	$s4 = "DELETE FROM articles WHERE id = '$id' ";	
	$result4 = mysqli_query($conn , $s4);

	header('location:articles.php');
}

if($admin == 1 && isset($_POST['Approved_article']) && $_POST['Approved_article'] != "")
{
	//1. increase the number of published articles by 1.
	$s2 = "select * from users where username = '$author'";
	$result2 = mysqli_query($conn , $s2);
	$row2 = mysqli_fetch_assoc($result2);

	$num = $row2["num_of_articles"];
	$num += 1;

	$s3="UPDATE users SET num_of_articles = '$num' WHERE username = '$author'";
	$result3 = mysqli_query($conn , $s3);



	//2. Set the approved value to 1 in the articles table. 
	$s4="UPDATE articles SET approved = 1 WHERE id = '$id'";
	$result4 = mysqli_query($conn , $s4);
	
	header('location:articles.php');
}

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
			   <?php if($admin == 1) { ?>
				 <br>
				 <form method="POST"> 
				 <input type="submit" value="Delete that article" name="Deleted_article">
				</form> <br>
				
				<?php } ?>
				
				<?php if($admin == 1 && $approved == 0) { ?>
				 <br> <br> <br>
				 <form method="POST"> 
				 <input type="submit" value="Approve that article" name="Approved_article">
				</form> <br>
				
				<?php } ?>

			</div>
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
