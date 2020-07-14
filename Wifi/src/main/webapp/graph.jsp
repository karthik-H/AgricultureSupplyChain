<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="Esp8266.Wifi.farmer.ManageFarmer" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<jsp:useBean id="mybean1" class="Esp8266.Wifi.Graph" scope="request" />
<jsp:useBean id="mybean2" class="Esp8266.Wifi.AddNotification" scope="request" />
<% HttpSession session1 = request.getSession(false);
int id = 1;
if(session1.getAttribute("id")!=null){
	id = Integer.parseInt(session1.getAttribute("id").toString());
}
String phPoints = mybean1.populatePhgraphdata(id);
String moisturePoints = mybean1.populateMoisturegraphdata(id);
String tempPoints = mybean1.populateTempgraphdata(id);
String rainPoints = mybean1.populateRaingraphdata(id);
ManageFarmer manageFarmer = new ManageFarmer();
manageFarmer.sessionFactoryConfig();
String farmerName = manageFarmer.getFarmerName(id);
manageFarmer.closeSessionFactoryConfig();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<title>OpenAgr | FarmerAnalysis</title>

<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer1", {
	theme: "light2",
    animationEnabled: true,
	title: {
		text: "Quality based on Ph"
	},
	axisX: {
		title: "Ph"
	},
	axisY: {
		title: "quality"
	},
	data: [{
		type: "line",
		yValueFormatString: "#,##0",
		dataPoints : <%out.print(phPoints);%>
	}]
});
chart.render();
 
var chart = new CanvasJS.Chart("chartContainer2", {
	theme: "light2",
    animationEnabled: true,
	title: {
		text: " Quality based on mositure"
	},
	axisX: {
		title: "Mositure(%)"
	},
	axisY: {
		title: "quality"
	},
	data: [{
		type: "line",
		yValueFormatString: "#,##0",
		dataPoints : <%out.print(moisturePoints);%>
	}]
});
chart.render();

var chart = new CanvasJS.Chart("chartContainer3", {
	theme: "light2",
    animationEnabled: true,
	title: {
		text: "Quality based on Temp"
	},
	axisX: {
		title: "Temp(K)"
	},
	axisY: {
		title: "quality"
	},
	data: [{
		type: "line",
		yValueFormatString: "#,##0",
		dataPoints : <%out.print(tempPoints);%>
	}]
});
chart.render();

var chart = new CanvasJS.Chart("chartContainer4", {
	theme: "light2",
    animationEnabled: true,
	title: {
		text: "Quality based on Average Rainfall"
	},
	axisX: {
		title: "Average Rainfall(mm)"
	},
	axisY: {
		title: "quality"
	},
	data: [{
		type: "line",
		yValueFormatString: "#,##0",
		dataPoints : <%out.print(rainPoints);%>
	}]
});
chart.render();
}
</script>
</head>
<body>
<div class="header">
<img src="image/openagr.png" alt="OpenAgr Icon" style="width:128px;height:128px;
left:50%;">
</div>
<ul>
  <li><a href="sensordata.jsp">SensorData</a></li>
  <li><a href="/Wifi/CbrAlgorithm">Crop Prediction</a></li>
  <li><a href="updatesensordata.jsp">Update Data</a></li>
  <li><a href="add_notification.jsp">Add Notification</a></li>
  <li><a class="active" href="graph.jsp">Analyse Farmer</a></li>
    <li><a href="bayesian.jsp">Bayesian Analysis</a></li>
    <li><a href="Logout">Logout</a></li>
  
</ul>
 <form method="post" action="GraphServlet">
 <select name="farmer_id"><%= mybean2.populateFarmer() %></select>
 <input type="submit" value="select" />
 </form>
 <h3>Farmer Name : <%= farmerName %></h3>
<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<div id="chartContainer1" style="width: 45%; height: 300px;display: inline-block;"></div> 
<div id="chartContainer2" style="width: 45%; height: 300px;display: inline-block;"></div><br/>
<div id="chartContainer3" style="width: 45%; height: 300px;display: inline-block;"></div>
<div id="chartContainer4" style="width: 45%; height: 300px;display: inline-block;"></div>
</body>
</html>