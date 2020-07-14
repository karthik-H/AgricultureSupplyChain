package Esp8266.Wifi;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.cropavailable.ManageCropAvailable;
import Esp8266.Wifi.cropavailable.ManageFarmerCrop;
import Esp8266.Wifi.cropavailable.ManageFarmerRecommendation;

public class ManageIteration {
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
	public void incrementIteration() {
		Session session = sessionfactory.openSession();
		List<Integer> value = session.createSQLQuery("select iteration from iteration where id = 1").list();
		int iteration = value.get(0);
		iteration++;
		Transaction tx = null;
		tx = session.beginTransaction();
		session.createSQLQuery("update iteration set iteration = "+iteration+" where id = 1").executeUpdate();
		tx.commit();
		session.close();
	}
	public void resetIteration() {
		ManageCropAvailable manageCropAvailable = new ManageCropAvailable();
		manageCropAvailable.sessionFactoryConfig();
		ManageFarmerCrop manageFarmerCrop = new ManageFarmerCrop();
		manageFarmerCrop.sessionFactoryConfig();
		ManageFarmerRecommendation manageFarmerRecommend = new ManageFarmerRecommendation();
		manageFarmerRecommend.sessionFactoryConfig();
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		session.createSQLQuery("update iteration set iteration = 0 where id = 1").executeUpdate();
		tx.commit();
		manageCropAvailable.resetAvailablity();
		manageFarmerCrop.truncate();
		manageFarmerRecommend.truncateRecommendation();
		session.close();
	}
	public int getIterationValue() {
		Session session = sessionfactory.openSession();
		List<Integer> value = session.createSQLQuery("select iteration from iteration where id = 1").list();
		session.close();
		return value.get(0);
	}
}
