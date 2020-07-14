package Esp8266.Wifi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.sun.research.ws.wadl.Resources;

public class CBR {
	static float ph, moi, tem, avg;
	
	public static String getCrop(float ph, float moi, float tem, float avg, Set<String> crop) throws IOException {
		// TODO Auto-generated method stub
		String line;
		Integer line_number = 0, file_total_lines = 0;
		
		String file = CBR.class.getResource("crop_data").getPath();
		System.out.println("reading from"+file);
		System.out.println("param"+ph+" "+moi+" "+tem+" "+avg+" "+crop);
		BufferedReader buf = new BufferedReader(new FileReader(file));
		int max_count = 0;
		String retrive = "",previous_retrive = "";
		while ((line = buf.readLine()) != null) {
			//System.out.println(line);
			line_number++;
			int count = 0;
			String[] words = line.split(",");
			if (Float.parseFloat(words[3]) <= moi && moi <= Float.parseFloat(words[4])) {
				// System.out.println(words[3]+" "+l);
				count++;
			}
			if (Float.parseFloat(words[1]) <= ph && ph <= Float.parseFloat(words[2])) {
				count++;
			}
			if (Float.parseFloat(words[5]) <= tem && tem <= Float.parseFloat(words[6])) {
				count++;
			}
			if (Float.parseFloat(words[7]) <= avg && avg <= Float.parseFloat(words[8])) {
				count++;
			}
			
			if (count == 4) {
				System.out.println("found suitable crop : " + " " + words[9]
						+ " and predicted the user action using the row: c" + line_number);
				if (!(crop.contains(words[9].toString()))) {
					return words[9].toString();
				} else
					continue;
			} else {
				if (max_count > count) {

				} else if (max_count == count) {
					retrive = retrive + line_number.toString() + ",";
				} else {
					max_count = count;
					//previous_retrive = retrive;
					retrive = "";
					retrive = retrive + line_number.toString() + ",";
				}

			}

		}
		buf.close();
		file_total_lines = line_number;
		System.out.println("total lines" + file_total_lines+"count"+max_count+"retrive"+retrive);
		// System.out.println("next line"+buf.readLine());
//		if(setCount && !previous_retrive.isEmpty()) {
//			retrive = previous_retrive;
//		}
		if (retrive.length() == 2) {
			buf = new BufferedReader(new FileReader(file));
			line_number = Character.getNumericValue(retrive.charAt(0));
			int i = line_number;

			for (; line_number > 1; line_number--) {
				buf.readLine();
			}
			String output = buf.readLine();
			String[] words = output.split(",");
			System.out.println(output);
			System.out.println("found class User Action 1 : " + " " + words[9]
					+ " and predicted the user action using the row: c" + i);
			buf.close();
			if (!(crop.contains(words[9].toString()))) {
				return words[9].toString();
			}
		} else {
			buf = new BufferedReader(new FileReader(file));

			Map<String, String> map_loc = new HashMap<String, String>();
			Map<String, String> map_thread = new HashMap<String, String>();
			Map<String, String> map_auth = new HashMap<String, String>();
			Map<String, String> map_rain = new HashMap<String, String>();
			int pos = 0, line_num = 1, expected_line_num;
			String str[] = retrive.split(",");
			while ((line = buf.readLine()) != null && pos < ((retrive.length() / 2) - 1)) {
				expected_line_num = Integer.parseInt(str[pos]);
				System.out.println("expected line number : "+expected_line_num+" current line number "+line_num
						+"pos"+pos);
				if (expected_line_num == line_num) {
					pos++;
					String result[] = line.split(",");
					if (Float.parseFloat(result[3]) <= moi && moi <= Float.parseFloat(result[4])) {
						map_loc.put(result[0], result[9]);
						if (Float.parseFloat(result[1]) <= ph && ph <= Float.parseFloat(result[2])) {
							map_thread.put(result[0], result[9]);
							System.out.println("found class User Action : " + result[0]
									+ " and predicted the user action using the row: " + result[9]);
							if (!(crop.contains(result[9].toString()))) {
								if(crop.isEmpty()) {
									//writeFile(file_total_lines + 1, ph, moi, tem, avg, result[9].toString());
								}
								return result[9].toString();
							} 

						} else if (Float.parseFloat(result[5]) <= tem && tem <= Float.parseFloat(result[6])) {
							map_auth.put(result[0], result[9]);
							System.out.println("found class User Action : " + result[0]
									+ " and predicted the user action using the row: " + result[9]);
							if (!(crop.contains(result[9].toString()))) {
								if(crop.isEmpty()) {
									//writeFile(file_total_lines + 1, ph, moi, tem, avg, result[9].toString());
								}
								return result[9].toString();
							} 

						} else if (Float.parseFloat(result[7]) <= avg && avg <= Float.parseFloat(result[8])) {
							map_rain.put(result[0], result[9]);
							System.out.println("found class User Action : " + result[0]
									+ " and predicted the user action using the row: " + result[9]);
							if (!(crop.contains(result[9].toString()))) {
								if(crop.isEmpty()) {
									//writeFile(file_total_lines + 1, ph, moi, tem, avg, result[9].toString());
								}
								return result[9].toString();
							}

						}
					}
					if (Float.parseFloat(result[1]) <= ph && ph <= Float.parseFloat(result[2])) {
						map_thread.put(result[0], result[9]);
						if (Float.parseFloat(result[5]) <= tem && tem <= Float.parseFloat(result[6])) {
							map_auth.put(result[0], result[9]);
							System.out.println("found class User Action : " + result[0]
									+ " and predicted the user action using the row: " + result[9]);
							if (!(crop.contains(result[9].toString()))) {
								if(crop.isEmpty()) {
									//writeFile(file_total_lines + 1, ph, moi, tem, avg, result[9].toString());
								}
								return result[9].toString();
							} 
						} else if (Float.parseFloat(result[7]) <= avg && avg <= Float.parseFloat(result[8])) {
							map_rain.put(result[0], result[9]);
							System.out.println("found class User Action : " + result[0]
									+ " and predicted the user action using the row: " + result[9]);
							if (!(crop.contains(result[9].toString()))) {
								if(crop.isEmpty()) {
									//writeFile(file_total_lines + 1, ph, moi, tem, avg, result[9].toString());
								}
								return result[9].toString();
							}
						}
					}
					if (Float.parseFloat(result[5]) <= tem && tem <= Float.parseFloat(result[6])) {
						map_auth.put(result[0], result[9]);
						if (Float.parseFloat(result[7]) <= avg && avg <= Float.parseFloat(result[8])) {
							map_rain.put(result[0], result[9]);
							System.out.println("found class User Action : " + result[0]
									+ " and predicted the user action using the row: " + result[9]);
							if (!(crop.contains(result[9].toString()))) {
								if(crop.isEmpty()) {
									//writeFile(file_total_lines + 1, ph, moi, tem, avg, result[9].toString());
								}
								return result[9].toString();
							} 
						}

					}
					if (Float.parseFloat(result[7]) <= avg && avg <= Float.parseFloat(result[8])) {
						map_rain.put(result[0], result[9]);
						System.out.println("found class User Action : " + result[0]
								+ " and predicted the user action using the row: " + result[9]);
						if (!(crop.contains(result[9].toString()))) {
							if(crop.isEmpty()) {
								//writeFile(file_total_lines + 1, ph, moi, tem, avg, result[9].toString());
							}
							return result[9].toString();
						}
					}

					//pos++;
				}
				line_num++;

			}

			return "no data";
		}
		return "no data";

	}// eof main

	public static boolean isUnique(Map<String, String> aMap) {
		Object value = null;

		for (Object entry : aMap.values()) {
			if (value == null) {
				value = entry;
			} else if (!value.equals(entry)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String s[]) throws IOException {
		//    [maize, blackgram]
		float ph = (float) 6.74706;
		float m = (float) 86.0;
		float t = (float) 29.0;
		float a = (float) 233.0;
		 //6.05926 75.0 30.0 83.0
		 //6 8 71 42
		boolean setCount = false;
		 Set<String> cropset = new LinkedHashSet<String>();
		 String crop = "";
		 for(int i = 0; i < 3;i++) {
		 crop = getCrop(ph,m,t,a,cropset);
		 if(crop=="nodata") {
			 crop = getCrop(ph,m,t,a,cropset);
		 }
		 cropset.add(crop);
		 }
		 for(String k : cropset) {
		 System.out.println(k);
		 }

	}

	public static void writeFile(int line_number, float ph, float m, float t, float a, String cropname) {
		StringBuffer data = new StringBuffer();

		data.append("c").append(line_number).append(",").append((ph - 0.5)).append(",").append(ph).append(",")
				.append((m - 10)).append(",").append(m).append(",").append((t - 5)).append(",").append(t).append(",")
				.append((a - 2)).append(",").append(a).append(",").append(cropname);

		try {
			String file = CBR.class.getResource("crop_data").getPath();
				FileWriter fr = new FileWriter(file, true);
				BufferedWriter bufWriter = new BufferedWriter(fr);
				bufWriter.write("\r\n");
				bufWriter.write(data.toString());
				System.out.println("done writing"+data+"to path"+file);
				bufWriter.close();
				fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
