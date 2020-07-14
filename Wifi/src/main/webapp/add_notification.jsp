<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mybean1" class="Esp8266.Wifi.AddNotification" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Openagr | addNotification</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	  <link rel="stylesheet" href="css/styles.css">
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
  <li><a class="active" href="add_notification.jsp">Add Notification</a></li>
  <li><a href="graph.jsp">Analyse Farmer</a></li>
    <li><a href="bayesian.jsp">Bayesian Analysis</a></li>
    <li><a href="Logout">Logout</a></li>
</ul>
<br>
<br>
<div class="container">
 <form method="post" action="AddNotification">
    <div class="row">
      <div class="input-group input-group-icon">
      <h3>Create Notification</h3>
      <div class="row">
      <h5>Notification Name</h5>
      </div>
       <select name="notificationId"><%= mybean1.populateNotification() %></select>
      </div>
      
	  <div class="input-group input-group-icon">
      <div class="row">
      <h5>Farmer Name</h5>
      </div>
       <select name="farmerId"><%=mybean1.populateFarmer() %></select>
      </div>
      <div class="input-group input-group-icon">
	  <br>
       <input type="submit" value="send notification" />
      </div>
    </div>
  </form>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    
</body>
</html>