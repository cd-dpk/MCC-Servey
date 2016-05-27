<?php
	/*
	$json_string = "{
				\"invoice\":{
					\"name\":\"Dipok\",
					\"email\":\"a@gmail.com\",
					\"phone\":\"01743972128\",
					\"address\":\"Dhaka\",
					\"number\":1
					},
				\"orders\":
				[
					{
						\"product_id\":\"101\",
						\"order_des\":\"4\",
						\"quantity\":\"4\"
					}
				]
	}";
	*/
	$json_string = urldecode($_POST["invoice_info"]);
	//echo $json_string;
	$json_array = json_decode($json_string,true);
	$conn = mysqli_connect("localhost","root","");
	if(mysqli_connect_errno()){
		echo mysqli_connect_error();
	}
	else{
		mysqli_select_db($conn, "bagdoomdb");
		$invoice_query = "insert into  Invoice (name, email, phone, address, invoice_time) values ( '".$json_array['invoice']['name']."','".$json_array['invoice']['email']."','".$json_array['invoice']['phone']."','".$json_array['invoice']['address']."', NOW())";
		$result = mysqli_query($conn, $invoice_query);
		if($result == false){
			echo $mysqli_error($conn);
		}
		else{
			$last_invoice_id = mysqli_insert_id ($conn);
			$order_query ="";			
			for($x = 0; $x < $json_array ['invoice']['number']; $x++ ){
				$order_query ="";
				$order_query = "insert into Orders (invoice_id , product_id , order_des , quantity )  values (". $last_invoice_id.",". $json_array['orders'][$x]['product_id'].",'".$json_array['orders'][$x]['order_des']."',". $json_array['orders'][$x]['quantity'] .")";
				$result = true;
				$result = mysqli_query($conn, $order_query);
				if($result == false){
					echo mysqli_error($conn);
				}
				else{
					echo "Invoice-ID :".$last_invoice_id." Successful";
				}
			}
		}
	}
?>