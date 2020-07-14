package Esp8266.Wifi.customer;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		ManageCustomer manageCustomer = new ManageCustomer();
		manageCustomer.sessionFactoryConfig();
		//manageCustomer.addCustomer(customer);
		//System.out.println("added success fully");
		//customer = manageCustomer.getCustomer(1);
		customer.setName("karthik");
		customer.setPassword("password");
		
	}

}
