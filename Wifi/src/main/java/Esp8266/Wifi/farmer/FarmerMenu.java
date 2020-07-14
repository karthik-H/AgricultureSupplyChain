package Esp8266.Wifi.farmer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.AutomateData;
import Esp8266.Wifi.ManageIteration;
import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.cropavailable.FarmerCrop;
import Esp8266.Wifi.cropavailable.FarmerRecommendation;
import Esp8266.Wifi.cropavailable.ManageFarmerAvailableCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerRecommendation;

/**
 * Servlet implementation class FarmerMenu
 */
public class FarmerMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FarmerMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(false);
		String farmerName = httpSession.getAttribute("username").toString();
		ManageFarmer manageFarmer = new ManageFarmer();
		manageFarmer.sessionFactoryConfig();
		int farmer_id = manageFarmer.getFarmerId(farmerName);
		String option = request.getParameter("option");
		int crop_id = Integer.parseInt(request.getParameter("cropid"));
	
		if(option.equals("accept")) {
			ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
			manageFarmerCrop.sessionFactoryConfig();
			ManageCrop manageCrop = new ManageCrop();
			manageCrop.sessionFactoryConfig();
			ManageIteration manageIteration = new ManageIteration();
			manageIteration.sessionFactoryConfig();
			AutomateData automate = new AutomateData();
			FarmerCrop farmerCrop = new FarmerCrop();
			farmerCrop.setCrop_id(crop_id);
			farmerCrop.setFarmer_id(farmer_id);
			farmerCrop.setDate(new Date());
			farmerCrop.setIteration(manageIteration.getIterationValue());
			farmerCrop.setQuality(automate.generateRank(farmer_id));
			int cropAmount = manageCrop.getCropStatus(crop_id);
			cropAmount--;
			manageCrop.updateCropStatus(crop_id, cropAmount);
			manageFarmerCrop.addFarmerCrop(farmerCrop);
			response.sendRedirect("farmer_menu.jsp");
		}
		if(option.equals("recommend")) {
			int primary_crop_id = Integer.parseInt(httpSession.getAttribute("primaryCropId").toString());
			ManageFarmerRecommendation manageFarmerRecommendation = new ManageFarmerRecommendation();
			manageFarmerRecommendation.sessionFactoryConfig();
			
			if(manageFarmerRecommendation.getFarmerRecommendation(farmer_id) == null) {
			FarmerRecommendation farmerRecommendation = new FarmerRecommendation();
			farmerRecommendation.setFarmer_id(farmer_id);
			farmerRecommendation.setRecommend_crop_id(crop_id);
			farmerRecommendation.setPrimary_crop_id(primary_crop_id);
			manageFarmerRecommendation.addFarmerRecommendation(farmerRecommendation);
			response.sendRedirect("farmer_menu.jsp");
			}
			else {
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('For now only one recommendation per farmer we will soon increase it');");
				   out.println("location='farmer_menu.jsp';");
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
	public StringBuilder populateCropData(String farmername, HttpSession session) {
		StringBuilder str = new StringBuilder();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		if(manageFarmerCrop.checkFarmer(farmername)) {
			manageFarmerCrop.closeSessionFactoryConfig();
			str.append("<h1> You already selected one crop (one crop per farmer)</h1>")
	  		.append("<br><br><h1>Thank You</h1>")
	  		.append("<h1>openagr</h1>");
	  		return str;
		}
		int i = 0;
		ManageFarmerAvailableCrop manageAvailableCrop = new ManageFarmerAvailableCrop();
	  	manageAvailableCrop.sessionFactoryConfig();
	  	ManageFarmer manageFarmer = new ManageFarmer();
	  	manageFarmer.sessionFactoryConfig();
	  	ManageCrop manageCrop = new ManageCrop();
	  	manageCrop.sessionFactoryConfig();
	  	ManageFarmerRecommendation manageFarmerRecommendation = new ManageFarmerRecommendation();
	  	manageFarmerRecommendation.sessionFactoryConfig();
	  	int farmer_id = manageFarmer.getFarmerId(farmername);
	  	List<Integer> cropNames = manageAvailableCrop.getCropNames(farmer_id);
	  	if(cropNames.isEmpty()) {
	  		manageAvailableCrop.closeSessionFactoryConfig();
	  		manageFarmer.closeSessionFactoryConfig();
	  		manageCrop.closeSessionFactoryConfig();
	  		manageFarmerRecommendation.closeSessionFactoryConfig();
	  		str.append("<h1> Sorry no crop available right now for more info "
	  				+ "try contacting admin</h1>")
	  		.append("<br><br><h1>Thank You</h1>")
	  		.append("<h1>openagr</h1>");
	  		return str;
	  	}
	  	Iterator<Integer> cropitr = cropNames.iterator();
	  	while(cropitr.hasNext()) {
	  		int val = cropitr.next();
	  		String cropName = manageCrop.getCropName(val);
	  		int cropStatus = manageCrop.getCropStatus(val);
	  		if(cropStatus != 0) {
	  			
	  			String url1 = "/Wifi/FarmerMenu?option=accept&cropid="+val;
	  			String url2 = "/Wifi/FarmerMenu?option=recommend&cropid="+val;
		  		if(i == 0) {
		  			session.setAttribute("primaryCropId", val);
			  		str.append("    <div class=\"w3-third w3-container w3-margin-bottom\">\r\n" + 
			  				"     <a href=\"AboutCrops?cropname="+cropName.toLowerCase()+"\"> <img src=\"image/"
			  				+cropName.toLowerCase()+".jpg\" style=\"width:100%\" class=\"w3-hover-opacity\"></a>\r\n" + 
			  				"      <div class=\"w3-container w3-white\">\r\n" + 
			  				"        <p><b>"+cropName.toUpperCase()+"</b></p>\r\n" + 
			  				"        <p>Availablity : "+cropStatus+"</p>\r\n" + 
			  				"	        <div class=\"w3-section\">\r\n" + 
			  				"<a class=\"w3-button w3-green\" href="+url1+">Accept</a>\r\n" +  
			  				"      </div>\r\n" + 
			  				"		</div>\r\n" + 
			  				"    </div>");
		  		}
		  		else if(i != 0 && manageFarmerRecommendation.checkRecommendation(farmer_id, val)) {
			  		str.append("    <div class=\"w3-third w3-container w3-margin-bottom\">\r\n" +"     <a href=\"AboutCrops?cropname="
		  		+ cropName.toLowerCase()+
		  		"\">       <img src=\"image/"+cropName.toLowerCase()+".jpg\" style=\"width:100%\" class=\"w3-hover-opacity\">\r\n" + 
			  				"      <div class=\"w3-container w3-white\">\r\n" + 
			  				"        <p><b>"+cropName.toUpperCase()+"</b></p>\r\n" + 
			  				"        <p>Availablity : "+cropStatus+"</p>\r\n" + 
			  				"	        <div class=\"w3-section\">\r\n" +  
			  				"<p>Recommended</p>\r\n" +  
			  				"      </div>\r\n" + 
			  				"		</div>\r\n" + 
			  				"    </div>");
		  		}
		  		else {
			  		str.append("    <div class=\"w3-third w3-container w3-margin-bottom\">\r\n" + 
			  				"      <img src=\"image/"+cropName.toLowerCase()+".jpg\" style=\"width:100%\" class=\"w3-hover-opacity\">\r\n" + 
			  				"      <div class=\"w3-container w3-white\">\r\n" + 
			  				"        <p><b>"+cropName.toUpperCase()+"</b></p>\r\n" + 
			  				"        <p>Availablity : "+cropStatus+"</p>\r\n" + 
			  				"	        <div class=\"w3-section\">\r\n" +  
			  				"<a class=\"w3-button w3-green\" href="+url2+">Recommend Me</a>\r\n" +  
			  				"      </div>\r\n" + 
			  				"		</div>\r\n" + 
			  				"    </div>");
		  		}
		  		i++;
	  			
	  		}

	  	}
	  	manageAvailableCrop.closeSessionFactoryConfig();
  		manageFarmer.closeSessionFactoryConfig();
  		manageCrop.closeSessionFactoryConfig();
  		manageFarmerRecommendation.closeSessionFactoryConfig();
	  	return str;
	  	
	}
	public StringBuilder populateCrop(int farmer_id) {
		StringBuilder str = new StringBuilder();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		List<FarmerCrop> farmerCrops = manageFarmerCrop.getFarmerCrop(farmer_id);
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		if(farmerCrops == null) {
			manageFarmerCrop.closeSessionFactoryConfig();
			manageCrop.closeSessionFactoryConfig();
			System.out.println("null");
		}
		else {
			for(FarmerCrop f : farmerCrops) {
				str.append("<div class=\"w3-container\">\r\n" + 
						"          <h5 class=\"w3-opacity\"><b></b>"+manageCrop.getCropName(f.getCrop_id())
						.toUpperCase()+"</h5>\r\n" + 
						"          <h6 class=\"w3-text-teal\"><i class=\"fa fa-calendar fa-fw w3-margin-"
						+ "right\"></i>"+f.getDate()+"</h6>\r\n" + 
						"          <hr>\r\n" + 
						"        </div>");
			}
		}
		manageFarmerCrop.closeSessionFactoryConfig();
		manageCrop.closeSessionFactoryConfig();
		return str;
	}
}
