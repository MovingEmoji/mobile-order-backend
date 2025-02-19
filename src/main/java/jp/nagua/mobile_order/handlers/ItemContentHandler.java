package jp.nagua.mobile_order.handlers;

import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.interfaces.ContentHandler;

import java.util.ArrayList;

public class ItemContentHandler implements ContentHandler {

    private static ArrayList<ItemContent> itemContents;

    private static ItemContentHandler handler;

    public static ItemContentHandler getInstance() {
        if(handler == null) {
            handler = new ItemContentHandler();
            return handler;
        }
        return handler;
    }

    @Override
    public void initializeContentsList() {
        itemContents = new ArrayList<>();
    }

    @Override
    public void addContentToList(Object object) {
        itemContents.add((ItemContent) object);
    }

    @Override
    public void removeContentFromList(Object object) {
        itemContents.remove((ItemContent) object);
    }

    @Override
    public Object getContentFromList(String string) {
        for(ItemContent content : itemContents) {
            if(content.getName().equals(string)) {
                return content;
            }
        }
        return null;
    }

    @Override
    public Object getContentFromList(int id) {
        for(ItemContent content : itemContents) {
            if(content.getId() == id) {
                return content;
            }
        }
        return null;
    }

    @Override
    public Object getContents() {
        return itemContents.clone();
    }
}