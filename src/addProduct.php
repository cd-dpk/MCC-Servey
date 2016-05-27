<html>
	<head>
		<title>Add a product</title>
		<link rel="stylesheet" type="text/css" href="layout.css"/>
		<script src="commonjs.js"></script>
	</head>
	<body onload="loadMenu()">
		<div id="div_header"></div>
	<div id="div_menu" ></div>
	<div id="div_content">
	<?php	
	$server_name = "localhost";
	$user_name = "root";
	$user_pass = "";
	
	$database_name ="bagdoomdb";
	$conn = mysqli_connect($server_name, $user_name, $user_pass);

	$var_category="";
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
	}
	else{
		mysqli_select_db($conn, $database_name);
		$sql_query = "select * from Category";
		$result = mysqli_query($conn, $sql_query);
		
		$var_length =0;
		if($result == false){
			echo mysqli_error($conn);
		}
		else{
			if (mysqli_num_rows($result)>0){
				$var_length = mysqli_num_rows($result);
				$var_category .="[";
				while($row = mysqli_fetch_assoc($result)){
					if($var_category != "[") $var_category .= ",";
					$var_category = $var_category. "{"."\"category_id\":". $row["category_id"].","."\"category_name\":\"".$row["category_name"]."\"}";
				}
				$var_category .= "]";
			}
			else {
				$var_category ="{[]}";
			}
		}
	}
	echo '<div align ="center">';
	echo '<p>Add A New Product</p>';
	echo '<form action="insert_a_product.php" method="POST">';
		echo '<table>';
		echo '<tr><td>Category ID</td>';
		echo '<td><select name="category">';
		$json_arr= json_decode($var_category,true);
		for($x =0; $x < $var_length; $x++){
			echo '<option value ="'.$json_arr[$x]["category_id"].'">'.$json_arr[$x]["category_name"].'</option>';
		}
		echo '</select></td></tr>';
		echo '<tr><td>Product Name</td><td><input type="text" name="product_name"></input></td></tr>';
		echo '<tr><td>Product Photo URL</td><td><input type="text" name="product_photo_url" value="pic.PNG" readonly></input></td></tr>';
		echo '<tr><td>Product Des</td><td><input type="text" name="product_description"></input></td></tr>';
		echo '<tr><td>Price</td><td><input type="text" name="price"></input></td></tr>';
		echo '<tr><td>Special Price</td><td><input type="text" name="special_price"></input></td></tr>';
		echo '<tr><td>Quantity</td><td><input type="text" name="quantity"></input></td></tr>';
		echo '<tr><td colspan="2" align="center"><input type ="submit" value="ADD"></input></td></tr>';
	echo '</table></form>';	
	echo '</div>';
	?>	
	</body>
</html>