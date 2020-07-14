package Esp8266.Wifi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Esp8266.Wifi.crop.ManageCrop;

/**
 * Servlet implementation class ProductDetail
 */
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session.getAttribute("username")==null){
			System.out.println("session null");
			response.sendRedirect("Login.jsp");
			//request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		if(session.getAttribute("username")!=null) {
			System.out.println("session not null" + session.getAttribute("username") + "a");
			
			String cropname = request.getParameter("cropname");
			request.setAttribute("cropname", cropname);
			request.getRequestDispatcher("product_details.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String populateCropCost(String cropname){
		ManageCrop manageCrop = new ManageCrop();
		manageCrop.sessionFactoryConfig();
		int cropid = manageCrop.getCropid(cropname);
		Float val = manageCrop.getCropCost(cropid);
		manageCrop.closeSessionFactoryConfig();
		return val.toString();
	}
	
	public String populateCropDetails(String cropname) {
		String cropdetails = "";
		File file = new File("aboutcrop/"+cropname);
		//String file = CBR.class.getResource("aboutcrop/"+cropname).getPath();
		if(!file.exists()) {
			return "We will be adding crop details soon....";
		}
		try {
		BufferedReader buf = new BufferedReader(new FileReader(file));
		cropdetails = buf.readLine();
		}catch(FileNotFoundException fileexp) {
			System.out.println(cropname + " file not found");
			fileexp.printStackTrace();
		}
		catch(IOException ioexp) {
			System.out.println("file not found exception for " + cropname);
			ioexp.printStackTrace();
		}
		
		return cropdetails;
	}
	
	

}
