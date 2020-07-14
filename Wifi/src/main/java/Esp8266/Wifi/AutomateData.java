package Esp8266.Wifi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Esp8266.Wifi.cropavailable.FarmerCrop;
import Esp8266.Wifi.cropavailable.ManageCropAvailable;
import Esp8266.Wifi.cropavailable.ManageFarmerAvailableCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;
import Esp8266.Wifi.farmer.ManageFarmer;

public class AutomateData {

	public void automateCropAcceptance() {
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		ManageIteration manageIteration = new ManageIteration();
		manageIteration.sessionFactoryConfig();
		ManageFarmer manageFarmer = new ManageFarmer();
		manageFarmer.sessionFactoryConfig();
		ManageFarmerAvailableCrop manageFarmerAvailableCrop = new ManageFarmerAvailableCrop();
		manageFarmerAvailableCrop.sessionFactoryConfig();
		ManageCropAvailable manageCropAvailable = new ManageCropAvailable();
		manageCropAvailable.sessionFactoryConfig();
		FarmerCrop farmerCrop = new FarmerCrop();
		
		int currentIteration = manageIteration.getIterationValue();
		manageFarmerCrop.deleteByIteration();
		farmerCrop.setDate(new Date());
		farmerCrop.setIteration(currentIteration);
		int farmerCount = manageFarmer.getFarmerCount().intValue();
		System.out.println(farmerCount);
		Map<Integer,Integer> cropids = generateCropId(currentIteration);
		Set< Map.Entry< Integer,Integer> > st = cropids.entrySet();
		for(Map.Entry<Integer, Integer> ma: st ) {
			farmerCrop.setFarmer_id(ma.getKey());
			farmerCrop.setCrop_id(ma.getValue());
			farmerCrop.setQuality(generateRank(ma.getKey()));
			manageFarmerCrop.addFarmerCrop(farmerCrop);
			System.out.println(ma.getKey()+"val"+ma.getValue());
		}
		
		manageCropAvailable.updateAvailablity();
		manageFarmerCrop.closeSessionFactoryConfig();
		manageIteration.closeSessionFactoryConfig();
		manageFarmer.closeSessionFactoryConfig();
		manageFarmerAvailableCrop.closeSessionFactoryConfig();
		manageCropAvailable.closeSessionFactoryConfig();
	}
	public int generateRank(int farmerId) {
		Random rand = new Random();
		if(farmerId <= 3) {
			return (int) ( 7 + rand.nextFloat()* (11 - 7));
		}
		if(farmerId <= 6) {
			return (int) ( 5 + rand.nextFloat()* (8 - 5));
		}
		else {
			return (int) ( 1 + rand.nextFloat()* (5 - 1));
		}
		
	}
	
	public Map<Integer, Integer> generateCropId(int iteration) {
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
				if(farmerCrop != null) {
					farmerCropMap.put(i, farmerCrop.getCrop_id());
				}
				manageFarmerCrop.closeSessionFactoryConfig();
			}
			}
		Set< Map.Entry< Integer,Integer> > st = farmerCropMap.entrySet();
		for(Map.Entry<Integer, Integer> ma: st ) {
			System.out.println(ma.getKey()+" "+ma.getValue());
		}
		manageFarmerAvailableCrop.closeSessionFactoryConfig();
		manageFarmer.closeSessionFactoryConfig();
		
		return farmerCropMap;
		}
	
	
	
//	public Map<Integer,Integer> generateCropId(){
//		boolean set = false;
//		int crop_id = 0;
//		ManageFarmerAvailableCrop m = new ManageFarmerAvailableCrop();
//		m.sessionFactoryConfig();
//		Map<Integer,Integer> farmerCrop = new HashMap<Integer,Integer>();
//		
//		List<Integer> cropdata = new ArrayList<Integer>();
//		ManageFarmer manageFarmer = new ManageFarmer();
//		manageFarmer.sessionFactoryConfig();
//		for(int i = 1;i <=manageFarmer.getFarmerCount().intValue();i++) {
//			crop_id = 0;
//			set = false;
//			List<Integer> farmercrop = m.getCrop(i);
//			System.out.println(i+" "+farmercrop.size()+"size");
//			for(int j = 0; j < farmercrop.size();j++) {
//				if(!cropdata.contains(farmercrop.get(j))) {
//					crop_id = farmercrop.get(j);
//					cropdata.add(farmercrop.get(j));
//					set = true;
//					break;
//				}
//				else continue;
//			}
//			if(!set && !farmercrop.isEmpty()) {
//				cropdata.add(farmercrop.get(farmercrop.size() - 1));
//				crop_id = farmercrop.get(farmercrop.size() - 1);
//			}
//			if(crop_id != 0) {
//				farmerCrop.put(i, crop_id);
//			}
//		}
//		
//		return farmerCrop;
//	}
	
	
}
