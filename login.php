<?php 
  include_once 'database.php';
  session_start();
  $username="";
  $pass="";
  $passerror="";

  if (isset($_POST['user'])) {
    $username = $_POST['user'];


if (isset($_POST['password'])) {
    $pass = $_POST['password'];
}

 
  $s = "select * from users where username = '$username' && password = '$pass'";
  $result = mysqli_query($conn , $s);
  $num = mysqli_num_rows($result);
  
  
  if ($num == 1)
  {
        $row = mysqli_fetch_assoc($result);
        $admin = $row["admin"];
        $_SESSION['admin'] = $admin;
        $_SESSION['user'] = $username;
        header('location:home.php');
        exit();
      
  }
  else{
    $passerror = "INVALID USERNAME OR PASSWORD , PLEASE TRY AGAIN";
   
  }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <title> log in </title>
    <!-- meta tags -->
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- /meta tags -->
    <!-- custom style sheet -->
    <link href="css/style_log.css" rel="stylesheet" type="text/css" />
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
    <h1>Journal Articles</h1>
    <div class=" w3l-login-form">
        <h2>Login Here</h2>
        <form  method="POST">

            <div class=" w3l-form-group">
                <h2 style="color:red;"> <?php echo $passerror; ?> </h2>
                <label>Username:</label>
                <div class="group">
                    <i class="fas fa-user"></i>
                    <input type="text" class="form-control" placeholder="Username" required="required" name="user"/>
                </div>
            </div>

            <div class=" w3l-form-group">
                <label>Password:</label>
                <div class="group">
                    <i class="fas fa-unlock"></i>
                    <input type="password" class="form-control" placeholder="Password" required="required" name="password" />
                </div>
            </div>

            <button type="submit">Login</button>
        </form>
        <p class=" w3l-register-p">Don't have an account?<a href="index.php" class="register"> Sign up </a></p>
    </div>

</body>

</html>