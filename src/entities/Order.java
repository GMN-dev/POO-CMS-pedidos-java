package entities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.enums.Status;

public class Order {
	private LocalDateTime moment;
	private Status orderStatus;
	List<OrderItem> items = new ArrayList<OrderItem>();
	Client client;

	public Order(String statusInput, Client clientInput) {
		this.setOrderStatus(statusInput);
		this.moment = LocalDateTime.now();
		this.client = clientInput;
	}
	public String getMoment() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy h:m:s");
		return this.moment.format(dtf);
	}
	public void setOrderStatus(String orderStatusInput) {
		try {
			this.orderStatus = Status.valueOf(orderStatusInput);
		}catch(Error err) {
			this.orderStatus = null;
		}
	}
	public Status getOrderStatus() {
		return this.orderStatus;
	}
	public List<OrderItem> getItems(){
		return this.items;
	}
	public Client getClient() {
		return this.client;
	}
	
	public void addItem(OrderItem orderItenInput) {
		this.getItems().add(orderItenInput);
	}
	
	public void removeItem(OrderItem orderItenInput) {
		this.getItems().remove(orderItenInput);
	}
	
	public Double total() {
		Double res = this.getItems().stream()
				.mapToDouble(x->x.subTotal())
				.sum();
		
		return res;
	}
}
