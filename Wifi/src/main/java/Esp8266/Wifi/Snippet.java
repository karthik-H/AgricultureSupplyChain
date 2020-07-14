package Esp8266.Wifi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.opencsv.CSVWriter;

import Esp8266.Wifi.crop.ManageCrop;

public class Snippet {
	
	public static void main(String s[]) throws IOException {
		String line;
		File file1 = new File("farmer_data.csv");
		if(!file1.exists()) {
			file1.createNewFile();
		}
		ManageCrop m = new ManageCrop();
		m.sessionFactoryConfig();
		
		System.out.println(file1.getAbsolutePath());
		FileWriter fileWriter = new FileWriter(file1);
		Integer i = 1;
		CSVWriter csv = new CSVWriter(fileWriter);
		String[] header = {"moisture","soil_ph","temperature","avg_rainfall","quality"};
		csv.writeNext(header);
		String[] header1 = {"7.5","65","37","150"};
		String[] header2 = {"8","55","32","40"};
		String[] header3 = {"9","75","40","80"};
		String[] header4 = {"6","45","30","20"};
		csv.writeNext(header1);
		csv.writeNext(header2);
		csv.writeNext(header3);
		csv.writeNext(header4);
		
//		String file = CBR.class.getResource("crop_data").getPath();
//		BufferedReader buf = new BufferedReader(new FileReader(file));
//		while ((line = buf.readLine()) != null) {
//			String[] words = line.split(",");
//			String[] content = new String[6];
//			float moist = Float.parseFloat(words[3]) + Float.parseFloat(words[4]);
//			float ph = Float.parseFloat(words[1]) + Float.parseFloat(words[2]);
//			float temp = Float.parseFloat(words[5]) + Float.parseFloat(words[6]);
//			float avg = Float.parseFloat(words[7]) + Float.parseFloat(words[8]);
//			content[0] = i.toString();
//			content[1] = String.valueOf(moist/2.0);
//			content[2] = String.valueOf(ph/2.0);
//			content[3] = String.valueOf(temp/2.0);
//			content[4] = String.valueOf(avg/2.0);
//			content[5] = String.valueOf(m.getCropid(words[9]));
//			csv.writeNext(content);	
//			i++;
//			System.out.println(moist/2.0+" "+ph/2.0+" "+temp/2.0+" "+avg/2.0);
//		}
		csv.close();
//		buf.close();
		
	}
}