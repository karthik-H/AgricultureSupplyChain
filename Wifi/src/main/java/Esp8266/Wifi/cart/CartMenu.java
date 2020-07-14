package Esp8266.Wifi.cart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.CBR;
import Esp8266.Wifi.crop.Crop;
import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.customer.Customer;
import Esp8266.Wifi.customer.ManageCustomer;

/**
 * Servlet implementation class CartMenu
 */
public class CartMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int totalAmount = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public StringBuilder populateCartData(HttpSession session) {
		StringBuilder str = new StringBuilder();
		String username = session.getAttribute("username").toString();
		ManageCart manageCart = new ManageCart();
		manageCart.sessionFactoryConfig();
		List<Cart> cartlist = manageCart.getAllCartData();
		ManageCustomer mc = new ManageCustomer();
		mc.sessionFactoryConfig();
		ManageCrop m = new ManageCrop();
		m.sessionFactoryConfig();
		float cropcost = (float) 0;
		if(cartlist.isEmpty()) {
			str.append("shop more and add them to cart to see here");
			manageCart.closeSessionFactoryConfig();
			mc.closeSessionFactoryConfig();
			m.closeSessionFactoryConfig();
			return str;
		}
		for(Cart cart : cartlist) {
			
			
			Customer customer = mc.getCustomer(cart.getCustomer_id());
			if(customer.getName().equals(username)) {
				
				Crop crop = m.getCrop(cart.getCrop_id());
				String cropname = crop.getCrop_name().toLowerCase();
				cropcost = m.getCropCost(cart.getCrop_id());
				
				totalAmount += cart.getCrop_amount()* cropcost;
				System.out.println(cropcost);
				str.append("<tbody>\r\n" + 
						"                                    <tr>\r\n" + 
						"                                        <td class=\"cart_product_img\">\r\n" + 
						"                                            <a href=\"#\"><img src=\"image/"+cropname+".jpg\" alt=\"Product\"></a>\r\n" + 
						"                                        </td>\r\n" + 
						"                                        <td class=\"cart_product_desc\">\r\n" + 
						"                                            <h5>"+cropname.toUpperCase()+"</h5>\r\n" + 
						"                                        </td>\r\n" + 
						"                                        <td class=\"price\">\r\n" + 
						"                                            <span>Rs"+ cropcost+"</span>\r\n" + 
						"                                        </td>\r\n" + 
						"                                        <td class=\"qty\">\r\n" + 
						"                                            <div class=\"qty-btn d-flex\">\r\n" + 
						"                                                <div class=\"quantity\">\r\n" + 
						"                                                <p>"+ cart.getCrop_amount() +"Kg</p>    \r\n" + 
						"												</div>\r\n" + 
						"                                            </div>\r\n" + 
						"                                        </td>\r\n" + 
						"                                    </tr>\r\n" + 
						"									<td class=\"price\">\r\n" + 
								"                                            <span>"+ cart.getOrder_date()+"</span>\r\n" + 
								"                                        </td>\r\n" + 
						"                                </tbody>");
				
			}
			
		}
		manageCart.closeSessionFactoryConfig();
		mc.closeSessionFactoryConfig();
		m.closeSessionFactoryConfig();
		
		return str;
	}
	public int populateTotalAmount() {
		return totalAmount;
	}
}
