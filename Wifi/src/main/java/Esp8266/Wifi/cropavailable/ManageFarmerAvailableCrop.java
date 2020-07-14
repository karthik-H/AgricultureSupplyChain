package Esp8266.Wifi.cropavailable;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.farmer.Farmer;

public class ManageFarmerAvailableCrop {

	public SessionFactory sessionFactory;
	public void sessionFactoryConfig() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void closeSessionFactoryConfig() {
		sessionFactory.close();
	}
	
	public void addFarmerCrop(FarmerAvailableCrop farmer) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(farmer);
			tx.commit();
			session.close();
			}catch(Exception e) {
				System.out.println("exception while adding the customer data addFarmer(Farmer):");
				e.printStackTrace();
			}
	}
	public void truncateCrop() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		session.createSQLQuery("truncate table farmer_available_crop").executeUpdate();
		tx.commit();
		session.close();
		}catch(Exception e) {
			System.out.println("exception while adding the customer data addFarmer(Farmer):");
			e.printStackTrace();
		}
		
	}
	
	public List<Integer> getCropNames(int farmer_id){
		Session session = sessionFactory.openSession();
		List<Integer> cropList = session.createQuery("select f.crop_id from FarmerAvailableCrop f where f.farmer_id "
				+ "= :id").setInteger("id", farmer_id).list();
		session.close();
		return cropList;
	}
	
	public int getPrimaryCrop(int farmer_id) {
		Session session = sessionFactory.openSession();
		List<Integer> cropList = session.createSQLQuery("select crop_id from "
				+ "farmer_available_crop where farmer_id = "+farmer_id+" LIMIT 1").list();
		session.close();
		return cropList.get(0);
	}
	
	public List<Integer> getCrop(int farmer_id) {
		Session session = sessionFactory.openSession();
		List<Integer> crops = session.createSQLQuery("select crop_id from "
				+ "farmer_available_crop where farmer_id = "+farmer_id).list();
		session.close();
		return crops;
		
	}
	
}
