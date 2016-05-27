<html>
	<head>
		<title>Add a Category</title>
		<link rel="stylesheet" type="text/css" href="layout.css"/>
		<script src="commonjs.js"></script>
	</head>
	<body onload="loadMenu()">
		<div id="div_header"></div>
	<div id="div_menu" ></div>
	<div id="div_content">
	<?php
	echo '<div align ="center">';
	echo '<p>Add A New Category</p>';
	echo '<form action="insert_a_category.php" method="POST">';
		echo '<table>';
		echo '<tr><td>Category Name</td><td><input type="text" name="category_name"></input></td></tr>';
		echo '<tr><td colspan="2" align="center"><input type ="submit" value="ADD"></input></td></tr>';
	echo '</table></form>';	
	echo '</div>';
	?>	
	</body>
</html>