<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8" />

<meta name="viewport" content="width=device-width, initial-scale=1" />

<meta name="author" content="Dixit Goyal">


<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link href="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

</head>
<body>

<?php 
  
  session_start();

  if(isset($_SESSION['id']))
    echo '<script>window.open("../index.php","_self");</script>';

 ?>


<form autocomplete="off" id="loginform" >
  
  <input type="hidden" name="action" value="login" required />

  <div class="form-group">
    <label for="email">Username:</label>
    <input type="text" class="form-control" name="username" id="email">
  </div>
  
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" name="password" class="form-control" id="pwd">
  </div>

  <button type="submit" id="loginButton" class="btn btn-default">Submit</button>

</form>


<a href="../Register/">Register now</a>

<!-- Main body -->

</body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/script.js" ></script>
</html>