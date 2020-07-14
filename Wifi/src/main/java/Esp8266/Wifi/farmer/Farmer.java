package Esp8266.Wifi.farmer;

public class Farmer {

	public int id;
	public String farmer_first_name;
	public String farmer_last_name;
	public String username;
	public String password;
	public String farmer_address;
	public float water_availablity;
	public float electricity_availablity;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFarmer_first_name() {
		return farmer_first_name;
	}
	public void setFarmer_first_name(String farmer_first_name) {
		this.farmer_first_name = farmer_first_name;
	}
	public String getFarmer_last_name() {
		return farmer_last_name;
	}
	public void setFarmer_last_name(String farmer_last_name) {
		this.farmer_last_name = farmer_last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFarmer_address() {
		return farmer_address;
	}
	public void setFarmer_address(String farmer_address) {
		this.farmer_address = farmer_address;
	}
	public float getWater_availablity() {
		return water_availablity;
	}
	public void setWater_availablity(float water_availablity) {
		this.water_availablity = water_availablity;
	}
	public float getElectricity_availablity() {
		return electricity_availablity;
	}
	public void setElectricity_availablity(float electricity_availablity) {
		this.electricity_availablity = electricity_availablity;
	}

	
	
}
