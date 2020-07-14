package Esp8266.Wifi;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Esp8266.Wifi.cropavailable.FarmerCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerAvailableCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;
import Esp8266.Wifi.farmer.ManageFarmer;

public class TestingClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		getCropId(3);
		
		
		
		
		
		
//		String str ="11,77.5783310,13.1292610,58,100,\r\n" + 
//				"PH:8.56, W: 0, L: 142, T:  38,--12,77.5783310,13.1292610,58,100,\r\n" + 
//				"PH:8.56, W: 0, L: 142, T:  38,\r\n" + 
//				"PH:8.71, W: 0, L: 142, T:  38\r\n" + 
//				"";
		/*String id;
		float latitude,longitude,accurecy,moisture,ph,temp,rainfall;
		
		
		String[] wsnData = str.split("--");
		
		for(String s : wsnData) {
			String[] sensorData = s.split(",");
			System.out.println("input"+ s);
			id = sensorData[0];
			latitude = Float.parseFloat(sensorData[1]);
			longitude = Float.parseFloat(sensorData[2]);
			accurecy = Float.parseFloat(sensorData[3]);
			moisture = Float.parseFloat(sensorData[4]);
			ph = Float.parseFloat(sensorData[5].substring(5));
			temp = Float.parseFloat(sensorData[8].substring(5));
			rainfall = new JsonParser().getAverageRainfall(new Date());
			System.out.println("id"+id +"lat"+ latitude+"long"+longitude+"accurecy"+accurecy+"moisture"+moisture
					+"ph"+ph+"temp"+temp+"rainfall"+rainfall);
			float location[] = {latitude,longitude,accurecy,ph,moisture,temp,rainfall};
			continue;
		}*/

	}
	public static Map<Integer, Integer> getCropId(int iteration) {
		int farmer_crop_size = 0;
		ManageFarmerAvailableCrop manageFarmerAvailableCrop = new ManageFarmerAvailableCrop();
		manageFarmerAvailableCrop.sessionFactoryConfig();
		
		Map<Integer,Integer> farmerCropMap = new HashMap<Integer,Integer>();
		
		//List<Integer> cropdata = new ArrayList<Integer>();
		
		ManageFarmer manageFarmer = new ManageFarmer();
		manageFarmer.sessionFactoryConfig();
		for(int i = 1;i <=manageFarmer.getFarmerCount().intValue();i++) {
			
			List<Integer> farmercrop = manageFarmerAvailableCrop.getCrop(i);
			farmer_crop_size = farmercrop.size();
			System.out.println(i+" "+farmer_crop_size+"size");
			if(iteration < farmer_crop_size) {
				farmerCropMap.put(i, farmercrop.get(iteration));
			}
			else {
				//add else
				FarmerCrop farmerCrop = new FarmerCrop();
				ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
				manageFarmerCrop.sessionFactoryConfig();
				farmerCrop = manageFarmerCrop.getCropQuailtyRank(i, iteration);
				farmerCropMap.put(i, farmerCrop.getCrop_id());
			}
			}
		return farmerCropMap;
//		Set< Map.Entry< Integer,Integer> > st = farmerCropMap.entrySet();
//		for(Map.Entry<Integer, Integer> ma: st ) {
//			System.out.println(ma.getKey()+" "+ma.getValue());
//		}
		}
	}

