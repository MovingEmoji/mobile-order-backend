package jp.nagua.mobile_order.elements;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.Serializable;
import java.util.UUID;

public class OrderContent implements Serializable {

    private UUID order_id;
    private JSONPObject order_json;


}
