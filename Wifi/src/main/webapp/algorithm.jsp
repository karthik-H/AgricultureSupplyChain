<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mybean1" class="Esp8266.Wifi.CbrAlgorithm" scope="request" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OpenAgr | Crop Prediction</title>
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<style type="text/css">
.no-js #loader { display: none;  }
.js #loader { display: block; position: absolute; left: 100px; top: 0; }
.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url(images/loader-64x/Preloader_2.gif) center no-repeat #fff;
}
</style>
</head>
<script type="text/javascript">
$(window).load(function() {
	// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");;
});
</script>
<body>
<div class="se-pre-con"></div>
<div class="header">
<img src="image/openagr.png" alt="OpenAgr Icon" style="width:128px;height:128px;
left:50%;">
</div>
<ul>
  <li><a href="/Wifi/SensorData">SensorData</a></li>
  <li><a class="active" href="algorithm.jsp">Crop Prediction</a></li>
  <li><a href="updatesensordata.jsp">Update Data</a></li>
  <li><a href="add_notification.jsp">Add Notification</a></li>
  <li><a href="graph.jsp">Analyse Farmer</a></li>
    <li><a href="bayesian.jsp">Bayesian Analysis</a></li>
    <li><a href="Logout">Logout</a></li>
</ul>

<table>
		<tr>
		<td> WSN ID </td>
		<td> Latitude </td>
		<td> Longitude </td>
		<td> Accurecy </td>
		<td> Soil PH </td>
		<td> Moisture </td>
		<td> Temperature </td>
		<td> Avg Rainfall </td>
		<td> Prediction 1 </td>
		<td> Prediction 2 </td>
		<td> Prediction 3 </td>
		</tr>
<!--       <c:forEach items="${sensordata}" var="sensordata">
       
            <tr>
                <td><c:out value="${sensordata.id}"/></td>
                <td><c:out value="${sensordata.latitude}"/></td>
                <td><c:out value="${sensordata.longitude}"/></td>
                <td><c:out value="${sensordata.accurecy}"/></td>
                <td><c:out value="${sensordata.soil_ph}"/></td>
                <td><c:out value="${sensordata.moisture}"/></td>
                <td><c:out value="${sensordata.temperature}"/></td>
                <td><c:out value="${sensordata.avg_rainfall}"/></td>
                   
            </tr>
        </c:forEach> -->
        <%= mybean1.populateAlgoTable()%>
    </table>



</body>
</html>