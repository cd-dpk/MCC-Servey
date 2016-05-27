<?php
	$server_name = "localhost";
	$user_name = "root";
	$user_pass = "";
	$database_name ="bagdoomdb";
	$conn = mysqli_connect($server_name, $user_name, $user_pass);
	
	$category_id = urldecode($_POST["category"]);
	$product_name = urldecode($_POST["product_name"]);
	$product_photo_url = urldecode($_POST["product_photo_url"]);
	$product_description = urldecode($_POST["product_description"]);
	$price = urldecode($_POST["price"]);
	$special_price = urldecode($_POST["special_price"]);
	$quantity = urldecode($_POST["quantity"]);
//	echo $category_id." ".$product_name." ". $product_photo_url." ".$product_description." ".$price." ".$special_price." ".$quantity;
	
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
	}
	else{
		mysqli_select_db($conn, $database_name);
		$sql_query = "insert into Product (category_id, product_name, product_photo_url, product_description, price, special_price, quantity, entry_time) values (". $category_id.",'".$product_name."','".$product_photo_url."','".$product_description."',".$price.",".$special_price.",".$quantity.",NOW())";
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
