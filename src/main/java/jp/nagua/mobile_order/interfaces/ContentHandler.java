package jp.nagua.mobile_order.interfaces;

import jp.nagua.mobile_order.elements.ItemContent;

import java.util.ArrayList;

public interface ContentHandler {

    public void initializeContentsList();

    public void addContentToList(Object object);

    public void removeContentFromList(Object object);

    public Object getContentFromList(String string);

    public Object getContentFromList(int id);

    public Object getContents();

}
