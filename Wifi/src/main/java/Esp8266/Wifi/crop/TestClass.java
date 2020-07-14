package Esp8266.Wifi.crop;

import java.util.List;

import Esp8266.Wifi.customer.CustomerMenu;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ManageCrop mc = new ManageCrop();
		mc.sessionFactoryConfig();
		mc.updateCropStatus(1,5);
		System.out.println();
	}

}
