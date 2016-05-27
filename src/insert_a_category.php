<?php
	$server_name = "localhost";
	$user_name = "root";
	$user_pass = "";
	$database_name ="bagdoomdb";
	$conn = mysqli_connect($server_name, $user_name, $user_pass);
	
	$category_name = urldecode($_POST["category_name"]);
//	echo $category_name;
	
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
	}
	else{
		mysqli_select_db($conn, $database_name);
		$sql_query = "insert into Category ( category_name) values ('".$category_name."')";
//		echo $sql_query;
		$result = mysqli_query($conn, $sql_query);
		if($result == false){
			echo mysqli_error($conn);
		}
		else{
			$last_id = mysqli_insert_id($conn);
			echo "Data Inserted Successfully with id ".$last_id;
		}
	}
?>
