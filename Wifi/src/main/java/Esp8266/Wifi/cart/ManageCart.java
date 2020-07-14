package Esp8266.Wifi.cart;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.customer.Customer;

public class ManageCart {

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
	 * function to add the new customer
	 * take the data sent from the customer in json format
	 * and store it in customer table using hibernate
	 */
	public void addCart(Cart cart) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(cart);
			tx.commit();
			session.close();
			}catch(Exception e) {
				session.close();
				System.out.println("exception while adding the customer data addCustomer(Customer):");
				e.printStackTrace();
			}
	}
	
	public List<Cart> getAllCartData() {
		Session session = sessionFactory.openSession();
		List<Cart> cartData = session.createQuery("From Cart").list();
		session.close();
		return cartData;
	}
	
	
}
