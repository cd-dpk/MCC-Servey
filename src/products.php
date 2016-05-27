<html>
	<head>
		<title>Product List</title>
	<link rel="stylesheet" type="text/css" href="layout.css"/>
		<script src="commonjs.js"></script>	</head>
	<body onload="loadMenu()">
		<div id="div_header"></div>
	<div id="div_menu" ></div>
	<div id="div_content">
<?php
	$server_name = "localhost";
	$user_name = "root";
	$user_pass = "";
	$product_id = urldecode($_GET["id"]);
	$database_name ="bagdoomdb";
	$conn = mysqli_connect($server_name, $user_name, $user_pass);
	
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
	}
	else{
		mysqli_select_db($conn, $database_name);
		if($product_id != ""){
			$sql_query = "select * from Product where product_id =".$product_id." order by product_id desc ";			
		}
		else{
			$sql_query = "select * from Product order by product_id desc ";				
		}
		$result = mysqli_query($conn, $sql_query);
		if($result == false){
			echo mysqli_error($conn);
		}
		else{
			echo "<table border ='1'>";
			echo "<caption> Products Table </caption>";
			echo "<tr>";
				echo "<th>Product ID</th><th>PRODUCT PHOTO URL</th><th>CATEGORY ID</th><th>PRODUCT NAME</th><th>PRODUCT DESCRIPTION</th><th>PRICE</th><th>SPECIAL PRICE</th><th>QUANTITY</th><th>TIMESTAMP</th>";
			echo "</tr>";
			if (mysqli_num_rows($result)>0){
				while($row = mysqli_fetch_assoc($result)){
					echo "<tr><td><a href=\"products.php?id=".$row["product_id"]."\" style=\"color:black;\">".$row["product_id"]."</a></td>"."<td>".$row["product_photo_url"]."</td>"."<td>"."<a href =\"categories.php?id=".$row["category_id"]."\" style=\"color:black;\">".$row["category_id"]."</td>"."<td>".$row["product_name"]."</td>"."<td>".$row["product_description"]."</td>"."<td>".$row["price"]."</td>"."<td>".$row["special_price"]."</td>"."<td>".$row["quantity"]."</td>"."<td>".$row["entry_time"]."</td></tr>";
				}
			}
			else {
				echo "<tr><td colspan='9'>0 results</td></tr>";
			}
			echo "</table>";
			echo "<div align=\"center\">";
			echo "<a align =\"center\" href=\"addProduct.php\" style=\"color:black\">ADD PRODUCT</a>";
			echo "</div>";
			echo "</div>";
		}
	}
?>
	</body>
</html>