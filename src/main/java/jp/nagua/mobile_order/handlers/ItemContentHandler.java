package jp.nagua.mobile_order.handlers;

import jp.nagua.mobile_order.elements.ItemContent;

import java.util.ArrayList;
import java.util.List;

public class ItemContentHandler {

    private static ArrayList<ItemContent> itemContents;

    public static void initializeContentsList() {
        itemContents = new ArrayList<>();
    }

    public static void addItemContentToList(ItemContent itemContent) {
        itemContents.add(itemContent);
    }

    public static void removeItemContentFromList(ItemContent itemContent) {
        itemContents.remove(itemContent);
    }

    public static ItemContent getItemContentFromList(String name) {
        for(ItemContent content : itemContents) {
            if(content.getName().equals(name)) {
                return content;
            }
        }
        return null;
    }

    public static ItemContent getItemContentFromList(int id) {
        for(ItemContent content : itemContents) {
            if(content.getId() == id) {
                return content;
            }
        }
        return null;
    }

    public static ArrayList<ItemContent> getItemContents() {
        return (ArrayList<ItemContent>) itemContents.clone();
    }
}
