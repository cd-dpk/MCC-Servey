<?php
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
		$sql_query = "select * from Orders";
		$result = mysqli_query($conn, $sql_query);
		$var ="{";
		if($result == false){
			echo mysqli_error($conn);
		}
		else{
			if (mysqli_num_rows($result)>0){
				$var .= "\"orders\":";
				$temp_var ="[";
				while($row = mysqli_fetch_assoc($result)){
					if($temp_var != "[" ) $temp_var .=",";
					$temp_var .= "{\"invoice_id\":".$row["invoice_id"].",\"order_id\":".$row["order_id"].",\"product_id\":".$row["product_id"].",\"unit_price\":".$row["sub_total_price"].",\"quantity\":".$row["quantity"].",\"order_des\":\"".$row["order_des"]."\",\"order_status\":\"".$row["order_status"]."\"}";
				}
				$temp_var .="]";
				$var .= $temp_var;
			}
			else {
			}
		}
		$var .= "}";
		echo $var;
	}
?>