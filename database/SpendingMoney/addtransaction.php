<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once 'connection.php';
$category = $_GET['category'];
$summary = $_GET['summary'];
$amount = $_GET['amount'];
$trans_date = $_GET['trans_date'];
$query = "insert into transactions(cat_id,category,summary,trans_date,amount) values(NULL,'$category','$summary','$trans_date',$amount)";
if(mysqli_query($con, $query)){
echo mysqli_insert_id($con);
// this returns the id that mysql used for the new transaction
}
else {echo "-1  $query";}