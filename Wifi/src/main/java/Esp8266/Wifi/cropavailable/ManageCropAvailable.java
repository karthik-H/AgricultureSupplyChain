package Esp8266.Wifi.cropavailable;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.ManageIteration;
import Esp8266.Wifi.crop.ManageCrop;

public class ManageCropAvailable {
	public SessionFactory sessionfactory;

	public void sessionFactoryConfig() {
		try{
			sessionfactory=new Configuration().configure().buildSessionFactory();
			
		}catch(Exception e){System.out.println("factory" + e);
	}
	}
	public void closeSessionFactoryConfig() {
		sessionfactory.close();
	}
	public int getAvailablity(int crop_id) {
		Session session = sessionfactory.openSession();
		List<Integer> cropavailable = session.createSQLQuery("select available from "
				+ "crop_available where crop_id = "+crop_id).list();
		session.close();
		return cropavailable.get(0);
	}
	public void updateAvailablity() {
		
		Session session = sessionfactory.openSession();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		List<Integer> currentAvailable;
		List<Integer> cropavailable = manageFarmerCrop.getCropAvailable();
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		Transaction tx = null;
		for(int i : cropavailable) {
			int amount = manageCrop.getCropStatus(i);
			currentAvailable = session.createSQLQuery("select available from "
					+ "crop_available where crop_id = "+i).list();
			amount += currentAvailable.get(0);
			tx = session.beginTransaction();
			session.createSQLQuery("update crop_available set available "
					+ "= "+amount+" where crop_id = "+i).executeUpdate();
			tx.commit();
		}
		session.close();
	}
	public void resetAvailablity() {
		Session session = sessionfactory.openSession();
		Transaction tx = null;
		List<BigInteger> id = session.createSQLQuery("select count(*) from crop_available").list();
		for(int i = 1;i <= id.get(0).intValue();i++) {
			tx = session.beginTransaction();
			session.createSQLQuery("update crop_available set available = 0 where crop_id = "+i).executeUpdate();
			tx.commit();
		}
		session.close();
	}
	
}
