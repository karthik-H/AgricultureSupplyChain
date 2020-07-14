package Esp8266.Wifi.cropavailable;

import java.util.Date;

public class FarmerCrop {
	public int id;
public int farmer_id;
public int crop_id;
public Date date;
public int iteration;
public int quality;

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
public int getCrop_id() {
	return crop_id;
}
public void setCrop_id(int crop_id) {
	this.crop_id = crop_id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public int getIteration() {
	return iteration;
}
public void setIteration(int iteration) {
	this.iteration = iteration;
}
public int getQuality() {
	return quality;
}
public void setQuality(int quality) {
	this.quality = quality;
}

}
