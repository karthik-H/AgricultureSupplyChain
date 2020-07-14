package Esp8266.Wifi;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.cart.Cart;
import Esp8266.Wifi.cart.ManageCart;
import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.customer.ManageCustomer;



/**
 * Servlet implementation class CartAdd
 */
public class CartAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = "";
		HttpSession session = request.getSession(false);
		if(session!=null) {
			Date date = new Date();
			//String format = "yyyy:mm:dd";
			//DateFormat dateFormat = new SimpleDateFormat(format);
			
			username = (String) session.getAttribute("username");
		String quantity = request.getParameter("quantity");
		System.out.println(quantity + request.getParameter("cropname")+request.getParameter("username"));
		Cart cart = new Cart();
		ManageCrop mc = new ManageCrop();
		mc.sessionFactoryConfig();
		int cropid =mc.getCropid(request.getParameter("cropname"));
		ManageCustomer manageCustomer = new ManageCustomer();
		manageCustomer.sessionFactoryConfig();
		int customerid = manageCustomer.getCustomerId(username);
		cart.setCrop_id(cropid);
		cart.setCustomer_id(customerid);
		cart.setCrop_amount(Integer.parseInt(quantity));
		cart.setOrder_date(date);
		cart.setCheckout(0);
		ManageCart manageCart = new ManageCart();
		manageCart.sessionFactoryConfig();
		manageCart.addCart(cart);
		mc.closeSessionFactoryConfig();
		manageCustomer.closeSessionFactoryConfig();
		manageCart.closeSessionFactoryConfig();
		response.sendRedirect("customer_menu.jsp");
		}
		else {

			response.sendRedirect("Login.jsp");;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
