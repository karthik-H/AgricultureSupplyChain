package Esp8266.Wifi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import Esp8266.Wifi.cropavailable.FarmerCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;

public class Graph {

	public String populatePhgraphdata(int farmer_id) {
		Gson gsonObj = new Gson();
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		List<FarmerCrop> farmerCrop = manageFarmerCrop.getFarmerCrop(farmer_id);
		for(FarmerCrop f : farmerCrop) {
			map = new HashMap<Object,Object>(); 
			map.put("label", getPhvalue(f.getCrop_id()));
			map.put("y", f.getQuality()); 
			list.add(map);
			System.out.println(f.getCrop_id()+" "+f.getQuality());
		}
		String dataPoints = gsonObj.toJson(list);
		manageFarmerCrop.closeSessionFactoryConfig();
		return dataPoints;
	}
	public String populateMoisturegraphdata(int farmer_id) {
		Gson gsonObj = new Gson();
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		List<FarmerCrop> farmerCrop = manageFarmerCrop.getFarmerCrop(farmer_id);
		for(FarmerCrop f : farmerCrop) {
			map = new HashMap<Object,Object>(); 
			map.put("label", getMoisturevalue(f.getCrop_id()));
			map.put("y", f.getQuality()); 
			list.add(map);
			System.out.println(f.getCrop_id()+" "+f.getQuality());
		}
		String dataPoints = gsonObj.toJson(list);
		manageFarmerCrop.closeSessionFactoryConfig();
		return dataPoints;
	}
	public String populateTempgraphdata(int farmer_id) {
		Gson gsonObj = new Gson();
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		List<FarmerCrop> farmerCrop = manageFarmerCrop.getFarmerCrop(farmer_id);
		for(FarmerCrop f : farmerCrop) {
			map = new HashMap<Object,Object>(); 
			map.put("label", getTempvalue(f.getCrop_id()));
			map.put("y", f.getQuality()); 
			list.add(map);
			System.out.println(f.getCrop_id()+" "+f.getQuality());
		}
		String dataPoints = gsonObj.toJson(list);
		manageFarmerCrop.closeSessionFactoryConfig();
		return dataPoints;
	}
	public String populateRaingraphdata(int farmer_id) {
		Gson gsonObj = new Gson();
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		List<FarmerCrop> farmerCrop = manageFarmerCrop.getFarmerCrop(farmer_id);
		for(FarmerCrop f : farmerCrop) {
			map = new HashMap<Object,Object>(); 
			map.put("label", getRainvalue(f.getCrop_id()));
			map.put("y", f.getQuality()); 
			list.add(map);
			System.out.println(f.getCrop_id()+" "+f.getQuality());
		}
		String dataPoints = gsonObj.toJson(list);
		manageFarmerCrop.closeSessionFactoryConfig();
		return dataPoints;
	}
	
	
	
	//sensor data
	public float getPhvalue(int cropid) {
		float result = (float) 0;
		try {
			
			int line_number = 1;
			String file = CBR.class.getResource("crop_data").getPath();
			BufferedReader buf = new BufferedReader(new FileReader(file));
			String line;
			while((line = buf.readLine()) != null) {
				if(line_number == cropid) {
					String[] words = line.split(",");
					float ph = Float.parseFloat(words[1])+Float.parseFloat(words[2]); 
					result =  ph / 2f;
					return result;
				}
				
				line_number++;
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public float getMoisturevalue(int cropid) {
		float result = (float) 0;
		try {
			
			int line_number = 1;
			String file = CBR.class.getResource("crop_data").getPath();
			BufferedReader buf = new BufferedReader(new FileReader(file));
			String line;
			while((line = buf.readLine()) != null) {
				if(line_number == cropid) {
					String[] words = line.split(",");
					float ph = Float.parseFloat(words[3])+Float.parseFloat(words[4]); 
					result =  ph / 2f;
					return result;
				}
				
				line_number++;
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public float getTempvalue(int cropid) {
		float result = (float) 0;
		try {
			
			int line_number = 1;
			String file = CBR.class.getResource("crop_data").getPath();
			BufferedReader buf = new BufferedReader(new FileReader(file));
			String line;
			while((line = buf.readLine()) != null) {
				if(line_number == cropid) {
					String[] words = line.split(",");
					float ph = Float.parseFloat(words[5])+Float.parseFloat(words[6]); 
					result =  ph / 2f;
					return result;
				}
				
				line_number++;
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public float getRainvalue(int cropid) {
		float result = (float) 0;
		try {
			
			int line_number = 1;
			String file = CBR.class.getResource("crop_data").getPath();
			BufferedReader buf = new BufferedReader(new FileReader(file));
			String line;
			while((line = buf.readLine()) != null) {
				if(line_number == cropid) {
					String[] words = line.split(",");
					float ph = Float.parseFloat(words[7])+Float.parseFloat(words[8]); 
					result =  ph / 2f;
					return result;
				}
				
				line_number++;
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
