package Esp8266.Wifi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;


/**
 * Servlet implementation class UpdateData
 */
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("btn");
		if(button.equals("all")) {
			String url = "http://192.168.225.42/";
		     HttpClient httpClient = HttpClients.createDefault();

	            // Create a method instance.
	            HttpGet get = new HttpGet(url);

	            HttpResponse res = httpClient.execute(get);

	            int internResponseStatus = res.getStatusLine().getStatusCode();
	            System.out.println("responce code to esp"+internResponseStatus);
			
			response.sendRedirect("/Wifi/SensorData");
		}
		else if(button.equals("moisture")) {
			String url = "http://192.168.225.42/moisture";
		     HttpClient httpClient = HttpClients.createDefault();

	            // Create a method instance.
	            HttpGet get = new HttpGet(url);

	            HttpResponse res = httpClient.execute(get);

	            int internResponseStatus = res.getStatusLine().getStatusCode();
	            System.out.println("responce code to esp"+internResponseStatus);
			
			response.sendRedirect("/Wifi/SensorData");
		}
		else if(button.equals("ph")) {
			String url = "http://192.168.225.42/ph";
		     HttpClient httpClient = HttpClients.createDefault();

	            // Create a method instance.
	            HttpGet get = new HttpGet(url);

	            HttpResponse res = httpClient.execute(get);

	            int internResponseStatus = res.getStatusLine().getStatusCode();
	            System.out.println("responce code to esp"+internResponseStatus);
			
			response.sendRedirect("/Wifi/SensorData");
		}
		else if(button.equals("itr")) {
			ManageIteration manageIteration = new ManageIteration();
			manageIteration.sessionFactoryConfig();
			manageIteration.incrementIteration();
			manageIteration.closeSessionFactoryConfig();
			response.sendRedirect("updatesensordata.jsp");
		}
		else if(button.equals("reset")) {
			ManageIteration manageIteration = new ManageIteration();
			manageIteration.sessionFactoryConfig();
			manageIteration.resetIteration();
			manageIteration.closeSessionFactoryConfig();
			response.sendRedirect("updatesensordata.jsp");
		}
		else if(button.equals("auto")) {
			AutomateData automate = new AutomateData();
			automate.automateCropAcceptance();
			response.sendRedirect("updatesensordata.jsp");
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
