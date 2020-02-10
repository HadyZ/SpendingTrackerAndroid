<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$server="localhost";
$user="root";
$password=""; // this field is equal to root for MAC users
$db = "spendingmoney";
$con = mysqli_connect($server,$user,$password,$db);
if (mysqli_connect_errno())
{echo mysqli_connect_error();}