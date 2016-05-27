<?php
	$type = urldecode ($_GET["type"]);
	$file_name ="insert.txt";
	if($type==""){
		$file_name ="ERROR";
	}else if($type == "insert"){
		$file_name="insert.txt";	
	}else if($type == "create"){
		$file_name="create.txt";
	}
	$server_name = "localhost";
	$user_name = "root";
	$user_pass = "";
	$database_name ="bagdoomdb";
	$conn = mysqli_connect($server_name, $user_name, $user_pass);
	
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
	}
	else{
		mysqli_select_db($conn, $database_name);
		if($file_name == "ERROR"){
			echo "Database is successfully connected";
		}
		else{
		$myfile = fopen($file_name, "r") or die("Unable to Open File");
		$sql_query = "";
		while(!feof($myfile)) {
			$sql_query = fgets($myfile);
			echo $sql_query;
			$result = mysqli_query($conn,$sql_query);
			if($result == false){
				echo "Why?";
				echo mysqli_error($conn);
			}
			else{
				echo "Okay";
			
			}
		}
		fclose($myfile);
		echo $sql_query;
		}
	}
?>