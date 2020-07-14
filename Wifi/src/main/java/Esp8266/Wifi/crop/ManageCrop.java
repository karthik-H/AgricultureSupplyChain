package Esp8266.Wifi.crop;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.customer.Customer;

public class ManageCrop {

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
	
	public Crop getCrop(int id) {
		Session session = sessionFactory.openSession();
		Crop crop = new Crop();
		crop = session.get(Crop.class, id);
		session.close();
		return crop;
		
	}
	
	public String getCropName(int id) {
		Session session = sessionFactory.openSession();
		Crop crop = new Crop();
		crop = session.get(Crop.class, id); 
		session.close();
		return crop.getCrop_name();
		
	}
	public int getCropStatus(int id) {
		Session session = sessionFactory.openSession();
		Crop crop = new Crop();
		crop = session.get(Crop.class, id); 
		session.close();
		return crop.getCrop_available();
	}
	public float getCropCost(int id) {
		Session session = sessionFactory.openSession();
		Crop crop = new Crop();
		crop = session.get(Crop.class, id); 
		session.close();
		return crop.getCost();
	}
	public int updateCropStatus(int crop_id, int cropAvailable) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createSQLQuery("UPDATE `location`.`crop_data` SET `crop_available`="+cropAvailable+""
					+ " WHERE `id`="+crop_id+"").executeUpdate();
			tx.commit();
			session.close();
			}catch(Exception e) {
				session.close();
				System.out.println("exception while adding the :");
				e.printStackTrace();
			}
		return 1;
	}
	public List<Crop> getCropName() {
		Crop crop = new Crop();
		Session session = sessionFactory.openSession();
		List<Crop> cropList = session.createQuery("From Crop").list();
		session.close();
		return cropList;
	}
	
	public int getCropid(String cropname) {
		Session session = sessionFactory.openSession();
		cropname = cropname.toLowerCase();
		List<Integer> cropList = session.createQuery("select c.id from Crop c where c.crop_name = :name").setString("name", cropname).list();
		Iterator cropitr = cropList.iterator();
		if(cropitr.hasNext()) {
			session.close();
			return (int) cropitr.next();
		}
		session.close();
		return 0;
	}
	public List<Crop> getAllCrop(){
		Session session = sessionFactory.openSession();
		List<Crop> cropData = session.createQuery("From Crop").list();
		session.close();
		return cropData;
	}
}
