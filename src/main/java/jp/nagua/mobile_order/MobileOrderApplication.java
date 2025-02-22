package jp.nagua.mobile_order;

import jp.nagua.mobile_order.handlers.UserHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MobileOrderApplication {

	public static UserHandler userHandler;

	public static void main(String[] args) {

		userHandler = new UserHandler();

		SpringApplication.run(MobileOrderApplication.class, args);
	}

}
