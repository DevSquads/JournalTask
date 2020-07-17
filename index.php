
<?php 
  include_once 'database.php';
  session_start();
  $username="";
  $pass="";
  $email="";
  $passerror="";

  if (isset($_POST['user'])) {
    $username = $_POST['user'];
  


if (isset($_POST['password'])) {
    $pass = $_POST['password'];
}


if (isset($_POST['email'])) {
    $email = $_POST['email'];
}

 
  $s = "select * from users where username = '$username'";
  $result = mysqli_query($conn , $s);
  $num = mysqli_num_rows($result);

  $s1 = "select * from users where email = '$email'";
  $result1 = mysqli_query($conn , $s1);
  $num1 = mysqli_num_rows($result1);
  if ($num > 0)
  {
        $passerror = "USERNAME ALREADY TAKEN :(";
      
  }
 else if ($num1 > 0){
    $passerror = "YOU REGISTERED WITH THAT EMAIL BEFORE !";
  }
  
  elseif (!filter_var($email, FILTER_VALIDATE_EMAIL))
  {
    $passerror = "$email is not a valid email address";
 }
  else
  {
      $register = "INSERT INTO users (username ,email , password , admin) VALUES ('$username' , '$email','$pass' , '0')";
      mysqli_query($conn , $register);
       $_SESSION['user'] = $username;
       header('location:home.php');
        exit();
  }

  }
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <title> log in form</title>
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
        <h2>Sign Up Here</h2>
        <form action="#" method="POST">

            <div class=" w3l-form-group">
                <h3 style="color:red; text-transform: uppercase;"> <?php  echo $passerror;?> </h3>
                <label>Username:</label>
                <div class="group">
                    <i class="fas fa-user"></i>
                    <input type="text" class="form-control" placeholder="Username" required="required" name="user" />
                </div>
            </div>
            <div class=" w3l-form-group">
                    <label>Email:</label>
                    <div class="group">
                        <i class="fas fa-user"></i>
                        <input type="text" class="form-control" placeholder="Email" required="required" name="email" />
                    </div>
                </div>
            <div class=" w3l-form-group">
                <label>Password:</label>
                <div class="group">
                    <i class="fas fa-unlock"></i>
                    <input type="password" class="form-control" placeholder="Password" required="required" name="password"/>
                </div>
            </div>
            <button type="submit"> Sign Up</button>
            
        </form>
        <p class=" w3l-register-p">YOU DO have an account?<a href="login.php" class="register"> LOG IN </a></p>
    </div>

</body>

</html>