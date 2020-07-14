package Esp8266.Wifi.cropavailable;

import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageFarmerRecommendation {


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
	public void addFarmerRecommendation(FarmerRecommendation farmerRecommendation) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(farmerRecommendation);
			tx.commit();
			session.close();
			}catch(Exception e) {
				session.close();
				System.out.println("exception while adding the farmer data");
				e.printStackTrace();
			}
	}
	
	public FarmerRecommendation getFarmerRecommendation(int id) {
		Session session = sessionFactory.openSession();
		FarmerRecommendation f = new FarmerRecommendation();
		
		f = session.get(FarmerRecommendation.class, id); 
		session.close();
		return f;
		
	}
	public boolean checkRecommendation(int farmer_id, int recommendation_id) {
		Session session = sessionFactory.openSession();
		List<Integer> farmer = session.createSQLQuery("select f.farmer_id from "
				+ "farmer_recommendation f where f.farmer_id ="+farmer_id+" and f.recommend_crop_id = "+recommendation_id).list();
		if(farmer.isEmpty()) {
			session.close();
			return false;
		}
		else {
			session.close();
			return true;
		}
	}
	public void truncateRecommendation() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.createSQLQuery("truncate farmer_recommendation").executeUpdate();
		tx.commit();
		session.close();
	}
	
	
}
