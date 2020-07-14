package Esp8266.Wifi.buyer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.Location;
import Esp8266.Wifi.ManageLocation;
import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.cropavailable.ManageCropAvailable;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerWsn;

/**
 * Servlet implementation class BuyerMenu
 */
public class BuyerMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubManageBuyerCrop manageBuyerCrop = new ManageBuyerCrop();
		HttpSession session = request.getSession();
		String user = session.getAttribute("username").toString();
		ManageBuyer manageBuyer = new ManageBuyer();
		manageBuyer.sessionFactoryConfig();
		ManageBuyerCrop manageBuyerCrop = new ManageBuyerCrop();
		manageBuyerCrop.sessionFactoryConfig();
		BuyerCrop buyerCrop = new BuyerCrop();
		buyerCrop.setBuyer_id(manageBuyer.getBuyerId(user));
		for(String s : request.getParameterValues("field")) {
			buyerCrop.setCrop_id(Integer.parseInt(s));
			manageBuyerCrop.addBuyerCrop(buyerCrop);
			//System.out.println(s);
		}
		manageBuyer.closeSessionFactoryConfig();
		manageBuyerCrop.closeSessionFactoryConfig();
		response.sendRedirect("buyer_setting.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
public String populateLocation(String buyerName) {
		
		String str = "",cropdetails ="";
		ManageFarmerWsn manageFarmerWsn = new ManageFarmerWsn();
		manageFarmerWsn.sessionFactoryConfig();
		ManageLocation manageLocation = new ManageLocation();
		manageLocation.sessionFactoryConfig();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		ManageCropAvailable manageCropAvailable = new ManageCropAvailable();
		manageCropAvailable.sessionFactoryConfig();
		ManageBuyer m = new ManageBuyer();
		m.sessionFactoryConfig();
		int buyerId = m.getBuyerId(buyerName);
		ManageBuyerCrop mb = new ManageBuyerCrop();
		mb.sessionFactoryConfig();
		for(int cropid : mb.getBuyerCropId(buyerId)) {
			cropdetails = "'cropname ="+manageCrop.getCropName(cropid)+" | quantity ="
			+ manageCropAvailable.getAvailablity(cropid)+"'";
			List<Integer> farmersId = manageFarmerCrop.getFarmers(cropid);
			
			for(int i : farmersId) {
				List<Integer> wsnId = manageFarmerWsn.getFarmerWsnId(i);
				System.out.println("farmerid"+i);
				for(int j : wsnId) {
					System.out.println("wsnid"+j);
					List<Location> location = manageLocation.getLocationId(j);
					for(Location l : location) {
						str +=cropdetails+","+ l.getLatitude() +","+l.getLongitude()+"--";
						System.out.println(str);
					}
				}
				
			}
		}
		manageFarmerWsn.closeSessionFactoryConfig();
		manageLocation.closeSessionFactoryConfig();
		manageFarmerCrop.closeSessionFactoryConfig();
		manageCrop.closeSessionFactoryConfig();
		manageCropAvailable.closeSessionFactoryConfig();
		m.closeSessionFactoryConfig();
		mb.closeSessionFactoryConfig();
		
		return str;
		//document.getElementById("location").value
	}

	public String populateCropDetail(String buyerName) {
		String str = "";
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		ManageBuyer m = new ManageBuyer();
		m.sessionFactoryConfig();
		int buyerId = m.getBuyerId(buyerName);
		ManageBuyerCrop mb = new ManageBuyerCrop();
		mb.sessionFactoryConfig();
		ManageFarmerCrop manageFarmer = new ManageFarmerCrop();
		manageFarmer.sessionFactoryConfig();
		ManageCropAvailable manageCropAvailable = new ManageCropAvailable();
		manageCropAvailable.sessionFactoryConfig();
		for(int cropid : mb.getBuyerCropId(buyerId)) {
			if(manageFarmer.checkCrop(cropid)) {
				str+="<tr>\r\n" + 
						"            <td><i class=\"fa fa-bookmark w3-text-blue w3-large\"></i></td>\r\n" + 
						"            <td>"+manageCrop.getCropName(cropid)+"</td>\r\n" + 
						"            <td><i>"+manageCropAvailable.getAvailablity(cropid) +"Kg</i></td>\r\n" + 
						"          </tr>";
			}
		}
		manageCrop.closeSessionFactoryConfig();
		m.closeSessionFactoryConfig();
		mb.closeSessionFactoryConfig();
		manageFarmer.closeSessionFactoryConfig();
		manageCropAvailable.closeSessionFactoryConfig();
		return str;
	}
	
	public String populateRemainingCrop(String username) {
		String str = "";
		ManageBuyer manageBuyer = new ManageBuyer();
		manageBuyer.sessionFactoryConfig();
		int id = manageBuyer.getBuyerId(username);
		ManageBuyerCrop m = new ManageBuyerCrop();
		m.sessionFactoryConfig();
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		for(int cropId : m.getBuyerRemainingCrop(id)) {
			str +="<input id=\"option\" type=\"checkbox\" name=\"field\" value="+cropId+">\r\n" + 
					"  <label for="+cropId+"><span><span></span></span>"+manageCrop.getCropName(cropId)+"</label>";
		}
		manageBuyer.closeSessionFactoryConfig();
		m.closeSessionFactoryConfig();
		manageCrop.closeSessionFactoryConfig();
		return str;
	}

}
