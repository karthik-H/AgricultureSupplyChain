package Esp8266.Wifi.cart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import Esp8266.Wifi.CBR;
import Esp8266.Wifi.crop.Crop;
import Esp8266.Wifi.crop.ManageCrop;
import Esp8266.Wifi.customer.Customer;
import Esp8266.Wifi.customer.ManageCustomer;

public class TestClassCart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManageCart manageCart = new ManageCart();
		manageCart.sessionFactoryConfig();
		List<Cart> cartlist = manageCart.getAllCartData();
		ManageCustomer mc = new ManageCustomer();
		mc.sessionFactoryConfig();
		ManageCrop m = new ManageCrop();
		m.sessionFactoryConfig();
		String cropcost = "";
		StringBuilder str = new StringBuilder();
		for(Cart cart : cartlist) {
			
			Customer customer = mc.getCustomer(cart.getCustomer_id());
			System.out.println(customer.getName());
			
			Crop crop = m.getCrop(cart.getCrop_id());
			String cropname = crop.getCrop_name().toLowerCase();
			System.out.println(cropname);
			String file = CBR.class.getResource("aboutcrop/"+cropname).getPath();
			try {
			BufferedReader buf = new BufferedReader(new FileReader(file));
			for(int i = 0;i < 4;i++) {
				buf.readLine();
			}
			cropcost = buf.readLine();
			}catch(FileNotFoundException fileexp) {
				System.out.println(cropname + " file not found");
				fileexp.printStackTrace();
			}
			catch(IOException ioexp) {
				System.out.println("file not found exception for " + cropname);
				ioexp.printStackTrace();
			}
			System.out.println(cropcost);
			
			System.out.println("<tbody>\r\n" + 
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

}
