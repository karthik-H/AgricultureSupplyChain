package Esp8266.Wifi;

public class Location {

public int id;
public float longitude;
public float latitude;
public float accurecy;
public float soil_ph;
public float moisture;
public float temperature;
public float avg_rainfall;


Location(){
	
}
Location(float latitude,float longitude,float accurecy,float soil_ph,float moisture,
		float temperature,float avg_rainfall){
	this.longitude = longitude;
	this.latitude = latitude;
	this.accurecy = accurecy;
	this.soil_ph = soil_ph;
	this.moisture = moisture;
	this.temperature = temperature;
	this.avg_rainfall = avg_rainfall;
	
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public float getLongitude() {
	return longitude;
}
public void setLongitude(float longitude) {
	this.longitude = longitude;
}
public float getLatitude() {
	return latitude;
}
public void setLatitude(float latitude) {
	this.latitude = latitude;
}
public float getAccurecy() {
	return accurecy;
}
public void setAccurecy(float accurecy) {
	this.accurecy = accurecy;
}
public float getSoil_ph() {
	return soil_ph;
}
public void setSoil_ph(float soil_ph) {
	this.soil_ph = soil_ph;
}
public float getMoisture() {
	return moisture;
}
public void setMoisture(float moisture) {
	this.moisture = moisture;
}
public float getTemperature() {
	return temperature;
}
public void setTemperature(float temperature) {
	this.temperature = temperature;
}
public float getAvg_rainfall() {
	return avg_rainfall;
}
public void setAvg_rainfall(float avg_rainfall) {
	this.avg_rainfall = avg_rainfall;
}

}