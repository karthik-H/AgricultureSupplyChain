package Esp8266.Wifi.crop;

public class Crop {

	public int id;
	public String crop_name;
	public int crop_available;
	public float cost;
	
	public Crop() {
		
	}
	public Crop(int id,String crop_name,int crop_available) {
		this.id = id;
		this.crop_name = crop_name;
		this.crop_available = crop_available;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCrop_name() {
		return crop_name;
	}
	public void setCrop_name(String crop_name) {
		this.crop_name = crop_name;
	}
	public int getCrop_available() {
		return crop_available;
	}
	public void setCrop_available(int crop_available) {
		this.crop_available = crop_available;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
}
