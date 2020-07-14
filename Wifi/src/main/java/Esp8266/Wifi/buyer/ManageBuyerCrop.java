package Esp8266.Wifi.buyer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageBuyerCrop {

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
	public void addBuyerCrop(BuyerCrop buyerCrop) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(buyerCrop);
			tx.commit();
			session.close();
			}catch(Exception e) {
				session.close();
				System.out.println("exception while adding the customer data addFarmer(Farmer):");
				e.printStackTrace();
			}
	}
	
	public List<Integer> getBuyerCropId(int buyerId){
		Session session = sessionFactory.openSession();
		List<Integer> crop_id = session.createSQLQuery("select crop_id from buyer_crop where buyer_id ="+buyerId).list();
		session.close();
		return crop_id;
	}
	
	public List<Integer> getBuyerRemainingCrop(int buyerId){

		Session session = sessionFactory.openSession();
		List<Integer> id = session.createSQLQuery("select id from crop_data where id not in (select crop_id "
				+ "from buyer_crop where buyer_id ="+buyerId+")").list();
		session.close();
		return id;
	}
	
}
