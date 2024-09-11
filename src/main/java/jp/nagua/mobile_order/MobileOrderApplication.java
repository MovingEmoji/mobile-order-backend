package jp.nagua.mobile_order;

import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.PaymentData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MobileOrderApplication {

	public static List<OrderContent> orders = new ArrayList<>();
	public static List<PaymentData> payments = new ArrayList<>();

	public static OrderContent getOrderContent(String uuid) {
		for(OrderContent order : orders) {
			if(order.getOrder_id().toString().equals(uuid)) {
				return order;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		SpringApplication.run(MobileOrderApplication.class, args);
	}

}
