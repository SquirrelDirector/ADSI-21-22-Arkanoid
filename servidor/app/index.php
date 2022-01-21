<?php 

$sqlStmt = $_POST['query'];
//$tipoQuery = $_GET['tipo'];
$token=$_POST['token'];

if($token==""){
	$servername = "db";
	$username = "test";
	$password = "test";
	$dbname = "test";
	
	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	
	// Check connection
	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}
	
	$result = $conn->query($sqlStmt);
	$filas = array();
	if ($result->num_rows > 0) {
	  
	  while($row = $result->fetch_assoc()) {
	    $filas[] = $row;
	  }
	}
	print json_encode($filas);
}else{
	header('HTTP/1.0 401 Unauthorized');
}



?>