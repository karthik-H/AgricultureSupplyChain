package Esp8266.Wifi.notification;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.farmer.ManageFarmer;

/**
 * Servlet implementation class NotificationMenu
 */
public class NotificationMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath())
		.append(request.getParameter("id").toString());
		int id = Integer.parseInt(request.getParameter("id"));
		ManageFarmerNotification manageFarmerNotification = new ManageFarmerNotification();
		manageFarmerNotification.sessionFactoryConfig();
		manageFarmerNotification.deleteNotification(id);
		response.sendRedirect("notification.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
public StringBuilder populateNotification(HttpSession session) {
	String farmerName = session.getAttribute("username").toString();
	
	StringBuilder str = new StringBuilder();
	String url = "";
	ManageFarmer manageFarmer = new ManageFarmer();
	manageFarmer.sessionFactoryConfig();
	int farmer_id = manageFarmer.getFarmerId(farmerName);
	ManageFarmerNotification manageFarmerNotification = new ManageFarmerNotification();
	manageFarmerNotification.sessionFactoryConfig();
	ManageNotification mn = new ManageNotification();
	mn.sessionFactoryConfig();
	List<FarmerNotification> farmerNotifications = manageFarmerNotification.getFarmerNotification(farmer_id);
	if(farmerNotifications.isEmpty()) {
		manageFarmer.closeSessionFactoryConfig();
		manageFarmerNotification.closeSessionFactoryConfig();
		mn.closeSessionFactoryConfig();
		str.append("Notification box is empty for now");
		return str;
	}
	for(FarmerNotification f: farmerNotifications) {
		url = "/Wifi/NotificationMenu?id="+f.getId();
		str.append("  <div class=\"w3-card-4 w3-dark-grey\" style=\"width:80%;margin-left:auto;margin-right:auto\">\r\n" + 
				"    <div class=\"w3-container w3-center\">\r\n" + 
				"      <h3>"+mn.getNotificationMessage(f.getNotification_id()).getNotification_topic()+"</h3>\r\n" + 
				"      <h5>"+mn.getNotificationMessage(f.getNotification_id()).getNotification_message()+"</h5>\r\n" + 
				"<h6>"+f.getDate()+"</h5>\r\n"+
				"\r\n" + 
				"      <div class=\"w3-section\">\r\n" + 
				"        <a href="+url+" class=\"w3-button w3-green\">Ok</a>\r\n" + 
				"      </div>\r\n" + 
				"    </div>\r\n" + 
				"  </div>\r\n" + 
				"  <br>");
				}
	manageFarmer.closeSessionFactoryConfig();
	manageFarmerNotification.closeSessionFactoryConfig();
	mn.closeSessionFactoryConfig();
	return str;
}
}
