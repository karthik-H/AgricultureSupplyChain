package Esp8266.Wifi.notification;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageFarmerNotification {


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
	
	public void addFarmerNotification(FarmerNotification farmerNotification) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(farmerNotification);
			tx.commit();
			session.close();
		}catch(Exception e) {
			session.close();
			System.out.println("exception while adding farmer notification");
		}
	}
	public List<FarmerNotification> getFarmerNotification(int farmer_id){
		Session session = sessionFactory.openSession();
		List<FarmerNotification> farmerNotification = session.createQuery("From FarmerNotification "
				+ "where farmer_id = "+ farmer_id).list();
		session.close();
		return farmerNotification;
	}
	public void deleteNotification(int notification_id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createSQLQuery("delete from farmer_notification where id ="+ notification_id).executeUpdate();
			tx.commit();
			session.close();
		}catch(Exception e) {
			session.close();
			e.printStackTrace();
		}
	}
	
}
