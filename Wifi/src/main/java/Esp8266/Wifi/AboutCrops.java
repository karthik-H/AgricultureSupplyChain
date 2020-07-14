package Esp8266.Wifi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

/**
 * Servlet implementation class AboutCrops
 */
public class AboutCrops extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutCrops() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cropname = request.getParameter("cropname");
		request.setAttribute("cropname", cropname);
		request.getRequestDispatcher("crop_details_template.jsp").forward(request, response);
		response.getWriter().append(cropname).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public StringBuilder populateCropData(String cropname) throws IOException {
		StringBuilder cropdata = new StringBuilder();
		String file = "";
		int i = 0;
		try {
			file = CBR.class.getResource("aboutcrop/"+cropname).getPath();
		}catch(Exception e) {
			cropdata.append("<h5>").append("About").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append("no data available we will soon be adding it").append("</p>")
			.append("<h5>").append("Duration").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append("").append("</p>")
			.append("<h5>").append("Initial Investment").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append("").append("</p>")
			.append("<h5>").append("Gross Salary").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append("").append("</p>");
			return cropdata;
		}
		
		BufferedReader buf = new BufferedReader(new FileReader(file));
		
			cropdata.append("<h5>").append("About").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append(buf.readLine()).append("</p>")
			.append("<h5>").append("Duration").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append(buf.readLine()).append("</p>")
			.append("<h5>").append("Initial Investment").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append(buf.readLine()).append("</p>")
			.append("<h5>").append("Gross Salary").append("</h5>")
			.append("<p class=\"w3-text-grey\">").append(buf.readLine()).append("</p>");
			buf.close();
			return cropdata;
		
		
	}
	public String populateCropName() {
		return "";
	}
	
}
