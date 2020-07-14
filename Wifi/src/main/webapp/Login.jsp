<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mybean1" class="Esp8266.Wifi.buyer.ManageBuyer" scope="request" />
<html>
<head>
<title>Open Agr</title>  
    <meta name="title" content="OpenAgr" />  
    <meta name="description" content="Agriculture product management system" />  
    <meta name="keywords" content="openagr" />  
    <meta name="subject" content="Your Web Site subject here" />  
    <meta name="copyright" content="Your company or organization" />  
    <meta name="robots" content="All" />  
    <meta name="abstract" content="Site description here" />  
    <meta name="MSSmartTagsPreventParsing" content="true" />  
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0"/>
    <link id="theme" rel="stylesheet" type="text/css" href="css/style.css" title="theme" />  
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto+Condensed:300|Roboto+Condensed|Roboto+Condensed:300|Roboto+Condensed:300|Roboto+Condensed:300|Roboto+Condensed:300" type="text/css" />  
	<script type="text/javascript" language="javascript" src="js/jquery-1.8.1.min.js"></script>  
	<script type="text/javascript" language="javascript" src="js/scripts.js"></script>  
	<script type="text/javascript" language="javascript" src="js/theme.js"></script>  
	<script type="text/javascript" language="javascript" src="js/custom.js"></script> 
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
	<style>
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}
.container {
  padding: 16px;
}
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
.headingcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}
.modal_users {
  display: none; /* Hidden by default */
  left: 0;
  top: 10px;
  bottom: 20px;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  padding-top: 60px;
}
@media only screen 
and (min-device-width : 320px) 
and (max-device-width : 480px) {
/* Styles */
}

/* Smartphones (landscape) ----------- */
@media only screen 
and (min-width : 321px) {
/* Styles */
}

/* Smartphones (portrait) ----------- */
@media only screen 
and (max-width : 320px) {
.button {
	display:none;
}
}
/* Desktops and laptops ----------- */
@media only screen 
and (min-width : 1224px) {
/* Styles */
}

/* Large screens ----------- */
@media only screen 
and (min-width : 1824px) {
/* Styles */
}

input[type=checkbox]:not(old),
input[type=radio   ]:not(old){
  width   : 28px;
  margin  : 0;
  padding : 0;
  opacity : 0;
}
input[type=checkbox]:not(old) + label,
input[type=radio   ]:not(old) + label{
  display      : inline-block;
  margin-left  : -28px;
  padding-left : 28px;
  background   : url('image/checks.png') no-repeat 0 0;
  line-height  : 24px;
}
input[type=checkbox]:not(old):checked + label{
  background-position : 0 -24px;
}

input[type=radio]:not(old):checked + label{
  background-position : 0 -48px;
}
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
<body>
<div class="se-pre-con"></div>

<header> 
      <div id="top"> 
        <div class="bg"></div>  
        <div class="row"> 
          <div id="logo">
          <img src="images/css/logo.png" />
          </div>  
          <nav> 
            <div id="hmenu"> 
              <ul> 
                <li> 
				
                  <a  onclick="document.getElementById('id01').style.display='block'">Login</a> 
                </li>  
                <li> 
                  <a onclick="document.getElementById('id02').style.display='block'">Sign Up</a> 
                </li>   
              </ul> 
            </div> 
          </nav>  
          <div class="clear"></div> 
        </div> 
      </div>  
      <div id="header-wrapper"> 
      
        <div class="bg">
        <img src="images/css/header-wrapper-bg.jpg" />
        </div>  
        <div class="row"> 
          <div id="header"> 
            <div id="tagline"> 
                
			  <h1> An Agriculture Product Management System</h1>
			  <br>
			  
			  <div id="id01" class="modal">
  
					<form class="modal-content animate" action="Login" method="post">

						<div class="headingcontainer">
							<h2> Login </h2>
						</div>
						<div class="container">
							<label for="uname"><b>Username</b></label>
								<input type="text" placeholder="Enter Username" name="uname" required>
								
								<label for="psw"><b>Password</b></label>
								<input type="password" placeholder="Enter Password" name="psw" required>
        
								<input type="radio" name="user_type" value="customer" />
								<label for="sizeWeight">Customer</label>
								
								<input type="radio" name="user_type" value="buyer" />
								<label for="sizeWeight">Buyer</label>
								
								<input type="radio" name="user_type" value="farmer" checked="checked" />
								<label for="sizeWeight">Farmer</label>
								
								<input type="radio" name="user_type" value="admin" />
								<label for="sizeWeight">Admin</label>
								
								<button type="submit">Login</button>
						</div>

						<div class="container" style="background-color:#f1f1f1">
							<button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
						</div>
					</form>
				</div>
				
				<div id="id02" class="modal">
  
					<form class="modal-content animate" action="Register" method="post">

						<div class="headingcontainer">
							<h2> Register new user </h2>
						</div>
						<div class="container">
							<label for="uname"><b>Username</b></label>
								<input type="text" placeholder="Enter Username" name="uname" required>
						
								<label for="psw"><b>Password</b></label>
								<input type="password" placeholder="Enter Password" name="psw" required>
								
								<input type="radio" name="user_type" value="customer" id="sizeWeight" checked="checked" onclick="document.getElementById('id04').style.display='block'; 
										document.getElementById('id06').style.display='none'; document.getElementById('id05').style.display='none'" style="width:auto;" />
								<label for="sizeWeight">Customer</label>
								
								<input type="radio" name="user_type" value="buyer" id="sizeDimensions" onclick="document.getElementById('id06').style.display='block'; 
										document.getElementById('id05').style.display='none'; document.getElementById('id04').style.display='none'" style="width:auto;"/>
								<label for="sizeDimensions">Buyer</label>
		
								<input type="radio" name="user_type" value="farmer" id="sizeWeight" onclick="document.getElementById('id05').style.display='block'; 
										document.getElementById('id06').style.display='none'; document.getElementById('id04').style.display='none'" style="width:auto;"/>
								<label for="sizeDimensions">Farmer</label>
								
								<div id="id04" class="modal_users">
									<label for="uname"><b>Customer Email</b></label>
									<input type="text" placeholder="Enter Email" name="email">
								</div>
	
								<div id="id06" class="modal_users">
									<label for="uname"><b>cropnames</b></label>
									<div>
									<%=mybean1.populateCrop()%>
									</div>
								</div>
								 
								<div id="id05" class="modal_users">
									
									<label for="uname"><b>First name</b></label>
									<input type="text" placeholder="Enter First name" name="first_name">
									
									<label for="uname"><b>Last Name</b></label>
									<input type="text" placeholder="Enter Last name" name="last_name">
									
									<label for="uname"><b>Adress</b></label>
									<input type="text" placeholder="Enter adress" name="address">
									
									<label for="uname"><b>Water availablity</b></label>
									<input type="number" placeholder="Enter water availablity" name="water_availablity">
									
									<label for="uname"><b>Electricity availablity</b></label>
									<input type="number" placeholder="Enter electicity availablity" name="electicity_availablity"> 
									<br>
									<br>
									
									<label for="uname"><b>Wsn Id will be auto-generated</b></label>
								</div>
								<br>
								<button type="submit">Register</button>
						</div>
							
						<div class="container" style="background-color:#f1f1f1">
							<button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
						</div>
					</form>
				</div>
			  
			  
              <input type="button" class="cta pie" onclick="location.href='index.html'" value="More about us" /> 
            </div> 
          </div> 
        </div> 
      </div> 
    </header>  
  
    <footer id="footer"> 
      <div class="bg"></div>  
      <div class="content"> 
        <div class="group"> 
          <div class="col span_1_of_3"> 
            <h2>Links</h2>  
            <div class="vmenu"> 
              <ul> 
                <li>
                  <a href="#">Team</a>
                </li>  
                <li>
                  <a href="http://www.enam.gov.in/enam/">NAM</a>
                </li>   
              </ul> 
            </div> 
          </div>  
          <div class="col span_1_of_3"> 
            <h2>About Us</h2>  
            <p>An online agriculture product management system</p> 
          </div>  
          <div class="col span_1_of_3"> 
            <h2>Contact</h2>  
            <p>OpenAgr
              <br /> 8088434609
              <br /> 
            </p> 
          </div> 
        </div>  
        <div class="clear"></div>  
        <div class="baseline"> 
          <div style="float:left;margin-top:7px"> 
            
          </div>  
          <ul class="social-links" style="float:right"> 
            <li> 
              <a href="#" rel="nofollow" style="background-image:url('images/facebook.png')" title="Be a fan on Facebook"></a> 
            </li>  
            <li> 
              <a href="#" rel="nofollow" style="background-image:url('images/twitter.png')" title="Follow us on Twitter"></a> 
            </li>  
            <li> 
              <a href="#" style="background-image:url('images/googleplus.png')" title="Circle us on Google+"></a> 
            </li>  
            <li> 
              <a href="#" rel="nofollow" style="background-image:url('images/rss.png')" title="RSS Feed"></a> 
            </li> 
          </ul>  
          <div class="clear"></div> 
        </div> 
      </div> 
    </footer> 
    
	
	<script>
// Get the modal
var modal = document.getElementById('id01');
var modal0 = document.getElementById('id02');
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modal0) {
        modal0.style.display = "none";
    }
	}
function myFunction() {
  var no = document.getElementById("no");
  var option = no.options[no.selectedIndex].text;
  var txt = document.getElementById("result").value;
  txt = txt +option+" | ";
  document.getElementById("hidden").value = document.getElementById("hidden").value 
  +"--"+document.getElementById("no").value;
  document.getElementById("result").value = txt;
}

</script>
</body>
</html>