package Esp8266.Wifi.notification;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageNotification {


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
	public List<Notification> getAllNotification() {
		Session session = sessionFactory.openSession();
		List<Notification> allNotification= session.createQuery("From Notification").list();
		session.close();
		return allNotification;
	}
	public Notification getNotificationMessage(int notification_id) {
		Session session = sessionFactory.openSession();
		List<Notification> notification = session.createQuery("From Notification where "
				+ "id ="+notification_id).list();
		session.close();
		return notification.get(0);
	}
}

