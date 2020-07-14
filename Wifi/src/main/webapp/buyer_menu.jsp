<%@page import="Esp8266.Wifi.Location"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mybean1" class="Esp8266.Wifi.buyer.BuyerMenu" scope="request" />
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  <title>OpenAgr | BuyerMenu</title> 
  <script src="http://maps.google.com/maps/api/js?sensor=false" 
          type="text/javascript"></script>
          <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://maps.google.com/maps/api/js?sensor=false" 
          type="text/javascript"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
.no-js #loader { display: none;  }
.js #loader { display: block; position: absolute; left: 100px; top: 0; }
.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url(images/spinner.gif) center no-repeat #fff;
}
</style>
</head>
<script type="text/javascript">
$(window).load(function() {
	// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");;
});
</script>

<body >
<div class="se-pre-con"></div>
		<%
HttpSession session12 = request.getSession();
String username = session12.getAttribute("username").toString();
%>
<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right">OpenAgr</span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s4">
    </div>
    <div class="w3-col s8 w3-bar">
      <span>Welcome, <strong><%= username %></strong></span><br>
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>Dashboard</h5>
  </div>
  <div class="w3-bar-block">
    <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
    <a href="#" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i>  Overview</a>
    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>  News</a>
    <a href="buyer_setting.jsp" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i>  Settings</a><br><br>
    <a href="Logout" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>  Logout</a>
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
  </header>

  <div class="w3-row-padding w3-margin-bottom">

  </div>

  <div class="w3-panel">
    <div class="w3-row-padding" style="margin:0 -16px">
      <div class="w3-third">
        <h5>Regions</h5>
		<div id="map" style="width: device-width; height: 280px;"></div>

<span id="idl" style="display:none"><%= mybean1.populateLocation(username) %></span>
         <script type="text/javascript">
  	var locationsvalue = document.getElementById("idl").innerHTML.split("--");
  	 var map = new google.maps.Map(document.getElementById('map'), {
 	      zoom: 8,
 	      center: new google.maps.LatLng(13.55, 77.55),
 	      mapTypeId: google.maps.MapTypeId.ROADMAP
 	    });

 	    var infowindow = new google.maps.InfoWindow();

  	for(j = 0; j < locationsvalue.length;j++){
  		
  	    var locations =  locationsvalue[j].split(",") ;
  	    var marker, i;
  	    

  	    for (i = 0; i < locations.length; i++) {  
  	      marker = new google.maps.Marker({
  	    	  
  	        position: new google.maps.LatLng(locations[1], locations[2]),
  	        map: map
  	      });
  		
  	}


      google.maps.event.addListener(marker, 'click', (function(marker, j) {
        return function() {
          infowindow.setContent(locationsvalue[j]);
          infowindow.open(map, marker);
        }
      })(marker, j));
    }
  </script>
      </div>
      <div class="w3-twothird">
        <h5>Crop Details</h5>
        <table class="w3-table w3-striped w3-white">
          <%= mybean1.populateCropDetail(username) %>
        </table>
      </div>
    </div>
  </div>
  <hr>
  <hr>

  <hr>


  <br>
  <div class="w3-container w3-dark-grey w3-padding-32">
    <div class="w3-row">
     
    </div>
  </div>



  <!-- End page content -->
</div>

<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}
</script>

</body>
</html>