<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mybean1" class="Esp8266.Wifi.farmer.ManageFarmer" scope="request" />
<jsp:useBean id="mybean2" class="Esp8266.Wifi.farmer.Farmer" scope="request" />
<jsp:useBean id="mybeanManageCrop" class="Esp8266.Wifi.crop.ManageCrop" scope="request" />
<jsp:useBean id="mybeanManageFarmerCrop" class="Esp8266.Wifi.cropavailable.ManageFarmerCrop" scope="request" />
<jsp:useBean id="mybeanFarmerCrop" class="Esp8266.Wifi.cropavailable.FarmerCrop" scope="request" />
<jsp:useBean id="mybeanManageFarmerWsn" class="Esp8266.Wifi.cropavailable.ManageFarmerWsn" scope="request" />
<jsp:useBean id="mybeanFarmerMenu" class="Esp8266.Wifi.farmer.FarmerMenu" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OpenAgr | Profile</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
</head>
<body class="w3-light-grey">
<%
HttpSession session12 = request.getSession(false);
String username = session12.getAttribute("username").toString();
mybean1.sessionFactoryConfig();
int id = mybean1.getFarmerId(username);
mybean2 = mybean1.getFarmer(id);
mybeanManageFarmerCrop.sessionFactoryConfig();
List<Esp8266.Wifi.cropavailable.FarmerCrop> farmerCrop = mybeanManageFarmerCrop.getFarmerCrop(id);
String cropName = "";
Date date = new Date();
mybeanManageFarmerWsn.sessionFactoryConfig();
List<Integer> wsnitr = mybeanManageFarmerWsn.getFarmerWsnId(id);
if(farmerCrop == null){
	cropName = "no crop selected";
}
else{
	
%>
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
          <img src="image/user.jpg" style="width:100%" alt="Avatar">
          <div class="w3-display-bottomleft w3-container w3-text-black">
            <h2><%= username.toUpperCase()%> </h2>
          </div>
        </div>
        <div class="w3-container">
          <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>Tier 1 Farmer</p>
          <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i><%= mybean2.getFarmer_address() %></p>
          <p><i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i>+91 8088434604</p>
          <hr>

          <p class="w3-large"><b><i class="fa fa-asterisk fa-fw w3-margin-right w3-text-teal"></i>Rankings</b></p>
          <p></p>
          
          <div class="w3-light-grey w3-round-xlarge w3-small">
            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:<%= mybeanManageFarmerCrop.getFarmerRank(id)*10%>%"><%= mybeanManageFarmerCrop.getFarmerRank(id)*10%>%</div>
          </div>
          <br>
          <br>
        </div>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16">Profile Info</h2>
		        <div class="w3-container">
          <h5 class="w3-opacity"><b>Full Name</b></h5>
          <p><%= mybean2.getFarmer_first_name()+" "+mybean2.getFarmer_last_name() %></p>
          <hr>
        </div>
		<div class="w3-container">
          <h5 class="w3-opacity"><b>WSN ID</b></h5>
        	    <p><%= wsnitr %> </p>
         
          <hr>
        </div>
        <div class="w3-container">
          <h5 class="w3-opacity"><b>Water Availablity</b></h5>
          <p><%= mybean2.getWater_availablity() %></p>
          <hr>
        </div>
		        <div class="w3-container">
          <h5 class="w3-opacity"><b>Electricity Availablity</b></h5>
          <p><%= mybean2.getElectricity_availablity() %></p>
          <hr>
        </div>

      </div>

      <div class="w3-container w3-card w3-white">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Crop History</h2>
        <%= mybeanFarmerMenu.populateCrop(id) %>
      </div>
<%} %>
    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
  </div>
  
  <!-- End Page Container -->
</div>
<%
mybean1.closeSessionFactoryConfig();
mybeanManageFarmerCrop.closeSessionFactoryConfig();
mybeanManageFarmerWsn.closeSessionFactoryConfig();
%>
<footer class="w3-container w3-teal w3-center w3-margin-top">
  <p>Openagr</p>
</footer>

</body>
</html>