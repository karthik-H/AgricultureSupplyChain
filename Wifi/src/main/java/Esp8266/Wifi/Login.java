package Esp8266.Wifi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.buyer.Buyer;
import Esp8266.Wifi.buyer.ManageBuyer;
import Esp8266.Wifi.customer.Customer;
import Esp8266.Wifi.customer.ManageCustomer;
import Esp8266.Wifi.farmer.Farmer;
import Esp8266.Wifi.farmer.ManageFarmer;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	      // Set response content type
		PrintWriter out = response.getWriter();
		String user = request.getParameter("uname");
		System.out.println(user);
		String psw = request.getParameter("psw");
		System.out.println(psw);
		String user_type = request.getParameter("user_type");
		System.out.println(user_type);
		//check customer
		if(user_type.equals("customer")) {
			ManageCustomer manageCustomer = new ManageCustomer();
			Customer customer = new Customer();
			customer.setName(user);
			customer.setPassword(psw);
			manageCustomer.sessionFactoryConfig();
			if(manageCustomer.checkCustomer(customer)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user);
				manageCustomer.closeSessionFactoryConfig();
				response.sendRedirect("customer_menu.jsp");
			}
			else {
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('User or password incorrect');");
				   out.println("location='Login.jsp';");
				   out.println("</script>");
			}
		}
		//check farmer
		else if(user_type.equals("farmer")) {
			Farmer farmer = new Farmer();
			farmer.setUsername(user);
			farmer.setPassword(psw);
			ManageFarmer manageFarmer = new ManageFarmer();
			manageFarmer.sessionFactoryConfig();
			if(manageFarmer.checkFarmer(farmer)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user);
				manageFarmer.closeSessionFactoryConfig();
				response.sendRedirect("farmer_menu.jsp");
			}
			else {
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('User or password incorrect');");
				   out.println("location='Login.jsp';");
				   out.println("</script>");
			}
		}
		else if(user_type.equals("admin")) {
			Users users = new Users();
			users.setUsername(user);
			users.setPassword(psw);
			ManageUser manageUser = new ManageUser();
			manageUser.sessionFactoryConfig();
			if(manageUser.checkUsers(user, psw)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user);
				manageUser.closeSessionFactoryConfig();
				response.sendRedirect("/Wifi/SensorData");
			}
			else {
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('User or password incorrect');");
				   out.println("location='Login.jsp';");
				   out.println("</script>");
			}
		}
		//check buyer
		else {
			Buyer buyer = new Buyer();
			buyer.setName(user);
			buyer.setPassword(psw);
			ManageBuyer manageBuyer = new ManageBuyer();
			manageBuyer.sessionFactoryConfig();
			if(manageBuyer.checkBuyer(buyer)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user);
				manageBuyer.closeSessionFactoryConfig();
				response.sendRedirect("buyer_menu.jsp");
			}
			else {
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('not configured');");
				   out.println("location='Login.jsp';");
				   out.println("</script>");
			}
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
