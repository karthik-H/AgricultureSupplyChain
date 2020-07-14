package Esp8266.Wifi;

import java.util.Date;
import java.util.Random;

import Esp8266.Wifi.farmer.ManageFarmer;

public class Service {
	
	public void addLoc(String msgid) {
		System.out.println("before manage");
	ManageLocation managelocation = new ManageLocation();
	System.out.println("after	 manage");
	System.out.println(msgid);
		float location[] = new float[7];
		String prelocation[] = msgid.split("--");
		for(int j = 0;j < prelocation.length;j++) {
			//ManageLocation managelocation = new ManageLocation();
			String[] temp = prelocation[j].split(" ");
			for(int i = 0; i < temp.length;i++) {
				location[i] = Float.parseFloat(temp[i]);
			}
			for(int i = 0; i < location.length;i++) {
				System.out.println(location[i]);
			}
			managelocation.sessionFactoryConfig();
			managelocation.addLocation(location);
			managelocation.closeSessionFactoryConfig();
			
		}	
		
	}

	public void addSingleRandomLocation() {
		ManageLocation managelocation = new ManageLocation();
		float value[] = new float[8];
		value[0] =getRandomValue(13,14);
		value[1] = getRandomValue(77,78);
		value[2] = getRandomValue(10,30);
		value[3] = getRandomValue(5,8);
		value[4] = getRandomValue(50,90);
		value[5] = getRandomValue(29,30);
		value[6] = getRandomValue(30,300);
		managelocation.sessionFactoryConfig();
		managelocation.addLocation(value);
		managelocation.closeSessionFactoryConfig();
	}
	
	public void updateLoc(String msgid) {
		
		ManageLocation managelocation = new ManageLocation();
		int id;
		float latitude,longitude,accurecy,moisture,ph,temp,rainfall;
		managelocation.sessionFactoryConfig();
		String[] wsnData = msgid.split("--");
		
		for(String s : wsnData) {
			String[] sensorData = s.split(",");
			System.out.println("input"+ s);
			id = Integer.parseInt(sensorData[0]);
			latitude = Float.parseFloat(sensorData[1]);
			longitude = Float.parseFloat(sensorData[2]);
			accurecy = Float.parseFloat(sensorData[3]);
			moisture = Float.parseFloat(sensorData[4]);
			ph = Float.parseFloat(sensorData[5].substring(5));
			temp = Float.parseFloat(sensorData[8].substring(5));
			rainfall = new JsonParser().getAverageRainfall(new Date());
			System.out.println("id"+id +"lat"+ latitude+"long"+longitude+"accurecy"+accurecy+"moisture"+moisture
					+"ph"+ph+"temp"+temp+"rainfall"+rainfall);
			float location[] = {longitude,latitude,accurecy,ph,moisture,temp,rainfall};
			
			managelocation.updateLocation(id, location);
			
			continue;
		}
		/*String prelocation[] = msgid.split("--");
		for(int j = 0;j < prelocation.length;j++) {
			String[] temp = prelocation[j].split(" ");
			id = Integer.parseInt(temp[0]);
			for(int i = 0; i < temp.length - 1;i++) {
				location[i] = Float.parseFloat(temp[i + 1]);
			}*/
			
		managelocation.closeSessionFactoryConfig();
		updateAllRandom();
	}
	public void updatePh(String msgid) {
		ManageLocation managelocation = new ManageLocation();
		managelocation.sessionFactoryConfig();
		System.out.println(msgid);
		int id;
		float ph;
		String preval[] = msgid.split("--");
		for(String s : preval) {
			String[] sensorData = s.split(",");
			id = Integer.parseInt(sensorData[0]);
			ph = Float.parseFloat(sensorData[1].substring(5));
			managelocation.updatePh(id, ph);
			
		}	
		managelocation.closeSessionFactoryConfig();
	}
	
	public void updateMoisture(String msgid) {
		ManageLocation managelocation = new ManageLocation();
		int id;
		String preval[] = msgid.split("--");
		for(int j = 0;j < preval.length;j++) {
			String[] temp = preval[j].split(",");
			id = Integer.parseInt(temp[0]);
			float val = Float.parseFloat(temp[1]);
			managelocation.sessionFactoryConfig();
			managelocation.updateMoisture(id, val);
			
			
		
		}	
		managelocation.closeSessionFactoryConfig();
	}
	
	public void updateTemperature(String msgid) {
		ManageLocation managelocation = new ManageLocation();
		int id;
		String preval[] = msgid.split("--");
		for(int j = 0;j < preval.length;j++) {
			String[] temp = preval[j].split(" ");
			id = Integer.parseInt(temp[0]);
			float val = Float.parseFloat(temp[1]);
			managelocation.sessionFactoryConfig();
			managelocation.updateTemperature(id, val);
			
		}	
		managelocation.closeSessionFactoryConfig();
	}
	
	public void updateAvg_Rainfall(String msgid) {
		ManageLocation managelocation = new ManageLocation();
		
		int id;
		String preval[] = msgid.split("--");
		for(int j = 0;j < preval.length;j++) {
			String[] temp = preval[j].split(" ");
			id = Integer.parseInt(temp[0]);
			float val = Float.parseFloat(temp[1]);
			managelocation.sessionFactoryConfig();
			managelocation.updateAvg_Rainfall(id, val);
			
		
		}
		managelocation.closeSessionFactoryConfig();
		
	}
	public void updateAllRandom() {
		ManageLocation managelocation = new ManageLocation();
		float value[] = new float[8];
		ManageFarmer manageFarmer = new ManageFarmer();
		manageFarmer.sessionFactoryConfig();
		for(int i = 3;i <= manageFarmer.getFarmerCount().intValue();i++) {
		
			value[0] =getRandomValue(13,14);
			value[1] = getRandomValue(77,78);
			value[2] = getRandomValue(10,30);
			value[3] = getRandomValue(5,8);
			value[4] = getRandomValue(50,90);
			value[5] = getRandomValue(29,30);
			value[6] = getRandomValue(30,300);
			managelocation.sessionFactoryConfig();
			managelocation.updateLocation(i+10, value);
			System.out.println("updated data for "+i);
			
		}
		managelocation.closeSessionFactoryConfig();
		manageFarmer.closeSessionFactoryConfig();
	}
	public float getRandomValue(int min, int max) {
		Random rand = new Random();
		return min + rand.nextFloat()* (max - min); 
	}
	public void addAllRandom() {
		System.out.println("before manage");
	ManageLocation managelocation = new ManageLocation();
	System.out.println("after	 manage");
	float value[] = new float[8];
	
	for(int in = 1;in <= 10 ;in++) {
		value[0] =getRandomValue(13,14);
		value[1] = getRandomValue(77,78);
		value[2] = getRandomValue(10,30);
		value[3] = getRandomValue(5,8);
		value[4] = getRandomValue(50,90);
		value[5] = getRandomValue(29,30);
		value[6] = getRandomValue(30,300);
			managelocation.sessionFactoryConfig();
			managelocation.addLocation(value);
			
		}
	managelocation.closeSessionFactoryConfig();
	}
}
