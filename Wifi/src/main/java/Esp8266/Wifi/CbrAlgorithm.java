package Esp8266.Wifi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.cropavailable.FarmerAvailableCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerAvailableCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerWsn;

/**
 * Servlet implementation class CbrAlgorithm
 */
public class CbrAlgorithm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CbrAlgorithm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	ManageSensorData sensordata = new ManageSensorData();
		sensordata.sessionFactoryConfig();
		//CBR cbr = new CBR();
		ArrayList<String> prediction = new ArrayList<String>();
		List<Location> data = sensordata.getData();
		Iterator<Location> value = data.iterator();
		while(value.hasNext()) {
			Location l = value.next();
			String result = CBR.getCrop(l.getSoil_ph(), l.getMoisture(), l.getTemperature(),l.getAvg_rainfall());
			prediction.add(result);
			result ="";
		}
		request.setAttribute("sensordata", data);
		request.setAttribute("prediction", prediction);
		request.getRequestDispatcher("algorithm.jsp").forward(request, response);*/
		response.sendRedirect("algorithm.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public StringBuilder populateAlgoTable() throws IOException {
		ManageSensorData sensordata = new ManageSensorData();
		sensordata.sessionFactoryConfig();
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		ManageFarmerWsn manageFarmerWsn = new ManageFarmerWsn();
		manageFarmerWsn.sessionFactoryConfig();
		ManageFarmerAvailableCrop manageFarmerAvailableCrop = new ManageFarmerAvailableCrop();
		manageFarmerAvailableCrop.sessionFactoryConfig();
		int count = 0;
		StringBuilder Str = new StringBuilder();
		String link = "";
		//CBR cbr = new CBR();
		ArrayList<String> prediction = new ArrayList<String>();
		List<Location> data = sensordata.getData();
		Iterator<Location> value = data.iterator();
		FarmerAvailableCrop farmerAvailableCrop = new FarmerAvailableCrop();
		manageFarmerAvailableCrop.truncateCrop();
		int wsn_id = 0;
		while(value.hasNext()) {
			Set<String> cropset = new LinkedHashSet<String>(); 
			String crop = "";
			Location result = value.next();
			wsn_id = result.getId();
			System.out.println("for wsn Id"+ wsn_id);
			farmerAvailableCrop.setWsn_id(wsn_id);
			Str.append("<tr><td>").append(result.getId()).append("</td>")
			.append("<td>").append(result.getLatitude()).append("</td>")
			.append("<td>").append(result.getLongitude()).append("</td>")
			.append("<td>").append(result.getAccurecy()).append("</td>")
			.append("<td>").append(result.getSoil_ph()).append("</td>")
			.append("<td>").append(result.getMoisture()).append("</td>")
			.append("<td>").append(result.getTemperature()).append("</td>")
			.append("<td>").append(result.getAvg_rainfall()).append("</td>");
			for(int i = 0; i < 3;i++) {
				crop = CBR.getCrop(result.getSoil_ph(), result.getMoisture(), result.getTemperature(),result.getAvg_rainfall(),cropset);
				
				cropset.add(crop);
				if(crop.equals("no data")){
					link = "#";
				}
				else {link = "http://localhost:8080/Wifi/AboutCrops?cropname="+crop;
				farmerAvailableCrop.setCrop_id(manageCrop.getCropid(crop));
				farmerAvailableCrop.setFarmer_id(manageFarmerWsn.getFarmerId(wsn_id));
				manageFarmerAvailableCrop.addFarmerCrop(farmerAvailableCrop);
				}
				Str.append("<td><a href="+link+" target='_blank'>").append(crop).append("</a></td>");
				System.out.println(crop);
			}
			}
		sensordata.closeSessionFactoryConfig();
		manageCrop.closeSessionFactoryConfig();
		manageFarmerWsn.closeSessionFactoryConfig();
		manageFarmerAvailableCrop.closeSessionFactoryConfig();
	return Str;

		}
	}

