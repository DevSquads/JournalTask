
<?php 

include_once 'database.php';
session_start();
$msg = "";
$author = $_SESSION['user'];

if (isset($_POST['submit']))
{
    $description = $_POST['description'];
    if($description != "" && isset($_POST['article_name']))
        {
           
            $name = $_POST['article_name'];
           // echo $name;
           // echo $description;
            $query = "INSERT INTO articles (name ,description , author , approved) VALUES ('$name' , '$description','$author' , 0)";
            mysqli_query($conn , $query);

            $msg = " The article has been submitted for approval";
        
        }
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <title> add article </title>
    <!-- meta tags -->
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- /meta tags -->
    <!-- custom style sheet -->
    
    <link href="css/style_add_article.css" rel="stylesheet" type="text/css" />
    
		<!-- Custom stlylesheet -->
      
    <!-- /custom style sheet -->
    <!-- fontawesome css -->
    <link href="css/fontawesome-all.css" rel="stylesheet" />
    <!-- /fontawesome css -->
    <!-- google fonts-->
    <link href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <!-- /google fonts-->

</head>


<body>

    <h1>ADD Article </h1>
    <h2 style="color:red;text-align:center;"> <?php echo $msg; ?> </h2>
    <div class=" w3l-login-form">
        <h2>Enter the information of the article here </h2>

        <form  method="POST">

            <div class=" w3l-form-group">
                <label>Title : <span style="color:yellow;">* Maximum 100 characters * </span> </label>
                
                 <div class="group">
                    <i class="fas fa-user"></i>
                    <input type="text" class="form-control" placeholder="article name" required="required" name="article_name"/>
                </div>
            </div>
            <br>
            <div class=" w3l-form-group">
                <label> Description: </label>
                 
            <textarea id="description" name="description"></textarea>
                
            </div>
            <br>
            <input type="submit" name="submit" value="Submit Article">
        </form>

        <p class=" w3l-register-p">Back to articles <a href="articles.php" class="register"> Go to articles </a></p>
    </div>
    <br> <br>

</body>

</html>