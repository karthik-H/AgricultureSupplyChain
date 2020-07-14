package Esp8266.Wifi.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.crop.Crop;
import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;

/**
 * Servlet implementation class CustomerMenu
 */
public class CustomerMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public StringBuilder populateAvailableCrop() {
		ManageFarmerCrop manageFarmer = new ManageFarmerCrop();
		manageFarmer.sessionFactoryConfig();
		StringBuilder str = new StringBuilder();
		ManageCrop crop = new ManageCrop();
		crop.sessionFactoryConfig();
		List<Crop> croplist = crop.getCropName();
		if(croplist.isEmpty()) {
			manageFarmer.closeSessionFactoryConfig();
			crop.closeSessionFactoryConfig();
			str.append("Sorry no crop available for now we will be adding soon...");
		}
		for(Crop c : croplist) {
			if(manageFarmer.checkCrop(c.getId())) {
				str.append("		<div class=\"single-products-catagory clearfix\">\r\n" + 
						"        <a href=\"ProductDetail?cropname="+c.getCrop_name().toLowerCase()+"\">\r\n" + 
						"            <img src=\"image/").append(c.getCrop_name()).append(".jpg\" alt=\"\">\r\n" + 
								"            <!-- Hover Content -->\r\n" + 
								"            <div class=\"hover-content\">\r\n" + 
								"                <div class=\"line\"></div>\r\n" + 
								"                <p>From ").append(c.getCost()).append(" Rs</p>\r\n" + 
								"                <h4>").append(c.getCrop_name().toUpperCase()).append("</h4>\r\n" + 
										"            </div>\r\n" + 
										"        </a>\r\n" + 
										"    </div>");
			}
			
		}

		manageFarmer.closeSessionFactoryConfig();
		crop.closeSessionFactoryConfig();
		
		return str;
	}

}
