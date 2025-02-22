package jp.nagua.mobile_order.handlers;

import jp.nagua.mobile_order.elements.ItemContent;
import jp.nagua.mobile_order.interfaces.ContentHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemContentHandler implements ContentHandler, Serializable {

    private ArrayList<ItemContent> itemContents;

    public ItemContentHandler() {
        this.itemContents = new ArrayList<>();
    }

    @Override
    public void addContent(Object object) {
        this.itemContents.add((ItemContent) object);
    }

    @Override
    public void removeContent(Object object) {
        this.itemContents.remove((ItemContent) object);
    }

    @Override
    public Object getContent(String string) {
        for(ItemContent content : this.itemContents) {
            if(content.getName().equals(string)) {
                return content;
            }
        }
        return null;
    }

    @Override
    public Object getContent(int id) {
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

    @Override
    public boolean contains(Object object) {
        if(itemContents.contains((ItemContent) object)) {
            return true;
        }
        return false;
    }
}