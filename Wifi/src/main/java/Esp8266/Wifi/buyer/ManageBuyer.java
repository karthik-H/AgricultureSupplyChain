package Esp8266.Wifi.buyer;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.crop.Crop;
import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.cropavailable.FarmerCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;
import Esp8266.Wifi.farmer.Farmer;

public class ManageBuyer {

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
	public void addBuyer(Buyer buyer) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(buyer);
			tx.commit();
			session.close();
			}catch(Exception e) {
				session.close();
				System.out.println("exception while adding the customer data addFarmer(Farmer):");
				e.printStackTrace();
			}
	}
	
	/**
	 * function to get all the details of the customers
	 * and send the data as Customer object the data is fetched based on customer id
	 * using ORM hibernate before calling this method call sessionFactoryConfig() - getCustomer(id)
	 */
	public Buyer getBuyer(int id) {
		Session session = sessionFactory.openSession();
		Buyer b = new Buyer();
		
		b = session.get(Buyer.class, id); 
		session.close();
		return b;
		
	}
	
	
	public boolean checkBuyer(Buyer b){
		Session session=sessionFactory.openSession();
		List<String> result = session.createQuery("select b.name from Buyer b where b.name = :name "
				+ "and b.password = :password").setString("name", b.getName())
				.setString("password", b.getPassword()).list();
		if(result.isEmpty()) {
			session.close();
			return false;
		}
		session.close();
		return true;
		
		
	}
	
	public boolean checkUniqueBuyer(String username) {
		Session session=sessionFactory.openSession();
		List<String> result = session.createQuery("select b.name from Buyer b where "
				+ "b.name = :name").setString("name", username).list();
		if(result.isEmpty()) {
			session.close();
			return true;
		}
		session.close();
		return false;
	}
	
	public StringBuilder populateCrop() {
		StringBuilder str = new StringBuilder();
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		List<Crop> Crops = manageCrop.getAllCrop();
		if(Crops == null) {
			System.out.println("null");
		}
		else {
			for(Crop f : Crops) {
				str.append("<input id=\"option\" type=\"checkbox\" name=\"field\" value="+f.getId()+">\r\n" + 
						"  <label for="+f.getId()+"><span><span></span></span>"+f.getCrop_name()+"</label>");
			}
		}
		manageCrop.closeSessionFactoryConfig();
		return str;
	}
	
	public int getBuyerId(String buyername) {
		Session session = sessionFactory.openSession();
		
		List<Integer> buyerid = session.createQuery("select b.id from Buyer b where b.name = :name").setString("name", buyername).list();
		Iterator buyeritr = buyerid.iterator();
		if(buyeritr.hasNext()) {
			session.close();
			return (int) buyeritr.next();
		}
		session.close();
		return 0;
	}
	
}
