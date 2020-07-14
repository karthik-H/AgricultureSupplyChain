<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OpenAgr | Bayesian Analysis</title>
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" type="text/css" href="css/button.css">
</head>
<body>
<div class="header">
<img src="image/openagr.png" alt="OpenAgr Icon" style="width:128px;height:128px;
left:50%;">
</div>
<ul>
  <li><a href="/Wifi/SensorData">SensorData</a></li>
  <li><a href="/Wifi/CbrAlgorithm">Crop Prediction</a></li>
  <li><a href="updatesensordata.jsp">Update Data</a></li>
  <li><a href="add_notification.jsp">Add Notification</a></li>
  <li><a href="graph.jsp">Analyse Farmer</a></li>
    <li><a class="active" href="bayesian.jsp">Bayesian Analysis</a></li>
    <li><a href="Logout">Logout</a></li>
</ul>
<br>
<br>
 <iframe src="http://127.0.0.1:6533/" height=800px width= 100%></iframe> 
</body>
</html>