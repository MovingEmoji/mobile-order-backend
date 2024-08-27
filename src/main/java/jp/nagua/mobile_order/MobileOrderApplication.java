package jp.nagua.mobile_order;

import jp.nagua.mobile_order.elements.OrderContent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MobileOrderApplication {

	public static List<OrderContent> orders = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(MobileOrderApplication.class, args);
	}

}
