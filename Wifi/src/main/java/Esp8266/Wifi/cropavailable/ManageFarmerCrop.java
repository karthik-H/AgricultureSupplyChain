package Esp8266.Wifi.cropavailable;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.ManageIteration;
import Esp8266.Wifi.farmer.Farmer;
import Esp8266.Wifi.farmer.ManageFarmer;

public class ManageFarmerCrop {


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
	
	public void addFarmerCrop(FarmerCrop farmerCropId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(farmerCropId);
			tx.commit();
			session.close();
			
			}catch(Exception e) {
				System.out.println("exception while adding the customer data addFarmer(Farmer):");
				e.printStackTrace();
			}
	}
	
	public List<FarmerCrop> getFarmerCrop(int farmer_id) {
		Session session = sessionFactory.openSession();
		FarmerCrop f = new FarmerCrop();
		List<FarmerCrop> farmerCrop = session.createQuery("From FarmerCrop where "
				+ "farmer_id = "+farmer_id).list();
		
		session.close();
		
		return farmerCrop;
		
	}
	public List<Integer> getFarmers(int crop_id){
		Session session = sessionFactory.openSession();
		List<Integer> farmer = session.createSQLQuery("select farmer_id from farmer_crop where "
				+ "crop_id="+crop_id).list();
		
		session.close();
		return farmer;
	}
	public boolean checkFarmer(String farmerName) {
		Session session = sessionFactory.openSession();
		ManageIteration manageIteration = new ManageIteration();
		manageIteration.sessionFactoryConfig();
		int iteration = manageIteration.getIterationValue();
		ManageFarmer manageFarmer = new ManageFarmer();
		manageFarmer.sessionFactoryConfig();
		int farmer_id = manageFarmer.getFarmerId(farmerName);
		List<Integer> farmerid = session.createSQLQuery("select f.farmer_id from farmer_crop f"
				+ " where f.farmer_id = "+farmer_id+" and f.iteration = "+iteration).list();
		if(farmerid.isEmpty()) {
			
			session.close();
			return false;
		}
		
		else {
			
			session.close();
			return true;
		}
	}
	public boolean checkCrop(int cropid) {
		Session session = sessionFactory.openSession();
		List<Integer> cropids = session.createSQLQuery("select f.crop_id from "
				+ "farmer_crop f where f.crop_id ="+cropid).list();
		if(cropids.isEmpty()) {
			
			session.close();
			return false;
		}
		 //;
		session.close();
		return true;
	}
	public List<Integer> getCropAvailable(){
		ManageIteration manageIteration = new ManageIteration();
		manageIteration.sessionFactoryConfig();
		int iteration = manageIteration.getIterationValue();
		Session session = sessionFactory.openSession();
		List<Integer> cropAvailable = session.createSQLQuery("select f.crop_id from "
				+ "farmer_crop f where iteration ="+iteration).list();
		 //;
		session.close();
		return cropAvailable;
		
	}
	public void truncate() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.createSQLQuery("truncate farmer_crop").executeUpdate();
		tx.commit();
		 //;
		session.close();
	}
	public void deleteByIteration() {
		ManageIteration manageIteration = new ManageIteration();
		manageIteration.sessionFactoryConfig();
		int iteration = manageIteration.getIterationValue();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.createSQLQuery("delete from farmer_crop where iteration="+iteration).executeUpdate();
		tx.commit();
		 //;
		session.close();
	}
	public int getFarmerRank(int farmer_id) {
		Session session = sessionFactory.openSession();
		int total = 0;
		List<Integer> quality = session.createSQLQuery("select quality from farmer_crop "
				+ "where farmer_id = "+farmer_id).list();
		if(quality.isEmpty()) {
			return 0;
		}
		for(int i : quality) {
			total +=i;
		}
		 //;
		session.close();
		return total/quality.size();
	}
	
	public FarmerCrop getCropQuailtyRank(int farmer_id,int iteration) {
		Session session = sessionFactory.openSession();
		int total = 0;
		System.out.println("geting quality for iteration"+iteration+"farmer"+farmer_id);
		SQLQuery query =  session.createSQLQuery("select avg(quality),crop_id from farmer_crop where farmer_id="+farmer_id+" "
				+ "and crop_id not in (select crop_id from farmer_crop where iteration = "+(iteration - 1)+" and "
				+ "farmer_id = "+farmer_id+" ) group by(crop_id) order by quality desc LIMIT 1");
		List<Object[]> quality =  query.list();
		FarmerCrop farmerCrop = new FarmerCrop();
		System.out.println("qurery op"+quality);
		if(quality.isEmpty()) {
			System.out.println("query null");
			 //;
			session.close();
			return null;
		}
		for(Object[] row : quality) {
			farmerCrop.setCrop_id(Integer.parseInt(row[1].toString()));
			//farmerCrop.setQuality(Integer.parseInt(row[0].toString()));
		}
		 //;
		session.close();
		return farmerCrop;
	}
	
}
