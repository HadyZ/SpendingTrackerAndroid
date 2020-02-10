<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once 'connection.php';
$cat_id = $_GET['cat_id'];
$query = "delete from transacions where cat_id='$cat_id'";
if(mysqli_query($con, $query)){echo "success";}
else {echo "fail";}