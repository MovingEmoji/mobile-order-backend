package jp.nagua.mobile_order;

import jp.nagua.mobile_order.handlers.ItemContentHandler;
import jp.nagua.mobile_order.handlers.OrderContentHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MobileOrderApplication {

	public static String TOKEN = UUID.randomUUID().toString();


	public static void main(String[] args) {

		ItemContentHandler.getInstance().initializeContentsList();
		OrderContentHandler.getInstance().initializeContentsList();

		SpringApplication.run(MobileOrderApplication.class, args);
	}

}
