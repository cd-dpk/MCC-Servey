<?php
	$server_name = "localhost";
	$user_name = "root";
	$user_pass = "";
	$database_name ="bagdoomdb";
	
	$phone = urldecode($_POST["phone"]);
	$name = urldecode($_POST["name"]);
	$email = urldecode($_POST["email"]);
	$address = urldecode($_POST["address"]);
	$photoID = urldecode($_POST["photoID"]);
	/*
	$phone = "1111111";
	$name = "Adil";
	$email = "adil@gmail.com";
	$address = "Dhaka";
	$photoID = "ABC";
	*/
	$conn = mysqli_connect($server_name, $user_name, $user_pass);
	$var ="";
	$var ="{";
	$con_var = "\"communication\" : 
					{
						\"connection\": \"okay\"
					}
				";
	$var = $var . $con_var.",";			
	$database_var = "\"database\":
						{";	
	
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
		$database_var .="\"database_connected\": \"no\",";
	}
	else{
		$database_var .="\"database_connected\": \"yes\",";
		mysqli_select_db($conn, $database_name);
		$sql_query = "insert into User (phone,name,email,address,photoID) values ('".$phone."','".$name."','".$email."','".$address."','".$photoID."'".")";
		$result =mysqli_query($conn, $sql_query);
		if($result == false){
			$database_var .= "\"insert\": \"no\",";
			echo mysqli_error($conn);
		}
		else{
			$database_var .= "\"insert\": \"yes\",";
			$database_var .= "\"last_id\":".mysqli_insert_id($conn).",";
			$sql_query = "select * from User order by user_id desc";
			$result = mysqli_query($conn, $sql_query);
			if($result == false){
				echo mysqli_error($conn);
				$database_var .="\"select\": \"no\",";
			}
			else{
				$database_var .="\"select\": \"yes\",";
				$database_var .="\"count\": 5}";	
				$var = $var . $database_var. ",";
				$var = $var . "\"table\":";
				$select ="";
				if (mysqli_num_rows($result)>0){
					$select =  "[";
					while($row = mysqli_fetch_assoc($result)){
						if($select!="[") $select .=","; 
						$select = $select. "{\"user_id\":".$row["user_id"]."".",\"phone\":\"".$row["phone"]."\"".",\"name\":\"".$row["name"]."\"".",\"email\":\"".$row["email"]."\"".",\"address\":\"".$row["address"]."\"".",\"photoID\":\"".$row["photoID"]."\""."}";
					}
					$select = $select. "]}";		
				}
				else {
				}
				$var .= $select;
			}
		}
	}	
	echo $var;
?>