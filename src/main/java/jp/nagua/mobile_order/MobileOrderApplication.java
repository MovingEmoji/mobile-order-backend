package jp.nagua.mobile_order;

import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.elements.PaymentData;
import jp.nagua.mobile_order.handlers.ItemContentHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MobileOrderApplication {

	public static String TOKEN = UUID.randomUUID().toString();


	public static void main(String[] args) {

		ItemContentHandler.initializeContentsList();

		SpringApplication.run(MobileOrderApplication.class, args);
	}

}
