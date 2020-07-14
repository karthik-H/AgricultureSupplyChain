package Esp8266.Wifi.cropavailable;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.crop.Crop;
import Esp8266.Wifi.farmer.Farmer;

public class ManageFarmerWsn {

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
	public void addFarmerWsn(int farmer_id, int wsn_id) {
		Session session = sessionFactory.openSession();
		FarmerWsn farmerWsn = new FarmerWsn();
		farmerWsn.setFarmer_id(farmer_id);
		farmerWsn.setWsn_id(wsn_id);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(farmerWsn);
			tx.commit();
			session.close();
			}catch(Exception e) {
				session.close();
				System.out.println("exception while adding ");
				e.printStackTrace();
			}
	}
	
	public FarmerWsn getData(int id) {
		Session session = sessionFactory.openSession();
		FarmerWsn farmerWsn = new FarmerWsn();
		farmerWsn = session.get(FarmerWsn.class, id); 
		session.close();
		return farmerWsn;
		
	}
	public int getFarmerId(int wsnid) {
		Session session = sessionFactory.openSession();
		List<Integer> farmerId = session.createQuery("select f.farmer_id from FarmerWsn f where f.wsn_id = :id").setInteger("id", wsnid).list();
		Iterator farmeritr = farmerId.iterator();
		if(farmeritr.hasNext()) {
			session.close();
			return (int) farmeritr.next();
		}
		session.close();
		return 0;
	}
	public List<Integer> getFarmerWsnId(int farmerid) {
		Session session = sessionFactory.openSession();
		List<Integer> wsnId = session.createQuery("select f.wsn_id from FarmerWsn f where f.farmer_id = :id").setInteger("id",farmerid).list();
		session.close();
		return wsnId;
	}

}
