<?php
$servername= "localhost";
$username ="root";
$password ="";
$dbname ="androiddb";

$conn = mysqli_connect($servername,$username,$password,$dbname);
if(mysqli_connect_errno()){
	echo "".mysqli_connect_error();
}
else {
	$email = urldecode ($_POST["email"]);
//	$email ="bsse";
	$sql = "select email, name, age from Student where email = "."'".$email."'" ;
//	$sql = "select email, name, age from Student ";
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
?>