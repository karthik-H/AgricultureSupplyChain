package Esp8266.Wifi;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/location")
public class Wsn {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public String getLocation(@PathParam("id") String msgid) {
		
		String str = msgid + " is the location";
		return str;
		
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public String postLoacation(String msgid,@PathParam("id") String status) {
		try {
			System.out.println("before call");
		Service service = new Service();
		System.out.println("before func");
		if(status.equals("add")) {
			service.addLoc(msgid);	
			return "post location";
		}
		else if(status.equals("update")) {
			 service.updateLoc(msgid);
			 return "updated location";
		}
		else if(status.equals("update_ph")) {
			service.updatePh(msgid);
			return "update_ph";
		}
		else if(status.equals("update_moisture")) {
			service.updateMoisture(msgid);
			return "update moisture";
		}
		else if(status.equals("update_temp")) {
			service.updateTemperature(msgid);
			return "update temperature";
		}
		else if(status.equals("update_avg_rainfall")) {
			service.updateAvg_Rainfall(msgid);
			return "update avg rainfall";
		}
		else return "input url is wrong";
	//	System.out.println("func complete");
		}catch(Exception e) {
			System.out.println(e);
		}
		return "input url is wrong" ;
	}
}
