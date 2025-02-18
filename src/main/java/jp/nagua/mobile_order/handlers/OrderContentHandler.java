package jp.nagua.mobile_order.handlers;

import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.elements.OrderContent;
import jp.nagua.mobile_order.interfaces.ContentHandler;

import java.util.ArrayList;

public class OrderContentHandler implements ContentHandler {

    private static ArrayList<OrderContent> orderContents;

    private static OrderContentHandler handler;

    public static OrderContentHandler getInstance() {
        if(handler == null) {
            handler = new OrderContentHandler();
            return handler;
        }
        return handler;
    }

    @Override
    public void initializeContentsList() {
        orderContents = new ArrayList<>();
    }

    @Override
    public void addContentToList(Object object) {
        orderContents.add((OrderContent) object);
    }

    @Override
    public void removeContentFromList(Object object) {
        orderContents.remove((OrderContent) object);
    }

    @Override
    public Object getContentFromList(String uuid) {
        for(OrderContent content : orderContents) {
            if(content.getUuid().equals(uuid)) {
                return content;
            }
        }
        return null;
    }

    @Override
    public Object getContentFromList(int id) {
        for(OrderContent content : orderContents) {
            if(content.getId() == id) {
                return content;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ItemContent> getContents() {
        return null;
    }
}
