package Esp8266.Wifi.cart;

import java.util.Date;

public class Cart {
	public int id;
	public int customer_id;
	public int crop_id;
	public int crop_amount;
	public Date order_date;
	public int checkout;
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getCrop_id() {
		return crop_id;
	}
	public void setCrop_id(int crop_id) {
		this.crop_id = crop_id;
	}
	public int getCrop_amount() {
		return crop_amount;
	}
	public void setCrop_amount(int crop_amount) {
		this.crop_amount = crop_amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getCheckout() {
		return checkout;
	}
	public void setCheckout(int checkout) {
		this.checkout = checkout;
	}
	
	
}
