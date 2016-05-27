<?php
$conn = mysqli_connect("localhost","root","");
if(mysqli_connect_errno()){
//	echo "Failed to connect to MySQL:\n".mysql_connect_error();
	echo "Fail";
}
else{
	mysqli_select_db($conn, "androiddb");
	
	$email = urldecode($_POST["email"]);
	$name  = urldecode($_POST["name"]);
	$age   = urldecode($_POST["age"]);
	$sql= "insert into Student (email,name,age) values ("."'".$email."'".",'".$name."',".$age.")";
	$result=mysqli_query($conn, $sql);
	if($result == false){
//		echo "Error". mysqli_error($conn);
		echo "Fail";
	}
	else{
		$sql = "select email, name, age from Student ";
		$result = mysqli_query($conn, $sql);
		if ($result == false){
			echo mysqli_error($conn);
		}
		else{
			$var ="[";
			if (mysqli_num_rows($result) > 0) {
				while($row = mysqli_fetch_assoc($result)) {
					if($var !="[") $var = $var .",";
					$var= $var."{\"email\":"."\"". $row["email"]."\"". ",\"name\":" ."\"". $row["name"]."\"". ",\"age\":"."\"".$row ["age"]."\""."}";
				}
				$var= $var."]";
				echo $var;	
			}
			else {
				echo '0 results';
			}
		}
	}
	mysqli_close($conn);	
	}	
?>