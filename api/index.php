<?php 

		session_start();

		function encryptPassword($password)
		{
		$salt = 'b63cc0d7241341f3419a00b289e37da7';
		$password1 = hash('sha256', $password);
		$ePassword = hash('sha256', $salt ^ $password1);
		return $ePassword;
		}



	/*******************************************************
		********************************************************
		*****Fn to execute an sql query and return the result***
		********************************************************
		********************************************************/

		function sql_query( $query )
		{

			//variables for sql operations
			$host = "localhost";
			$user = "phpmyadmin";
			$pass = "Harsh1859";
			$dbname = "LocMon";
			$con = mysqli_connect($host, $user, $pass, $dbname);
			$result = mysqli_query($con,$query);
			mysqli_close($con);
			return $result;

		}

		function sql_queryID( $query )
		{
	
			//variables for sql operations
			$host = "localhost";
			$user = "phpmyadmin";
			$pass = "Harsh1859";
			$dbname = "LocMon";
			$con = mysqli_connect($host, $user, $pass, $dbname);
			$result = mysqli_query($con,$query);
			return mysqli_insert_id($con);
			mysqli_close($con);
		
		}




	/*******************************************************
		********************************************************
		***************Sanitize a string************************
		********************************************************
		********************************************************/
		function sanitize($str) 
		{
			$str = @trim($str);
			if(get_magic_quotes_gpc()) 
			{
				$str = stripslashes($str);
			}
			$str = preg_replace('/[^a-z0-9@.]/i', '', $str);
			return $str;
		}


	/*******************************************************
		********************************************************
		**********Insert  user in database***************
		********************************************************
		********************************************************/
		function insert_user($contact,$fname,$lname,$email,$password,$username)
		{ 
			
				if(!empty($fname) && !empty($lname) && !empty($password) && !empty($email) && !empty($username))
				{
								
					$epassword = encryptPassword($password);
								
					$insert = sql_query("insert into user (contact,fname,lname,email,password, username) values ('$contact','$fname','$lname','$email','$epassword','$username')");
									
						if($insert)
						{
							return 'true';
						}
						else
						{
							return 'Username Already exists';
						}
				}
				else
				{
					return 'Welcome Hacker';
				}
		}


		/********************Assign Location of guard**********************/

		function assignLocation($uid,$lat,$lng,$startTime,$endTime)
		{
				
				if(!empty($uid) && !empty($lat) && !empty($lng) && !empty($startTime) && !empty($endTime))
				{
								
								
					$insert = sql_query( "insert into asssignLocation ( uid, lat, lng, startTime, endTime ) values ( '$uid','$lat','$lng','$startTime','$endTime' )" );
									
						if( $insert )
						{
							return 'true';
						}
						else
						{
							return 'Error in sql query';
						}
				
				}
				else
				{
					return 'Welcome Hacker';
				}


		}


	/*******************************************************
		***********Function for checking if user exist in db****
		********************************************************
		********************************************************
		********************************************************/
		function check_login($username,$password)
		{
				if(empty($username) || empty($password))
				{
					return 'Parameters missing';
				}

				else
				{ 

					$log=sql_query(" select * from adminLogin where username = '$username' ");
					
					if(mysqli_num_rows($log)>0)
					{
						if($row = mysqli_fetch_array($log))
						{
							if($row['password'] == $password)
							{	
								return 'true';
							}
							else
								return 'Please enter correct username or password';
						}
						else
							return 'Please enter correct username or password';				
					}
					else
						return 'Please enter valid credentials';				
				}	
		}


		function getUsers()
		{

			$users = sql_query(" select * from user");
			
			return $users;	
		
		}


		function trackUser( $uid, $date , $timeStart ,$timeEnd,$locationBit,$activeBit )
		{
								
								
					$insert = sql_query( "insert into locationData ( uid, date, timeStart, timeEnd, locationBit, activeBit ) values ( '$uid','$date','$timeStart','$timeEnd','locationBit', '$activeBit' )" );
									
						if( $insert )
						{
							return 'true';
						}
						else
						{
							return 'Error in sql query';
						}
				
				}
				else
				{
					return 'Welcome Hacker';
				}

		}
		

		function getUser($username, $password)
		{

			$epassword = encryptPassword($password);

			$log=sql_query(" select * from user where username = '$username' and password = '$ePassword' ");
					
					if(mysqli_num_rows($log)>0)
					{
						return $log;
					}
					else
						return 'false';				
		}


		function getUserLocationData($id)
		{
			
	
			$log = sql_query(" select * from assignLocation where uid= '$id'");
					
					if(mysqli_num_rows($log)>0)
					{
						return $log;
					}
					else
						return 'false';				
		}


		if(isset($_POST['action']))
		{
	
			if($_POST['action']=='signup')
			{
				$signup = insert_user($_POST['contact'],$_POST['fname'],$_POST['lname'],$_POST['email'],$_POST['password'],$_POST['username']);
				
					echo json_encode($signup);
			
			}
			
			else if($_POST['action']=='login')
			{
				$login = check_login($_POST['username'],$_POST['password']);
				
				echo json_encode($login);
			}

			else if($_POST['action']=='assignLocation')
			{
				$assign = assignLocation($_POST['uid'],$_POST['lat'],$_POST['lng'],$_POST['startTime'],$_POST['endTime']);
				
				echo json_encode($assign);
			}

			else if($_POST['action']=='trackUser')
			{
				$assign = trackUser($_POST['uid'],$_POST['date'],$_POST['timeStart'],$_POST['timeEnd'],$_POST['localhostBit'],$_POST['activeBit']);
				
				echo json_encode($assign);
			}

			else if($_POST['action']=='userLogin')
			{
				$assign = getUser($_POST['uid']);
				
				echo json_encode($assign);
			}

			else if($_POST['action']=='getUsers')
			{
				$users = getUsers();
				
				echo json_encode($users);
			}

			else if($_POST['action']=='getUserLocationData')
			{
				$result = getUserLocationData($POST['uid']);

				echo json_encode($result);
			}


			else if($_POST['action']=='logout')
			{
				session_destroy();
				echo json_encode('true');
			}	
						
		}

		else
		{
			echo json_encode("No Action defined");	
		}

		?>	
