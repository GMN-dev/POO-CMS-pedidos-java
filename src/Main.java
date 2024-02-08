import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;

public class Main {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//client vars
		String name;
		String email;
		String birth;
		
		//order vars
		String status;
		Integer orders;
		
		//-=-=-=-=-=Client data-=-=-=-=-=-=
		System.out.println("Enter Client data:");
		System.out.print("Name: ");
		name = sc.nextLine();
		System.out.print("Email: ");
		email = sc.nextLine();
		System.out.println("Birth date: ");
		birth = sc.nextLine();
		
		Client client = new Client(name, email, LocalDate.parse(birth, dtf));
		
		//-=-=-=-=-=Order Data-=-=-=-=-=-=-=
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		status = sc.nextLine();
		System.out.print("How many items to this order?");
		orders = sc.nextInt();
		
		Order order = new Order(status, client);
		
		for(int i = 1; i<=orders; i++) {
			System.out.printf("Enter #%d item data:", i);
			System.out.print("\nProduct name: ");
			String productName = sc.next();
			System.out.print("Product price: ");
			Double productPrice = sc.nextDouble();
			System.out.print("Quantity: ");
			Integer productQuantity = sc.nextInt();
			
			Product product = new Product(productName, productPrice);
			OrderItem orderItem = new OrderItem(productQuantity, productPrice, product);
			order.addItem(orderItem);
		}
		
		System.out.println("ORDER SUMMARY:");
		System.out.printf("Order moment: %s", order.getMoment());
		System.out.printf("\nOrder status: %s \n", order.getOrderStatus());
		System.out.println(order.getClient());
		System.out.println("Order items: ");
		for(OrderItem item:order.getItems()) {
			String res = String.format("%s, %.2f, Quantity: %d, Subtotal: %.2f", 
					item.getProduct().getName(),
					item.getPrice(),
					item.getQuantity(),
					item.subTotal());
			System.out.println(res);
		}
		System.out.printf("Total price: %.2f", order.total());
	sc.close();
	}
	
}
