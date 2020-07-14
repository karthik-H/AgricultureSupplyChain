package Esp8266.Wifi;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageUser {
	public SessionFactory sessionfactory;
	public void sessionFactoryConfig() {
		try{
			Configuration config = new Configuration().configure();
			sessionfactory=config.buildSessionFactory();
			
		}catch(Exception e){System.out.println("factory" + e);
	}
	}
	public void closeSessionFactoryConfig() {
		sessionfactory.close();
	}
	@SuppressWarnings("deprecation")
	public boolean checkUsers(String username, String password){
		Session session=sessionfactory.openSession();
		//List<Users> result = session.getSession().createCriteria(Users.class).list();
		List result = session.createQuery("From Users").list();
		Iterator it = result.iterator();
		while(it.hasNext()) {
			Users u = (Users) it.next();
			System.out.println(u.getUsername());
			System.out.println(u.getUsername()+"man");
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				session.close();
				return true;
			}
		}
		session.close();
		return false;
	}

}
