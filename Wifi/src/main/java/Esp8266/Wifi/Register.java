package Esp8266.Wifi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Esp8266.Wifi.buyer.Buyer;
import Esp8266.Wifi.buyer.BuyerCrop;
import Esp8266.Wifi.buyer.ManageBuyer;
import Esp8266.Wifi.buyer.ManageBuyerCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerWsn;
import Esp8266.Wifi.customer.Customer;
import Esp8266.Wifi.customer.ManageCustomer;
import Esp8266.Wifi.farmer.Farmer;
import Esp8266.Wifi.farmer.ManageFarmer;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String user = request.getParameter("uname");
		System.out.println(user);
		
		String password = request.getParameter("psw");
		System.out.println(password);
		String user_type = request.getParameter("user_type");
		System.out.println(user_type);
		if(user_type.equals("farmer")) {
			Farmer farmer = new Farmer();
			ManageFarmer manageFarmer = new ManageFarmer();
			manageFarmer.sessionFactoryConfig();
			if(!(manageFarmer.checkUniqueFarmer(user))) {
				manageFarmer.closeSessionFactoryConfig();
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('User already exists');");
				   out.println("location='Login.jsp';");
				   out.println("</script>");
			}
			else {
				farmer.setUsername(user);
				farmer.setPassword(password);
				farmer.setFarmer_first_name(request.getParameter("first_name"));
				farmer.setFarmer_last_name(request.getParameter("last_name"));
				farmer.setFarmer_address(request.getParameter("address"));
				farmer.setWater_availablity(Float.parseFloat(request.getParameter("water_availablity")));
				farmer.setElectricity_availablity(Float.parseFloat(request.getParameter("electicity_availablity")));
				
				manageFarmer.addFarmer(farmer);
				
				ManageLocation manageLocation = new ManageLocation();
				manageLocation.sessionFactoryConfig();
				ManageFarmerWsn manageFarmerWsn = new ManageFarmerWsn();
				manageFarmerWsn.sessionFactoryConfig();
				manageFarmerWsn.addFarmerWsn(manageFarmer.getFarmerId(user), manageLocation.getLastId()+1);
				Service service = new Service();
				service.addSingleRandomLocation();
				
				manageFarmer.closeSessionFactoryConfig();
				manageLocation.closeSessionFactoryConfig();
				manageFarmerWsn.closeSessionFactoryConfig();
				response.sendRedirect("/Wifi");
			}
		}
		else if(user_type.equals("customer")) {
			ManageCustomer manageCustomer = new ManageCustomer();
			manageCustomer.sessionFactoryConfig();
			Customer customer = new Customer();
			if(!(manageCustomer.checkUniqueCustomer(user))) {
				manageCustomer.closeSessionFactoryConfig();
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('User already exists');");
				   out.println("location='Login.jsp';");
				   out.println("</script>");
			}
			else {
				Mail mail = new Mail();
				String subject = "\n\nDear "+user+","
						+ "\n\n Welcome to OpenAgr"
						+ "\n\n Agriculture product management system"
						+ "\n\n <h1><a href='http://localhost:8090/Wifi/customer_menu.jsp'/></h1>";
				customer.setName(user);
				customer.setPassword(password);
				customer.setEmail(request.getParameter("email"));
				manageCustomer.addCustomer(customer);
				mail.sendMail(request.getParameter("email"), subject);
				manageCustomer.closeSessionFactoryConfig();
				response.sendRedirect("customer_menu.jsp");
			}
		}
		else if(user_type.equals("buyer")) {
			Buyer buyer = new Buyer();
			ManageBuyer manageBuyer = new ManageBuyer();
			manageBuyer.sessionFactoryConfig();
			if(!(manageBuyer.checkUniqueBuyer(user))) {
				manageBuyer.closeSessionFactoryConfig();
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('User already exists');");
				   out.println("location='Login.jsp';");
				   out.println("</script>");
			}
			else {
				buyer.setName(user);
				buyer.setPassword(password);
				manageBuyer.addBuyer(buyer);
				ManageBuyerCrop manageBuyerCrop = new ManageBuyerCrop();
				manageBuyerCrop.sessionFactoryConfig();
				BuyerCrop buyerCrop = new BuyerCrop();
				buyerCrop.setBuyer_id(manageBuyer.getBuyerId(user));
				for(String s : request.getParameterValues("field")) {
					buyerCrop.setCrop_id(Integer.parseInt(s));
					manageBuyerCrop.addBuyerCrop(buyerCrop);
					//System.out.println(s);
				}
				//System.out.print(request.getParameterValues("field"));
				manageBuyer.closeSessionFactoryConfig();
				manageBuyerCrop.closeSessionFactoryConfig();
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('User Registered Successfully please login :)');");
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
