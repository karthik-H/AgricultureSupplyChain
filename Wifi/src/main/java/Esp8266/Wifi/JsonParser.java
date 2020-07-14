package Esp8266.Wifi;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVReader;

public class JsonParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		 Date now = new Date();
	         // two digit numerical represenation
	        System.out.println(getAverageRainfall(new Date()));
	}
	
	public static float getAverageRainfall(Date now) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
		int month = Integer.parseInt(simpleDateformat.format(now)) + 1;
        float totalAvg = 0;
        int i = 0;
		try { 
			  
	        // Create an object of filereader 
	        // class with CSV file as a parameter. 
			String file = JsonParser.class.getResource("rainfall_data_southkarnataka.csv").getPath();
	        FileReader filereader = new FileReader(file); 
	  
	        // create csvReader object passing 
	        // file reader as a parameter 
	        CSVReader csvReader = new CSVReader(filereader); 
	        String[] nextRecord; 
	        
	        // we are going to read data line by line 
	        csvReader.readNext();
	        while ((nextRecord = csvReader.readNext()) != null) { 
	        	totalAvg += Float.parseFloat(nextRecord[month]);
	        	i++;
	       } 
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    }
		
		return totalAvg/i;
	}

}
