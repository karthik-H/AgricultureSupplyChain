package Esp8266.Wifi.cropavailable;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TestClassCropAvailable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FarmerCrop farmerCrop = new FarmerCrop();
//		f.setFarmer_id(1);
//		f.setCrop_id(1);
//		f.setDate(new Date());
//		f.setIteration(1);
//		f.setQuality(10);
		FarmerCrop farmerCrop = new FarmerCrop();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		farmerCrop = manageFarmerCrop.getCropQuailtyRank(7, 0);
		System.out.println(farmerCrop.getCrop_id());
//		for(FarmerCrop far : farmer) {
//			System.out.println(far.getCrop_id()+" "+far.getQuality());
//		}
		//System.out.println(f.getCrop_id()+" "+f.getQuality());
		/*
		FarmerCrop f = new FarmerCrop();
		ManageFarmerWsn mwf = new ManageFarmerWsn();
		mwf.sessionFactoryConfig();
		//System.out.println(mwf.getFarmerWsnId(1));
		Iterator<Integer> wsnitr = mwf.getFarmerWsnId(1).iterator();
		while(wsnitr.hasNext()) {
			System.out.println(wsnitr.next());
		}
		//System.out.println(mwf.getFarmerWsnId(1));
		//f = mf.getFarmerCrop(2);
		if(f == null) {
			
		}
		else {
			System.out.println(f.getCrop_id());
		}*/
//		ManageCropAvailable m = new ManageCropAvailable();
//		m.sessionFactoryConfig();
//		System.out.println(m.getAvailablity(1));
		
	}

}
