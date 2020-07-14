<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mybean1" class="Esp8266.Wifi.farmer.FarmerMenu" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OpenAgr | Farmer</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
</style>
</head>
<body class="w3-light-grey w3-content" style="max-width:1600px">
<%
	HttpSession session12 = request.getSession();
if(session12.getAttribute("username") == null){
	response.sendRedirect("Login.jsp");
}
else{
	String username = session12.getAttribute("username").toString();
	

%>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container">
    <a href="#" onclick="w3_close()" class="w3-hide-large w3-right w3-jumbo w3-padding w3-hover-grey" title="close menu">
      <i class="fa fa-remove"></i>
    </a>
    <img src="image/openagr.png" style="width:45%;" class="w3-round"><br><br>
    <h4><b><%= username.toUpperCase() %></b></h4>
    <p class="w3-text-grey">Farmer</p>
  </div>
  <div class="w3-bar-block">
    <a href="farmer_menu.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-padding w3-text-teal"><i class="fa fa-th-large fa-fw w3-margin-right"></i>Crops Selection</a> 
    <a href="farmer_profile.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-padding" target="_blank"><i class="fa fa-user fa-fw w3-margin-right"></i>Profile</a> 
    <a href="notification.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-padding" target="_blank"><i class="fa fa-envelope fa-fw w3-margin-right"></i>Notifications</a>
	<br><br>
	<a href="/Wifi/Logout" onclick="w3_close()" class="w3-bar-item w3-button w3-padding">LogOut</a>
	
  </div>
</nav>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px">

  <!-- Header -->
  <header id="portfolio">
    <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i class="fa fa-bars"></i></span>
    <div class="w3-container">
    <h1><b>OpenAgr</b></h1>
    </div>
  </header>

  <!-- First Photo Grid-->
  <div class="w3-row-padding">
<%= mybean1.populateCropData(username, session12) %>



  </div>
 

<!-- End page content -->
</div>
<%} %>
<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}
</script>

</body>
</html>