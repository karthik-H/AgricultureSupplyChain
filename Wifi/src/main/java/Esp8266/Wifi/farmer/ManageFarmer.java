package Esp8266.Wifi.farmer;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.notification.Notification;

public class ManageFarmer {

	public SessionFactory sessionFactory;
	public void sessionFactoryConfig() {
		try{
			sessionFactory=new Configuration().configure().buildSessionFactory();
			
		}catch(Exception e){System.out.println("factory" + e);
	}
	}
	
	public void closeSessionFactoryConfig() {
		sessionFactory.close();
	}
	
	/**
	 * function to add the new farmer
	 * take the data sent from the famer in json format
	 * and store it in customer table using hibernate
	 */
	public void addFarmer(Farmer farmer) {
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
	public List<Farmer> getAllFarmer() {
		Session session = sessionFactory.openSession();
		List<Farmer> allFarmer= session.createQuery("From Farmer").list();
		 
		session.close();
		return allFarmer;
	}
	
	/**
	 * function to get all the details of the customers
	 * and send the data as Customer object the data is fetched based on customer id
	 * using ORM hibernate before calling this method call sessionFactoryConfig() - getCustomer(id)
	 */
	public Farmer getFarmer(int id) {
		Session session = sessionFactory.openSession();
		Farmer f = new Farmer();
		
		f = session.get(Farmer.class, id); 
		 
		session.close();
		return f;
		
	}

	
	public boolean checkFarmer(Farmer farmer){
		Session session=sessionFactory.openSession();
		List<String> result = session.createQuery("select f.username from Farmer f where f.username = :name "
				+ "and f.password = :password").setString("name", farmer.getUsername())
				.setString("password", farmer.getPassword()).list();
		if(result.isEmpty()) {
			 
			session.close();
			return false;
		}
		 
		session.close();
		return true;
		
		
	}
	
	public boolean checkUniqueFarmer(String username) {
		Session session=sessionFactory.openSession();
		List<String> result = session.createQuery("select f.username from Farmer f where "
				+ "f.username = :name").setString("name", username).list();
		if(result.isEmpty()) {
			 
			session.close();
			return true;
		}
		 
		session.close();
		return false;
	}
	
	public int getFarmerId(String farmername) {
		Session session = sessionFactory.openSession();
		
		List<Integer> farmerid = session.createQuery("select f.id from Farmer f where f.username = :name").setString("name", farmername).list();
		Iterator farmeritr = farmerid.iterator();
		if(farmeritr.hasNext()) {
			 
			session.close();
			return (int) farmeritr.next();
		}
		 
		session.close();
		return 0;
	}
	public String getFarmerName(int id) {
		Session session = sessionFactory.openSession();
		
		List<Integer> farmerid = session.createQuery("select f.username from Farmer f where f.id = :id").setInteger("id", id).list();
		Iterator farmeritr = farmerid.iterator();
		if(farmeritr.hasNext()) {
			 
			session.close();
			return (String) farmeritr.next();
		}
		 
		session.close();
		return "";
	}
	public BigInteger getFarmerCount() {
		Session session = sessionFactory.openSession();
		List<BigInteger> value = session.createSQLQuery("select count(*) from farmer").list();
		 
		session.close();
		return value.get(0);
	}
	
}
