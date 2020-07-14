package Esp8266.Wifi.customer;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Esp8266.Wifi.Users;

@Path("/customer")
public class CustomerApi {
	
	/**
	 * get the customer data based on id 
	 * @param id = customer id (required)
	 * @return = customer data Json format
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("id") int id) {
		Customer customer = new Customer();
		System.out.println(id);
		ManageCustomer manageCustomer = new ManageCustomer();
		manageCustomer.sessionFactoryConfig();
		customer = manageCustomer.getCustomer(id);
		return customer;
	}
	
	/**
	 * add the customer data to the customer data base table
	 * @param customer customer data (name and password requeired)
	 * @return string reperesenting sucess or failure
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String postCustomer(Customer customer) {
		ManageCustomer manageCustomer = new ManageCustomer();
		manageCustomer.sessionFactoryConfig();
		manageCustomer.addCustomer(customer);
		return customer.getName()+" added succesfully";
	}
	
	/**
	 * authenticate user
	 * @param customer = customer data (user name and password in json format only)
	 * @param id = customer id who need to be authenticated
	 * @return = string represinting sussess or failure
	 */
	@POST
	@Path("/checkuser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String checkCustomer(Customer customer) {
		Customer validateCustomer = new Customer();
		ManageCustomer manageCustomer = new ManageCustomer();
		manageCustomer.sessionFactoryConfig();
		Configuration config = new Configuration().configure();
		SessionFactory sessionfactory=config.buildSessionFactory();
		/*
		Session session= sessionfactory.openSession();
		//List<Users> result = session.getSession().createCriteria(Users.class).list();
		List result = session.createQuery("From Customer").list();
		Iterator it = result.iterator();
		while(it.hasNext()) {
			Customer u = (Customer) it.next();
			System.out.println(u.getUsername());
			System.out.println(u.getUsername()+"man");
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return "true";
			}
		}*/
		return "false";
	}
}
