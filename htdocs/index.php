<?php 
// Ctestreating a connection
$user = $_GET['user'];
$pass = $_GET['pass'];
$db = $_GET['db'];
$con = mysqli_connect("localhost:3306", $user, $pass, $db);
if (mysqli_connect_errno()) {
	die("Error: Failed to connect to MySQL: " . mysqli_connect_error());
}

// Execute query
$sql = trim($_GET['sql']); // We should really do some security+cleanup work here
$result = mysqli_query($con ,$sql);
if (!$result) {
	die("Error: " . mysqli_error($con) . "; for query: " . $sql);
}
// Display result
if (substr_compare($sql, "SELECT", 0, 6, true) === 0 || substr_compare($sql, "SHOW", 0, 4, true) === 0) {
	$array = []; // Get all results
	while ($row = mysqli_fetch_assoc($result)) {
		$array[] = $row;
	}
	// Display as JSON
	header('Content-Type:Application/json');
	echo json_encode($array);
} else {
	var_dump($result); // just show the result (likely a Boolean)
}
?>
