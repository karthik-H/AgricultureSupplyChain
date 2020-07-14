<%@page import="Esp8266.Wifi.ManageIteration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Data</title>
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
  <li><a class="active" href="updatesensordata.jsp">Update Data</a></li>
  <li><a href="add_notification.jsp">Add Notification</a></li>
  <li><a href="graph.jsp">Analyse Farmer</a></li>
  <li><a href="bayesian.jsp">Bayesian Analysis</a></li>
  <li><a href="Logout">Logout</a></li>
</ul>
<br>
<br>
<form action="/Wifi/UpdateData">
    <button class="button" name = "btn" value="all"><span>All </span>
</button>


<button class="button" name = "btn" value="moisture"><span>Moisture </span>
</button>
<button class="button" name = "btn" value = "ph"><span>PH</span>
</button>
<% ManageIteration manageIteration = new ManageIteration();
	manageIteration.sessionFactoryConfig();
	int iteration = manageIteration.getIterationValue();
	String btnitr = "Increment Iteration to "+(iteration + 1);
	String btnato = "Automate Crop Acceptance for "+iteration;
	manageIteration.closeSessionFactoryConfig();
%>
<button class="button" name = "btn" value = "itr"><span><%= btnitr %></span>
<button class="button" name = "btn" value = "auto"><span><%= btnato %></span>
<button class="button" name = "btn" value = "reset"><span>Reset Iteration</span>
</button>    
</form>
</body>
</html>