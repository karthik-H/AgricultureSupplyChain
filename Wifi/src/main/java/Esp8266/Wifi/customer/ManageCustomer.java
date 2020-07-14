package Esp8266.Wifi.customer;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.Users;

public class ManageCustomer {

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
	public void addCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(customer);
			tx.commit();
			session.close();
			}catch(Exception e) {
				session.close();
				System.out.println("exception while adding the customer data addCustomer(Customer):");
				e.printStackTrace();
			}
	}
	
	/**
	 * function to get all the details of the customers
	 * and send the data as Customer object the data is fetched based on customer id
	 * using ORM hibernate before calling this method call sessionFactoryConfig() - getCustomer(id)
	 */
	public Customer getCustomer(int id) {
		Session session = sessionFactory.openSession();
		Customer customer = new Customer();
		
		customer = session.get(Customer.class, id);
		session.close();
		return customer;
		
	}
	
	public boolean checkCustomer(Customer customer){
		Session session=sessionFactory.openSession();
		//List<Users> result = session.getSession().createCriteria(Users.class).list();
		List<Customer> result = session.createQuery("From Customer").list();
		Iterator<Customer> it = result.iterator();
		while(it.hasNext()) {
			Customer c = (Customer) it.next();
			if(c.getName().equals(customer.getName()) && c.getPassword().equals(customer.getPassword())) {
				session.close();
				return true;
			}
		}
		session.close();
		return false;
	}
	
	public boolean checkUniqueCustomer(String username) {
		Session session=sessionFactory.openSession();
		List<String> result = session.createQuery("select c.name from Customer c where c.name = :name").setString("name", username).list();
		if(result.isEmpty()) {
			session.close();
			return true;
		}
		session.close();
		return false;
	}
	
	public int getCustomerId(String customername) {
		Session session = sessionFactory.openSession();
		
		List<Integer> cropList = session.createQuery("select c.id from Customer c where c.name = :name").setString("name", customername).list();
		Iterator cropitr = cropList.iterator();
		if(cropitr.hasNext()) {
			session.close();
			return (int) cropitr.next();
		}
		session.close();
		return 0;
	}
}
