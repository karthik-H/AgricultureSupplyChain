package Esp8266.Wifi.notification;

public class Notification {
	
	public int id;
	public String notification_topic;
	public String notification_message;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNotification_topic() {
		return notification_topic;
	}
	public void setNotification_topic(String notification_topic) {
		this.notification_topic = notification_topic;
	}
	public String getNotification_message() {
		return notification_message;
	}
	public void setNotification_message(String notification_message) {
		this.notification_message = notification_message;
	}
	
	
}
