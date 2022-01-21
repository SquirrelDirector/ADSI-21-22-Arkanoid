<?php 

$sqlStmt = $_POST['query'];
//$tipoQuery = $_GET['tipo'];
$token=$_POST['token'];

if($token==""){
    header('Content-Type: text/plain; charset=utf-8');
	$servername = "";
	$username = "";
	$password = "";
	$dbname = "";
	
	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	//$conn->set_charset('utf8mb4_spanish_ci');
	$conn->set_charset("utf8");
	
	// Check connection
	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}else{
	
	$result = $conn->query($sqlStmt);
	$filas = array();
	if ($result->num_rows > 0) {
	  while($row = $result->fetch_assoc()) {
	     array_push($filas, $row);
	  }
	}
	echo json_encode($filas, JSON_UNESCAPED_SLASHES);
	mysqli_close($conn);
	}
	
}else{
	header('HTTP/1.0 401 Unauthorized');
}



?>