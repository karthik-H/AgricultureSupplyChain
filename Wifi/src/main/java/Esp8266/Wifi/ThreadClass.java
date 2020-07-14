package Esp8266.Wifi;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ThreadClass implements Runnable{
	
	String path="";
	private HttpServletResponse responseGlobal;
	public ThreadClass() {
		
	}
	public ThreadClass(String path,HttpServletResponse res) {
		this.path = path;
		this.responseGlobal = res;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			responseGlobal.sendRedirect("/Wifi/SensorData");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
