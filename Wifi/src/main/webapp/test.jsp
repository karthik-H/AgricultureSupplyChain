<%@page import="Esp8266.Wifi.Location"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="mybean1" class="Esp8266.Wifi.buyer.ManageBuyer" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
    <script type="text/javascript">
    function processRecords(){
        document.getElementById("mainContent").src = "Login.jsp";
    }
    </script>

</head>
<body onload="processRecords()">
    <iframe id="mainContent" height="100%" width="100%"  style="border: none" seamless src="loader.jsp"></iframe>

</body>
</html>