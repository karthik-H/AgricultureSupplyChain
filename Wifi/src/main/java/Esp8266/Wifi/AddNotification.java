package Esp8266.Wifi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Esp8266.Wifi.farmer.Farmer;
import Esp8266.Wifi.farmer.ManageFarmer;
import Esp8266.Wifi.notification.FarmerNotification;
import Esp8266.Wifi.notification.ManageFarmerNotification;
import Esp8266.Wifi.notification.ManageNotification;
import Esp8266.Wifi.notification.Notification;

/**
 * Servlet implementation class AddNotification
 */
public class AddNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNotification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath())
		//.append(request.getParameter("notificationId")).append(request.getParameter("farmerId"));
		PrintWriter out = response.getWriter();
		int notificationId = Integer.parseInt(request.getParameter("notificationId"));
		int farmerId = Integer.parseInt(request.getParameter("farmerId"));
		FarmerNotification farmerNotification = new FarmerNotification();
		ManageFarmerNotification manageFarmerNotification = new ManageFarmerNotification();
		manageFarmerNotification.sessionFactoryConfig();
		farmerNotification.setFarmer_id(farmerId);
		farmerNotification.setNotification_id(notificationId);
		farmerNotification.setDate(new Date());
		manageFarmerNotification.addFarmerNotification(farmerNotification);
		manageFarmerNotification.closeSessionFactoryConfig();
		out.println("<script type=\"text/javascript\">");
		   out.println("alert('Notification Sent');");
		   out.println("location='add_notification.jsp';");
		   out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String populateNotification() {
		String Str="<option value1='0'>Select</option>";
		ManageNotification manageNotification = new ManageNotification();
		manageNotification.sessionFactoryConfig();
		List<Notification> notification = manageNotification.getAllNotification();
		for(Notification n : notification) {
			Str+="<option value="+n.getId()+">"+n.getNotification_topic()+"</option>";
			
		}
		manageNotification.closeSessionFactoryConfig();
		return Str;
	}
	
	public String populateFarmer() {
		String Str="<option value='0'>Select</option>";
		ManageFarmer manageFarmer = new ManageFarmer();
		manageFarmer.sessionFactoryConfig();
		List<Farmer> farmer = manageFarmer.getAllFarmer();
		for(Farmer f : farmer) {
			Str+="<option value="+f.getId()+">"+f.getFarmer_first_name()+" "+f.getFarmer_last_name()+"</option>";
		}
		manageFarmer.closeSessionFactoryConfig();
		return Str;
	}

}
