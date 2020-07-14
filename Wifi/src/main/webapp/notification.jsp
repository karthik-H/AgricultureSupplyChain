<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mybean1" class="Esp8266.Wifi.notification.NotificationMenu" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>OpenAgr | Notifications</title>
</head>
<body>

<div class="w3-container">
  <h2>Notifications</h2>
	<br>
  <div class="w3-row-padding">
  
  <%= mybean1.populateNotification(request.getSession()) %>


  
   
  </div>
  
</div>


</body>
</html>