package Esp8266.Wifi.farmer;

public class TestClassFarmer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Farmer f = new Farmer();
		//f.setUsername("sharath");
		//f.setPassword("password");
		
		ManageFarmer mf = new ManageFarmer();
		mf.sessionFactoryConfig();	
		System.out.println(mf.getFarmerName(1));
		
	//	farmermenu.populateCropData("karthik");
	}

}
