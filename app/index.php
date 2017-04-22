
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
?>

<h1>Home Page</h1>

<?php 

if(isset($_SESSION['id']))
{

?>

<a onclick = "logout();" href="" >Logout</a>

<?php
}
else
{
?>
<a href="./login/"> Login </a>

<a href="./Register/"> Register </a>

<?php
}
?>



</body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/script.js" ></script>
</html>

