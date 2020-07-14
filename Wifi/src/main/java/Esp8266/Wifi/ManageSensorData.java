package Esp8266.Wifi;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageSensorData {
	public SessionFactory sessionfactory;
	public ManageSensorData() {
		
	}
	public void sessionFactoryConfig() {
		try{
			sessionfactory=new Configuration().configure().buildSessionFactory();
			
		}catch(Exception e){System.out.println("factory" + e);
	}
	}
	public void closeSessionFactoryConfig() {
		sessionfactory.close();
	}
	public List<Location> getData(){
		Session session=sessionfactory.openSession();
		List<Location> result = session.createQuery("FROM Location").list();
		session.close();
		return result;
	}
}
