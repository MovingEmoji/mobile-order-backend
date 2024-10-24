package jp.nagua.mobile_order;

import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.PaymentData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MobileOrderApplication {

	public static List<OrderContent> orders = new ArrayList<>();
	public static List<PaymentData> payments = new ArrayList<>();
	public static String TOKEN = UUID.randomUUID().toString();
	public static Map<Integer, Integer> countMap = new HashMap<>();

	public static OrderContent getOrderContent(String uuid) {
		for(OrderContent order : orders) {
			if(order.getOrder_id().toString().equals(uuid)) {
				return order;
			}
		}
		return null;
	}

	public static PaymentData getPaymentData(String uuid) {
		for(PaymentData data : payments) {
			if(data.getUUID().toString().equals(uuid)) {
				return data;
			}
		}
		return null;
	}

	public static boolean runPayment(PaymentData data, int deposit) {
		if(data.getTotal() <= deposit) {
			data.setDeposit(deposit);
			data.setChange(deposit - data.getTotal());
			data.setStatus("complete");
			getOrderContent(data.getUUID().toString()).setStatus("paid");
			return true;
		}
		return false;
	}


	public static void main(String[] args) {

		SpringApplication.run(MobileOrderApplication.class, args);
		countMap.put(1,0);
		countMap.put(2,0);
		countMap.put(3,0);
	}

}
