<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once 'connection.php';

$getalldashboarddata = "SELECT a.*,MAX(a.amt), SUM(a.amt) AS total from (select *,SUM(amount) AS amt FROM `transactions` GROUP BY category) a";
$result = mysqli_query($con, $getalldashboarddata);
$return_array = array();


while ($row = mysqli_fetch_assoc($result)) {
// create an associative array for each row of the result
// returned by the query
// call it row_array, then push it into the main array
// called return_Array
$row_array['cat_id'] = $row['cat_id'];
// left side indexes are own choice,
// right side indexes should be the attributes of the table
$row_array['category'] = $row['category'];
$row_array['summary'] = $row['total'];
$row_array['amount'] = $row['amt'];

array_push($return_array, $row_array);
}
echo json_encode($return_array);

?>
