package Esp8266.Wifi.notification;

import java.util.Date;

public class FarmerNotification {

	public int id;
	public int farmer_id;
	public int notification_id;
	public Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFarmer_id() {
		return farmer_id;
	}
	public void setFarmer_id(int farmer_id) {
		this.farmer_id = farmer_id;
	}
	public int getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
