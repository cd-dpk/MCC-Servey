<?php
	$server_name = "localhost";
	$user_name = "root";
	$user_pass = "";
	$var_category="";
	$database_name ="bagdoomdb";
	$conn = mysqli_connect($server_name, $user_name, $user_pass);
	
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
	}
	else{
		mysqli_select_db($conn, $database_name);
		
		$sql_query = "select * from Category";
		$result = mysqli_query($conn, $sql_query);
		$var_category = "\"category\":[";
		if($result == false){
			echo mysqli_error($conn);
		}
		else{
			if (mysqli_num_rows($result)>0){
				while($row = mysqli_fetch_assoc($result)){
					 if ($var_category!= "\"category\":["){ 
						$var_category = $var_category.",";	
					 }
					$var_category = $var_category. "{\"category_id\" :".$row["category_id"].", \"category_name\":".$row["category_name"]."}";
				}
			}
			else {
				;
			}
			$var_category .= "]";
		}
		$var_product="\"product\":[";
		$sql_query = "select * from Product";
		$result = mysqli_query($conn, $sql_query);
		if($result == false){
			echo mysqli_error($conn);
		}
		else{
			if (mysqli_num_rows($result)>0){
				while($row = mysqli_fetch_assoc($result)){
					if ($var_product!= "\"product\":["){ 
						$var_product = $var_product. ",";	
					 }
					$var_product.="{\"product_id\":".$row["product_id"].",\"product_photo_url\":\"".$row["product_photo_url"]."\",\"category_id\":".$row["category_id"].",\"product_name\":\"".$row["product_name"]."\",\"product_description\":\"".$row["product_description"]."\",\"price\":".$row["price"].",\"special_price\":".$row["special_price"].",\"quantity\":".$row["quantity"].",\"entry_time\":\"".$row["entry_time"]."\"}";
				}
			}
			else {
				;
			}
			$var_product .= "]";
		}
				
		$var_response ="{".$var_category.",".$var_product."}";
		echo $var_response;
	}
?>